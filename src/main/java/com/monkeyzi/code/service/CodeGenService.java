package com.monkeyzi.code.service;

import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.dto.GenCodeDto;
import com.monkeyzi.code.dto.GenConfigDto;
import com.monkeyzi.code.dto.PageRequest;

public interface CodeGenService {

    byte[] generatorCode(GenConfigDto genConfig);

    /**
     * 分页查询表信息
     * @param page
     * @param genCodeDto
     * @return
     */
    PageInfo pageQuery(PageRequest page, GenCodeDto genCodeDto);
}
