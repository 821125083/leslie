package com.leslie.service.impl;

import com.leslie.mapper.Cov19Mapper;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.service.Cov19Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cov19ServiceImpl implements Cov19Service {

    @Autowired
    private Cov19Mapper cov19Mapper;

    @Override
    public Cov19CnRecord getRealTimeRecord() {
        return null;
    }
}
