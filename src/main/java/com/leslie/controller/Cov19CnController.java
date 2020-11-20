package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.cons.Const;
import com.leslie.mapper.CityMapper;
import com.leslie.mapper.Cov19Mapper;
import com.leslie.mapper.ProvinceMapper;
import com.leslie.pojo.City;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Province;
import com.leslie.service.Cov19Service;
import com.leslie.utils.JsonUtils;
import com.leslie.utils.RemoteUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/Cov19Cn")
@CrossOrigin
public class Cov19CnController {

    @Autowired
    private Cov19Service cov19Service;

    @Autowired
    private Cov19Mapper cov19Mapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    /**
     * @return 疫情实时数据
     */
    @RequestMapping("/realTimeRecord")
    public Cov19CnRecord getRealTimeRecord() {
        Map<String, String> headers = new HashMap<>();
        headers.put("apicode", Const.apiCode);

        String data = RemoteUtils.getRemoteData(Const.CNUrl, new HashMap<>(), headers);

        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(data).getString("data"), Cov19CnRecord.class);
        record.setDescription(record.getDescription().replaceAll(" ", ""));
        record.setSourceDesc(record.getSourceDesc().replaceAll(" ", ""));
        return record;
    }

    /**
     * 国内各省地图疫情情况
     *
     * @return
     */
    @RequestMapping("CNRealTimeRecord")
    public String getCNRealTimeRecord() {
        HashMap<String, String> header = new HashMap<>();
        header.put("authoration", "apicode");
        header.put("apicode", Const.apiCode);
        return RemoteUtils.getRemoteData(Const.MAPUrl, new HashMap<String, String>(), header);
    }

    /**
     * 获得某省数据
     *
     * @param province 查询省份信息
     * @return
     */
    @RequestMapping("CNRealTimeRecord/{province}")
    public String CNRealTimeRecordByProvince(@PathVariable String province) {
        HashMap<String, String> header = new HashMap<>();
        header.put("authoration", "apicode");
        header.put("apicode", Const.apiCode);
        String remoteData = RemoteUtils.getRemoteData(Const.MAPUrl, new HashMap<String, String>(), header);
        Province gd = new Province();

        return gd.toString();
    }




}
