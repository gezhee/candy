package com.utobun.candy.dao;


import java.util.List;

import com.utobun.candy.dao.base.BaseDao;
import com.utobun.candy.domain.Test;



public interface TestDao extends BaseDao<Test>{
    public List<Test> add();
}
