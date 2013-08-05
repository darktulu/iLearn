package com.simu.ilearn.server.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/applicationContext.xml",
        "classpath:/META-INF/applicationContext-security.xml",
        "classpath:/META-INF/applicationContext-data.xml",
        "classpath:/META-INF/applicationContext-drools.xml"
})
public class ServiceTest {
}
