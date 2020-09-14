package com.github.blog.service.Impl;

import com.github.blog.NotFoundException;
import com.github.blog.dao.BlogDao;
import com.github.blog.domain.Blog;
import com.github.blog.domain.Tag;
import com.github.blog.queryvo.*;
import com.github.blog.service.BlogService;
import com.github.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Transactional
    @Override
    public List<BlogQuery> getAllBlogs() {
        return blogDao.getAllBlogQuery();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setView(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogAndTag.setTagIds(tag.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.saveBlog(blog);

    }


    @Transactional
    @Override
    public int updateBlog(ShowBlog showblog) {
        showblog.setUpdateTime(new Date());
        //将标签数据保存
        String str = showblog.getTagIds();
        String[] arrayStr =new String[]{};
        BlogAndTag blogAndTag = new BlogAndTag();
        arrayStr = str.split(",");
        long[] list = (long[]) ConvertUtils.convert(arrayStr,long.class);
        for (Object tagid : list) {
            blogAndTag.setTagIds((long)tagid);
            blogAndTag.setBlogId(showblog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(showblog);
    }

    @Transactional
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Transactional
    @Override
    public List<BlogQuery> searchBlogs(SearchBlog searchBlog) {
        return blogDao.findByTittleAndType(searchBlog);
    }

    @Transactional
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    @Transactional
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        List<RecommendBlog> allRecommendedBlog = new ArrayList<>();
        for (RecommendBlog recommendBlog : allRecommendBlog) {
            if (recommendBlog.isRecommend() == true) {
                allRecommendedBlog.add(recommendBlog);
            }
        }
        return allRecommendedBlog;
    }

    @Override
    @Transactional
    public List<RecommendBlog> getRecommendedBlogWithSize(Integer size) {
        int i = 0;
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        List<RecommendBlog> allRecommendedBlog = new ArrayList<>();
        for (RecommendBlog recommendBlog : allRecommendBlog) {
            i++;
            if (recommendBlog.isRecommend() == true) {
                allRecommendedBlog.add(recommendBlog);
            }
            if (i>=size){
                break;
            }
        }
        return allRecommendedBlog;
    }

    @Transactional
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Transactional
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //文章访问数量自增
        blogDao.updateViews(id);
        blogDao.getCommentCountById(id);
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public int countBlog() {
        return blogDao.getAllBlogQuery().size();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }



}
