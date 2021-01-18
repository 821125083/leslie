package com.leslie.service.impl;

import com.leslie.mapper.CountryMapper;
import com.leslie.pojo.Country;
import com.leslie.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;


    @Override
    public void addCountry(List<Country> countryList) {

        System.out.println(countryList.size());
        countryList.forEach(country -> {
            countryMapper.insert(country);
        });
    }

    @Override
    public List<Country> queryAllCountry() {
        return countryMapper.selectList(null);
    }
}
