package com.pub.menber.model.dto;

import com.sinovatech.bms.adm.model.dto.TBmsLocationDTO;
import com.sinovatech.common.util.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 表PUB_MENBER的映射类，可在本类中过客户化操作
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:41:58 PM
 */
public class MenberDTO extends AbstractMenberDTO
{

	private String regTimeStr;//注册时间
	private String lastTimeStr;//最后一次登录时间
	private String joinTimeStr;//工人申请时间
	private String tixian = "";//是否有提现申请页面显示用
	private String locationName = "";//所属地区或团队
	
	private long days = 0;//加入时长（天）
	
	private String resultCode = "";



	
	public MenberDTO()
	{
		super();
	}

	public MenberDTO(java.lang.String id)
	{
		super(id);
	}

	public boolean equals(Object other)
	{
		if (!(other instanceof MenberDTO))
			return false;
		MenberDTO castOther = (MenberDTO) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId())
				.isEquals();
	}

	public int hashCode()
	{
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public String getRegTimeStr() {
		return regTimeStr;
	}

	public void setRegTimeStr(String regTimeStr) {
		this.regTimeStr = regTimeStr;
	}

	public String getLastTimeStr() {
		return lastTimeStr;
	}

	public void setLastTimeStr(String lastTimeStr) {
		this.lastTimeStr = lastTimeStr;
	}

	public String getTixian() {
		return tixian;
	}

	public void setTixian(String tixian) {
		this.tixian = tixian;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getJoinTimeStr() {
		return joinTimeStr;
	}

	public void setJoinTimeStr(String joinTimeStr) {
		this.joinTimeStr = joinTimeStr;
	}

	/**
	 * <p>
	 * <ul>
	 * <li>设置区域编号，供ActionForm使用</li>
	 * </ul>
	 * </p>
	 *
	 * @param id
	 */
	public void setLocationid(String id)
	{
		if (!StringUtils.isBlank(id))
		{
			this.setTbTBmsLocationDTO(new TBmsLocationDTO(id));
		}
	}
}