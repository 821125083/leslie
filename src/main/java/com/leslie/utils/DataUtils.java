package com.leslie.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.cons.Const;
import com.leslie.mapper.CityMapper;
import com.leslie.mapper.ProvinceMapper;
import com.leslie.mapper.TrendMapper;
import com.leslie.pojo.City;
import com.leslie.pojo.Province;
import com.leslie.pojo.Trend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

/**
 * 收集数据写进数据库
 */
public class DataUtils {

    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private TrendMapper trendMapper;

    /**
     * 抓取城市数据
     *
     * @param
     * @return
     */
    public void initCity() {
        HashMap<String, String> header = new HashMap<>();
        header.put("authoration", "apicode");
        header.put("apicode", Const.apiCode);
        String remoteData = RemoteUtils.getRemoteData(Const.MAPUrl, new HashMap<String, String>(), header);
        Province gd = new Province();
        List<Province> provinces = JSONObject.parseArray(JSON.parseObject(remoteData).get("newslist").toString(), Province.class);
        for (Province province1 : provinces) {
            QueryWrapper<Province> wrapper = new QueryWrapper<>();
            wrapper.eq("province_name", province1.getProvinceName());
            Province province2 = provinceMapper.selectOne(wrapper);   //有id   province1 有city
            province1.setId(province2.getId());
        }
        for (Province province1 : provinces) {
            if (province1.getCities().size() > 0) {
                for (City city : province1.getCities()) {
                    city.setProvinceId(province1.getId());
                    cityMapper.insert(city);
                }
            }
        }

    }

    /**
     * 抓取每日疫情数据
     */
    public static String initEveryDayData(){



        return AlibabaUtils.requestAlibabaData();
    }

}
