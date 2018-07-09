package com.common.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import com.sinovatech.bms.adm.model.facade.BmsLogOpFacade;
import com.sinovatech.bms.domain.model.bpo.BmsDomainBPO;
import com.sinovatech.framework.log.SinoLogger;

/**
 * 服务器重启时执行的定时任务
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 11, 2016 2:28:52 PM
 */
public class RefreshCacheJob extends TimerTask{
	
	private static final SinoLogger log = SinoLogger.getLogger();

    private BmsDomainBPO myBmsDomainBPO;
    private BmsLogOpFacade myBmsLogOpFacade;
	
	@Override
	public void run() {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		log.info("服务器重启定时任务-->刷新缓存.........开始时间=" + sdf.format(new Date()));
		//executeOperate();
//		log.info("服务器重启定时任务-->刷新缓存.........结束时间=" + sdf.format(new Date()));
	}
	
	public void executeOperate()
	{
		//刷新缓存（将字典表信息放入缓存中）
		//myBmsDomainBPO.refreshCache();
		
		//将当天操作日志入库
//		myBmsLogOpFacade.importTodayLogs();
	}


	
	public BmsDomainBPO getMyBmsDomainBPO() {
		return myBmsDomainBPO;
	}
	public void setMyBmsDomainBPO(BmsDomainBPO myBmsDomainBPO) {
		this.myBmsDomainBPO = myBmsDomainBPO;
	}
	public BmsLogOpFacade getMyBmsLogOpFacade() {
		return myBmsLogOpFacade;
	}
	public void setMyBmsLogOpFacade(BmsLogOpFacade myBmsLogOpFacade) {
		this.myBmsLogOpFacade = myBmsLogOpFacade;
	}
//	public ArticleBPO getMyArticleBPO() {
//		return myArticleBPO;
//	}
//	public void setMyArticleBPO(ArticleBPO myArticleBPO) {
//		this.myArticleBPO = myArticleBPO;
//	}
	
	
//    public static void main（String[] args） {
//        Timer timer new Timer（）；
//        Calendar date = Calendar.getInstance（）；
//        date.set（Calendar.DAY_OF_WEEK，Calendar.SUNDAY）；
//        date.set（Calendar.HOUR， 0）；
//        date.set（Calendar.MINUTE， 0）；
//        date.set（Calendar.SECOND， 0）；
//        date.set（Calendar.MILLISECOND， 0）；
//        // Schedule to run every Sunday in midnight
//        timer.schedule（new ReportGenerator（），  // TimerTask
//        date.getTime（），          // Timer
//        1000 * 60 * 60 * 24 * 7   // delay）
//        }；
}
