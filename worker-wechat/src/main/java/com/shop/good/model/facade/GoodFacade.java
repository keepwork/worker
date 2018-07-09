package com.shop.good.model.facade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shop.good.model.bpo.GoodBPO;
import com.shop.good.model.bpo.GoodCategoryBPO;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.MyTree;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护GoodDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class GoodFacade { 
	
	private GoodBPO myGoodBPO;
	
	private GoodCategoryBPO myGoodCategoryBPO;

	public boolean save(GoodDTO dto){
		return myGoodBPO.saveTX(dto);
	}

	
	public void update(GoodDTO dto){
		myGoodBPO.updateTX(dto);
	}

	public void saveOrUpdate(GoodDTO dto){
		myGoodBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids){
		myGoodBPO.deleteTX(ids);
	}

	
	public GoodDTO get(java.lang.String id) {
		return myGoodBPO.get(id);
	}


	public List list(LimitInfo limit){
		return myGoodBPO.list(limit);
	}

	
	public List listByIds(String ids){
		return myGoodBPO.listByIds(ids);
	}
	
	public List listByCateCode(String cateCode){
		return myGoodBPO.listByCateCode(cateCode);
	}


	public GoodBPO getMyGoodBPO() {
		return myGoodBPO;
	}
	public void setMyGoodBPO(GoodBPO myGoodBPO) {
		this.myGoodBPO = myGoodBPO;
	}
	public GoodCategoryBPO getMyGoodCategoryBPO() {
		return myGoodCategoryBPO;
	}
	public void setMyGoodCategoryBPO(GoodCategoryBPO myGoodCategoryBPO) {
		this.myGoodCategoryBPO = myGoodCategoryBPO;
	}


	// 构建商品类型树
    public MyTree getComponentTree()
    {
        return this.getComponentPhysicalTree(true);
    }
    
    public MyTree getComponentPhysicalTree(boolean box)
    {
    	List<GoodCategoryDTO> cateList = myGoodCategoryBPO.listByParent("");
        MyTree tree = new MyTree("0", "-1", "商品分类", null, null, "leftFrame", null, null, false, null, 0, null);
        for (int a = 0; a < cateList.size(); a++)
        {
        	GoodCategoryDTO cate = (GoodCategoryDTO)cateList.get(a);
            buildTree(cate, tree, box);
        }
        return tree;
    }
    
    public void buildTree(GoodCategoryDTO cate, MyTree tree, boolean box)
    {
        MyTree tr1 = new MyTree();
        tr1.setId(cate.getCode().toString());
        tr1.setPid("0");
        tr1.setName(cate.getName());
        tr1.setType("");
        tr1.setUrl("");
        tr1.setTarget("leftFrame");
        // if (emm.getStatus() != 0)//状态控制变颜色
        // tr1.setStatus1((int) emm.getStatus());
        tree.getTrees().add(tr1);
        
        List cateList2 = myGoodCategoryBPO.listByParent(cate.getCode().toString());
        for (int d = 0; d < cateList2.size(); d++)
        {
            MyTree tr2 = new MyTree();
            GoodCategoryDTO cate2 = (GoodCategoryDTO)cateList2.get(d);
            tr2.setId(cate2.getCode().toString());
            tr2.setPid(cate.getCode().toString());
            tr2.setName(cate2.getName());
            tr2.setType("");
            tr2.setUrl("");
            tr2.setTarget("leftFrame");
            tr2.setStatus1(Integer.parseInt("1"));
            if (box)
            {
                tr2.setIdentity("cateMap");
                tr1.getTrees().add(tr2);
            }
            else
            {
                tr1.getTrees().add(tr2);
            }
        }
        if (tr1.getTrees().size() == 0)
        { // 如果下面没有组件就不显示出来
            tree.getTrees().remove(tr1);
        }
    }
    
    public String getStringFromMap(Map thirdCategoryMap)
    {
        String categoryIds = "";
        Set<String> key = thirdCategoryMap.keySet();
        for (Iterator it = key.iterator(); it.hasNext();)
        {
            String s = (String)it.next();
            String code = (String)thirdCategoryMap.get(s);
            if (null != code && !code.equals("0") && !code.equals("") && !code.equals("insertGoods"))
            {
            	GoodCategoryDTO cate = myGoodCategoryBPO.get(code);
                if (null != cate && null != cate.getParentCode() && !cate.getParentCode().equals("0")
                    && !cate.getParentCode().equals("1") && !cate.getParentCode().equals("2"))
                {
                    categoryIds += code + ",";
                }
            }
        }
        if (!categoryIds.equals("")
            && categoryIds.substring(categoryIds.length() - 1, categoryIds.length()).equals(","))
        {
            categoryIds = categoryIds.substring(0, categoryIds.length() - 1);
        }
        return categoryIds;
    }
    
    public Map getCategoryIdsMap(String categoryId)
    {
        Map map = new HashMap();
        String[] categoryIds = categoryId.split(",");
        for (int i = 0; i < categoryIds.length; i++)
        {
            map.put(String.valueOf(i), categoryIds[i]);
        }
        return map;
    }
    
    
    
    public List listGoodImages(String goodId) {
		return myGoodBPO.listGoodImages(goodId);
	}
    public void pictureMltUpload(String fileName,String goodId) throws Exception{
		myGoodBPO.pictureMltUploadTX(fileName,goodId);
	}
	public void deleteProductImg(String id) throws AppException {
		myGoodBPO.deleteProductImgTX(id);
	}
	


}