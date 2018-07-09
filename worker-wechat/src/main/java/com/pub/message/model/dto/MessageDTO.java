package com.pub.message.model.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_ARTICLE的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class MessageDTO extends AbstractMessageDTO implements Serializable
{
	
	private String catName;// 类别名称
    
    private String updateTimeStr;//修改时间
    
    private String resultCode;
    
    
	public MessageDTO()
	{
		super();
	}

	public MessageDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof MessageDTO))
			return false;
		MessageDTO castOther = (MessageDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

    public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	
	

}