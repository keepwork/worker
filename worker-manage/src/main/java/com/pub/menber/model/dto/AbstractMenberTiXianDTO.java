package com.pub.menber.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_TIXIAN 会员提现历史记录
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberTiXianDTO extends DtoSupport
{ 
	private String id;
	private String menId;//会员id
	private BigDecimal total;//提现金额
	private Integer isDown;//是否完成提现（1已完成，2未完成）
	private Date createTime;//提现时间
	
	public AbstractMenberTiXianDTO()
	{
	}
	public AbstractMenberTiXianDTO(java.lang.String id)
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Integer getIsDown() {
		return isDown;
	}
	public void setIsDown(Integer isDown) {
		this.isDown = isDown;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}