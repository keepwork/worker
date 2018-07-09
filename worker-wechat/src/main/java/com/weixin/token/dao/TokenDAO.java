package com.weixin.token.dao;

import java.util.List;
import com.sinovatech.common.model.dao.DaoSupport;
import com.weixin.token.dto.TokenDTO;

/**
 * TOKEN DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 4, 2016 5:03:32 PM
 */
public class TokenDAO extends DaoSupport
{
	public void update(TokenDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}
	
	public TokenDTO get(java.lang.String id)
	{
		String hql = "from TokenDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (TokenDTO) list.get(0);
		else
			return null;
	}

}