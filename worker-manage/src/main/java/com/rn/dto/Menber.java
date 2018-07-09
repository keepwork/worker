package com.rn.dto;

/**
 * 会员（微信会员、安装工）
 *
 * @author kevin(keepwork512@163.com)
 * @date Jul 25, 2016 12:30:22 PM
 */
public class Menber 
{
	// 主键id
	private String id = "";
	// 登录名
	private String loginName = "";
	// 电子邮箱
	private String email = "";
	// 真实姓名
	private String realName = "";
	// 服务区域
	private String areaCode = "";
	// 手机
	private String mobile = "";
	// 身份证
	private String pid = "";
	// 会员积分
	private String point = "";
	// 1-微信客户，2-安装工
	private String type = "";
	// 0-正常，1-停用
	private String status = "";
	// 可提现余额
	private String balanceFee = "";
	// 注册时间
	private String regTime = "";
	// 服务类型
	private String serviceType = "";
	
	// 错误码，0000表示正常，1111表示无数据，9999表示系统异常
	private String resultCode = "";
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBalanceFee() {
		return balanceFee;
	}

	public void setBalanceFee(String balanceFee) {
		this.balanceFee = balanceFee;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	
	
}