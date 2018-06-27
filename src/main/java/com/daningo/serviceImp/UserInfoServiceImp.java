package com.daningo.serviceImp;

import com.daningo.entity.UserInfo;
import com.daningo.mappers.UserMapper;
import com.daningo.service.UIService;
import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserInfoServiceImp implements UIService<UserInfo> {

    @Inject
    private SqlSession sqlSession;
    @Inject
    private UserMapper userMapper;

    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public List<UserInfo> getAll(int id) {
        return null;
    }

    @Override
    public UserInfo get(UserInfo model) {
        return null;
    }

    @Override
    public UserInfo add(UserInfo model) {
        return null;
    }

    @Override
    public void update(UserInfo model) {

    }

    @Override
    public List<UserInfo> getAll(UserInfo model) {
        return null;
    }
}
