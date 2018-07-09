package com.common.job;

import java.util.Date;
import org.quartz.JobExecutionException;
import com.sinovatech.bms.adm.model.facade.BmsLogOpFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;
import com.sinovatech.framework.log.SinoLogger;

/**
 * 每天执行的定时任务
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 11, 2016 2:28:52 PM
 */
public class EveryDayJob {

	private static SinoLogger log = SinoLogger.getLogger();

//	private BmsLogOpFacade myBmsLogOpFacade;
	
	//定时任务 - 每天晚上1点执行
	public void doExecute() throws JobExecutionException 
	{
//		log.info("每天执行定时任务.........开始时间=" + new Date().toString());
//		myBmsLogOpFacade = (BmsLogOpFacade) SpringBeanHelper.getBeanContext().getBean("myBmsLogOpFacade");
		
		//将当天操作日志入库
//		myBmsLogOpFacade.importTodayLogs();
		
//		log.info("每天执行定时任务.........结束时间=" + new Date().toString());
	}

}
