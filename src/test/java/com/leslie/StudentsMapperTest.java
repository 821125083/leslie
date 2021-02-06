//package com.leslie;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//
//import com.leslie.cons.Const;
//import com.leslie.mapper.CityMapper;
//import com.leslie.mapper.ProvinceMapper;
//import com.leslie.mapper.TrendMapper;
//import com.leslie.pojo.*;
//import com.leslie.service.LocationService;
//import com.leslie.utils.ABUtils;
//import com.leslie.utils.AlibabaUtils;
//import com.leslie.utils.DataUtils;
//import com.leslie.utils.RemoteUtils;
//import com.leslie.vo.ABTrendVo;
//import com.leslie.vo.CityVO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.rmi.Remote;
//import java.util.*;
//
//@SpringBootTest(classes = App.class)
//@RunWith(SpringRunner.class)
//public class StudentsMapperTest  {
//
//    @Autowired
//    private TrendMapper trendMapper;
//
//    @Autowired
//    private LocationService locationService;
//
//    @Autowired
//    private ProvinceMapper provinceMapper;
//
//    @Autowired
//    private CityMapper cityMapper;
////
////    @Autowired
////    private NewsMapper newsMapper;
//
//    @Test
//    public void test1() throws Exception {
//        String url = "https://api.yonyoucloud.com/apis/dst/ncov/country";
//        Map<String,String > headers = new HashMap<>();
//        headers.put("apicode","4fa69c43e75f4779b6bc50177e361ee5");
//        String remoteData = RemoteUtils.getRemoteData(url, new HashMap<>(), headers);
//        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(remoteData).getString("data"), Cov19CnRecord.class);
//        System.out.println(record);
//    }
//
//    //插入所有城市和省份数据
//    @Test
//    public void test2() throws Exception{
//        String s = AlibabaUtils.requestAlibabaData();
//        JSONObject jsonObject = JSON.parseObject(s);
//        Object provinceArray = jsonObject.get("provinceArray");
//        System.out.println(provinceArray);
//        List<Province> provinces = JSONObject.parseArray(provinceArray.toString(), Province.class);
//        provinces.forEach(province -> {
//            List<City> cityList = province.getCityList();
//            Wrapper<Province> wrapper = new QueryWrapper<>();
//            ((QueryWrapper<Province>) wrapper).eq("province_name", province.getProvinceName());
//            Province one = provinceMapper.selectOne(wrapper);
//            cityList.forEach(city -> {
//                city.setProvinceId(one.getId());
//                cityMapper.insert(city);
//            });
//        });
//    }
//
//    /**
//     * 初始化数据
//     */
//    @Test
//    public void test3() throws Exception{
//        String s = DataUtils.initEveryDayData();
//        JSONObject object = JSON.parseObject(s);
//        Object trend = object.get("trend");
//
//
//        List<Trend> trends = JSONObject.parseArray(trend.toString(), Trend.class);
//        trends.forEach(t->{
//            trendMapper.insert(t);
//        });
//    }
//
//    @Test
//    public void test4() throws Exception{
//
//        //调用远程接口的数据
//        String jsonString = AlibabaUtils.requestAlibabaData();
//
//        JSONObject object = JSON.parseObject(jsonString);
//
//        JSONArray provinceArray = object.getJSONArray("provinceArray");
//
//        ListIterator<Object> iterator = provinceArray.listIterator();
//
//        //省份迭代器
//        while (iterator.hasNext()){
//           Object province = iterator.next();
//            JSONObject object1 = JSON.parseObject(province.toString());
//            if (object1.get("childStatistic").equals("湖北省")){
//                Object cityArray = object1.get("cityArray");
//                List<CityVO> cityVO = JSONObject.parseArray(cityArray.toString(), CityVO.class);
//                cityVO.forEach(ciy->{
//                    System.out.println(ciy.getCityName()+ciy.getTotalConfirmed());
//                });
//            }
//        }
//
//
//    }
//
////    @Test
////    public void test5(){
////        String remoteData = RemoteUtils.getRemoteData("https://cdn.mdeer.com/contentdtos.js");
////        remoteData = remoteData.substring(20,remoteData.length()-1);
////        List<News> newsList = JSON.parseArray(remoteData, News.class);
////        newsList.forEach(news-> System.out.println(news));
////    }
//
//    @Test
//    public void test6(){
//        String remoteData = RemoteUtils.getRemoteData("https://cdn.mdeer.com/data/yqstaticdata.js");
//        System.out.println(remoteData);
//
//    }
//
//    @Test
//    public void test7(){
////        String remoteData = RemoteUtils.getRemoteData(Const.newsUrl);
////        remoteData = remoteData.substring(20, remoteData.length()-1);
////        List<News> newsList = JSON.parseArray(remoteData, News.class);
////        newsList.forEach(news -> {
////            Wrapper wrapper = new QueryWrapper<News>();
////            ((QueryWrapper) wrapper).eq("content_id", news.getContentId());
////            News selectNews = newsMapper.selectOne(wrapper);
////            if (null == selectNews){
////                newsMapper.insert(news);
////            }
////        });
//    }
//
//    @Test
//    public void test8(){
//
//        String remoteData = RemoteUtils.getRemoteData("https://cdn.mdeer.com/data/yqstaticdata.js");
//
//        remoteData = remoteData.substring(19,remoteData.length() - 1);
//        System.out.println(remoteData);
//    }
//
//
//    @Test
//    public void test9(){
//
//        String remoteData = RemoteUtils.getRemoteData(Const.Api163);
//
//        JSONObject jsonObject = JSONObject.parseObject(remoteData);
//
//        Object data = jsonObject.get("data");
//
//        jsonObject = JSONObject.parseObject(data.toString());
//
//        Object areaTree = jsonObject.get("areaTree");
//
//        JSONArray objects = JSONObject.parseArray(areaTree.toString());
//
//        Iterator<Object> iterator = objects.iterator();
//
//        while (iterator.hasNext()){
//            Object next = iterator.next();
//
//            System.out.println(next);
//        }
//    }
//
//    @Test
//    public void test10(){
//        String remoteData = RemoteUtils.getRemoteData(Const.weatherApi.concat(Const.GUANGZHOUWEATHERCODE.toString()));
//        JSONObject jsonObject = JSON.parseObject(remoteData);
//        Object cityInfo = jsonObject.get("cityInfo");
//        Object data = jsonObject.get("data");
//        Object forecast = JSON.parseObject(data.toString()).get("forecast");
//        List<Weather> weathers = JSONObject.parseArray(forecast.toString(), Weather.class);
//        weathers.forEach(System.out::println);
//    }
//
//    @Test
//    public void test11(){
//        String remoteData = RemoteUtils.getRemoteData("https://cdn.mdeer.com/data/yqstaticdata.js");
//        System.out.println(remoteData);
//    }
//
//    @Test
//    public void test12(){
//        ABUtils.abroadIncTread().forEach(trend -> {
//            System.out.println(trend);
//        });
//    }
//
//    @Test
//    public void test13(){
//        List<ABTrendVo> abTrendVos = ABUtils.abroadIncTread();
//        abTrendVos.forEach(System.out::println);
//    }
//}
