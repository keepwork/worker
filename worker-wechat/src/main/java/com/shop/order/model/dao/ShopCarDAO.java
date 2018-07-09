package com.shop.order.model.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.shop.order.model.dto.ShopCarDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 购物车DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class ShopCarDAO extends DaoSupport
{
	public void save(ShopCarDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public ShopCarDTO get(java.lang.String id)
	{
		String hql = "from ShopCarDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (ShopCarDTO) list.get(0);
		else
			return null;
	}

	public void delete(ShopCarDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteAll(String menId)
	{
		final String sql = "delete from pub_shop_car where MEN_ID='"+menId+"'  ";
        this.getHibernateTemplate().execute(new HibernateCallback(){
            @Override
            public Object doInHibernate(Session arg0)
                    throws HibernateException, SQLException {
                // TODO Auto-generated method stub
                SQLQuery query=arg0.createSQLQuery(sql);
//                query.setString("menId", menId);
                return query.executeUpdate();
            }
        });
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(ShopCarDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(ShopCarDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from ShopCarDTO Z ";
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
			String totalHQl = "from ShopCarDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String id)
	{
		return this.getHibernateTemplate().find("from ShopCarDTO Z where Z.id in (" + id + ")");
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopCarDTO> listByOpenId(String openId)
	{
		return this.getHibernateTemplate().find("from ShopCarDTO Z where Z.openId = '" + openId + "'");
	}
	
	@SuppressWarnings("unchecked")
	public List<ShopCarDTO> listByMenId(String menId)
	{
		return this.getHibernateTemplate().find("from ShopCarDTO Z where Z.menId = '" + menId + "'");
	}
	
	
	public List<ShopCarDTO> listShopCar(String menId,String goodId)
	{
		return this.getHibernateTemplate().find("from ShopCarDTO Z where Z.menId = '" + menId + "' and Z.goodId = '" + goodId + "' ");
	}
	

}