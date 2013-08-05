package com.simu.ilearn.server.job.impl;

import com.simu.ilearn.server.job.Worker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service("myJob")
public class myJob implements Worker {
    private Log logger = LogFactory.getLog("Worker Job");

    @Override
    public void work() {
        logger.info("I'm working");
    }
}
