package com.shop.order.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_ORDER_ITEM的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class OrderItemDTO extends AbstractOrderItemDTO
{
	
	public OrderItemDTO()
	{
		super();
	}

	public OrderItemDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof OrderItemDTO))
			return false;
		OrderItemDTO castOther = (OrderItemDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	
}