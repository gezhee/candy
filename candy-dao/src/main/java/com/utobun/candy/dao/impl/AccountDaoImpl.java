package com.utobun.candy.dao.impl;

import org.springframework.stereotype.Repository;

import com.utobun.candy.dao.AccountDao;
import com.utobun.candy.dao.base.BaseDaoAbstract;
import com.utobun.candy.domain.Account;

@Repository
public class AccountDaoImpl extends BaseDaoAbstract<Account> implements AccountDao{

    public AccountDaoImpl() {
        super(Account.class);
    }

}
