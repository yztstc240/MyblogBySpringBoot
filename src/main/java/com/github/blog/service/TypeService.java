package com.github.blog.service;

import com.github.blog.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TypeService {

    /**
     * 保存类型
     * @param type
     * @return
     */
    Integer saveType(Type type);

    /**
     * 获取类型
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 获得所有分类
     * @return
     */
    List<Type> getAllTypes();

    /**
     * 根据名字查询分类
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 更改类型
     * @param type
     * @return
     */
    Integer updateType(Type type);

    /**
     * 删除类型
     * @param id
     */
    void deleteType(Long id);

    /**
     * 获取类型与对应博客
     * @return
     */
    List<Type> getAllTypeAndBlogs();
}
