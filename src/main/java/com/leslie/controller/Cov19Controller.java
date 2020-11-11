package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leslie.mapper.Cov19Mapper;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.service.Cov19Service;
import com.leslie.utils.JsonUtils;
import com.leslie.utils.RemoteUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/Cov19Cn")
@CrossOrigin
public class Cov19Controller {

    @Autowired
    private Cov19Service cov19Service;

    @Autowired
    private Cov19Mapper cov19Mapper;

    /**
     *
     * @return 疫情实时数据
     * */
    @RequestMapping("/realTimeRecord")
    public Cov19CnRecord getRealTimeRecord(){
        Map<String,String> headers = new HashMap<>();
        headers.put("apicode","4fa69c43e75f4779b6bc50177e361ee5");

        String data = RemoteUtils.getRemoteData("https://api.yonyoucloud.com/apis/dst/ncov/country", new HashMap<>(), headers);

        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(data).getString("data"), Cov19CnRecord.class);
        record.setDescription(record.getDescription().replaceAll(" ","" ));
        record.setSourceDesc(record.getSourceDesc().replaceAll(" ","" ));
        cov19Mapper.insert(record);
        return record;
    }
}
