package com.utobun.candy.contants;

public enum ReturnCode
{
    /** SESSION 失效 */
    SESSION_TIME_OUT("20018", "SESSION 失效"),

    /** 用户名或密码有误 */
    USERNAME_OR_PASSWORD_ERROR("20010", "用户名或密码有误"),

    /** 注册失败 */
    REGISTRATION_FAILED("040901", "注册失败"),

    /** 未登录 */
    NO_LOGIN("040902", "未登录"),
    /**验证码错误*/
    VERIFY_CODE("500001", "验证码错误"),

    /** 成功 */
    SUCCESS("0", "成功"),

    /** 系统繁忙，请稍候再试 */
    RESTRICT_SYSTEM_BUSY("10000", "系统繁忙，请稍候再试"),
    
    /** 接口不存在 */
    RESTRICT_API_NOT_EXIST("10001", "接口不存在"),

    /** 接口版本已过期 */
    RESTRICT_API_DEPRECATED("10002", "接口版本已过期"),
    
    /** 参数错误 */
    PARAM("20000", "参数错误"),
    
    /** 
     * SYSTEM_WRONG:系统错误.  
     */
    SYSTEM_WRONG("10003", "系统错误");

    private String code; // 编码
    private String desc; // 描述

    private ReturnCode(String code, String desc)
    {
	this.code = code;
	this.desc = desc;
    }

    public String getCode()
    {
	return code;
    }

    public String getDesc()
    {
	return desc;
    }

    @Override
    public String toString()
    {
	return "code: " + code + ", desc: " + desc;
    }
}
