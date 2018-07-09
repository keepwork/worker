package com.shop.complaint.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_COMPLAINT的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class ComplaintDTO extends AbstractComplaintDTO
{
	
	private String menName;//投诉人
	private String userName;//处理人
	private String compTimeStr;//投诉时间
	private String handTimeStr;//受理时间(第一次查看时间)
	private String commitTimeStr;//处理时间

	public ComplaintDTO()
	{
		super();
	}

	public ComplaintDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof ComplaintDTO))
			return false;
		ComplaintDTO castOther = (ComplaintDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public String getMenName() {
		return menName;
	}

	public void setMenName(String menName) {
		this.menName = menName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompTimeStr() {
		return compTimeStr;
	}

	public void setCompTimeStr(String compTimeStr) {
		this.compTimeStr = compTimeStr;
	}

	public String getHandTimeStr() {
		return handTimeStr;
	}

	public void setHandTimeStr(String handTimeStr) {
		this.handTimeStr = handTimeStr;
	}

	public String getCommitTimeStr() {
		return commitTimeStr;
	}

	public void setCommitTimeStr(String commitTimeStr) {
		this.commitTimeStr = commitTimeStr;
	}

	

	
}