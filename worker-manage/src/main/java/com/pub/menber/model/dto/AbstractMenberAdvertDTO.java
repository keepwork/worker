package com.pub.menber.model.dto;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_ADVERT 投诉与建议
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberAdvertDTO extends DtoSupport
{ 
	private String id;
	private String menId;//会员ID
	private String adId;//广告ID
    

	public AbstractMenberAdvertDTO()
	{
	}


	public String getMenId() {
		return menId;
	}


	public void setMenId(String menId) {
		this.menId = menId;
	}


	public String getAdId() {
		return adId;
	}


	public void setAdId(String adId) {
		this.adId = adId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}