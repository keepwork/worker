package com.pub.article.model.bpo;

import java.util.List;

import com.pub.article.model.dao.ArticleCategoryDAO;
import com.pub.article.model.dto.ArticleCategoryDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ArticleCategoryDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class ArticleCategoryBPO extends BpoSupport { 
	
	private ArticleCategoryDAO myArticleCategoryDAO;

	
	public boolean saveTX(ArticleCategoryDTO dto) throws AppException {
		myArticleCategoryDAO.save(dto);
		return true;
	}

	
	public void updateTX(ArticleCategoryDTO dto) throws AppException {
		myArticleCategoryDAO.update(dto);
	}

	
	public void saveOrUpdateTX(ArticleCategoryDTO dto) throws AppException {
		myArticleCategoryDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ArticleCategoryDTO dto = this.get(id[i]);
			this.myArticleCategoryDAO.delete(dto);

		}
	}

	
	public ArticleCategoryDTO get(java.lang.String code) throws AppException {
		return myArticleCategoryDAO.get(code);
	}


	public List list(LimitInfo limit) throws AppException {
		return myArticleCategoryDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myArticleCategoryDAO.listByIds(ids);
	}
	
	public List listByParent(String parentCode) throws AppException {
		return myArticleCategoryDAO.listByParent(parentCode);
	}

	

	public ArticleCategoryDAO getMyArticleCategoryDAO() {
		return myArticleCategoryDAO;
	}
	public void setMyArticleCategoryDAO(ArticleCategoryDAO myArticleCategoryDAO) {
		this.myArticleCategoryDAO = myArticleCategoryDAO;
	}

	

}