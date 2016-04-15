package com.utobun.candy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * ClassName: CommentArt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: 记录用户对文章评论实体类. <br/> 
 * date: 2016年3月17日 下午11:50:02 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
@Entity
@Table(name = "comment_art")
public class CommentArt extends BaseEntity{
    /** 
     * articleId:文章id.  
     */
    @Column(name = "article_id")
    private Long articleId;
    
    /** 
     * userId:用户ID.  
     */
    @Column(name = "user_id")
    private Long userId;
    
    /** 
     * userIdReply:用户回复id.
     */
    @Column(name = "user_id_reply")
    private Long userIdReply;
    
    /** 
     * comment:评论内容.  
     */
    @Column(name = "comment")
    private String comment;
    
    /** 
     * reply:回复内容.  
     */
    @Column(name = "reply")
    private String reply;
    
    /** 
     * state:状态.  
     */
    @Column(name = "state")
    private int state;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserIdReply() {
        return userIdReply;
    }

    public void setUserIdReply(Long userIdReply) {
        this.userIdReply = userIdReply;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
}
