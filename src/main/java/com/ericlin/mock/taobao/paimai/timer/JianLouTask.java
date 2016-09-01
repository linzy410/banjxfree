package com.ericlin.mock.taobao.paimai.timer;

import com.ericlin.mock.taobao.paimai.Jianlou;
import com.qnvip.util.scheduling.Scheduler;
import com.qnvip.util.scheduling.SchedulerTask;
import com.qnvip.util.scheduling.iterators.HourIterator;

public class JianLouTask {

	private Scheduler scheduler = new Scheduler();
	
	public void start() {
		scheduler.schedule(new SchedulerTask() {
			@Override
			public void run() {
				try {
					Jianlou.fetch();
				} catch (Exception e) {
				}
				System.out.println("OK");
			}

		}, new HourIterator(0, 55, 0));
	}
}
