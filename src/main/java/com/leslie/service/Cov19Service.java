package com.leslie.service;

import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Province;
import com.leslie.pojo.Trend;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Cov19Service {
    /**
     *
     * @return
     */
    List<Trend> cov19TrendLineChart();


    List<Province> cov19CnProvincesData();
}
