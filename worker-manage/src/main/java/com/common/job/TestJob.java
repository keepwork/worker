package com.common.job;

import com.sinovatech.framework.log.SinoLogger;

/**
 * 中奖记录排行定时任务
 * 
 * @author kevin(keepwork512@163.com)
 * @date Mar 18, 2016 9:16:20 AM
 */
public class TestJob {

	private static final SinoLogger log = SinoLogger.getLogger();

//	private YxActivityFacade myYxActivityFacade;


	/**
	 * 执行任务
	 */
	public void doTestTask() 
	{
		log.info("定时任务-->多节点并发任务开始.........");
//		if (JobNodesHelper.isStartupTask(SystemTokenCons.PRWIN_TOP_CRON)) { // 多节点并发控制
//			log.info("定时任务-->多节点并发任务.........开始时间=" + new Date().toString());
//			//业务逻辑
//			//myYxActivityFacade.delete("");
//			log.info("定时任务-->多节点并发任务.........结束时间=" + new Date().toString());
//		}
	}
	
	
//	public YxActivityFacade getMyYxActivityFacade() {
//		return myYxActivityFacade;
//	}
//	public void setMyYxActivityFacade(YxActivityFacade myYxActivityFacade) {
//		this.myYxActivityFacade = myYxActivityFacade;
//	}

	
}
