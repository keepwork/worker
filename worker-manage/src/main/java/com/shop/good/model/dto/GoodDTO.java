package com.shop.good.model.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_GOOD的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class GoodDTO extends AbstractGoodDTO
{
	
	private String catRootName;
    
    private String cateMap;
    
    private String resultCode;
    
    
	public String getCatRootName() {
		return catRootName;
	}

	public void setCatRootName(String catRootName) {
		this.catRootName = catRootName;
	}

	public String getCateMap() {
		return cateMap;
	}

	public void setCateMap(String cateMap) {
		this.cateMap = cateMap;
	}

	public GoodDTO()
	{
		super();
	}

	public GoodDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof GoodDTO))
			return false;
		GoodDTO castOther = (GoodDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}