package com.github.blog.dao;


import com.github.blog.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface TagDao {

    //新增标签
    Integer saveTag(Tag tag);

    //根据id查询标签
    Tag findById(Long id);

    //查询所有标签
    List<Tag> getAllTags();

    //根据名称查询标签
    Tag findByName(String name);

    //更改标签
    Integer updateTag(Tag tag);

    //删除标签
    void deleteTag(Long id);

    //获取博客的所有标签
    List<Tag> getAllTagAndBlogs();


}
