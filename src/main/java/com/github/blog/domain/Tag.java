package com.github.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签的实体类
 */
public class Tag implements Serializable {

    private Long id;    /*标签的ID*/
    private String name;    /*标签名称*/

    private List<Blog> blogs = new ArrayList<>();

    /*空参构造*/
    public Tag() {
    }



    /*全参构造*/
    public Tag(Long id, String name, List<Blog> blogs) {
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

    /*重写toString*/
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
