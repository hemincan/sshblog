package com.xrom.ssh.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrom.ssh.repository.DomainRepository;
import com.xrom.ssh.util.Page;


public class CommonRepositoryImpl<T> implements DomainRepository<T>{

	
    @Autowired
    private SessionFactory sessionFactory;
    public Session getCurrentSession() {
    	return sessionFactory.getCurrentSession();
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
	@SuppressWarnings("unchecked")
	@Override
	public T load(Integer id) {
		return (T) getCurrentSession().load(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Integer id) {
		return (T) getCurrentSession().get(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
	@Override
    public Page<T> findPage(T entity, int pageNum, int pageSize, String orderBy) {
    	Session session = getCurrentSession();
    	String queryString = this.getAndQueryString(entity);
    	if(orderBy!=null) {
    		queryString = queryString + " order by " + orderBy;
    	}
    	Query q = session.createQuery(queryString);
    	 //得到滚动结果集
        ScrollableResults scroll = q.scroll();
        //滚动到最后一行
        scroll.last();
        int maxRecord = scroll.getRowNumber() + 1;
        //设置分页位置
        q.setFirstResult((pageNum-1)*pageSize);
        q.setMaxResults(pageSize);
        Page<T> page =new Page<T>(pageSize, (long)maxRecord, pageNum);
        page.setResult(q.list());
		return page;
    }
    /**
     * 通过传入的entity中属性不为空的字段来查询数据,只能查and
     * @param entity
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<T> queryByEntity(T entity){
    	String queryString = this.getAndQueryString(entity);
    	Session session = getCurrentSession();
    	Query q = session.createQuery(queryString);
		return q.list();
    	
    }
    private String getAndQueryString(T entity) {
    	Class<T> cls = this.clazz;
    	Field[] fields = cls.getDeclaredFields();  
    	StringBuffer hql = new StringBuffer();
    	hql.append("from " + this.clazz.getName() + " as t");
    	if(entity == null) {
    		return hql.toString();
    	}
    	List<String> list = new ArrayList<String>();
    	for(int i=0; i<fields.length; i++){  
            Field f = fields[i];  
            f.setAccessible(true);  
            try {
//            	System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(entity));
            	Object value = f.get(entity);
            	if(value == null) {
            		continue;
            	}
            	if(value instanceof java.lang.String){
            		 value = "'" + value + "'";
            	}
            	list.add(" t." + f.getName() + "=" + value + " ");
			} catch (Exception e) {
				e.printStackTrace();
			} 
        }   
    	for (int i = 0; i < list.size(); i++) {
    		if(i == 0) {
    			hql.append(" where ");
    		}
			hql.append(list.get(i));
			if(i < list.size()-1){
				hql.append(" and ");
			}
		}
    	System.out.println(hql.toString());
		return hql.toString();
    }
}
