package com.pub.article.model.bpo;

import java.util.List;
import java.util.Map;
import com.pub.article.model.dao.ArticleCategoryDAO;
import com.pub.article.model.dao.ArticleDAO;
import com.pub.article.model.dto.ArticleDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 维护ArticleDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class ArticleBPO extends BpoSupport { 
	
	private static ICache cache = CacheFactory.newCache();
	
	private ArticleDAO myArticleDAO;
	private ArticleCategoryDAO myArticleCategoryDAO;

	
	public boolean saveTX(ArticleDTO dto) throws AppException {
		myArticleDAO.save(dto);
		return true;
	}

	
	public void updateTX(ArticleDTO dto) throws AppException {
		myArticleDAO.update(dto);
	}

	
	public void saveOrUpdateTX(ArticleDTO dto) throws AppException {
		myArticleDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ArticleDTO dto = this.get(id[i]);
			this.myArticleDAO.delete(dto);

		}
	}

	
	public ArticleDTO get(java.lang.String id) throws AppException {
		return myArticleDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myArticleDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myArticleDAO.listByIds(ids);
	}
	
	public List listByCateCode(String cateCode) throws AppException {
		return myArticleDAO.listByCateCode(cateCode);
	}
	
	public List listByCateCode(String cateCode,int num) throws AppException {
		return myArticleDAO.listByCateCode(cateCode,num);
	}
	
	public List listByCateCodes(String cateCodes,int num){
		return myArticleDAO.listByCateCodes(cateCodes,num);
	}
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myArticleDAO.listForPagination(params);
    }


	public ArticleDAO getMyArticleDAO() {
		return myArticleDAO;
	}
	public void setMyArticleDAO(ArticleDAO myArticleDAO) {
		this.myArticleDAO = myArticleDAO;
	}
	public ArticleCategoryDAO getMyArticleCategoryDAO() {
		return myArticleCategoryDAO;
	}
	public void setMyArticleCategoryDAO(ArticleCategoryDAO myArticleCategoryDAO) {
		this.myArticleCategoryDAO = myArticleCategoryDAO;
	}

	

}