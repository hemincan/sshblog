package com.xrom.ssh.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrom.ssh.repository.DomainRepository;


public class CommonRepositoryImpl<T> implements DomainRepository<T>{

	
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
	private Class<T> clazz;  
	
	@SuppressWarnings("unchecked")
	public CommonRepositoryImpl() {
		//使用反射技术得到T的真实类型  
        //获取当前new的对象的泛型的父类  
        ParameterizedType pt =(ParameterizedType) this.getClass().getGenericSuperclass();  
        //获取第一个类型参数的真实类型  
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];  
	}
	/**
	 * 延迟加载一个对象，当使用到它时才查询
	 */
	@Override
	public T load(Integer id) {
		return (T) getCurrentSession().load(this.clazz, id);
	}

	@Override
	public T get(Integer id) {
		return (T) getCurrentSession().get(this.clazz, id);
	}

	@Override
	public List<T> findAll() {
		Query q = getCurrentSession().createQuery("from "+ this.clazz.getName());
        return q.list();
	}

	@Override
	public void persist(T entity) {
		getCurrentSession().persist(entity);
		
	}

	@Override
	public Integer save(T entity) {
		return (Integer) getCurrentSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Session session =getCurrentSession();
		session.saveOrUpdate(entity);
		session.flush();
		
	}

	@Override
	public void delete(Integer id) {
		 T t = get(id);
		 Session session =getCurrentSession();
		 session.delete(t);
		 session.flush();
	}
	
	
	@Override
	public void flush() {
		getCurrentSession().flush();
		
	}




}
