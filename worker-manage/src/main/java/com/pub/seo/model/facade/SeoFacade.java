package com.pub.seo.model.facade;

import com.sinovatech.common.exception.AppException;
import com.pub.seo.model.bpo.SeoBPO;
import com.pub.seo.model.dto.SeoDTO;

/**
 * 维护SeoDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class SeoFacade { 
	
	private SeoBPO mySeoBPO;
	
	public void update(SeoDTO dto) throws AppException {
		mySeoBPO.updateTX(dto);
	}
	
	public SeoDTO get(java.lang.String id){
		return mySeoBPO.get(id);
	}

	public SeoBPO getMySeoBPO() {
		return mySeoBPO;
	}
	public void setMySeoBPO(SeoBPO mySeoBPO) {
		this.mySeoBPO = mySeoBPO;
	}



}