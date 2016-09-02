package com.ericlin.mock.taobao.paimai.timer;

import java.util.Calendar;

import com.ericlin.mock.taobao.paimai.Jianlou;
import com.qnvip.util.DateFormatUtil;
import com.qnvip.util.scheduling.Scheduler;
import com.qnvip.util.scheduling.SchedulerTask;
import com.qnvip.util.scheduling.iterators.TimeIterator;

public class JianLouTask {

	private Scheduler scheduler = new Scheduler();
	
	public void start() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 55);
		String startTime = DateFormatUtil.formatDateTime(cal.getTime());
		scheduler.schedule(new SchedulerTask() {
			@Override
			public void run() {
				try {
					Jianlou.fetch();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(DateFormatUtil.getCurrentTime() + " OK");
			}
			
		}, new TimeIterator(startTime, 60));
	}
	
	public static void main(String[] args) {
		new JianLouTask().start();
	}
}
