package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.Cov19CnRecord;
import com.leslie.pojo.Trend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface Cov19Mapper extends BaseMapper<Trend> {
}
