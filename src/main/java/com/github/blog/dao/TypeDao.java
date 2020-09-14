package com.github.blog.dao;


import com.github.blog.domain.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    //新增分类
    Integer saveType(Type type);

    //根据id查询分类
    Type findById(Long id);

    //查询所有分类
    List<Type> getAllTypes();

    //根据名称查询分类
    Type findByName(String name);

    //更改分类
    Integer updateType(Type type);

    //删除分类
    void deleteType(Long id);

    //查询类型对应的博客
    List<Type>getAllTypeAndBlogs();
}
