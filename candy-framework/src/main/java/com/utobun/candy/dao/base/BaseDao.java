package com.utobun.candy.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public interface BaseDao<T> {
    
    Session getSession();
    
    public Long add(T entity);
    
    public void saveOrUpdate(T entity);
    
    public void update(T entity);
    
    public void delete(T entity);
    
    public T get(Serializable id);
    
    public T load(Serializable id);
    
    public T get(Map<String, Object> map);
    
    public List<T> getList(Map<String, Object> map);

    public boolean exist(String hql, Object... params);

    public boolean exist(Map<String, Object> map);
    
    @SuppressWarnings("hiding")
    public <T> List<T> findByCriteria(List<Criterion> criterions);

    @SuppressWarnings("hiding")
    public <T> List<T> findByHql(String hql, Object... params);

    @SuppressWarnings("hiding")
    public <T> List<T> findBySql(String sql, Object... params);
    
    public Object getBySql(String sql, Object... params);

    @SuppressWarnings("hiding")
    public <T> List<T> findByHql(String hql, List<T> list);
    
    @SuppressWarnings("hiding")
    public <T> List<T> findBySql(String sql, List<T> list);

    public void addList(List<T> entityList);

    public void updateBySql(String sql, Object... params);

    public void deleteBySql(String sql, Object... params);

    public int count(Map<String, Object> map);

    public int count(String hql, Object... params);
    
    public int sum(String hql, Object... params);
    
    public List<T> findDtoBySql(String sql,  Object... params);
}
