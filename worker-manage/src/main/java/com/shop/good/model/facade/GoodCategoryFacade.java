package com.shop.good.model.facade;

import java.util.List;

import com.shop.good.model.bpo.GoodCategoryBPO;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护GoodCategoryDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class GoodCategoryFacade { 
	
	private GoodCategoryBPO myGoodCategoryBPO;

	public boolean save(GoodCategoryDTO dto) throws AppException {
		return myGoodCategoryBPO.saveTX(dto);
	}

	
	public void update(GoodCategoryDTO dto) throws AppException {
		myGoodCategoryBPO.updateTX(dto);
	}

	public void saveOrUpdate(GoodCategoryDTO dto) throws AppException {
		myGoodCategoryBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myGoodCategoryBPO.deleteTX(ids);
	}

	
	public GoodCategoryDTO get(java.lang.String code) throws AppException {
		return myGoodCategoryBPO.get(code);
	}


	public List list(LimitInfo limit) throws AppException {
		return myGoodCategoryBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myGoodCategoryBPO.listByIds(ids);
	}
	
	public List listByParent(String parentCode) throws AppException {
		return myGoodCategoryBPO.listByParent(parentCode);
	}


	public GoodCategoryBPO getMyGoodCategoryBPO() {
		return myGoodCategoryBPO;
	}
	public void setMyGoodCategoryBPO(GoodCategoryBPO myGoodCategoryBPO) {
		this.myGoodCategoryBPO = myGoodCategoryBPO;
	}



}