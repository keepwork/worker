package com.pub.menber.model.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

//import com.pub.menber.model.dto.MenberAdvertDTO;
import com.pub.menber.model.dto.MenberDTO;
//import com.pub.menber.model.dto.MenberExitDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 会员DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class MenberDAO extends DaoSupport
{
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void save(MenberDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}
	
	public void saveMenberPoint(MenberPointDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public MenberDTO validateMenber(String loginName, String password) {
		List list = this.getHibernateTemplate().find(
				"from MenberDTO Z where Z.status=0 and (Z.loginName=? or Z.mobile=? or Z.pid=?) and Z.password=? ",
				new String[] { loginName,loginName,loginName,password });
		if (list.size() > 0) {
			return (MenberDTO) list.get(0);
		} else {
			return null;
		}
	}
	
	public void updateMenberBySql(MenberDTO dto) {
		final String sql = "update pub_menber set " +
				" LOGIN_NAME='"+dto.getLoginName()+"'," +
				" PASSWORD='"+dto.getPassword()+"'," +
				" REAL_NAME='"+dto.getRealName()+"'," +
				" MOBILE='"+dto.getMobile()+"'," +
				" EMAIL='"+dto.getEmail()+"'," +
				" PID='"+dto.getPid()+"'," +
				" MOBILE_EMERGENCY='"+dto.getMobileEmergency()+"'," +
				" REAL_NAME_EMERGENCY='"+dto.getRealNameEmergency()+"' " +
				" where ID = '"+dto.getId()+"' ";
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
	
	public void updateMenberPWBySql(MenberDTO dto) {
		final String sql = "update pub_menber set " +
				" PASSWORD='"+dto.getPassword()+"' " +
				" where ID = '"+dto.getId()+"' ";
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
	
	public MenberDTO get(java.lang.String id)
	{
		String hql = "from MenberDTO Z "
			+" left join fetch Z.tbTBmsLocationDTO B  where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (MenberDTO) list.get(0);
		else
			return null;
	}
	
	
	public MenberDTO findMenberByOpenId(java.lang.String openId)
	{
		String hql = "from MenberDTO Z where Z.openId=?";
		List list = this.getHibernateTemplate().find(hql, openId);
		if (list.size() == 1)
			return (MenberDTO) list.get(0);
		else
			return null;
	}
	
	public MenberDTO findMenberByLoginName(java.lang.String loginName)
	{
		String hql = "from MenberDTO Z where Z.loginName='"+loginName+"' or Z.mobile='"+loginName+"' or Z.pid='"+loginName+"' ";
		List list = this.getHibernateTemplate().find(hql);
		if (list.size()>0)
			return (MenberDTO) list.get(0);
		else
			return null;
	}

	public void delete(MenberDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(MenberDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(MenberDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit,String type)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from MenberDTO Z "
				+ "left join fetch Z.tbTBmsLocationDTO B  ";
		Object[] param = limit.getWhereHQL("Z");
		if (limit.getRowDisplayed() < 1)
		{// 非分页, 每页记录小于1时表示不进行分页
			return this.listWithNamePrams(hql + " where Z.type=" + type + " " + param[0] + " "
					+ limit.getOrder("Z"), (Map) param[1]);
		} else
		{// 分页查询
			// 查询记录
			List list = this.listWithLimit(hql + " where Z.type=" + type + " " + param[0] + " "
					+ limit.getOrder("Z"), (Map) param[1], limit
					.getStartLineNum(), limit.getRowDisplayed());
			// 查询并设置记录总数
			String totalHQl = "from MenberDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where Z.type=" + type + " " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String codes)
	{
		return this.getHibernateTemplate().find("from MenberDTO Z where Z.id in (" + codes + ")");
	}
	
	public List listAllMenbers()
	{
		return this.getHibernateTemplate().find("from MenberDTO Z where Z.pid is not null");
	}
	
	
	public List listMenbers(Map<String,String> params) throws ParseException
	{
		String where = " where 1=1 ";
		String beginTimeStr = params.get("beginTimeStr");
		if(null!=beginTimeStr && !beginTimeStr.equals("")){
			where += " and Z.regTime >= '"+beginTimeStr+"' ";
		}
		String endTimeStr = params.get("endTimeStr");
		if(null!=endTimeStr && !endTimeStr.equals("")){
			where += " and Z.regTime <= '"+endTimeStr+"' ";
		}
		String isjoin = params.get("isjoin");
		if(null!=isjoin && !isjoin.equals("")){
			where += " and Z.isjoin = " + isjoin + " ";
		}
		String type = params.get("type");
		if(null!=type && !type.equals("")){
			where += " and Z.type = " + type + " ";
		}
		return this.getHibernateTemplate().find("from MenberDTO Z " + where);
	}
	
	/**
	 * 查询已加入计划的会员
	 * @return
	 */
	public List listJoinMenbers()
	{
		return this.getHibernateTemplate().find("from MenberDTO Z where Z.isjoin in('1','2') ");
	}
	
	/**
	 * 查询推荐会员
	 * @return
	 */
	public List listSaleMenbers(String menId)
	{
		return this.getHibernateTemplate().find("from MenberDTO Z where Z.salesMenId = '"+menId+"' ");
	}
	
	/**
	 * 查询托管会员
	 * @return
	 */
	public List listFamilyMenbers(String menId)
	{
		return this.getHibernateTemplate().find("from MenberDTO Z where Z.familyMenId = '"+menId+"' ");
	}
	
	/**
	 * 保存退出申请
	 * @param dto
	 */
//	public void saveMenberExit(MenberExitDTO dto)
//	{
//		this.getHibernateTemplate().save(dto);
//	}
	
	/**
	 * 保存会员点击广告记录
	 * 
	 * @param dto
	 */
//	public void saveMenberAdvert(MenberAdvertDTO dto)
//	{
//		this.getHibernateTemplate().save(dto);
//	}
	/**
	 * 检查会员当天是否点击过某广告
	 * @param menId
	 * @param advertId
	 * @return
	 */
	public boolean checkAdvertClick(java.lang.String menId,String advertId)
	{
		final String sql = "select t.MEN_ID from pub_menber_advert t where t.MEN_ID = '"+menId+"' and t.AD_ID = '"+advertId+"' ";
		Integer size = (Integer) this.getHibernateTemplate().execute(
		        new HibernateCallback() {
		            @Override
		            public Object doInHibernate(Session arg0)
		                    throws HibernateException, SQLException {
		                SQLQuery query = arg0.createSQLQuery(sql);
		                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		                return query.list().size();
		            }
		        });
		if (size>0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 删除所有会员当天点击广告记录
	 */
	@SuppressWarnings("unchecked")
	public void deleteMenberAdvert()
	{
		final String sql = "delete from pub_menber_advert ";
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
	
	
	/**
	 * 计算所有计划会员总余额
	 * 
	 * @return
	 */
	public double sumMenberFee()
	{
		final String sql = "SELECT SUM(t.balance_fee) as TOTAL FROM pub_menber t WHERE t.isjoin IN(1,2) ";
		BigDecimal total = (BigDecimal) this.getHibernateTemplate().execute(
		        new HibernateCallback() {
		            @Override
		            public Object doInHibernate(Session arg0)
		                    throws HibernateException, SQLException {
		                SQLQuery query = arg0.createSQLQuery(sql);
		                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		                List list = query.list();
		                Map map = (Map)list.get(0);
		                BigDecimal sum = (BigDecimal)map.get("TOTAL");
		                return sum;
		            }
		        });
		return total.doubleValue();
	}
	
	
	/**
	 * 扫描所有会员账户情况，失效的会员等待期回到180天
	 */
//	public void updateJoinMenbers() {
//		String toDay = sdf.format(new Date());
//		final String sql = "update pub_menber m set m.reg_time = str_to_date('"+toDay+"','%Y-%m-%d %H:%i:%T')" +
//				" where m.isjoin = '1' and m.balance_fee < 5 ";
//		Integer count=(Integer)this.getHibernateTemplate().execute(new HibernateCallback(){
//			@Override
//			public Object doInHibernate(Session arg0)
//					throws HibernateException, SQLException {
//				// TODO Auto-generated method stub
//				SQLQuery query=arg0.createSQLQuery(sql);
//				return query.executeUpdate();
//			}
//		});
//	}
	
	
	/**
	 * 扫描所有会员账户情况，等待期超过180天的，将状态改成'已过等待期'
	 */
	public void updateJoinMenbers2() {
		final String sql = "update pub_menber m set m.isjoin = '2',m.effect_time=NOW() " +
				" where m.isjoin = '1' and TO_DAYS(NOW()) - TO_DAYS(m.join_time) >= 180 ";
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
	
	
	/**
	 * 每个月1号，给所有会员增加100元的保额
	 */
	public void addRightFee() {
		final String sql = "update pub_menber m set m.right_fee = m.right_fee + 100 " +
				" where m.isjoin in('1','2') ";
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