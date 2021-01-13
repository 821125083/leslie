package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.exception.AliInterfaceException;
import com.leslie.pojo.Province;
import com.leslie.pojo.Trend;
import com.leslie.service.Cov19Service;
import com.leslie.service.LocationService;
import com.leslie.utils.AlibabaUtils;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.CityVO;
import com.leslie.vo.ProvinceVO;
import com.leslie.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@ResponseBody
@RequestMapping("/Cov19Alibaba")
@CrossOrigin
public class AlibabaCov19CnController {

    @Autowired
    private Cov19Service cov19Service;

    @Autowired
    private LocationService locationService;

    private final Logger LOGGER = LoggerFactory.getLogger(AlibabaCov19CnController.class);

    /**
     * 全国疫情曲线图数据
     * @return
     */
    @RequestMapping("Cov19TrendLineChart")
    public ResultVO trendCnList(){
        return ResultVO.success(cov19Service.cov19TrendLineChart());
    }

    /**
     * 省份表格数据
     * @return
     */
    @RequestMapping("cov19ProvinceTableData")
    public ResultVO provinceTableData(){
        JSONObject jsonObject = null;
        try {
            jsonObject = AlibabaUtils.requestAlibabaJsonObject();
        } catch (Exception e) {
            LOGGER.error("阿里接口不行了");
            return ResultVO.error("阿里接口不行了");
        }
        List<ProvinceVO> provinceArray = JSONObject.parseArray(jsonObject.get("provinceArray").toString(), ProvinceVO.class);

        return ResultVO.success(provinceArray);
    }

    //记载城市数据
    @RequestMapping("/loadCitiesByProvinceName/{provinceName}")
    public ResultVO loadCitiesByProvinceName(@PathVariable String provinceName) {

        //调用远程接口的数据
        JSONObject object = null;
        try {
            object = AlibabaUtils.requestAlibabaJsonObject();

        } catch (Exception e) {
            LOGGER.error("阿里接口不行了");
            return ResultVO.error("阿里接口不行了");
        }

        JSONArray provinceArray = object.getJSONArray("provinceArray");

        ListIterator<Object> iterator = provinceArray.listIterator();

        List<CityVO> cityVO = null;
        //省份迭代器
        while (iterator.hasNext()){
            Object province = iterator.next();
            JSONObject object1 = JSON.parseObject(province.toString());
            if (object1.get("childStatistic").equals(provinceName)){
                Object cityArray = object1.get("cityArray");
                cityVO = JSONObject.parseArray(cityArray.toString(), CityVO.class);
            }
        }

        return ResultVO.success(cityVO);
    }

    //加载条形图数据
    @RequestMapping("initBarData")
    public ResultVO initBarData(){

        JSONObject json = null;
        try {
            json = AlibabaUtils.requestAlibabaJsonObject();
        } catch (Exception e) {
            LOGGER.error("阿里接口不行了");
            return ResultVO.error("阿里接口不行了");
        }

        Object array = json.get("provinceArray");

        List<ProvinceVO> provinces = JSONObject.parseArray(array.toString(), ProvinceVO.class);

        return ResultVO.success(provinces);
    }

    //省表格数据
    @RequestMapping("queryProvinceBarChart/{provinceId}")
    public ResultVO queryProvinceBarChart(@PathVariable String provinceId){

        //根据id获得省份，用于得到省份名称
        Province province = locationService.queryProvinceById(provinceId);
        JSONObject jsonObject = null;
        //远程接口获得接口数据的json对象
        try {
            jsonObject =  AlibabaUtils.requestAlibabaJsonObject();
        }catch (Exception e){
            LOGGER.error("阿里接口不行了");
            return ResultVO.error("阿里接口不行了");
        }
        //获得json数据的所有省份信息
        JSONArray provinceArray = jsonObject.getJSONArray("provinceArray");
        //迭代器
        ListIterator<Object> iterator = provinceArray.listIterator();
        //用于返回数据
        List<CityVO> cityVOS = null;
        //遍历
        while (iterator.hasNext()){
            Object next = iterator.next();
            //转化喂json对象
            JSONObject object = JSON.parseObject(next.toString());
            //得到省份名称
            Object provinceName = object.get("childStatistic");
            //是所查询的省份信息时
            if (provinceName.toString().equals(province.getProvinceName())){
                //将该省的城市返回
                cityVOS = JSONObject.parseArray(object.get("cityArray").toString(), CityVO.class);
            }
        }
        return ResultVO.success(cityVOS);
    }

    //
    @PostMapping("/loadCityData")
    public ResultVO loadCityData(@RequestBody HashMap<String,ArrayList<String>> map){
        Set<Map.Entry<String, ArrayList<String>>> entrySet = map.entrySet();
        List<String> cityNames = new ArrayList<>();
        List<CityVO> cityVOS = new ArrayList<>();

        entrySet.forEach(set->{
            //获得所有需要画图的城市的名称
            cityNames.add(set.getKey());
        });
        JSONObject jsonObject = null;
        try {
            jsonObject = AlibabaUtils.requestAlibabaJsonObject();
        } catch (Exception e) {
            return ResultVO.error("vipshop");
        }

        ListIterator<Object> iterator = jsonObject.getJSONArray("provinceArray").listIterator();

        while (iterator.hasNext()){
            Object next = iterator.next();
            JSONObject province = JSON.parseObject(next.toString());
            JSONArray cityArray = province.getJSONArray("cityArray");
            Iterator<Object> citys = cityArray.iterator();
            while (citys.hasNext()) {
                Object city = citys.next();
                CityVO parse = JSON.parseObject(city.toString(), CityVO.class);
                if (cityNames.contains(parse.getCityName())){
                    cityVOS.add(parse);
                }
            }
        }

        return ResultVO.success(cityVOS);
    }

    // 返回地图
    @RequestMapping("initProvincesData")
    @ResponseBody
    public ResultVO initProvincesData(){
        List<ProvinceVO> provinceVOSList = null;
        JSONObject jsonObject = null;
        try {
            jsonObject = AlibabaUtils.requestAlibabaJsonObject();
        } catch (Exception e) {
            LOGGER.error("error");
            return ResultVO.error("阿里妈妈接口蹦了");
        }
        // 转化为java集合对象
        Object provinceArray = jsonObject.get("provinceArray");
        provinceVOSList = JSON.parseArray(provinceArray.toString(), ProvinceVO.class);
        // 给各省份设置名字
        formatProvinceName(provinceVOSList);

        return ResultVO.success(provinceVOSList);
    }

    //给省名称欢哥名字
    public static void formatProvinceName(List<ProvinceVO> provinceList){
        provinceList.stream().forEach(province ->{
            province.setProvinceName(province.getProvinceName()
                    .replace("省", "")
                    .replace("市","" )
                    .replace("中国", "")
                    .replace("自治区","" ));

            if (province.getProvinceName().equals("宁夏回族")){
                province.setProvinceName("宁夏");
            }
            if (province.getProvinceName().equals("广西壮族")){
                province.setProvinceName("广西");
            }
            if (province.getProvinceName().equals("新疆维吾尔")){
                province.setProvinceName("新疆");
            }
        });


    }

    //全球数据
    @RequestMapping("initAllData")
    public ResultVO initAllData(){
        String remoteData = RemoteUtils.getRemoteData(Const.Api163);

        JSONObject jsonObject = JSONObject.parseObject(remoteData);

        Object data = jsonObject.get("data");

        jsonObject = JSONObject.parseObject(data.toString());

        Object areaTree = jsonObject.get("areaTree");

        JSONArray objects = JSONObject.parseArray(areaTree.toString());

        Iterator<Object> iterator = objects.iterator();

        while (iterator.hasNext()){
            Object next = iterator.next();

            System.out.println(next);
        }
        return ResultVO.success(iterator);
    }



}
