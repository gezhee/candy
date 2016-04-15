package com.utobun.candy.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.utobun.candy.contants.Contants;

@Repository
public abstract class BaseDaoAbstract<T> implements BaseDao<T>{
    
    protected static Logger log = LogManager.getLogger(BaseDaoAbstract.class.getName());
    /**
     * sessionFactory实例
     */
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 泛型entity
     */
    private Class<T> entityClass;
    
    /**
     * @return 当前工作的session
     */
    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }
    
    
    public BaseDaoAbstract(Class<T> clazz) {
        this.entityClass = clazz;
    }
    
    public Long add(T entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }
    
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
    
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
    
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
    
    @SuppressWarnings("unchecked")
    public T get(Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }
    
    @SuppressWarnings("unchecked")
    public T load(Serializable id) {
        return (T) sessionFactory.getCurrentSession().load(entityClass, id);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T get(Map<String, Object> map) {
        String hql = " from " + entityClass.getSimpleName() + " where 1 = 1";
        Set<String> keySet = map.keySet();
        for (Iterator key = keySet.iterator(); key.hasNext();) {
            String sKey = (String) key.next();
            hql += " and " + sKey + "=:" + sKey;
        }
        Query query = this.getSession().createQuery(hql);
        for (Iterator key = keySet.iterator(); key.hasNext();) {
            String sKey = (String) key.next();
            query.setParameter(sKey, map.get(sKey));
        }
        return (T) query.uniqueResult();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> getList(Map<String, Object> map) {
        String hql = " from " + entityClass.getSimpleName() + " where 1 = 1";
        Set<String> keySet = map.keySet();

        for (Iterator key = keySet.iterator(); key.hasNext();) {
            String sKey = (String) key.next();
            hql += " and " + sKey + "=:" + sKey;
        }
        Query query = this.getSession().createQuery(hql);
        for (Iterator key = keySet.iterator(); key.hasNext();) {
            String sKey = (String) key.next();
            query.setParameter(sKey, map.get(sKey));
        }
        return query.list();
    }

    public boolean exist(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return !query.list().isEmpty();
    }

    public boolean exist(Map<String, Object> map) {
        return count(map) > 0;
    }
    
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findByCriteria(List<Criterion> criterions) {
        Criteria criteria = this.getSession().createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return (List<T>) criteria.list();
    }

    @SuppressWarnings({ "hiding", "unchecked" })
    public <T> List<T> findByHql(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return (List<T>) query.list();
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findBySql(String sql, Object... params) {
        if("".equals(sql) || sql == null) {
            return new ArrayList<T>();
        }
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        for (int i = 0; params != null && i < params.length; i++) {
            sqlQuery.setParameter(i, params[i]);
        }
        return (List<T>) sqlQuery.list();
    }
    
    public Object getBySql(String sql, Object... params) {
        return findBySql(sql, params).get(0);
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findByHql(String hql, List<T> list) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; list != null && i < list.size(); i++) {
            query.setParameter(i, list.get(i));
        }
        return (List<T>) query.list();
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> List<T> findBySql(String sql, List<T> list) {
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        for (int i = 0; list != null && i < list.size(); i++) {
            sqlQuery.setParameter(i, list.get(i));
        }
        return (List<T>) sqlQuery.list();
    }

    public void addList(List<T> entityList) {
        Session session = getSession();
        session.setCacheMode(CacheMode.IGNORE);
        for (int i = 0; i < entityList.size(); i++) {
            session.save(entityList.get(i));
            if (i % Contants.BATCH_SAVE_NUM == 0) {
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
        session.setCacheMode(CacheMode.NORMAL);
    }

    public void updateBySql(String sql, Object... params) {
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        for (int i = 0; params != null && i < params.length; i++) {
            sqlQuery.setParameter(i, params[i]);
        }
        sqlQuery.executeUpdate();
    }

    public void deleteBySql(String sql, Object... params) {
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        for (int i = 0; params != null && i < params.length; i++) {
            sqlQuery.setParameter(i, params[i]);
        }
        sqlQuery.executeUpdate();
    }

    @SuppressWarnings("rawtypes")
    public int count(Map<String, Object> map) {
        String hql = " select count(*) from " + entityClass.getSimpleName() + " where 1 = 1";
        Set<String> keySet = map.keySet();
        for (Iterator key = keySet.iterator(); key.hasNext();) {
            String sKey = (String) key.next();
            hql += " and " + sKey + "=:" + sKey;
        }

        Query query = this.getSession().createQuery(hql);
        for (Iterator key = keySet.iterator(); key.hasNext();) {
            String sKey = (String) key.next();
            query.setParameter(sKey, map.get(sKey));
        }
        return Integer.parseInt(String.valueOf(query.list().get(0)));
    }

    public int count(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return Integer.parseInt(String.valueOf(query.list().get(0)));
    }
    
    public int sum(String hql, Object... params) {
        Query query = this.getSession().createQuery(hql);
        for (int i = 0; params != null && i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        Object o = query.list().get(0);
        if(o == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(query.list().get(0)));
    }
    

    @SuppressWarnings({ "unchecked" })
    public List<T> findDtoBySql(String sql,  Object... params) {
        SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
        for (int i = 0; params != null && i < params.length; i++) {
            sqlQuery.setParameter(i, params[i]);
        }
        return sqlQuery.list();
    }
}
