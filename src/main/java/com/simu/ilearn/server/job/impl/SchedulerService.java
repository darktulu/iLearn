package com.simu.ilearn.server.job.impl;

import com.simu.ilearn.server.job.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
    @Autowired
    private Worker worker;

    @Scheduled(cron = "0 * * * * ?")
    public void doSchedule() {
        worker.work();
    }
}
