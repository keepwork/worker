package com.shop.good.model.bpo;

import java.util.List;

import com.shop.good.model.dao.GoodDAO;
import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.GoodPicDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护GoodDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class GoodBPO extends BpoSupport { 
	
	private GoodDAO myGoodDAO;

	
	public boolean saveTX(GoodDTO dto) {
		myGoodDAO.save(dto);
		return true;
	}

	
	public void updateTX(GoodDTO dto) {
		myGoodDAO.update(dto);
	}

	
	public void saveOrUpdateTX(GoodDTO dto) {
		myGoodDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			GoodDTO dto = this.get(id[i]);
			this.myGoodDAO.delete(dto);

		}
	}

	
	public GoodDTO get(java.lang.String id) {
		return myGoodDAO.get(id);
	}


	public List list(LimitInfo limit) {
		return myGoodDAO.list(limit);
	}

	
	public List listByIds(String ids) {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myGoodDAO.listByIds(ids);
	}
	
	public List listByCateCode(String cateCode) {
		return myGoodDAO.listByCateCode(cateCode);
	}

	

	@SuppressWarnings("unchecked")
	public List listGoodImages(String goodId) {
		return myGoodDAO.listGoodImages(goodId);
	}
	@SuppressWarnings("unchecked")
    public void pictureMltUploadTX(String fileName,String goodId) throws Exception{
    		try {
    			GoodPicDTO dto = new GoodPicDTO();
    			dto.setPic(fileName);
    			dto.setOrderNum("1");
    			dto.setGoodId(goodId);
    			myGoodDAO.saveGoodImage(dto);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	}
	@SuppressWarnings("unchecked")
	public void deleteProductImgTX(String id) throws AppException {
		this.myGoodDAO.deleteProductImg(id);
	}
	
	
	public GoodDAO getMyGoodDAO() {
		return myGoodDAO;
	}
	public void setMyGoodDAO(GoodDAO myGoodDAO) {
		this.myGoodDAO = myGoodDAO;
	}

	

}