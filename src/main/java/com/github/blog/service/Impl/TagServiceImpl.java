package com.github.blog.service.Impl;

import com.github.blog.NotFoundException;
import com.github.blog.dao.TagDao;
import com.github.blog.dao.TagDao;
import com.github.blog.domain.Tag;
import com.github.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public Integer saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        Tag byId = tagDao.findById(id);
        if (byId == null){
            throw new NotFoundException("该id不存在");
        }
        return byId;
    }

    //获取所有标签
    @Transactional
    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        Tag byName = tagDao.findByName(name);
        return byName;
    }


    @Transactional
    @Override
    public Integer updateTag(Tag tag) {
        Integer updateTag = tagDao.updateTag(tag);
        return updateTag;
    }


    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }

    @Transactional
    public List<Tag> getTagsByString(String tagIds){
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(tagIds);
        for (Long aLong : longs) {
            Tag byId = tagDao.findById(aLong);
            tags.add(byId);
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }



    @Transactional
    @Override
    public List<Tag> getAllTagAndBlogs(){
        return tagDao.getAllTagAndBlogs();
    }

}
