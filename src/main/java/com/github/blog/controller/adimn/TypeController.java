package com.github.blog.controller.adimn;


import com.github.blog.domain.Type;
import com.github.blog.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    /*分页查询*/
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        /*按照字段顺序排序*/
        String orderBy="id asc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<Type> list = typeService.getAllTypes();
        /*将查询结果根据分页规则封装为分页类*/
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    /*跳转到新增页面*/
    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    /*新增分类*/
    @PostMapping("/types")
    public String post(@Validated Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
         attributes.addFlashAttribute("message","不能重复添加已有分类");
         return "redirect:/admin/types";
        }
        int t = typeService.saveType(type);
        if (t == 0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }


    /*跳转到修改分类页面*/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }


    /*修改分类*/
    @PostMapping("/types/{id}")
    public String editPost(@Validated Type type,@PathVariable Long id, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            attributes.addFlashAttribute("message","不能重复添加已有分类");
            return "redirect:/admin/types";
        }
        int t = typeService.updateType(type);
        if (t == 0){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }


    /*删除分类*/
    @GetMapping("types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
