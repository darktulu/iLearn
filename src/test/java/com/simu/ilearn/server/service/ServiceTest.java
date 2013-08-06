package com.simu.ilearn.server.service;

import com.google.common.collect.Lists;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.server.business.Learn;
import com.simu.ilearn.server.repos.LearnRepo;
import com.simu.ilearn.server.util.MyModelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/applicationContext.xml",
        "classpath:/META-INF/applicationContext-security.xml",
        "classpath:/META-INF/applicationContext-data.xml",
        "classpath:/META-INF/applicationContext-drools.xml"
})
public class ServiceTest {
    @Inject
    private LearnRepo learnRepo;
    @Inject
    private MyModelMapper mapper;

    @Test
    public void nothing() {
        List<LearnVO> result = Lists.newArrayList();
        for (Learn entity : learnRepo.findAll()) {
            result.add(mapper.map(entity, LearnVO.class));
        }
        System.out.println(result);
    }
}
