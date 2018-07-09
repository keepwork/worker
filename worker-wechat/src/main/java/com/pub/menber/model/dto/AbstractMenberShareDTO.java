package com.pub.menber.model.dto;

import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_SHARE 分享记录
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberShareDTO extends DtoSupport
{ 
	private String id;
	private String menId;//分享会员ID
	private String shareMenId;//被分享会员ID
	private Date createTime;//分享时间
    

	public AbstractMenberShareDTO()
	{
	}
	public AbstractMenberShareDTO(java.lang.String id)
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
	public String getShareMenId() {
		return shareMenId;
	}
	public void setShareMenId(String shareMenId) {
		this.shareMenId = shareMenId;
	}
	
}