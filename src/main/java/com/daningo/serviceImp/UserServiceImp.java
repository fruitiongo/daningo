package com.daningo.serviceImp;

import com.daningo.entity.User;
import com.daningo.mappers.UserMapper;
import com.daningo.service.UIService;

import java.util.logging.Logger;
import com.google.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;


/**
 * Created by naing on 6/21/18.
 */
public class UserServiceImp implements UIService<User>  {

    @Inject
    private SqlSession sqlSession;
    @Inject
    private UserMapper userMapper;

    public List<User> getAll(){
        return null;
    }

    @Override
    public List<User> getAll(int id) {
        return null;
    }

    @Override
    public List<User> getAll(User model) {
        return null;
    }

    @Override
    public User add(User user) {
        this.userMapper.addUser(user);
        return user;
    }


    @Override
    public void update(User model) {
        this.userMapper.updateUser(model);
    }

    @Override
    public User get(User model) {
        return this.userMapper.getUser(model.getUserID());
    }
}
