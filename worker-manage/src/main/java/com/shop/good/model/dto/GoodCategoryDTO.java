package com.shop.good.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_ARTICLE_CATEGORY的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class GoodCategoryDTO extends AbstractGoodCategoryDTO
{
	private String parentName;
	
	private String resultCode;
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public GoodCategoryDTO()
	{
		super();
	}

	public GoodCategoryDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof GoodCategoryDTO))
			return false;
		GoodCategoryDTO castOther = (GoodCategoryDTO) other;
		return new EqualsBuilder().append(this.getCode(), castOther.getCode())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getCode()).toHashCode();
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	
}