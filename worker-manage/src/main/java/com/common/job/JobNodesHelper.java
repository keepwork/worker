package com.common.job;

import com.sinovatech.framework.common.helper.SpringBeanHelper;
import com.sinovatech.framework.log.SinoLogger;
//import com.sinovatech.marketplat.token.model.facade.YxSysTokenFacade;

/**
 * 多节点的定时任务处理类
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 11, 2016 2:28:52 PM
 */
public abstract class JobNodesHelper {

	private static SinoLogger log = SinoLogger.getLogger();

	/*private static YxSysTokenFacade myYxSysTokenFacade;

	*//**
	 * 是否启动定时器
	 * 
	 * @param tokenCode
	 * @return
	 *//*
	public synchronized static boolean isStartupTask(String tokenCode) {
		if (myYxSysTokenFacade == null) {
			myYxSysTokenFacade = (YxSysTokenFacade) SpringBeanHelper.getBeanContext().getBean("myYxSysTokenFacade");
		}
		if (myYxSysTokenFacade != null) {
			return myYxSysTokenFacade.isUsableJob(tokenCode);
		} else {
			log.error("isUsableJob()[YxSysTokenFacade is null,it is not startup...]");
		}
		return false;
	}*/

}
