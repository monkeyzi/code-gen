package com.monkeyzi.code.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.monkeyzi.code.dto.GenConfigDto;
import com.monkeyzi.code.entity.ColumnEntity;
import com.monkeyzi.code.entity.TableEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: 高yg
 * @date: 2020/1/2 22:32
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:代码生成工具类
 */
@Slf4j
@UtilityClass
public class CodeGenUtils {
    /**
     * 去掉表前缀
     */
    private static final Integer TRIM_PRIX_YES=1;

    private List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("templates/generator/Entity.java.vm");
        templates.add("templates/generator/Mapper.java.vm");
        templates.add("templates/generator/Mapper.xml.vm");
        templates.add("templates/generator/Service.java.vm");
        templates.add("templates/generator/ServiceImpl.java.vm");
        templates.add("templates/generator/Controller.java.vm");
        return templates;
    }

    private final String ENTITY_JAVA_VM = "Entity.java.vm";
    private final String MAPPER_JAVA_VM = "Mapper.java.vm";
    private final String SERVICE_JAVA_VM = "Service.java.vm";
    private final String SERVICE_IMPL_JAVA_VM = "ServiceImpl.java.vm";
    private final String CONTROLLER_JAVA_VM = "Controller.java.vm";
    private final String MAPPER_XML_VM = "Mapper.xml.vm";


    /**
     * 代码生成
     * @param genConfig 配置信息
     * @param table 表信息
     * @param columns 列信息
     * @param zip
     */
    public void generatorCode(GenConfigDto genConfig, Map<String, String> table,
                              List<Map<String, String>> columns, ZipOutputStream zip) {
        Configuration config=getConfig();
        //列属性是否含有 bigDecimal
        boolean hasBigDecimal = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        //设置表备注
        if (StrUtil.isNotBlank(genConfig.getComments())){
            tableEntity.setComments(genConfig.getComments());
        }else {
            tableEntity.setComments(table.get("tableComment"));
        }
        String tablePrefix="";
        if (TRIM_PRIX_YES.equals(genConfig.getIsTrim())){
            tablePrefix=genConfig.getTablePrefix();
        }
        //java类名
        String className=tableToJava(tableEntity.getTableName(),tablePrefix);
        //设置驼峰类型
        tableEntity.setCaseClassName(className);
        //普通类型
        tableEntity.setLowerClassName(StringUtils.uncapitalize(className));
        //列信息
        List<ColumnEntity> columnList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setCaseAttrName(attrName);
            columnEntity.setLowerAttrName(StringUtils.uncapitalize(attrName));
            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);
            if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey"))
                    ||"P".equalsIgnoreCase(column.get("columnKey"))
                    && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }
            columnList.add(columnEntity);
        }
        tableEntity.setColumns(columnList);
        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
       //封装模板数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableEntity.getTableName());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getCaseClassName());
        map.put("classname", tableEntity.getLowerClassName());
        map.put("pathName", tableEntity.getLowerClassName().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("datetime", DateUtil.now());
        map.put("comments", tableEntity.getComments());
        map.put("author", genConfig.getAuthor());
        map.put("moduleName", genConfig.getModuleName());
        map.put("package", genConfig.getPackageName());
        map.put("mainPath", genConfig.getPackageName());
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();

        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetUtil.UTF_8);
            tpl.merge(context, sw);
            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(Objects
                        .requireNonNull(getFileName(template, tableEntity.getCaseClassName()
                                , map.get("package").toString(), map.get("moduleName").toString()
                                ,genConfig.getProjectName()))));
                IoUtil.write(zip, StandardCharsets.UTF_8, false, sw.toString());
                IoUtil.close(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：{},{}" , tableEntity.getTableName(), e);
            }
        }

    }

    /**
     * 表名转换成Java类名
     */
    private String tableToJava(String tableName, String tablePrefix) {
        if (StrUtil.isNotBlank(tablePrefix)) {
            tableName = tableName.replaceFirst(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 列名转换成Java属性名
     */
    private String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 获取配置信息
     */
    private Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            log.error("读取配置文件失败");
            return null;
        }
    }

    /**
     * 获取文件名
     */
    private String getFileName(String template, String className, String packageName,
                               String moduleName,String projectName) {
        String packagePath = projectName + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains(ENTITY_JAVA_VM)) {
            return packagePath + "entity" + File.separator + className + ".java";
        }

        if (template.contains(MAPPER_JAVA_VM)) {
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains(SERVICE_JAVA_VM)) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains(SERVICE_IMPL_JAVA_VM)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains(CONTROLLER_JAVA_VM)) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains(MAPPER_XML_VM)) {
            return projectName + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
        }
        return null;
    }

}
