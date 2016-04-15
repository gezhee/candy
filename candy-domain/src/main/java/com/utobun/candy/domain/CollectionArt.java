package com.utobun.candy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * ClassName: CollectionArt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: 用户收藏文章. <br/> 
 * date: 2016年3月17日 下午11:40:45 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
@Entity
@Table(name = "collection_art")
public class CollectionArt extends BaseEntity{
    
    /** 
     * userId:用户id.  
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    /** 
     * articleId:文章id.  
     */
    @Column(name = "article_id", nullable = false)
    private Long articleId;
    
    /** 
     * state:收藏状态.  
     */
    @Column(name = "state", nullable = false)
    private int state;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
    
}
