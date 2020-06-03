package com.lopponia.service.impl;

import com.lopponia.dao.BaseDictDao;
import com.lopponia.bean.BaseDict;
import com.lopponia.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("basaeDictService")
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictDao baseDictDao;

    public List<BaseDict> findBaseDictByTypeCode(String typecode) {
        return baseDictDao.selectBaseDictByTypeCode(typecode);
    }
}
