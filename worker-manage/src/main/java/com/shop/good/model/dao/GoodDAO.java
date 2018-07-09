package com.shop.good.model.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.GoodPicDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 商品DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 10:00:54 PM
 */
public class GoodDAO extends DaoSupport
{
	public void save(GoodDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public GoodDTO get(java.lang.String id)
	{
		String hql = "from GoodDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (GoodDTO) list.get(0);
		else
			return null;
	}

	public void delete(GoodDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(GoodDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(GoodDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from GoodDTO Z ";
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
			String totalHQl = "from GoodDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String codes)
	{
		return this.getHibernateTemplate().find("from GoodDTO Z where Z.id in (" + codes + ")");
	}
	
	
	public List listByCateCode(String cateCode)
	{
		String param = "";
		if(null!=cateCode && !cateCode.equals("")){
			param = " where Z.catCode like '%" + cateCode + "%' ";
		}
		return this.getHibernateTemplate().find("from GoodDTO Z " + param + " order by orderNum asc,updateTime desc");
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	public List listGoodImages(String goodId) {
		return this.getHibernateTemplate().find("from GoodPicDTO Z where Z.goodId='" + goodId + "' order by orderNum asc");
	}
	public void saveGoodImage(GoodPicDTO dto){
		this.getHibernateTemplate().save(dto);
	}
	public void deleteProductImg(String id) {
		final String sql = " DELETE FROM pub_good_pic WHERE id = '"+id+"'  ";
		Integer count=(Integer)this.getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				SQLQuery query=arg0.createSQLQuery(sql);
				return query.executeUpdate();
			}
		});
	}
	
	
	

}