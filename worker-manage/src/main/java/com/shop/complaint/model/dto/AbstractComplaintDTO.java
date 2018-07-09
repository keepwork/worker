package com.shop.complaint.model.dto;

import java.util.Date;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_COMPLAINT 投诉与建议
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractComplaintDTO extends DtoSupport
{ 
	private String id;
	private String menId;//会员编号
	private String userId;//管理员编号
	private String content;//投诉内容
	private String feedback;//回复内容
	private Integer type;//投诉类型   0-商品 1-服务
	private Integer status;//状态 0-未处理  1-已处理
	private Date compTime;//投诉时间
	private Date handTime;//受理时间(第一次查看时间)
	private Date commitTime;//处理时间
    

	public AbstractComplaintDTO()
	{
	}
	public AbstractComplaintDTO(java.lang.String id)
	{
		this.setId(id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenId() {
		return menId;
	}
	public void setMenId(String menId) {
		this.menId = menId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCompTime() {
		return compTime;
	}
	public void setCompTime(Date compTime) {
		this.compTime = compTime;
	}
	public Date getHandTime() {
		return handTime;
	}
	public void setHandTime(Date handTime) {
		this.handTime = handTime;
	}
	public Date getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	

}