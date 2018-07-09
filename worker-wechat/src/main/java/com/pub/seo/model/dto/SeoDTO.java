package com.pub.seo.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_SEO的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class SeoDTO extends AbstractSeoDTO
{
	
	public SeoDTO()
	{
		super();
	}

	public SeoDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof SeoDTO))
			return false;
		SeoDTO castOther = (SeoDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

}