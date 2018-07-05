package com.daningo.serviceImp;

import com.daningo.entity.UserTransaction;
import com.daningo.mappers.TransactionMapper;
import com.daningo.service.UIService;
import org.apache.ibatis.session.SqlSession;

import javax.inject.Inject;
import java.util.List;

public class TransactionServiceImp implements UIService<UserTransaction> {
    @Inject
    public SqlSession sqlSession;

    @Inject
    public TransactionMapper paymentMapper;

    @Override
    public List<UserTransaction> getAll() {
        return null;
    }

    @Override
    public List<UserTransaction> getAll(int id) {
        return null;
    }

    @Override
    public UserTransaction get(UserTransaction model) {
        return this.paymentMapper.getTopicPayment(model);
    }

    @Override
    public UserTransaction add(UserTransaction model) {
        this.paymentMapper.createPayment(model);
        return model;
    }

    @Override
    public void update(UserTransaction model) {
        this.paymentMapper.updatePayment(model);
    }

    @Override
    public List<UserTransaction> getAll(UserTransaction model) {
        return null;
    }
}
