package com.shop.order.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_SHOP_CAR的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class ShopCarDTO extends AbstractShopCarDTO
{
	
	public ShopCarDTO()
	{
		super();
		System.out.print("licai");

	}

	public ShopCarDTO(java.lang.String id)
	{
		super(id);
		System.out.print("22");

	}

	public boolean equals(Object other)
	{
		if (!(other instanceof ShopCarDTO)) 
			return false;
		ShopCarDTO castOther = (ShopCarDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}   
	
}