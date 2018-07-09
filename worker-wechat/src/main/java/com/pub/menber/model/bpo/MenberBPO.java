package com.pub.menber.model.bpo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.pub.menber.model.dao.MenberChargeDAO;
import com.pub.menber.model.dao.MenberDAO;
//import com.pub.menber.model.dto.MenberAdvertDTO;
//import com.pub.menber.model.dto.MenberChargeDTO;
import com.pub.menber.model.dto.MenberDTO;
//import com.pub.menber.model.dto.MenberExitDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 维护MenberDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberBPO extends BpoSupport { 
	
	private static ICache cache = CacheFactory.newCache();
	private MenberDAO myMenberDAO;
//	private MenberChargeDAO myMenberChargeDAO;

	public MenberDTO validateMenber(String loginName, String password) {
		return myMenberDAO.validateMenber(loginName,password);
	}
	
	public void saveMenberPoint(MenberPointDTO dto)
	{
		myMenberDAO.saveMenberPoint(dto);
	}
	
	
	public String saveTX(MenberDTO dto){
		myMenberDAO.save(dto);
		MenberPointDTO point = new MenberPointDTO();
		point.setMenId(dto.getId());
		point.setPoint(dto.getPoint());
		point.setPointDesc("注册送积分");
		point.setCreateTime(new Date());
		myMenberDAO.saveMenberPoint(point);
		return dto.getId();
	}

	/**
	 * 保存充值记录
	 * @param men
	 * @param price
	 */
	public void saveRechargeTX(MenberDTO men,BigDecimal price,String squeues) {
//		myMenberDAO.update(men);
//		MenberChargeDTO charge = new MenberChargeDTO();
//		charge.setMenId(men.getId());
//		charge.setPrice(price);
//		charge.setCreateTime(new Date());
//		charge.setSqueues(squeues);
//		myMenberChargeDAO.save(charge);
	}
	
	
	public void updateTX(MenberDTO dto) {
		myMenberDAO.update(dto);
	}
	
	
	public void updateMenberBySqlTX(MenberDTO dto) {
		myMenberDAO.updateMenberBySql(dto);
	}
	
	public void updateMenberPWBySqlTX(MenberDTO dto) {
		myMenberDAO.updateMenberPWBySql(dto);
	}
	
	public void signTX(MenberDTO dto,int point) {
		myMenberDAO.update(dto);
		MenberPointDTO p = new MenberPointDTO();
		p.setMenId(dto.getId());
		p.setPoint(point);
		p.setPointDesc("签到送积分");
		p.setCreateTime(new Date());
    	myMenberDAO.saveMenberPoint(p);
	}

	
	public void saveOrUpdateTX(MenberDTO dto) throws AppException {
		myMenberDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberDTO dto = this.get(id[i]);
			this.myMenberDAO.delete(dto);

		}
	}

	
	public MenberDTO get(java.lang.String id){
		return myMenberDAO.get(id);
	}
	
	public MenberDTO findMenberByOpenId(java.lang.String openId){
		return myMenberDAO.findMenberByOpenId(openId);
	}
	
	
	public MenberDTO findMenberByLoginName(java.lang.String loginName){
		return myMenberDAO.findMenberByLoginName(loginName);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberDAO.listByIds(ids);
	}
	
	public List listSaleMenbers(String menId) throws AppException {
		return myMenberDAO.listSaleMenbers(menId);
	}
	
	public List listFamilyMenbers(String menId) throws AppException {
		return myMenberDAO.listFamilyMenbers(menId);
	}
	
	
	public List listAllMenbers(){
		return myMenberDAO.listAllMenbers();
	}
	
	public List listMenbers(Map<String,String> params) throws ParseException{
		return myMenberDAO.listMenbers(params);
	}
	
	
	public List listJoinMenbers(){
		return myMenberDAO.listJoinMenbers();
	}
	
	
	public boolean checkAdvertClick(java.lang.String menId,String advertId){
		return myMenberDAO.checkAdvertClick(menId,advertId);
	}
	
	
	
//	public void saveMenberAdvertTX(MenberAdvertDTO dto){
//		myMenberDAO.saveMenberAdvert(dto);
//	}
//
//
//	public void saveMenberExitTX(MenberExitDTO dto){
//		myMenberDAO.saveMenberExit(dto);
//	}
//
	
	
	/**
	 * 删除所有会员当天点击广告记录
	 */
	public void deleteMenberAdvert()
	{
		myMenberDAO.deleteMenberAdvert();
	}
	
	
	/**
	 * 更新缓存
	 */
	public void refreshCache()
	{
    	//查询计划会员人数
    	List<MenberDTO> joinMenberList = myMenberDAO.listJoinMenbers();
    	cache.set("cache_playMenberNum", joinMenberList.size());
    	
    	//查询计划会员总余额
    	double total = myMenberDAO.sumMenberFee();
    	//System.out.println("============================================total:"+total);
    	cache.set("cache_playMenberTotal", total);
    	
    	//查询会员总人数
    	List<MenberDTO> allMenberList = myMenberDAO.listAllMenbers();
    	cache.set("cache_AllMenberNum", allMenberList.size());
	}
	
	
	/**
	 * 扫描所有会员账户情况，等待期超过180天的，将状态改成'已过等待期'
	 */
	public void scanJoinMenbers2()
	{
    	myMenberDAO.updateJoinMenbers2();
	}
	
	/**
	 * 每个月1号，给所有会员增加100元的保额
	 */
	public void addRightFee()
	{
		// 获得一个日历对象
		Calendar c = Calendar.getInstance();
		// 得到本月的那一天
		int today = c.get(c.DAY_OF_MONTH);
		// 然后判断是不是本月的第一天
		if(today ==1){//是
			myMenberDAO.addRightFee();
		}
	}
	

	public MenberDAO getMyMenberDAO() {
		return myMenberDAO;
	}
	public void setMyMenberDAO(MenberDAO myMenberDAO) {
		this.myMenberDAO = myMenberDAO;
	}
//	public MenberChargeDAO getMyMenberChargeDAO() {
//		return myMenberChargeDAO;
//	}
//	public void setMyMenberChargeDAO(MenberChargeDAO myMenberChargeDAO) {
//		this.myMenberChargeDAO = myMenberChargeDAO;
//	}

	

}