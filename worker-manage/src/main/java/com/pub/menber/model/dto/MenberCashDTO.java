package com.pub.menber.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_MENBER_CASH的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class MenberCashDTO extends AbstractMenberCashDTO
{
	
	private String createTimeStr;//提现时间（页面显示用）
	private String menberName;//提现人姓名
	private String mobile;//提现人手机号
	private String pid;//提现人身份证
	private double balanceFee;//提现人余额
	private String auditTimeStr;//审核时间（页面显示用）
	
	public MenberCashDTO()
	{
		super();
	}

	public MenberCashDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof MenberCashDTO))
			return false;
		MenberCashDTO castOther = (MenberCashDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getMenberName() {
		return menberName;
	}

	public void setMenberName(String menberName) {
		this.menberName = menberName;
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

	public double getBalanceFee() {
		return balanceFee;
	}

	public void setBalanceFee(double balanceFee) {
		this.balanceFee = balanceFee;
	}

	public String getAuditTimeStr() {
		return auditTimeStr;
	}

	public void setAuditTimeStr(String auditTimeStr) {
		this.auditTimeStr = auditTimeStr;
	}

	
	
	
}