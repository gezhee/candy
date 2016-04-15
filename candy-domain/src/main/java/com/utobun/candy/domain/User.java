package com.utobun.candy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @ClassName: User 
* @Description: 用户信息
* @author HemingWu 
* @date 2015年10月4日 下午1:03:23  
*/
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**  @Fields userName : 用户名 */
    @Column(name = "user_name", nullable=false, columnDefinition="VARCHAR(50)")
    private String userName;
    
    /**  @Fields email : 用户邮箱 */
    @Column(name = "email", nullable=false, columnDefinition = "VARCHAR(45)")
    private String email;
    
    /**  @Fields cellphone : 用户电话号码 */
    @Column(name = "cellphone", columnDefinition = "VARCHAR(30)")
    private String cellphone;
    
    /**  @Fields createDate : 创建时间 */
    @Column(name = "create_date", columnDefinition = "timestamp")
    private Date createDate;
    
    /**  @Fields updateDate : 更新时间 */
    @Column(name = "update_date", columnDefinition = "timestamp")
    private Date updateDate;
    
    /** 
     * state:用户状态 
     */
    @Column(name = "state")
    private int state;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    
}
