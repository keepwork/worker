package com.shop.good.model.dto;

import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_GOOD_PIC 商品图片关联
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractGoodPicDTO extends DtoSupport
{ 
	private String id;
    private String goodId;//商品id
    private String pic;//商品图片
    private String orderNum;//排序
   
    
    public AbstractGoodPicDTO(String id2) {
		// TODO Auto-generated constructor stub
	}
	public AbstractGoodPicDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}