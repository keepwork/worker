package com.shop.good.model.bpo;

import java.util.List;

import com.shop.good.model.dao.GoodCategoryDAO;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护GoodCategoryDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class GoodCategoryBPO extends BpoSupport { 
	
	private GoodCategoryDAO myGoodCategoryDAO;

	
	public boolean saveTX(GoodCategoryDTO dto) throws AppException {
		myGoodCategoryDAO.save(dto);
		return true;
	}

	
	public void updateTX(GoodCategoryDTO dto) throws AppException {
		myGoodCategoryDAO.update(dto);
	}

	
	public void saveOrUpdateTX(GoodCategoryDTO dto) throws AppException {
		myGoodCategoryDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			GoodCategoryDTO dto = this.get(id[i]);
			this.myGoodCategoryDAO.delete(dto);

		}
	}

	
	public GoodCategoryDTO get(java.lang.String code){
		return myGoodCategoryDAO.get(code);
	}


	public List list(LimitInfo limit) throws AppException {
		return myGoodCategoryDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myGoodCategoryDAO.listByIds(ids);
	}
	
	public List listByParent(String parentCode){
		return myGoodCategoryDAO.listByParent(parentCode);
	}

	

	public GoodCategoryDAO getMyGoodCategoryDAO() {
		return myGoodCategoryDAO;
	}
	public void setMyGoodCategoryDAO(GoodCategoryDAO myGoodCategoryDAO) {
		this.myGoodCategoryDAO = myGoodCategoryDAO;
	}

	

}