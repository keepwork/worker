package com.shop.appraise.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.shop.appraise.model.dto.AppraiseDTO;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.shop.complaint.model.dto.ComplaintDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * DAO
 *
 * @author BruceKobe(javalc@163.com)
 * @date Dec 28, 2018 12:39:58 PM
 */
public class AppraiseDAO extends DaoSupport
{
	public void save(AppraiseDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public AppraiseDTO get(java.lang.String id)
	{
		String hql = "from AppraiseDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (AppraiseDTO) list.get(0);
		else
			return null;
	}

	public void delete(AppraiseDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(AppraiseDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(AppraiseDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from AppraiseDTO Z ";
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
			String totalHQl = "from AppraiseDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}
	
	
	
	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	public Map<String,Object> listForPagination(Map<String,String> params){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", getPageList(params));
		map.put("rows", getPageCount(params));
		return map;
    }
	public List<ComplaintDTO> getPageList(Map<String,String> params)
	{
        final String sql = "select t.ID,t.COMP_TIME,t.STATUS from pub_menber_apprise t where t.MEN_ID = '"+params.get("menId")+"' " +
        		" order by t.comp_time desc " +
        		" LIMIT "+params.get("start")+","+params.get("size")+" ";
        
        List<ComplaintDTO> result = new ArrayList<ComplaintDTO>();
        List list = (List) this.getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session arg0)
                            throws HibernateException, SQLException {
                        SQLQuery query = arg0.createSQLQuery(sql);
                        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                        return query.list();
                    }
                });
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Map map = (Map) iterator.next();
            ComplaintDTO temp = new ComplaintDTO();
            temp.setId((String) map.get("ID"));
            temp.setCompTime((Date) map.get("COMP_TIME"));
            temp.setStatus((Integer) map.get("STATUS"));
            result.add(temp);
        }
        return result;
	}
	public int getPageCount(Map<String,String> params)
	{
		final String sql = "select t.ID from pub_menber_apprise t where t.MEN_ID = '"+params.get("menId")+"' " ;
		List list = (List) this.getHibernateTemplate().execute(
		        new HibernateCallback() {
		            @Override
		            public Object doInHibernate(Session arg0)
		                    throws HibernateException, SQLException {
		                SQLQuery query = arg0.createSQLQuery(sql);
		                return query.list();
		            }
		        });
		return list.size();
	}
	
	
	

	public List listByIds(String ids)
	{
		return this.getHibernateTemplate().find("from AppraiseDTO Z where Z.id in (" + ids + ")");
	}
	

}