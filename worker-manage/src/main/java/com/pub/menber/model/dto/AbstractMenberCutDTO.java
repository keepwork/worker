package com.pub.menber.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_CUT 扣款记录
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberCutDTO extends DtoSupport
{ 
	private String id;
	private String menId;//扣款会员ID
	private Integer type;//扣款类型（1、保证金扣款；2、功德平摊扣款；3、购物扣款）
	private String helpMenId;//帮助的会员ID（贡献）
	private BigDecimal price;//扣款金额
	private Date createTime;//捐款时间
    

	public AbstractMenberCutDTO()
	{
	}
	public AbstractMenberCutDTO(java.lang.String id)
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getHelpMenId() {
		return helpMenId;
	}
	public void setHelpMenId(String helpMenId) {
		this.helpMenId = helpMenId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}