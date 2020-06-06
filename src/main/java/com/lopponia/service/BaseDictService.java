package com.lopponia.service;

import com.lopponia.bean.BaseDict;

import java.util.List;

public interface BaseDictService {
    public List<BaseDict> findBaseDictByTypeCode(String typecode);
}
