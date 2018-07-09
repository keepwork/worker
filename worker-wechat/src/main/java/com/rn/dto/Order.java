package com.rn.dto;

/**
 * 安装工订单
 *
 * @author kevin(keepwork512@163.com)
 * @date Jul 25, 2016 10:15:14 AM
 */
public class Order
{
	// 主键id
	private String orderId = "";
	// 订单编号
	private String orderSn = "";
	// 下单时间
	private String orderTime = "";
	// 接单时间
	private String orderTakeTime = "";
	// 订单描述
	private String orderDesc = "";
	// 订单状态：1-进行中,2-已完成,-3-已关闭
	private String orderStatus = "";
	// 支付状态：0-未支付,1-已支付
	private String payStatus = "";
	// 订单总金额
	private String orderPrice = "";
	// 确认上门时间
	private String sureTime = "";
	// 订单结束时间
	private String endTime = "";
	
	// 安装工ID
	private String workerId = "";
	// 安装工姓名
	private String workerName = "";
	// 安装工手机
	private String workerMobile = "";
	// 安装工住址
	private String workerAddr = "";
	
	// 服务类型：1-预约安装,2-预约维修,3-预约保养,4-预约测量,5-预约咨询
	private String serviceType = "";
	// 服务类型图标
	private String serviceImage = "";
	// 产品一级分类
	private String firstCateName = "";
	// 产品二级类型
	private String secondCateName = "";
	
	// 错误码，0000表示正常，1111表示无数据，9999表示系统异常
	private String resultCode = "";
	
	// 备注1
	private String desc1 = "";
	
	// 备注2
	private String desc2 = "";

	

	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderTakeTime() {
		return orderTakeTime;
	}

	public void setOrderTakeTime(String orderTakeTime) {
		this.orderTakeTime = orderTakeTime;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerMobile() {
		return workerMobile;
	}

	public void setWorkerMobile(String workerMobile) {
		this.workerMobile = workerMobile;
	}

	public String getWorkerAddr() {
		return workerAddr;
	}

	public void setWorkerAddr(String workerAddr) {
		this.workerAddr = workerAddr;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public String getFirstCateName() {
		return firstCateName;
	}

	public void setFirstCateName(String firstCateName) {
		this.firstCateName = firstCateName;
	}

	public String getSecondCateName() {
		return secondCateName;
	}

	public void setSecondCateName(String secondCateName) {
		this.secondCateName = secondCateName;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSureTime() {
		return sureTime;
	}

	public void setSureTime(String sureTime) {
		this.sureTime = sureTime;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	
	
}