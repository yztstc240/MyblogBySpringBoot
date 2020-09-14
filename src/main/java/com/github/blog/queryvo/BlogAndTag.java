package com.github.blog.queryvo;
/*
    博客和标签关系类
 */
public class BlogAndTag {
    private Long blogId;
    private Long tagIds;

    public BlogAndTag() {
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getTagIds() {
        return tagIds;
    }

    public void setTagIds(Long tagId) {
        this.tagIds = tagId;
    }

    public BlogAndTag(Long blogId, Long tagIds) {
        this.blogId = blogId;
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return "BlogAndTag{" +
                "blogId=" + blogId +
                ", tagIds=" + tagIds +
                '}';
    }
}
