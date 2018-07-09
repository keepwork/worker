package com.pub.menber.model.dto;

import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_POINT
 * 
 * 积分政策
 * 邀请人加入送积分50
 * 提醒好友充值送积分1
 * 签到送积分1
 * 点广告送积分10
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberPointDTO extends DtoSupport
{ 
	private String id;
	private String menId;//会员编号
	private Integer point;//本次积分
	private String pointDesc;//本次积分描述
	private Date createTime;//积分时间
    

	public AbstractMenberPointDTO()
	{
	}
	public AbstractMenberPointDTO(java.lang.String id)
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPointDesc() {
		return pointDesc;
	}
	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	
}