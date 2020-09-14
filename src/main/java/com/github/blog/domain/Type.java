package com.github.blog.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 分类的实体类
 */
public class Type implements Serializable {
    private Long id;    /*分类的ID*/
    private String name;    /*分类名称*/
    private List<Blog> blogs = new ArrayList<>();

    /*空参构造*/
    public Type() {
    }

    /*全参构造*/
    public Type(Long id, String name, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }


    /*getter和setter方法*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
