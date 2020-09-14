package com.github.blog.controller;

import com.github.blog.domain.Tag;
import com.github.blog.domain.Type;
import com.github.blog.queryvo.DetailedBlog;
import com.github.blog.queryvo.FirstPageBlog;
import com.github.blog.queryvo.RecommendBlog;
import com.github.blog.service.BlogService;
import com.github.blog.service.TagService;
import com.github.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TypeService typeService;



    /*首页展示*/
    @RequestMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 6);
        PageHelper.clearPage();
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        /*System.out.println("num:"+allFirstPageBlog.size());
        for (FirstPageBlog firstPageBlog : allFirstPageBlog) {
            System.out.println(firstPageBlog);
        }*/
        List<Type> allType = typeService.getAllTypeAndBlogs();
        /*System.out.println("num:" + allType.size());
        for (Type type : allType) {
            System.out.println(type);
        }*/
        List<Tag> allTag = tagService.getAllTagAndBlogs();
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        model.addAttribute("recommendedBlogs", recommendedBlog);


        return "index";

    }

    /*博客搜索*/
    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 100);
        PageHelper.clearPage();
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        /*for (FirstPageBlog firstPageBlog : searchBlog) {
            System.out.println(firstPageBlog);
        }*/
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);


        return "search";
    }


    /*详情页跳转*/
    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);

        model.addAttribute("blog", detailedBlog);
        return "blog";
    }


    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.getRecommendedBlogWithSize(3));
        return "_fragments :: newblogList";
    }

}
