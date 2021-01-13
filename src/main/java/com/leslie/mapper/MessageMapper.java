package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper  extends BaseMapper<Message> {
}
