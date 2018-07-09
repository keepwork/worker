package com.pub.message.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_ARTICLE_CATEGORY的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class MessageCategoryDTO extends AbstractMessageCategoryDTO
{
	private String parentName;
	
	private String resultCode;
	
	private long messageCount;//消息数量
	
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public long getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(long messageCount) {
		this.messageCount = messageCount;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public MessageCategoryDTO()
	{
		super();
	}

	public MessageCategoryDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof MessageCategoryDTO))
			return false;
		MessageCategoryDTO castOther = (MessageCategoryDTO) other;
		return new EqualsBuilder().append(this.getCode(), castOther.getCode())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getCode()).toHashCode();
	}

	
}