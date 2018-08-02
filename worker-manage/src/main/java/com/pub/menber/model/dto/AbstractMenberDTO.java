package com.pub.menber.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinovatech.bms.adm.model.dto.TBmsLocationDTO;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER 会员
 *
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractMenberDTO extends DtoSupport
{
	private String id;//会员ID
	private String salesMenId;//推荐人id
	private String familyMenId;//托管人id
	private String loginName;//登录名
	private String password;//密码
	private String email;//电子邮箱
	private String realName;//真实姓名
	private String areaCode;//服务区域
	private String serviceType;//服务类型：1-安装,2-维修,3-保养,4-测量,5-咨询
	private String postCode;//邮编
	private String tel;//座机
	private String fax;//传真
	private String mobile;//手机
	private String detailAddr;//技能描述
	private String pid;//身份证
	private Integer point;//会员积分
	private Integer type;//1-微信客户，2-安装工，3-工人申请
	private Integer status;//0-正常（已关注），1-停用（未关注）
	private Integer isjoin;//是否加入了计划(0-未加入，1-已加入但未过等待期，2-已过等待期)
	private Integer remind;//是否需要提醒充值(0-余额不足（账户小于10元需要提醒），1-余额充足（不需要提醒）)
	private BigDecimal balanceFee;//可提现余额
	private BigDecimal rightFee;//权利金
	private BigDecimal contributeFee;//贡献金
	private Date regTime;//注册时间
	private Date joinTime;//加入计划时间（工人申请时间）
	private Date effectTime;//计划生效时间（工人申请通过时间）
	private Date lastTime;//最后一次登录时间
	private Integer sign;//用于保存推荐人是否已经奖励过积分（1已奖励，0未奖励）
	private String realNameEmergency;//紧急联系人姓名
	private String mobileEmergency;//紧急联系人电话
	private String workYears;//工龄
	private String workType;//工种
	//微信资料
	private String openId;//微信用户id
	private String sex;//微信性别
	private String city;//微信用户所在城市
	private String headimgurl;//微信用户头像

	//所属地区或团队
	private TBmsLocationDTO tbTBmsLocationDTO;


	public AbstractMenberDTO()
	{
	}
	public AbstractMenberDTO(java.lang.String id)
	{
		this.setId(id);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalesMenId() {
		return salesMenId;
	}
	public void setSalesMenId(String salesMenId) {
		this.salesMenId = salesMenId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getBalanceFee() {
		return balanceFee;
	}
	public void setBalanceFee(BigDecimal balanceFee) {
		this.balanceFee = balanceFee;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Integer getIsjoin() {
		return isjoin;
	}
	public void setIsjoin(Integer isjoin) {
		this.isjoin = isjoin;
	}
	public BigDecimal getRightFee() {
		return rightFee;
	}
	public void setRightFee(BigDecimal rightFee) {
		this.rightFee = rightFee;
	}
	public BigDecimal getContributeFee() {
		return contributeFee;
	}
	public void setContributeFee(BigDecimal contributeFee) {
		this.contributeFee = contributeFee;
	}
	public String getRealNameEmergency() {
		return realNameEmergency;
	}
	public void setRealNameEmergency(String realNameEmergency) {
		this.realNameEmergency = realNameEmergency;
	}
	public String getMobileEmergency() {
		return mobileEmergency;
	}
	public void setMobileEmergency(String mobileEmergency) {
		this.mobileEmergency = mobileEmergency;
	}
	public Integer getRemind() {
		return remind;
	}
	public void setRemind(Integer remind) {
		this.remind = remind;
	}
	public String getFamilyMenId() {
		return familyMenId;
	}
	public void setFamilyMenId(String familyMenId) {
		this.familyMenId = familyMenId;
	}
	public Date getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getWorkYears() {
		return workYears;
	}

	public void setWorkYears(String workYears) {
		this.workYears = workYears;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public TBmsLocationDTO getTbTBmsLocationDTO() {
		return this.tbTBmsLocationDTO;
	}

	public void setTbTBmsLocationDTO(TBmsLocationDTO tbTBmsLocationDTO) {
		this.tbTBmsLocationDTO = tbTBmsLocationDTO;
	}
}