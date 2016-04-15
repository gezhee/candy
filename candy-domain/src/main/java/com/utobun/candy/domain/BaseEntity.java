package com.utobun.candy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;
    
    /**  @Fields createDate : 创建时间 */
    @Column(name = "create_date",  columnDefinition = "TIMESTAMP")
    protected Date createDate;
    
    /** 
     * updateDate:更新时间.  
     */
    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    protected Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
    

}
