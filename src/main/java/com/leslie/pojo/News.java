package com.leslie.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("L_news")
public class News {

    @TableId
    private Integer contentId;

    private String title;

    private String authorName;

    private Integer authorId;

    private String Url;

    private Date publishTime;

    private String articleAbstract;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Date getPublishDate() {
        return publishTime;
    }

    public void setPublishDate(Date publishDate) {
        this.publishTime = publishDate;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "contentId=" + contentId +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorId=" + authorId +
                ", Url='" + Url + '\'' +
                ", publishTime=" + publishTime +
                ", articleAbstract='" + articleAbstract + '\'' +
                '}';
    }
}
