package com.github.blog.dao;

import com.github.blog.domain.Blog;
import com.github.blog.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * blog持久层接口
 */

@Mapper
@Repository
public interface BlogDao {

    //保存博客
    int saveBlog(Blog blog);

    //查询所有博客
    List<BlogQuery> getAllBlogQuery();

    //删除博客
    void deleteBlog(Long id);

    //编辑博客
    int updateBlog(ShowBlog showBlog);

    //查询编辑修改的文章
    ShowBlog getBlogById(Long id);

    //根据title和类型查找博客
    List<BlogQuery>findByTittleAndType(SearchBlog searchBlog);

    //保存博客和标签关系
    int saveBlogAndTag(BlogAndTag blogAndTag);

    //获取首页所有博客
    List<FirstPageBlog>getFirstPageBlog();

    //获取被推荐博客
    List<RecommendBlog> getAllRecommendBlog();

    //搜索首页博客
    List<FirstPageBlog> getSearchBlog(String query);

    //获取详情页
    DetailedBlog getDetailedBlog(Long id);

    //根据博客id查评论
    int getCommentCountById(Long id);

    //文章访问更新
    int updateViews(Long id);

    //根据类型id查询文章
    List<FirstPageBlog> getByTypeId(Long typeId);

    //根据标签id查询文章
    List<FirstPageBlog> getByTagId(Long tagId);

    //查询所有年份，返回一个集合
    List<String> findGroupYear();

    //按年份查询博客
    List<Blog> findByYear(@Param("year") String year);

}
