package com.shop.order.model.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
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
	private String sureTimeStr = "";
	private String takeTimeStr = "";
	private String payTimeStr = "";
	private String actualTimeStr = "";
	private String startTimeStr = "";
	private String finishTimeStr = "";
	private String payTime1Str = "";
	private String payTime2Str = "";
	private String payTime3Str = "";

	private String resultCode;
	private String workerName = "";

	//查询条件
	private String beginTimeStr = "";
	private String endTimeStr = "";

	private Date beginTime;
	private Date endTime;

	private String locationName = "";//所属地区或团队

	private String projectProgress = "";//项目进度

	private String province = "";//省份
	private String city = "";//城市
	private String team = "";//队
	private String userLevel = "";//用户等级
	private BigDecimal sumTotalPrice;//总订单金额
	private BigDecimal sumCost;//总成本
	private BigDecimal sumProfit;//总成本
	private BigDecimal historyOutputValue;//历史产值
	private BigInteger totalOrderNum;//总订单数
	private BigDecimal monthOutputValue;//本月产值
	private BigInteger monthOrderNum;//本月订单数
	private String month;//月份


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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getProjectProgress() {
		return projectProgress;
	}

	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
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

	public String getPayTime1Str() {
		return payTime1Str;
	}

	public void setPayTime1Str(String payTime1Str) {
		this.payTime1Str = payTime1Str;
	}

	public String getPayTime2Str() {
		return payTime2Str;
	}

	public void setPayTime2Str(String payTime2Str) {
		this.payTime2Str = payTime2Str;
	}

	public String getPayTime3Str() {
		return payTime3Str;
	}

	public void setPayTime3Str(String payTime3Str) {
		this.payTime3Str = payTime3Str;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public BigDecimal getSumTotalPrice() {
		return sumTotalPrice;
	}

	public void setSumTotalPrice(BigDecimal sumTotalPrice) {
		this.sumTotalPrice = sumTotalPrice;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

	public BigDecimal getSumProfit() {
		return sumProfit;
	}

	public void setSumProfit(BigDecimal sumProfit) {
		this.sumProfit = sumProfit;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public BigDecimal getHistoryOutputValue() {
		return historyOutputValue;
	}

	public void setHistoryOutputValue(BigDecimal historyOutputValue) {
		this.historyOutputValue = historyOutputValue;
	}

	public BigDecimal getMonthOutputValue() {
		return monthOutputValue;
	}

	public void setMonthOutputValue(BigDecimal monthOutputValue) {
		this.monthOutputValue = monthOutputValue;
	}

	public BigInteger getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(BigInteger totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public BigInteger getMonthOrderNum() {
		return monthOrderNum;
	}

	public void setMonthOrderNum(BigInteger monthOrderNum) {
		this.monthOrderNum = monthOrderNum;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}