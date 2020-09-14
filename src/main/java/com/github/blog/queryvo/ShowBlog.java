package com.github.blog.queryvo;
/**
 * 博客编辑相关实体类
 */

import com.github.blog.domain.Blog;
import com.github.blog.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShowBlog {

        @Autowired
        private Blog blog;

        private Long id;
        private String flag;
        private String title;
        private String content;
        private Long typeId;
        private String firstPicture;
        private boolean recommend;
        private boolean published;
        private boolean shareStatement;
        private boolean appreciation;
        private boolean commentTable;
        private Date updateTime;
        private String tagIds;
        private String description;
        private List<Tag> tags;


    public ShowBlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isCommentTable() {
        return commentTable;
    }

    public void setCommentTable(boolean commentTabled) {
        this.commentTable = commentTabled;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String  getTagIds() {
        return tagIds;
    }

    public void setTagIds(String  tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ShowBlog{" +
                "id=" + id +
                ", flag='" + flag + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", typeId=" + typeId +
                ", firstPicture='" + firstPicture + '\'' +
                ", recommend=" + recommend +
                ", published=" + published +
                ", shareStatement=" + shareStatement +
                ", appreciation=" + appreciation +
                ", commentTable=" + commentTable +
                ", updateTime=" + updateTime +
                ", tagIds='" + tagIds + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
