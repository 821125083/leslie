package com.leslie.service.impl;

import com.leslie.mapper.CountryMapper;
import com.leslie.pojo.Country;
import com.leslie.service.CountryService;
import com.leslie.vo.CountryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;


    @Override
    public void addCountry(List<Country> countryList) {

        System.out.println(countryList.size());
        countryList.parallelStream().forEach(country -> {
            countryMapper.insert(country);
        });
    }

    @Override
    public List<Country> queryAllCountry() {
        return countryMapper.selectList(null);
    }

    @Override
    public Map<String, Object> countryBarData(List<CountryVO> countryVOS,Integer limited) {
        // String 国家名字 Long 确诊人数\死亡人数\治愈人数
        Map<String, Long> confirmed = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName, CountryVO::getConfirm));
        Map<String, Long> dead = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName, CountryVO::getDead));
        Map<String, Long> heal = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName,CountryVO::getHeal));
        Map<String, Object> result = new HashMap<>();

        

        result.put("confirmed", confirmed);
        result.put("heal", heal);
        result.put("dead", dead);
        return result;
    }
}
