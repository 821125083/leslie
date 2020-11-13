package com.leslie;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.leslie.pojo.Cov19CnRecord;
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



    @Test
    public void test6() throws Exception {
        String url = "https://api.yonyoucloud.com/apis/dst/ncov/country";
        Map<String,String > headers = new HashMap<>();
        headers.put("apicode","4fa69c43e75f4779b6bc50177e361ee5");
        String remoteData = RemoteUtils.getRemoteData(url, new HashMap<>(), headers);
        Cov19CnRecord record = JSON.parseObject(JSON.parseObject(remoteData).getString("data"), Cov19CnRecord.class);
        System.out.println(record);
    }

}
