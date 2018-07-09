package com.pub.menber.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_MENBER_EXIT的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class MenberExitDTO extends AbstractMenberExitDTO
{
	
	private String createTimeStr;//申请时间（页面显示用）
	private String auditTimeStr;//审核时间（页面显示用）
	private String menRealName;//会员真实姓名
	private String menPid;//会员身份证号
	
	public MenberExitDTO()
	{
		super();
	}

	public MenberExitDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof MenberExitDTO))
			return false;
		MenberExitDTO castOther = (MenberExitDTO) other;
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

	public String getAuditTimeStr() {
		return auditTimeStr;
	}

	public void setAuditTimeStr(String auditTimeStr) {
		this.auditTimeStr = auditTimeStr;
	}

	public String getMenRealName() {
		return menRealName;
	}

	public void setMenRealName(String menRealName) {
		this.menRealName = menRealName;
	}

	public String getMenPid() {
		return menPid;
	}

	public void setMenPid(String menPid) {
		this.menPid = menPid;
	}

	
}