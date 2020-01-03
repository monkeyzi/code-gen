package com.monkeyzi.code.service;

import com.monkeyzi.code.dto.GenConfigDto;

public interface CodeGenService {

    byte[] generatorCode(GenConfigDto genConfig);
}
