package com.pub.article.model.facade;

import java.util.List;

import com.pub.article.model.bpo.ArticleCategoryBPO;
import com.pub.article.model.dto.ArticleCategoryDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ArticleCategoryDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class ArticleCategoryFacade { 
	
	private ArticleCategoryBPO myArticleCategoryBPO;

	public boolean save(ArticleCategoryDTO dto) throws AppException {
		return myArticleCategoryBPO.saveTX(dto);
	}

	
	public void update(ArticleCategoryDTO dto) throws AppException {
		myArticleCategoryBPO.updateTX(dto);
	}

	public void saveOrUpdate(ArticleCategoryDTO dto) throws AppException {
		myArticleCategoryBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myArticleCategoryBPO.deleteTX(ids);
	}

	
	public ArticleCategoryDTO get(java.lang.String code) throws AppException {
		return myArticleCategoryBPO.get(code);
	}


	public List list(LimitInfo limit) throws AppException {
		return myArticleCategoryBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myArticleCategoryBPO.listByIds(ids);
	}
	
	public List listByParent(String parentCode) throws AppException {
		return myArticleCategoryBPO.listByParent(parentCode);
	}


	public ArticleCategoryBPO getMyArticleCategoryBPO() {
		return myArticleCategoryBPO;
	}
	public void setMyArticleCategoryBPO(ArticleCategoryBPO myArticleCategoryBPO) {
		this.myArticleCategoryBPO = myArticleCategoryBPO;
	}



}