package com.leslie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.leslie.mapper.TrendMapper;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Province;
import com.leslie.pojo.Trend;
import com.leslie.utils.AlibabaUtils;
import com.leslie.utils.DataUtils;
import com.leslie.utils.RemoteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class StudentsMapperTest  {

    @Autowired
    private TrendMapper trendMapper;


    @Test
    public void test6() throws Exception {
        String url = "https://api.yonyoucloud.com/apis/dst/ncov/country";
        Map<String,String > headers = new HashMap<>();
        headers.put("apicode","4fa69c43e75f4779b6bc50177e361ee5");
        String remoteData = RemoteUtils.getRemoteData(url, new HashMap<>(), headers);
        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(remoteData).getString("data"), Cov19CnRecord.class);
        System.out.println(record);
    }

    @Test
    public void test7(){
        String remoteData = RemoteUtils.getRemoteData("https://cdn.mdeer.com/data/yqstaticdata.js");
        System.out.println(remoteData);
    }

    /**
     * 初始化数据
     */
    @Test
    public void test(){
        String s = DataUtils.initEveryDayData();
        JSONObject object = JSON.parseObject(s);
        Object trend = object.get("trend");


        List<Trend> trends = JSONObject.parseArray(trend.toString(), Trend.class);
        trends.forEach(t->{
            trendMapper.insert(t);
        });
    }

    @Test
    public void test2(){
        String s = AlibabaUtils.requestAlibabaData();
        JSONObject jsonObject = JSON.parseObject(s);
        Object provinceArray = jsonObject.get("provinceArray");
        List<Province> provinces = JSONObject.parseArray(provinceArray.toString(), Province.class);
        provinces.forEach(System.out::println);
    }

}
