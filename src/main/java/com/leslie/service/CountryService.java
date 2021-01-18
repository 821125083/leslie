package com.leslie.service;

import com.leslie.pojo.Country;

import java.util.List;

public interface CountryService {

    /**
     * 批量添加 国家信息
     * @param countryList countryList
     */
    void addCountry(List<Country> countryList);

    List<Country> queryAllCountry();
}
