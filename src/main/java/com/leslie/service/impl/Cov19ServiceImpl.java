package com.leslie.service.impl;

import com.leslie.mapper.Cov19Mapper;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Province;
import com.leslie.pojo.Trend;
import com.leslie.service.Cov19Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cov19ServiceImpl implements Cov19Service {

    @Autowired
    private Cov19Mapper cov19Mapper;


    @Override
    public List<Trend> cov19TrendLineChart() {
        return cov19Mapper.selectList(null);
    }

    @Override
    public List<Province> cov19CnProvincesData() {
        return null;
    }
}
