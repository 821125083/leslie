package com.leslie.controller;

import com.leslie.mapper.ProvinceMapper;
import com.leslie.pojo.Province;
import com.leslie.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @RequestMapping("/allProvinces")
    public List<Province> queryAllProvince(){
        return locationService.queryAllCnProvince();
    }

}
