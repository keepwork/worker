package com.pub.menber.model.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.pub.menber.model.dto.MenberFamilyDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 会员亲友DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class MenberFamilyDAO extends DaoSupport
{
	public void save(MenberFamilyDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}
	
	public MenberFamilyDTO get(java.lang.String id)
	{
		String hql = "from MenberFamilyDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (MenberFamilyDTO) list.get(0);
		else
			return null;
	}

	public void delete(MenberFamilyDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(MenberFamilyDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(MenberFamilyDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from MenberFamilyDTO Z ";
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
			String totalHQl = "from MenberFamilyDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String ids)
	{
		return this.getHibernateTemplate().find("from MenberFamilyDTO Z where Z.id in (" + ids + ")");
	}
	public List listByMenId(String menId)
	{
		return this.getHibernateTemplate().find("from MenberDTO Z where Z.salesMenId = '" + menId + "'");
	}
	
	

}