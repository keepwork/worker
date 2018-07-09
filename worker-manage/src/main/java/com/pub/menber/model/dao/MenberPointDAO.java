package com.pub.menber.model.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pub.menber.model.dto.MenberPointDTO;
import com.shop.complaint.model.dto.ComplaintDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 会员积分DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class MenberPointDAO extends DaoSupport
{
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public void save(MenberPointDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}
	
	public MenberPointDTO get(java.lang.String id)
	{
		String hql = "from MenberPointDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (MenberPointDTO) list.get(0);
		else
			return null;
	}
	
	public boolean checkSign(java.lang.String menId)
	{
		String today = format.format(new Date());
		final String sql = "select t.ID from pub_menber_point t where t.MEN_ID = '"+menId+"' " +
								" and  DATE_FORMAT(t.CREATE_TIME,'%Y-%m-%d') = '"+today+"' and t.point_desc = '签到送积分'";
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
		if (list.size()>0){
			return false;
		}else{
			return true;
		}
	}

	public void delete(MenberPointDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(MenberPointDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(MenberPointDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from MenberPointDTO Z ";
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
			String totalHQl = "from MenberPointDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String codes)
	{
		return this.getHibernateTemplate().find("from MenberPointDTO Z where Z.id in (" + codes + ")");
	}
	
	public List listByMenId(String menId)
	{
		return this.getHibernateTemplate().find("from MenberPointDTO Z where Z.menId = '" + menId + "'");
	}
	
	
	
	
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	public Map<String,Object> listForPagination(Map<String,String> params){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String menId = params.get("menId");
		String where = "";
		if(null != menId && !menId.equals("")){
			where = " where t.MEN_ID = '"+menId+"' ";
		}
		
		map.put("list", getPageList(params,where));
		map.put("rows", getPageCount(params,where));
		return map;
    }
	public List<MenberPointDTO> getPageList(Map<String,String> params,String where)
	{
		final String sql = "select t.ID,t.MEN_ID,t.POINT,t.POINT_DESC,t.CREATE_TIME from pub_menber_point t  " + where + 
        		" order by t.CREATE_TIME desc " +
        		" LIMIT "+params.get("start")+","+params.get("size")+" ";
        
        List<MenberPointDTO> result = new ArrayList<MenberPointDTO>();
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
            MenberPointDTO temp = new MenberPointDTO();
            temp.setId((String) map.get("ID"));
            temp.setMenId((String) map.get("MEN_ID"));
            temp.setPoint((Integer) map.get("POINT"));
            temp.setPointDesc((String) map.get("POINT_DESC"));
            temp.setCreateTime((Date) map.get("CREATE_TIME"));
            result.add(temp);
        }
        return result;
	}
	public int getPageCount(Map<String,String> params,String where)
	{
		final String sql = "select t.ID from pub_menber_point t " + where ;
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
	

}