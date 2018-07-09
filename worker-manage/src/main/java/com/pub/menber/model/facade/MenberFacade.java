package com.pub.menber.model.facade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.common.util.date.DateUtil;
import com.common.util.excel.ExcelBean;
import com.pub.menber.model.bpo.MenberBPO;
import com.pub.menber.model.dto.MenberAdvertDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberExitDTO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberFacade { 
	
	private MenberBPO myMenberBPO;

	public MenberDTO validateMenber(String loginName, String password) {
		return myMenberBPO.validateMenber(loginName,password);
	}
	
	public void saveMenberPoint(MenberPointDTO dto)
	{
		myMenberBPO.saveMenberPoint(dto);
	}
	
	
	public String save(MenberDTO dto){
		return myMenberBPO.saveTX(dto);
	}

	
	public void update(MenberDTO dto){
		myMenberBPO.updateTX(dto);
	}
	
	
	public void saveRecharge(MenberDTO dto,BigDecimal price,String squeues){
		myMenberBPO.saveRechargeTX(dto,price,squeues);
	}
	
	
	public void updateMenberBySql(MenberDTO dto){
		myMenberBPO.updateMenberBySqlTX(dto);
	}
	public void updateMenberPWBySql(MenberDTO dto){
		myMenberBPO.updateMenberPWBySqlTX(dto);
	}
	
	
	public void sign(MenberDTO dto,int point){
		myMenberBPO.signTX(dto,point);
	}

	public void saveOrUpdate(MenberDTO dto) throws AppException {
		myMenberBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberBPO.deleteTX(ids);
	}

	
	public MenberDTO get(java.lang.String id) {
		return myMenberBPO.get(id);
	}
	
	public MenberDTO findMenberByOpenId(java.lang.String openId) {
		return myMenberBPO.findMenberByOpenId(openId);
	}
	
	
	public MenberDTO findMenberByLoginName(java.lang.String loginName) {
		return myMenberBPO.findMenberByLoginName(loginName);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberBPO.listByIds(ids);
	}
	
	public List listSaleMenbers(String menId) throws AppException {
		return myMenberBPO.listSaleMenbers(menId);
	}
	
	public List listFamilyMenbers(String menId) throws AppException {
		return myMenberBPO.listFamilyMenbers(menId);
	}
	
	public List listAllMenbers(){
		return myMenberBPO.listAllMenbers();
	}
	
	public List listMenbers(Map<String,String> params) throws ParseException{
		return myMenberBPO.listMenbers(params);
	}
	
	
	public List listJoinMenbers(){
		return myMenberBPO.listJoinMenbers();
	}


	
	
	/**
	 * Export activity data
	 * 
	 * @param list
	 * @param excelBean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void exportData(List<MenberDTO> list,
			ExcelBean excelBean,String type) throws Exception {
		Map _tmpMap = null;
		if (list != null && excelBean != null) {
			List _newList = new ArrayList();
			LinkedHashMap<String, String> _props = new LinkedHashMap<String, String>();
			_props.put("realName", "会员姓名");
			_props.put("mobile", "手机号");
			_props.put("pid", "身份证号");
			_props.put("email", "邮箱");
			_props.put("regTime", "注册时间");
			
			try {
				for (MenberDTO _menber : list) {
					_tmpMap = new HashMap();
					// 活动名称
					_tmpMap.put("realName", _menber.getRealName());
					// 手机号
					_tmpMap.put("mobile",_menber.getMobile());
					// 身份证号
					_tmpMap.put("pid",_menber.getPid());
					// 手机号
					_tmpMap.put("email",_menber.getEmail());
					// 注册时间
					_tmpMap.put("regTime",DateUtil.getFormatYMD(_menber.getRegTime()));
					_newList.add(_tmpMap);
				}
				excelBean.exportExcel(_props, _newList);
			} finally {
				_newList = null;
			}
		}
	}
	
	
	
	public boolean checkAdvertClick(java.lang.String menId , String advertId) throws AppException {
		return myMenberBPO.checkAdvertClick(menId,advertId);
	}
	
	public void saveMenberAdvert(MenberAdvertDTO dto){
		myMenberBPO.saveMenberAdvertTX(dto);
	}
	
	public void saveMenberExit(MenberExitDTO dto){
		myMenberBPO.saveMenberExitTX(dto);
	}
	
	public MenberBPO getMyMenberBPO() {
		return myMenberBPO;
	}
	public void setMyMenberBPO(MenberBPO myMenberBPO) {
		this.myMenberBPO = myMenberBPO;
	}



}