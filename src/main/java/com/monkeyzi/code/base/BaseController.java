package com.monkeyzi.code.base;

import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

import static org.apache.shiro.SecurityUtils.getSubject;

@Slf4j
public class BaseController {

    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected SysUser getCurrentUser() {
        return (SysUser) getSubject().getPrincipal();
    }
    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    protected Map<String, Object> getDataTable(PageInfo pageInfo) {
        return getDataTable(pageInfo, CommonConstants.DATA_MAP_INITIAL_CAPACITY);
    }

    protected Map<String, Object> getDataTable(PageInfo pageInfo, int dataMapInitialCapacity) {
        Map<String, Object> data = new HashMap<>(dataMapInitialCapacity);
        data.put("rows", pageInfo.getList());
        data.put("total", pageInfo.getTotal());
        return data;
    }
}
