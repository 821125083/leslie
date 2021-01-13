package com.leslie.service.impl;

import com.leslie.mapper.MessageMapper;
import com.leslie.pojo.Message;
import com.leslie.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void insertMessage(Message mes) {
        messageMapper.insert(mes);
    }
}
