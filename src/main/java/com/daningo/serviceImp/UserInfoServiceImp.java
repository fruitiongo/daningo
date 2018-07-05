package com.daningo.serviceImp;

import com.daningo.mappers.UserMapper;
import com.daningo.service.UIService;
import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import com.daningo.entity.UserInstagram;

import java.util.List;

public class UserInfoServiceImp implements UIService<UserInstagram> {

    @Inject
    private SqlSession sqlSession;
    @Inject
    private UserMapper userMapper;

    @Override
    public List<UserInstagram> getAll() {
        return null;
    }

    @Override
    public List<UserInstagram> getAll(int id) {
        return null;
    }

    @Override
    public UserInstagram get(UserInstagram model) {
        return null;
    }

    @Override
    public UserInstagram add(UserInstagram model) {
        return null;
    }

    @Override
    public void update(UserInstagram model) {

    }

    @Override
    public List<UserInstagram> getAll(UserInstagram model) {
        return null;
    }
}
