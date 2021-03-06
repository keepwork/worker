package com.pub.menber.model.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.pub.menber.model.dto.MenberAddrDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 配送地址DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class MenberAddrDAO extends DaoSupport
{
	public void save(MenberAddrDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}
	
	public MenberAddrDTO get(java.lang.String id)
	{
		String hql = "from MenberAddrDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (MenberAddrDTO) list.get(0);
		else
			return null;
	}

	public void delete(MenberAddrDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(MenberAddrDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(MenberAddrDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from MenberAddrDTO Z ";
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
			String totalHQl = "from MenberAddrDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	
	public List listByIds(String codes)
	{
		return this.getHibernateTemplate().find("from MenberAddrDTO Z where Z.id in (" + codes + ")");
	}
	
	
	public List listByMenId(String menId)
	{
		return this.getHibernateTemplate().find("from MenberAddrDTO Z where Z.menId = '" + menId + "' ");
	}
	
	
	

}