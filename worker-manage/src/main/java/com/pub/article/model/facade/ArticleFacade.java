package com.pub.article.model.facade;

import java.util.List;
import java.util.Map;
import com.pub.article.model.bpo.ArticleBPO;
import com.pub.article.model.dto.ArticleDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ArticleDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class ArticleFacade { 
	
	private ArticleBPO myArticleBPO;

	public boolean save(ArticleDTO dto) throws AppException {
		return myArticleBPO.saveTX(dto);
	}

	
	public void update(ArticleDTO dto) throws AppException {
		myArticleBPO.updateTX(dto);
	}

	public void saveOrUpdate(ArticleDTO dto) throws AppException {
		myArticleBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myArticleBPO.deleteTX(ids);
	}

	
	public ArticleDTO get(java.lang.String id) throws AppException {
		return myArticleBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myArticleBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myArticleBPO.listByIds(ids);
	}
	
	public List listByCateCode(String cateCode) throws AppException {
		return myArticleBPO.listByCateCode(cateCode);
	}
	
	public List listByCateCode(String cateCode,int num) throws AppException {
		return myArticleBPO.listByCateCode(cateCode,num);
	}
	
	public List listByCateCodes(String cateCodes,int num){
		return myArticleBPO.listByCateCodes(cateCodes,num);
	}

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myArticleBPO.listForPagination(params);
    }

	/**
	 * 更新缓存
	 */
	public void refreshCache()
	{
		myArticleBPO.refreshCache();
	}

	public ArticleBPO getMyArticleBPO() {
		return myArticleBPO;
	}
	public void setMyArticleBPO(ArticleBPO myArticleBPO) {
		this.myArticleBPO = myArticleBPO;
	}



}