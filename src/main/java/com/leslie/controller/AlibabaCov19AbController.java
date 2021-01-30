package com.leslie.controller;

import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.service.CountryService;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.CountryVO;
import com.leslie.vo.ResultVO;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.*;

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

        return ResultVO.success(this.queryCountryVO(true));
    }

    /**
     * total 是否查询全球的数据
     * 查询全球的数据
     * @return
     */
    private List<CountryVO> queryCountryVO(Boolean total){
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
        if (total){
            list.add(this.countGlobalCountVO(list));
        }
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
        if (limited == null || limited.equals(0)){
            // 为空或者为零
            limited = 50;
        }
        List<CountryVO> countryVOS = this.queryCountryVO(true);
        Map<String ,Object> result = countryService.countryBarData(countryVOS,limited);
        return ResultVO.success(result);
    }

    /**
     * 查询条形图
     * @param limited 查询条数
     * @param searchType 查询类型 1：确诊 2：死亡 3：治愈 null 查全部
     * @return
     */
    @RequestMapping("initAllAbData/{limited}/{searchType}")
    public ResultVO initAllAbDataByLimit2(@PathVariable Integer limited,@PathVariable Integer searchType){

        if(limited == null || limited.equals(0)) {
            limited = 50;
        }
        // todo 查询所有数据 重构为一个方法
        List<CountryVO> countryVOS = this.queryCountryVO(true);
        Map<String, Object> map = countryService.countryBarData(countryVOS, limited, searchType);
        Map<String, Long> data = null;
        if (searchType == 1){
            data = (HashMap)map.get("confirmed");
        }else if (searchType == 2){
            data = (HashMap)map.get("dead");
        }else {
            data = (HashMap)map.get("heal");
        }

        List<CountryVO> list = new ArrayList<>();

        data.entrySet().forEach(ent -> {
            CountryVO countryVO = new CountryVO();
            countryVO.setName(ent.getKey());
            countryVO.setHeal(ent.getValue());
            list.add(countryVO);
        });

        return ResultVO.success(list);
    }

    @RequestMapping("initAllCountryData")
    @ResponseBody
    public ResultVO initAllCountryData(){
        List<CountryVO> list = this.queryCountryVO(false);

        return ResultVO.success(list);
    }

    @RequestMapping("selectTheWorldCount")
    @ResponseBody
    public ResultVO selectTheWorldCount(){
        return ResultVO.success(this.countGlobalCountVO(this.queryCountryVO(false)));
    }

}
