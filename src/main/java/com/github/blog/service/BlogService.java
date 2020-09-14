package com.github.blog.service;

import com.github.blog.domain.Blog;
import com.github.blog.domain.Tag;
import com.github.blog.queryvo.*;

import java.util.List;
import java.util.Map;

/**
 * 博客管理的业务层代码
 */
public interface BlogService {

    /*查询所有博客*/
    List<BlogQuery> getAllBlogs();

    /*新增博客*/
    int saveBlog(Blog blog);

    /*修改博客*/
    int updateBlog(ShowBlog showblog);

    /*查询编辑修改的博客*/
    ShowBlog getBlogById(Long id);

    /*删除博客*/
    void deleteBlog(Long id);

    /*搜索博客*/
    List<BlogQuery>searchBlogs(SearchBlog searchBlog);

    /*获取首页内容*/
    List<FirstPageBlog>getAllFirstPageBlog();

    /*获取被推荐博客*/
    List<RecommendBlog> getRecommendedBlog();

    /*博客搜索*/
    List<FirstPageBlog> getSearchBlog(String query);

    /*获取博客详情*/
    DetailedBlog getDetailedBlog(Long id);

    //根据类型id查询文章
    List<FirstPageBlog> getByTypeId(Long typeId);

    //根据标签id查询文章
    List<FirstPageBlog> getByTagId(Long tagId);

    //查询博客总条数
    int countBlog();

    //根据更新时间分类博客
    Map<String, List<Blog>> archiveBlog();

    List<RecommendBlog> getRecommendedBlogWithSize(Integer size);






    }
