package com.shop.good.model.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.shop.good.model.dto.GoodCategoryDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 商品分类DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class GoodCategoryDAO extends DaoSupport
{
	public void save(GoodCategoryDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public GoodCategoryDTO get(java.lang.String code)
	{
		String hql = "from GoodCategoryDTO Z where Z.code=?";
		List list = this.getHibernateTemplate().find(hql, code);
		if (list.size() == 1)
			return (GoodCategoryDTO) list.get(0);
		else
			return null;
	}

	public void delete(GoodCategoryDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(GoodCategoryDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(GoodCategoryDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from GoodCategoryDTO Z ";
		Object[] param = limit.getWhereHQL("Z");
		if (limit.getRowDisplayed() < 1)
		{// 非分页, 每页记录小于1时表示不进行分页
			return this.listWithNamePrams(hql + " where 1=1 " + param[0] + " "
					+ limit.getOrder("Z"), (Map) param[1]);
		} else
		{// 分页查询
			// 查询记录
			List list = this.listWithLimit(hql + " where 1=1 " + param[0] + " "
					+ limit.getOrder("Z"), (Map) param[1], limit
					.getStartLineNum(), limit.getRowDisplayed());
			// 查询并设置记录总数
			String totalHQl = "from GoodCategoryDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String codes)
	{
		return this.getHibernateTemplate().find("from GoodCategoryDTO Z where Z.code in (" + codes + ")");
	}
	
	
	public List listByParent(String parentCode)
	{
		String where = "";
		if(parentCode.equals("")){
			where = " where Z.parentCode is null or Z.parentCode = '' ";
		}else{
			where = " where Z.parentCode = '" + parentCode + "'";
		}
		return this.getHibernateTemplate().find("from GoodCategoryDTO Z " + where);
	}

	

}