package com.pub.article.model.dto;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:BMS_ROLE 文章分类
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractArticleCategoryDTO extends DtoSupport
{ 
	private String code;// 分类编号
    
    private String parentCode;// 父类编号
    
    private String name; // 分类名称
    
    private String keyword;// 关键字
    
    private String descr; // 分类描述
    
    private Integer catType; // 分类类型

	private Integer status;// 状态：1可用，0禁用
    
    private Integer orderNum; // 排序
    

	public AbstractArticleCategoryDTO()
	{
	}
	public AbstractArticleCategoryDTO(java.lang.String code)
	{
		this.setCode(code);
	}

	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getCatType() {
		return catType;
	}

	public void setCatType(Integer catType) {
		this.catType = catType;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}