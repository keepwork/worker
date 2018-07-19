package com.pub.advert.model.dto;

import java.util.Date;
import com.sinovatech.common.model.dto.DtoSupport;

/**
 * 实体对象，请勿做客户化操作， 对应表:PUB_ADVERT 广告
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:39:58 PM
 */
public abstract class AbstractAdvertDTO extends DtoSupport
{ 
	private String id;// 文章新闻编号
    private String title;// 广告标题（只有文字广告用）
    private String pic;// 广告图片（只有图片广告用）
    private Integer cate;// 广告种类（1、联盟广告；2、图片广告；3、文字广告；）
    private Integer type;// 广告类型（wap广告;web广告;）
    private Integer count;// 广告点击次数
    private Integer point;// 广告每次点击可得积分
    private Integer orderNum;// 排序
    private Date createTime;// 创建时间
    private String content;// 广告内容（联盟广告存放脚本，图片文字广告存放备注）
    private String url;// 广告链接
    

	public AbstractAdvertDTO()
	{
	}
	public AbstractAdvertDTO(java.lang.String id)
	{
		this.setId(id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCate() {
		return cate;
	}
	public void setCate(Integer cate) {
		this.cate = cate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}