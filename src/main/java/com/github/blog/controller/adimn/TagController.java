package com.github.blog.controller.adimn;


import com.github.blog.domain.Tag;
import com.github.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /*分页查询*/
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        /*按照字段顺序排序*/
        String orderBy="id asc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Tag> list = tagService.getAllTags();
        /*将查询结果根据分页规则封装为分页类*/
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    /*跳转到新增页面*/
    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    /*新增分类*/
    @PostMapping("/tags")
    public String post(@Validated Tag tag, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
         attributes.addFlashAttribute("message","不能重复添加已有标签");
         return "redirect:/admin/tags";
        }
        int t = tagService.saveTag(tag);
        if (t == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }


    /*跳转到修改分类页面*/
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }


    /*修改分类*/
    @PostMapping("/tags/{id}")
    public String editPost(@Validated Tag tag,@PathVariable Long id, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            attributes.addFlashAttribute("message","不能重复添加已有标签");
            return "redirect:/admin/tags";
        }
        int t = tagService.updateTag(tag);
        if (t == 0){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }


    /*删除分类*/
    @GetMapping("tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
