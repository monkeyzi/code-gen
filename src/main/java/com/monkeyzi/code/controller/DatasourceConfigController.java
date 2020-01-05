package com.monkeyzi.code.controller;

import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.base.BaseController;
import com.monkeyzi.code.base.Result;
import com.monkeyzi.code.config.DynamicDataSourceConfig;
import com.monkeyzi.code.dto.PageRequest;
import com.monkeyzi.code.entity.SysDatasourceConf;
import com.monkeyzi.code.service.DataSourceConfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据源管理controller
 */
@Slf4j
@RestController
@RequestMapping(value = "/ds")
public class DatasourceConfigController extends BaseController {

    @Autowired
    private DataSourceConfService dataSourceConfService;
    @Autowired
    private DynamicDataSourceConfig dataSourceConfig;

    @GetMapping("/page")
    public Result getSysDatasourceConfPage(PageRequest page, SysDatasourceConf sysDatasourceConf) {
        PageInfo pageInfo=dataSourceConfService.pageQuery(page,sysDatasourceConf);
        Map<String,Object> results=super.getDataTable(pageInfo);
        return Result.ok(results);
    }


    /**
     * 查询全部数据源
     *
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        List<Map<String,Object>> list=dataSourceConfService.list().stream()
                .map(a->{
                    Map<String,Object> os=new HashMap<>();
                    os.put("id",a.getId());
                    os.put("name",a.getName());
                    return os;
                }).collect(Collectors.toList());
        return Result.ok(list);
    }



    /**
     * 通过id查询数据源表
     *
     * @param id id
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        SysDatasourceConf conf=this.dataSourceConfService.getById(id);
        return Result.ok(conf);
    }


    /**
     * 新增数据源表
     *
     * @param sysDatasourceConf 数据源表
     */
    @PostMapping(value = "save")
    public Result save(SysDatasourceConf sysDatasourceConf) {
        this.dataSourceConfService.saveDataSource(sysDatasourceConf);
        return Result.ok("数据源新增成功！");
    }

    /**
     * 修改数据源
     * @param sysDatasourceConf
     * @return
     */
    @PostMapping(value = "update")
    public Result updateById (SysDatasourceConf sysDatasourceConf) {
        this.dataSourceConfService.updateDataSource(sysDatasourceConf);
        return Result.ok("ok！");
    }

    /**
     * 删除数据源
     * @param id
     * @return
     */
    @PostMapping("remove/{id}")
    public Result removeById(@PathVariable Long id) {
        this.dataSourceConfService.deleteDataSource(id);
        return Result.ok("ok");
    }

}
