package com.shop.order.model.dto;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_ORDER的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class OrderDTO extends AbstractOrderDTO
{
	private String menName = "";
	private String menMobile = "";
	private String orderTimeStr = "";
	private String takeTimeStr = "";
	private String sureTimeStr = "";
	private String actualTimeStr = "";
	private String finishTimeStr = "";

	private String payTimeStr = "";
	
	private String resultCode;
	private String workerName = "";
	
	//查询条件
	private String beginTimeStr = "";
	private String endTimeStr = "";
	
	private Date beginTime;
	private Date endTime;
	
	public OrderDTO()
	{
		super();
	}

	public OrderDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof OrderDTO))
			return false;
		OrderDTO castOther = (OrderDTO) other;
		return new EqualsBuilder().append(this.getOrderId(), castOther.getOrderId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getOrderId()).toHashCode();
	}

	public String getOrderTimeStr() {
		return orderTimeStr;
	}

	public void setOrderTimeStr(String orderTimeStr) {
		this.orderTimeStr = orderTimeStr;
	}

	public String getPayTimeStr() {
		return payTimeStr;
	}

	public void setPayTimeStr(String payTimeStr) {
		this.payTimeStr = payTimeStr;
	}

	public String getMenName() {
		return menName;
	}

	public void setMenName(String menName) {
		this.menName = menName;
	}

	public String getMenMobile() {
		return menMobile;
	}

	public void setMenMobile(String menMobile) {
		this.menMobile = menMobile;
	}

	public String getBeginTimeStr() {
		return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		this.beginTimeStr = beginTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getSureTimeStr() {
		return sureTimeStr;
	}

	public void setSureTimeStr(String sureTimeStr) {
		this.sureTimeStr = sureTimeStr;
	}

	public String getTakeTimeStr() {
		return takeTimeStr;
	}

	public void setTakeTimeStr(String takeTimeStr) {
		this.takeTimeStr = takeTimeStr;
	}

	public String getActualTimeStr() {
		return actualTimeStr;
	}

	public void setActualTimeStr(String actualTimeStr) {
		this.actualTimeStr = actualTimeStr;
	}

	public String getFinishTimeStr() {
		return finishTimeStr;
	}

	public void setFinishTimeStr(String finishTimeStr) {
		this.finishTimeStr = finishTimeStr;
	}
}