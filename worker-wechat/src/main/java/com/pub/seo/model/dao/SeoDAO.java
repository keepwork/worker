package com.pub.seo.model.dao;

import java.util.List;

import com.pub.seo.model.dto.SeoDTO;
import com.sinovatech.common.model.dao.DaoSupport;

/**
 * Seo DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 10:00:54 PM
 */
public class SeoDAO extends DaoSupport
{

	public SeoDTO get(java.lang.String id)
	{
		String hql = "from SeoDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (SeoDTO) list.get(0);
		else
			return null;
	}


	public void update(SeoDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	

}