package com.pub.article.model.dto;

import java.util.Date;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_ARTICLE 文章
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractArticleDTO extends DtoSupport
{ 
	private String id;// 文章新闻编号
    
    private String catCode;// 类别编号
    
    private String title;// 标题
    
    private String pic;// 图片
    
    private Integer orderNum;// 排序
    
    private Date updateTime;// 更新时间
    
    private String content;// 内容
    

	public AbstractArticleDTO()
	{
	}
	public AbstractArticleDTO(java.lang.String id)
	{
		this.setId(id);
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}


}