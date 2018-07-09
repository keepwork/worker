package com.shop.order.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_SHOP_CAR 购物车
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractShopCarDTO extends DtoSupport
{ 
	private String id;
	private String menId;//会员id
	private String openId;//微信ID
	private String goodId;//商品ID
	private String goodName;//商品名称
	private String goodPicture;//商品图片
	private BigDecimal price;//商品单价
	private BigDecimal totalPrice;//商品金额
	private BigDecimal priceShip;//运费
	private Integer amount;//数量
	private Integer point;//商品积分
	private Integer totalPoint;//商品总积分
	private String orderType;//订单类型（1、微信订单；2、网站订单）
	private Date createTime;//加入时间

	
	public AbstractShopCarDTO()
	{
	}
	public AbstractShopCarDTO(java.lang.String id)
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
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getGoodPicture() {
		return goodPicture;
	}
	public void setGoodPicture(String goodPicture) {
		this.goodPicture = goodPicture;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
	public BigDecimal getPriceShip() {
		return priceShip;
	}
	public void setPriceShip(BigDecimal priceShip) {
		this.priceShip = priceShip;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
}