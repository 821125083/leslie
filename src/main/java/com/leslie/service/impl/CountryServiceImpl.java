package com.leslie.service.impl;

import com.leslie.mapper.CountryMapper;
import com.leslie.pojo.Country;
import com.leslie.service.CountryService;
import com.leslie.vo.CountryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;


    @Override
    public void addCountry(List<Country> countryList) {

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
        return this.countryBarData(countryVOS, limited, null);
    }

    @Override
    public Map<String, Object> countryBarData(List<CountryVO> countryVOS, Integer limited, Integer searchType) {
        Map<String, Object> result = new HashMap<>();
        if (searchType != null){
            if (searchType == 1){
                Map<String, Long> confirmed = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName, CountryVO::getConfirm));
                result.put("confirmed", sortMap(confirmed,limited));
            }else if (searchType == 2){
                Map<String, Long> dead = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName, CountryVO::getDead));
                result.put("dead", sortMap(dead,limited));
            }else if (searchType == 3){
                Map<String, Long> heal = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName,CountryVO::getHeal));
                result.put("heal", sortMap(heal,limited));
            }
        }else {
            Map<String, Long> heal = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName,CountryVO::getHeal));
            Map<String, Long> dead = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName, CountryVO::getDead));
            Map<String, Long> confirmed = countryVOS.parallelStream().collect(Collectors.toMap(Country::getName, CountryVO::getConfirm));
            result.put("heal", sortMap(heal,limited));
            result.put("dead", sortMap(dead,limited));
            result.put("confirmed", sortMap(confirmed,limited));
        }
        return result;
    }


    private Map<String,Long> sortMap(Map<String,Long> map,Integer limited){
        Set<Map.Entry<String, Long>> entrySet = map.entrySet();

        ArrayList<Map.Entry> list = new ArrayList<>();
        entrySet.forEach(entry -> {
            list.add(entry);
        });

        list.sort((o1, o2) -> {
            Long value1 = (Long)o1.getValue();
            Long value2 = (Long)o2.getValue();
            Long val = value1 - value2;
            return -val.intValue();

        });

        LinkedHashMap<String, Long> hashMap = new LinkedHashMap<>();
        list.stream().limit(limited).forEach(record->{
            hashMap.put((String)record.getKey(),(Long) record.getValue());
        });

        return hashMap;
    }

    @Override
    public List<CountryVO> readCountryBarData(List<CountryVO> list, Integer limited, Integer searchType) {

        return null;
    }
}
