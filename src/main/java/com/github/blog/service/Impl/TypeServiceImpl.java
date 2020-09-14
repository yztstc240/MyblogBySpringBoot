package com.github.blog.service.Impl;

import com.github.blog.NotFoundException;
import com.github.blog.dao.TypeDao;
import com.github.blog.domain.Type;
import com.github.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Integer saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Type byId = typeDao.findById(id);
        if (byId == null){
            throw new NotFoundException("该id不存在");
        }
        return byId;
    }

    @Transactional
    @Override
    public List<Type> getAllTypes() {
        return typeDao.getAllTypes();
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        Type byName = typeDao.findByName(name);
        return byName;
    }


    @Transactional
    @Override
    public Integer updateType(Type type) {
        Integer updateType = typeDao.updateType(type);
        return updateType;
    }


    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }


    @Transactional
    @Override
    public List<Type> getAllTypeAndBlogs(){
        return typeDao.getAllTypeAndBlogs();
    }
}
