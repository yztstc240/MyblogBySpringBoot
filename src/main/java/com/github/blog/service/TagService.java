package com.github.blog.service;

import com.github.blog.domain.Tag;

import java.util.List;


public interface TagService {

    /**
     * 保存类型
     * @param tag
     * @return
     */
    Integer saveTag(Tag tag);

    /**
     * 获取类型
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 获得所有分类
     * @return
     */
    List<Tag> getAllTags();

    /**
     * 根据名字查询分类
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 更改类型
     * @param tag
     * @return
     */
    Integer updateTag(Tag tag);

    /**
     * 删除类型
     * @param id
     */
    void deleteTag(Long id);


    List<Tag> getTagsByString(String tagIds);

    List<Tag> getAllTagAndBlogs();



}
