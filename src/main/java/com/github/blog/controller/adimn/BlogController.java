package com.github.blog.controller.adimn;


import com.github.blog.domain.Blog;
import com.github.blog.domain.Type;
import com.github.blog.domain.User;
import com.github.blog.queryvo.BlogQuery;
import com.github.blog.queryvo.SearchBlog;
import com.github.blog.queryvo.ShowBlog;
import com.github.blog.service.BlogService;
import com.github.blog.service.TagService;
import com.github.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.jar.Attributes;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    //分页查询
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段 倒序 排序
        String orderBy = "updateTime asc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> list = blogService.getAllBlogs();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("types",typeService.getAllTypes());
        model.addAttribute("pageInfo",pageInfo);

        PageHelper.clearPage();
        return LIST;
    }

    //搜索博客管理列表
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.searchBlogs(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);

        PageHelper.clearPage();
        return "admin/blogs :: blogList";
    }

    //获取分类和标签
    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.getAllTypes());
        model.addAttribute("tags",tagService.getAllTags());
    }
    //新增页面跳转
    @GetMapping("blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return INPUT;
    }
    //编辑页面跳转
    @GetMapping("blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        ShowBlog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        return INPUT;
    }


    //新增博客与编辑博客功能
    @PostMapping("/blogs")
    public String post(ShowBlog showBlog,Blog blog, RedirectAttributes attributes, HttpSession session) {
        int b;
        if (blog.getId() == null) {
            //新增的时候需要传递blog对象，blog对象需要有user
            blog.setUser((User) session.getAttribute("user"));
            //设置blog的type
            blog.setType(typeService.getType(blog.getType().getId()));
            //设置blog中typeId属性
            blog.setType_id(blog.getType().getId());
            //设置用户id
            blog.setUser_id(blog.getUser().getId());
            //设置blog中的Tags
            blog.setTags(tagService.getTagsByString(blog.getTagIds()));
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(showBlog);
        }

        if (b == 0) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    //删除功能
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

}
