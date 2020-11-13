package com.leslie.service.impl;

import com.leslie.mapper.ProvinceMapper;
import com.leslie.pojo.Province;
import com.leslie.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private ProvinceMapper provinceMapper;


    @Override
    public List<Province> queryAllCnProvince() {
        return provinceMapper.selectList(null);
    }
}
