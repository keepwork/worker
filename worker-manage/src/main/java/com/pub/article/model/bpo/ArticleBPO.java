package com.pub.article.model.bpo;

import java.util.List;
import java.util.Map;
import com.pub.article.model.dao.ArticleCategoryDAO;
import com.pub.article.model.dao.ArticleDAO;
import com.pub.article.model.dto.ArticleCategoryDTO;
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

	/**
	 * 更新缓存
	 */
	@SuppressWarnings("unchecked")
	public void refreshCache()
	{
//		List<ArticleDTO> articleList4 = myArticleDAO.listByCateCode("4",5);//公告
//		cache.set("cache_articleList4", articleList4);

		//公司简介
		ArticleDTO article_101 = myArticleDAO.get("101");
		cache.set("cache_article_101", article_101);

		//联系我们
		ArticleDTO article_102 = myArticleDAO.get("102");
		cache.set("cache_article_102", article_102);

		//小广播通知
		ArticleDTO article_108 = myArticleDAO.get("108");
		cache.set("cache_article_108", article_108);

		//常见问题
//		List<ArticleCategoryDTO> cateList = myArticleCategoryDAO.listByParent("36");
//		String cates = "";
//		for (ArticleCategoryDTO cate : cateList) {
//			cates += "'"+cate.getCode() + "',";
//		}
//		if(cates.length()>0){
//			cates = cates.substring(0, cates.length()-1);
//			List<ArticleDTO> articleList36 = myArticleDAO.listByCateCodes(cates,8);
//			cache.set("cache_articleList36", articleList36);
//		}
//
//		List<ArticleDTO> articleList32 = myArticleDAO.listByCateCode("32",8);//媒体报道
//		List<ArticleDTO> articleList33 = myArticleDAO.listByCateCode("33",8);//捐助单位
//		List<ArticleDTO> articleList34 = myArticleDAO.listByCateCode("34",8);//风险观念
//		List<ArticleDTO> articleList35 = myArticleDAO.listByCateCode("35",8);//保险知识
//		List<ArticleDTO> articleList39 = myArticleDAO.listByCateCode("39",8);//抢购快讯
//
//		cache.set("cache_articleList32", articleList32);
//		cache.set("cache_articleList33", articleList33);
//		cache.set("cache_articleList34", articleList34);
//		cache.set("cache_articleList35", articleList35);
//		cache.set("cache_articleList39", articleList39);
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