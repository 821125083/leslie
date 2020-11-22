package com.leslie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.leslie.mapper.CityMapper;
import com.leslie.mapper.ProvinceMapper;
import com.leslie.mapper.TrendMapper;
import com.leslie.pojo.City;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Province;
import com.leslie.pojo.Trend;
import com.leslie.service.LocationService;
import com.leslie.utils.AlibabaUtils;
import com.leslie.utils.DataUtils;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.CityVO;
import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import netscape.javascript.JSObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class StudentsMapperTest  {

    @Autowired
    private TrendMapper trendMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void test1() throws Exception {
        String url = "https://api.yonyoucloud.com/apis/dst/ncov/country";
        Map<String,String > headers = new HashMap<>();
        headers.put("apicode","4fa69c43e75f4779b6bc50177e361ee5");
        String remoteData = RemoteUtils.getRemoteData(url, new HashMap<>(), headers);
        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(remoteData).getString("data"), Cov19CnRecord.class);
        System.out.println(record);
    }

    //插入所有城市和省份数据
    @Test
    public void test2() throws Exception{
        String s = AlibabaUtils.requestAlibabaData();
        JSONObject jsonObject = JSON.parseObject(s);
        Object provinceArray = jsonObject.get("provinceArray");
        System.out.println(provinceArray);
        List<Province> provinces = JSONObject.parseArray(provinceArray.toString(), Province.class);
        provinces.forEach(province -> {
            List<City> cityList = province.getCityList();
            Wrapper<Province> wrapper = new QueryWrapper<>();
            ((QueryWrapper<Province>) wrapper).eq("province_name", province.getProvinceName());
            Province one = provinceMapper.selectOne(wrapper);
            cityList.forEach(city -> {
                city.setProvinceId(one.getId());
                cityMapper.insert(city);
            });
        });
    }

    /**
     * 初始化数据
     */
    @Test
    public void test3() throws Exception{
        String s = DataUtils.initEveryDayData();
        JSONObject object = JSON.parseObject(s);
        Object trend = object.get("trend");


        List<Trend> trends = JSONObject.parseArray(trend.toString(), Trend.class);
        trends.forEach(t->{
            trendMapper.insert(t);
        });
    }

    @Test
    public void test4() throws Exception{

        //调用远程接口的数据
        String jsonString = AlibabaUtils.requestAlibabaData();

        JSONObject object = JSON.parseObject(jsonString);

        JSONArray provinceArray = object.getJSONArray("provinceArray");

        ListIterator<Object> iterator = provinceArray.listIterator();

        //省份迭代器
        while (iterator.hasNext()){
           Object province = iterator.next();
            JSONObject object1 = JSON.parseObject(province.toString());
            if (object1.get("childStatistic").equals("湖北省")){
                Object cityArray = object1.get("cityArray");
                List<CityVO> cityVO = JSONObject.parseArray(cityArray.toString(), CityVO.class);
                cityVO.forEach(ciy->{
                    System.out.println(ciy.getCityName()+ciy.getTotalConfirmed());
                });
            }
        }


    }

}
