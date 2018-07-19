package com.shop.order.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.bms.adm.model.dto.TBmsLocationDTO;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_ORDER 订单
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractOrderDTO extends DtoSupport
{ 
	private String orderId;//订单ID
	private String orderSn;//订单序列号
	private String menId;//会员id
	private String userId;//管理员ID
	private String openId;//微信ID
	private String addrId;//配送地址
	private String invoice;//发票号
	private BigDecimal totalPrice;//订单总金额
	private BigDecimal shippingPrice;//运费
	private Integer totalPoint;//订单总积分
	private String orderStatus;//订单状态：1-待派单，2-已派单，3-已确认时间，4-已上门，5-已完成施工，6-已评价，7-已取消订单
	private String shippingStatus;//配送状态：0-未配送,1-已配送,2-已送达
	private String payStatus;//支付状态：0-未支付,1-已支付
	private String payType;//支付类型：1-余额支付,2-积分支付,3-微信支付
	private String orderType;//订单类型：1-微信订单,2-网页订单
	private String orderDesc = "";//订单描述
	private Integer amount;//数量
	private Date orderTime;//下单时间
	private Date payTime;//支付时间
	
	private String expName;//快递名称
	private String expNumber;//快递单号
	private Date expTime;//快递发货时间
	
	private String workerId;//安装工ID
	private String serviceType;//服务类型：1-预约安装,2-预约维修,3-预约保养,4-预约测量,5-预约咨询
	private String firstCate;//产品分类
	private String firstCateName;//产品分类名称
	private String secondCate;//产品型号
	private String secondCateName;//产品型号名称
	private Date takeTime;//接单时间
	private Date sureTime;//确认上门时间
	private Date endTime;//订单结束时间
	private String desc1;//描述
	private String desc2;//描述
	private String appraiseId;//评价ID
	private Date actualTime;//实际上门时间
	private Date finishTime;//施工完成时间
	private Integer cycleInit;//完成施工周期(天)
	private Integer cycleAdd;//完成施工补充周期(天)

	//所属地区或团队
	private TBmsLocationDTO tbTBmsLocationDTO;

	public AbstractOrderDTO()
	{
	}
	public AbstractOrderDTO(java.lang.String orderId)
	{
		this.setOrderId(orderId);
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getMenId() {
		return menId;
	}
	public void setMenId(String menId) {
		this.menId = menId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}
	public String getAddrId() {
		return addrId;
	}
	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(BigDecimal shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	public Integer getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getExpName() {
		return expName;
	}
	public void setExpName(String expName) {
		this.expName = expName;
	}
	public String getExpNumber() {
		return expNumber;
	}
	public void setExpNumber(String expNumber) {
		this.expNumber = expNumber;
	}
	public Date getExpTime() {
		return expTime;
	}
	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getFirstCate() {
		return firstCate;
	}
	public void setFirstCate(String firstCate) {
		this.firstCate = firstCate;
	}
	public String getSecondCate() {
		return secondCate;
	}
	public void setSecondCate(String secondCate) {
		this.secondCate = secondCate;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getSureTime() {
		return sureTime;
	}
	public void setSureTime(Date sureTime) {
		this.sureTime = sureTime;
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

	public TBmsLocationDTO getTbTBmsLocationDTO() {
		return this.tbTBmsLocationDTO;
	}

	public void setTbTBmsLocationDTO(TBmsLocationDTO tbTBmsLocationDTO) {
		this.tbTBmsLocationDTO = tbTBmsLocationDTO;
	}

	public String getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}

	public Date getActualTime() {
		return actualTime;
	}

	public void setActualTime(Date actualTime) {
		this.actualTime = actualTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getCycleInit() {
		return cycleInit;
	}

	public void setCycleInit(Integer cycleInit) {
		this.cycleInit = cycleInit;
	}

	public Integer getCycleAdd() {
		return cycleAdd;
	}

	public void setCycleAdd(Integer cycleAdd) {
		this.cycleAdd = cycleAdd;
	}
}