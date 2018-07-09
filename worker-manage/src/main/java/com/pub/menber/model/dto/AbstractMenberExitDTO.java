package com.pub.menber.model.dto;

import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_EXIT 退出计划
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberExitDTO extends DtoSupport
{ 
	private String id;
	private String menId;//退出会员ID
	private String bankUserName;//收款人姓名
	private String mobile;//收款人电话
	private String bankName;//收款银行名称
	private String bankNum;//收款银行卡号
	private String content;//退出原因
	private Date createTime;//退出申请时间
	
	private Date auditTime;//审核时间
	private String auditor;//审核人
	private Integer auditResult;//审核结果（1申请中，2已批准，3已拒绝）
	private String auditDesc;//审核说明
	
    

	public AbstractMenberExitDTO()
	{
	}
	public AbstractMenberExitDTO(java.lang.String id)
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
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public Integer getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}
	
	
}