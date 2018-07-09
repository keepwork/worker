package com.pub.article.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_ARTICLE_CATEGORY的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class ArticleCategoryDTO extends AbstractArticleCategoryDTO
{
	private String parentName;
	
	private String resultCode;
	
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public ArticleCategoryDTO()
	{
		super();
	}

	public ArticleCategoryDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof ArticleCategoryDTO))
			return false;
		ArticleCategoryDTO castOther = (ArticleCategoryDTO) other;
		return new EqualsBuilder().append(this.getCode(), castOther.getCode())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getCode()).toHashCode();
	}

	
}