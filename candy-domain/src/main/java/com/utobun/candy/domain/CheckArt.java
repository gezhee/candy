package com.utobun.candy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * ClassName: CheckArt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: 用户查看文章实体类. <br/> 
 * date: 2016年3月17日 下午11:36:24 <br/> 
 * 
 * @author HemingWu 
 * @version  
 * @since JDK 1.8 
 */
@Entity
@Table(name = "check_art")
public class CheckArt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    /** 
     * articleId:文章id.  
     */
    @Column(name = "article_id")
    private Long articleId;
    
    /** 
     * userId:用户id.  
     */
    @Column(name = "user_id")
    private Long userId;
    
    /** 
     * createDate:创建时间.  
     */
    @Column(name = "create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
}
