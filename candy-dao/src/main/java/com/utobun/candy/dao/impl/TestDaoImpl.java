package com.utobun.candy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.utobun.candy.dao.TestDao;
import com.utobun.candy.dao.base.BaseDaoAbstract;
import com.utobun.candy.domain.Test;
@Repository
public class TestDaoImpl extends BaseDaoAbstract<Test> implements TestDao{

    public TestDaoImpl() {
        super(Test.class);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Test> add() {
        
        return null;
    }
}
