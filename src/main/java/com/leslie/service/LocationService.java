package com.leslie.service;

import com.leslie.pojo.Province;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationService {
    List<Province> queryAllCnProvince();

    Province queryProvinceById(String provinceId);
}
