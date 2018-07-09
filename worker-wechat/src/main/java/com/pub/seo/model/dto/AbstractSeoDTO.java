package com.pub.seo.model.dto;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_SEO 文章
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractSeoDTO extends DtoSupport
{ 
	private String id;
    
    private String title;
    
    private String keywords;
    
    private String description;
    
    private String generator;
    

	public AbstractSeoDTO()
	{
	}
	public AbstractSeoDTO(java.lang.String id)
	{
		this.setId(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	
}