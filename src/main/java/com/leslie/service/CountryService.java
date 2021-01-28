package com.leslie.service;

import com.leslie.pojo.Country;
import com.leslie.vo.CountryVO;

import java.util.List;
import java.util.Map;

public interface CountryService {

    /**
     * 批量添加 国家信息
     * @param countryList countryList
     */
    void addCountry(List<Country> countryList);

    List<Country> queryAllCountry();

    Map<String ,Object> countryBarData(List<CountryVO> countryVOS,Integer limited);

    Map<String ,Object> countryBarData(List<CountryVO> countryVOS,Integer limited,Integer searchType);

    List<CountryVO> readCountryBarData(List<CountryVO> countryVOS, Integer limited, Integer searchType);
}
