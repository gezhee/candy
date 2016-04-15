package com.utobun.candy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article extends BaseEntity{
    
    /** 
     * title:文章标题.  
     */
    @Column(name = "title")
    private String title;
    
    /** 
     * author:作者.  
     */
    @Column(name = "author")
    private String author;
    
    /** 
     * from:文章来源.  
     */
    @Column(name = "from")
    private String from;
    
    /** 
     * summary:TODO.  
     */
    @Column(name = "summary")
    private String summary;
    
    /** 
     * contents:文章内容.  
     */
    @Column(name = "contents")
    private String contents;
    
    /** 
     * state:文章状态.  
     */
    @Column(name = "state", nullable = false)
    private int state;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
    
}
