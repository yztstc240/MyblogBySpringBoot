package com.github.blog.dao;

import com.github.blog.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    /**
     * 保存评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    /**
     * 查看父级评论
     * @param blogId
     * @param blogParentId
     * @return
     */
    List<Comment>findByBlogIdParentIdNull(@Param("blogId") Long blogId,@Param("blogParentId") Long blogParentId);

    /**
     * 查看一级子评论
     * @param blogId
     * @param id
     * @return
     */
    List<Comment>findByBlogIdParentIdNotNull(@Param("blogId") Long blogId,@Param("id") Long id);

    /**
     * 查看二级子评论
     * @param blogId
     * @param childId
     * @return
     */
    List<Comment>findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Long id);
}
