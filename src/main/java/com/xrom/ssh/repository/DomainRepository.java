package com.xrom.ssh.repository;

import java.util.List;

import com.xrom.ssh.util.Page;

/**
 * 这个接口定义一些十分通用的方法
 * Created by XRom
 * On 11/16/2017.11:52 PM
 */
public interface DomainRepository<T> {
    T load(Integer id);

    T get(Integer id);

    List<T> findAll();

    void persist(T entity);

    Integer save(T entity);

    void saveOrUpdate(T entity);

    void delete(Integer id);

    void flush();

	Page<T> findPage(T entity, int pageNum, int pageSize, String orderBy);

}