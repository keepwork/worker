package com.pub.menber.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_CHARGE 充值记录
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberChargeDTO extends DtoSupport
{ 
	private String id;
	private String menId;//充值会员ID
	private BigDecimal price;//充值金额
	private Date createTime;//充值时间
	private String squeues;//已支付返回标识
    

	public AbstractMenberChargeDTO()
	{
	}
	public AbstractMenberChargeDTO(java.lang.String id)
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSqueues() {
		return squeues;
	}
	public void setSqueues(String squeues) {
		this.squeues = squeues;
	}
	
	
}