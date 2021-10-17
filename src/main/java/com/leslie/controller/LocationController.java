package com.leslie.controller;

import com.leslie.mapper.ProvinceMapper;
import com.leslie.pojo.Country;
import com.leslie.pojo.Province;
import com.leslie.service.CountryService;
import com.leslie.service.LocationService;
import com.leslie.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 */
@RequestMapping("/location")
@Controller
@ResponseBody
@CrossOrigin
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProvinceMapper provinceMapper;

    @RequestMapping("/allProvinces")
    public ResultVO queryAllProvince(){
        List<Province> provinces = locationService.queryAllCnProvince();
        return ResultVO.success(provinces);
    }

    @RequestMapping("/allCountry")
    public ResultVO queryAllCountry(){
        List<Country> countryList = countryService.queryAllCountry();
        return ResultVO.success(countryList);
    }

    @RequestMapping("/queryProvinceByNameAndId/{provinceName}/{provinceId}")
    public ResultVO queryProvinceByNameAndId(@PathVariable("provinceName") String provinceName,@PathVariable("provinceId") Integer provinceId){
        return ResultVO.success(provinceMapper.queryProvinceByNameAndId(provinceId,provinceName));
    }

}
