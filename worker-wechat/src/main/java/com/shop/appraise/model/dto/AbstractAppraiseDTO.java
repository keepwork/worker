package com.shop.appraise.model.dto;

import java.util.Date;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_MENBER_APPRAISE 评价表
 *
 * @author BruceKobe(javalc@163.com)
 * @date Dec 28, 2018 12:39:58 PM
 */
public abstract class AbstractAppraiseDTO extends DtoSupport
{ 
	private String id;
	private String orderId;//订单ID
	private String menId;//会员编号
	private String menName;//客户名称
	private String workerId;//师傅ID
	private String workerName;//师傅名称
	private String scoreZhunShi;//分数（准时）
	private String scoreTaiDu;//分数（态度）
	private String scoreZhiLiang;//分数（质量）
	private String isShow;//是否显示（1显示，0不显示）
	private String content;//评价内容
	private String cFrom;//评价来源（1微信，2网页，3其它）
	private Date createTime;//评价时间
    

	public AbstractAppraiseDTO()
	{
	}
	public AbstractAppraiseDTO(java.lang.String id)
	{
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMenId() {
		return menId;
	}

	public void setMenId(String menId) {
		this.menId = menId;
	}

	public String getMenName() {
		return menName;
	}

	public void setMenName(String menName) {
		this.menName = menName;
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

	public String getScoreZhunShi() {
		return scoreZhunShi;
	}

	public void setScoreZhunShi(String scoreZhunShi) {
		this.scoreZhunShi = scoreZhunShi;
	}

	public String getScoreTaiDu() {
		return scoreTaiDu;
	}

	public void setScoreTaiDu(String scoreTaiDu) {
		this.scoreTaiDu = scoreTaiDu;
	}

	public String getScoreZhiLiang() {
		return scoreZhiLiang;
	}

	public void setScoreZhiLiang(String scoreZhiLiang) {
		this.scoreZhiLiang = scoreZhiLiang;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getcFrom() {
		return cFrom;
	}

	public void setcFrom(String cFrom) {
		this.cFrom = cFrom;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}