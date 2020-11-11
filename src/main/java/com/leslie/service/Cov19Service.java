package com.leslie.service;

import com.leslie.pojo.Cov19CnRecord;
import org.springframework.stereotype.Service;


public interface Cov19Service {
    Cov19CnRecord getRealTimeRecord();
}
