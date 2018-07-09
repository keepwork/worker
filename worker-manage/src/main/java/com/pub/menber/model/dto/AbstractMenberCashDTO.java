package com.pub.menber.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_CASH 提现记录
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberCashDTO extends DtoSupport
{ 
	private String id;
	private String menId;//提现会员ID
	private BigDecimal price;//充值金额
	private Date createTime;//积分时间
	private int isDown;//1已申请，2已批准，3已拒绝
	private String comment;//审核说明
	private Date auditTime;//审核时间
	private String auditor;//审核人id
	
	private String bankName;//收款银行名称
	private String bankNum;//收款银行卡号
	private String bankUserName;//收款人姓名
    

	public AbstractMenberCashDTO()
	{
	}
	public AbstractMenberCashDTO(java.lang.String id)
	{
		this.setId(id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenId() {
		return menId;
	}
	public void setMenId(String menId) {
		this.menId = menId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getIsDown() {
		return isDown;
	}
	public void setIsDown(int isDown) {
		this.isDown = isDown;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	
}