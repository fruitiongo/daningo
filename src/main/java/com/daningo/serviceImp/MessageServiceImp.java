package com.daningo.serviceImp;

import com.daningo.entity.Message;
import com.daningo.mappers.MessageMapper;
import com.daningo.service.UIService;

import java.util.logging.Logger;
import com.google.inject.Inject;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MessageServiceImp implements UIService<Message> {
    @Inject
    private SqlSession sqlSession;
    @Inject
    private MessageMapper messageMapper;

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public List<Message> getAll(int topicID) {
        return this.messageMapper.getMessages(topicID);
    }

    public List<Message> getAll(int topicID, int messageID) {
        return this.messageMapper.getMessagesByMessageID(topicID, messageID);
    }

    @Override
    public List<Message> getAll(Message model) {
        return null;
    }

    @Override
    public Message get(Message model) {
        return null;
    }

    @Override
    public Message add(Message model) {
        this.messageMapper.createMessage(model);
        return model;
    }

    @Override
    public void update(Message model) {
    }
}
