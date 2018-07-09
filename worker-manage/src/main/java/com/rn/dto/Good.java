package com.rn.dto;

import java.util.List;

/**
 * 配件
 *
 * @author kevin(keepwork512@163.com)
 * @date Jul 25, 2016 9:59:16 AM
 */
public class Good
{
	// 主键id
	private String id = "";
	// 配件名称
	private String name = "";
	// 配件型号
	private String code = "";
	// 配件图片
//	private String image = "";
	// 配件价格
	private String price = "";
	// 配件分类编号
	private String catCode = "";
	// 配件分类名称
    private String catName = "";
    // 状态：0-上架1-下架2-缺货
	private String status = "";
	// 错误码，0000表示正常，1111表示无数据，9999表示系统异常
    private String resultCode = "";
    
    //图片列表
    private List<GoodPic> picList = null;

    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List getPicList() {
		return picList;
	}

	public void setPicList(List picList) {
		this.picList = picList;
	}
	

}