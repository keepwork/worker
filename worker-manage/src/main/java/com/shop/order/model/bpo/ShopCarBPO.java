package com.shop.order.model.bpo;

import java.util.List;

import com.shop.order.model.dao.ShopCarDAO;
import com.shop.order.model.dto.ShopCarDTO;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ShopCarDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class ShopCarBPO extends BpoSupport { 
	
	private ShopCarDAO myShopCarDAO;

	
	public boolean saveTX(ShopCarDTO dto) {
		myShopCarDAO.save(dto);
		return true;
	}

	
	public void updateTX(ShopCarDTO dto) {
		myShopCarDAO.update(dto);
	}

	
	public void saveOrUpdateTX(ShopCarDTO dto) {
		myShopCarDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ShopCarDTO dto = this.get(id[i]);
			this.myShopCarDAO.delete(dto);

		}
	}
	
	
	public void deleteAllTX(String menId) {
		this.myShopCarDAO.deleteAll(menId);
	}

	
	public ShopCarDTO get(java.lang.String id){
		return myShopCarDAO.get(id);
	}


	public List list(LimitInfo limit) {
		return myShopCarDAO.list(limit);
	}

	
	public List listByIds(String ids) {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myShopCarDAO.listByIds(ids);
	}
	
	public List<ShopCarDTO> listByOpenId(String openId) {
		return myShopCarDAO.listByOpenId(openId);
	}
	
	public List<ShopCarDTO> listByMenId(String menId) {
		return myShopCarDAO.listByMenId(menId);
	}
	
	
	public List<ShopCarDTO> listShopCar(String menId,String goodId){
		return myShopCarDAO.listShopCar(menId,goodId);
	}
	

	public ShopCarDAO getMyShopCarDAO() {
		return myShopCarDAO;
	}
	public void setMyShopCarDAO(ShopCarDAO myShopCarDAO) {
		this.myShopCarDAO = myShopCarDAO;
	}

	

}