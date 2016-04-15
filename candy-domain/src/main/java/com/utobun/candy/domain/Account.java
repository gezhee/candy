package com.utobun.candy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    /**  @Fields userId : 用户id */
    @Column(name="user_id", nullable=false, columnDefinition="INT")
    private Long userId;
    
    @Column(name = "user_name", nullable = false, columnDefinition = "varchar")
    private String userName;
    
    /**  @Fields password : 加密后的密码 */
    @Column(name = "password", nullable=false, columnDefinition = "VARCHAR(70)")
    private String password;
    
    /**  @Fields createDate : 创建时间 */
    @Column(name = "create_date",  columnDefinition = "TIMESTAMP")
    private Date createDate;
    
    /** 
     * updateDate:更新时间.  
     */
    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    private Date updateDate;
    
    /**  @Fields deleteFlag : 删除标志，默认为0,即没删除 */
    @Column(name = "state", columnDefinition = "TINYINT(1) DEFAULT 0" )
    private int state;
    
    /** @Fields acticode : 激活码*/
    @Column(name = "active_code", columnDefinition = "VARCHAR(20)")
    private String activeCode;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
