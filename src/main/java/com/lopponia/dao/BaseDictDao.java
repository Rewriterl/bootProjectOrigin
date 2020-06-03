package com.lopponia.dao;

import com.lopponia.bean.BaseDict;

import java.util.List;

public interface BaseDictDao {
    public List<BaseDict> selectBaseDictByTypeCode(String typecode);
}
