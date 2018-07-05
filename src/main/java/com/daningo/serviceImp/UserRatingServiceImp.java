package com.daningo.serviceImp;

import com.daningo.entity.UserRating;
import com.daningo.mappers.UserMapper;
import com.daningo.service.BaseService;
import com.daningo.service.UIService;
import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserRatingServiceImp implements UIService<UserRating> {

    @Inject
    private SqlSession sqlSession;
    @Inject
    private UserMapper userMapper;

    @Override
    public List<UserRating> getAll(UserRating model) {
        return null;
    }

    @Override
    public void update(UserRating model) {

    }

    @Override
    public UserRating add(UserRating model) {
        this.userMapper.addUserRating(model);
        return model;
    }

    @Override
    public UserRating get(UserRating model) {
        return null;
    }

    @Override
    public List<UserRating> getAll(int id) {
        return null;
    }

    public UserRating get(int id) {
        return this.userMapper.getUserRating(id);
    }

    @Override
    public List<UserRating> getAll() {
        return null;
    }
}
