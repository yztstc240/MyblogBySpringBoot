package com.github.blog.service;

import com.github.blog.domain.Comment;

import java.util.List;

public interface CommentService {

    //保存评论
    int saveComment(Comment comment);

    //根据博客id查询评论信息
    List<Comment> listCommentByBlogId(Long blogId);

    //删除评论
    void deleteComment(Comment comment,Long id);
}
