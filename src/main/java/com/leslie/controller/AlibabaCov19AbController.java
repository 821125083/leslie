package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.pojo.Country;
import com.leslie.service.CountryService;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.CountryVO;
import com.leslie.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("AbCov19")
@ResponseBody
@Controller
@CrossOrigin
public class AlibabaCov19AbController {


    @Autowired
    private CountryService countryService;

    /**
     * 获取疫情全球数据
     * @return result
     */
    @RequestMapping("initAllAbData")
    public ResultVO initAllData(){

        return ResultVO.success(this.queryCountryVO());
    }

    private List<CountryVO> queryCountryVO(){
        List<CountryVO> list = new ArrayList<>();
        // 返回JSONArray
        Iterator<Object> iterator = JSONObject.parseObject(RemoteUtils.getRemoteData(Const.Api163)).getJSONObject("data").getJSONArray("areaTree").iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            CountryVO countryVO = new CountryVO();
            JSONObject countryJSONObject = JSONObject.parseObject(next.toString());
            // 封装数据
            countryVO.setName(countryJSONObject.get("name").toString());
            countryVO.setConfirm(countryJSONObject.getJSONObject("total").getLong("confirm"));
            countryVO.setInput(countryJSONObject.getJSONObject("total").getLong("input"));
            countryVO.setSevere(countryJSONObject.getJSONObject("total").getLong("severe"));
            countryVO.setHeal(countryJSONObject.getJSONObject("total").getLong("heal"));
            countryVO.setDead(countryJSONObject.getJSONObject("total").getLong("dead"));
            countryVO.setSuspect(countryJSONObject.getJSONObject("total").getLong("suspect"));
            list.add(countryVO);
        }
        // 添加一条全球数据
        list.add(this.countGlobalCountVO(list));
        // 按照确诊人数从高到低排序
        list.sort(Comparator.comparingLong(countryVO -> {
            return -countryVO.getConfirm();
        }));
        return list;
    }

    private CountryVO countGlobalCountVO(List<CountryVO> countryList){
        CountryVO countryVO = new CountryVO();
        // reduce 计算 计算所有的确诊、治愈、重症、疑似
        countryVO.setHeal(countryList.parallelStream().map(CountryVO::getHeal).reduce(0L, Long::sum));
        countryVO.setSevere(countryList.parallelStream().map(CountryVO::getSevere).reduce(0L, Long::sum));
        countryVO.setSuspect(countryList.parallelStream().map(CountryVO::getSuspect).reduce(0L, Long::sum));
        countryVO.setDead(countryList.parallelStream().map(CountryVO::getDead).reduce(0L, Long::sum));
        countryVO.setConfirm(countryList.parallelStream().map(CountryVO::getConfirm).reduce(0L, Long::sum));
        countryVO.setName("全球");
        return countryVO;
    }

    @RequestMapping("initAllAbData/{limited}")
    public ResultVO initAllAbDataByLimit(@PathVariable Integer limited){
        System.out.println("leslie");
        if (limited == null || limited.equals(0)){
            // 为空或者为零
            limited = 50;
        }
        List<CountryVO> countryVOS = this.queryCountryVO();
        Map<String ,Object> result = countryService.countryBarData(countryVOS,limited);
        return ResultVO.success(result);
    }
}
