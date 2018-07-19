package com.pub.seo.model.bpo;

import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;
import com.pub.menber.model.dao.MenberDAO;
import com.pub.seo.model.dao.SeoDAO;
import com.pub.seo.model.dto.SeoDTO;

/**
 * 维护SeoDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class SeoBPO extends BpoSupport { 
	
	private static ICache cache = CacheFactory.newCache();
	
	private SeoDAO mySeoDAO;
	
    private MenberDAO myMenberDAO;
    
    
	public void updateTX(SeoDTO dto) throws AppException {
		mySeoDAO.update(dto);
	}
	
	public SeoDTO get(java.lang.String id){
		return mySeoDAO.get(id);
	}
	
	
	/**
	 * 更新缓存
	 */
	public void refreshCache()
	{
    	//同步SEO信息到缓存中
    	SeoDTO m = mySeoDAO.get("1");
    	cache.set("seo_title", m.getTitle());
		cache.set("seo_keywords", m.getKeywords());
		cache.set("seo_description", m.getDescription());
		cache.set("seo_generator", m.getGenerator());
	}


	public SeoDAO getMySeoDAO() {
		return mySeoDAO;
	}
	public void setMySeoDAO(SeoDAO mySeoDAO) {
		this.mySeoDAO = mySeoDAO;
	}
	public MenberDAO getMyMenberDAO() {
		return myMenberDAO;
	}
	public void setMyMenberDAO(MenberDAO myMenberDAO) {
		this.myMenberDAO = myMenberDAO;
	}

}