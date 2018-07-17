package com.shop.appraise.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_MENBER_APPRAISE的映射类，可在本类中过客户化操作
 *
 * @author BruceKobe(javalc@163.com)
 * @date Dec 28, 2018 12:39:58 PM
 */
public class AppraiseDTO extends AbstractAppraiseDTO
{
	private String createTimeStr;//评价时间字符串

	public AppraiseDTO()
	{
		super();
	}

	public AppraiseDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof AppraiseDTO))
			return false;
		AppraiseDTO castOther = (AppraiseDTO) other;
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
}