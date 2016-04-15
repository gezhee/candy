package com.utobun.candy.dao.impl;

import org.springframework.stereotype.Repository;

import com.utobun.candy.dao.UserDao;
import com.utobun.candy.dao.base.BaseDaoAbstract;
import com.utobun.candy.domain.User;


@Repository
public class UserDaoImpl extends BaseDaoAbstract<User> implements UserDao{

    public UserDaoImpl() {
        super(User.class);
    }

}
