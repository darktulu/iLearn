package com.simu.ilearn.server.service;

import com.google.common.collect.Lists;
import com.simu.ilearn.common.shared.type.Authority;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.common.shared.vo.TagVO;
import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.server.business.Learn;
import com.simu.ilearn.server.business.User;
import com.simu.ilearn.server.repos.LearnRepo;
import com.simu.ilearn.server.repos.UserRepo;
import com.simu.ilearn.server.service.impl.DroolsServiceImpl;
import com.simu.ilearn.server.util.MyModelMapper;
import org.drools.command.CommandFactory;
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
    private UserRepo userRepo;
    @Inject
    private MyModelMapper mapper;
    @Inject
    private DroolsServiceImpl droolsService;

    @Test
    public void nothing() {
        List<TagVO> tags = Lists.newArrayList();
        TagVO tag = new TagVO();
        tag.setTitle("spring");
        tags.add(tag);
        tag = new TagVO();
        tag.setTitle("activiti");
        tags.add(tag);
        tag = new TagVO();
        tag.setTitle("i'm ubuntu");
        tags.add(tag);
        droolsService.getKsession().execute(CommandFactory.newInsertElements(tags));

        for (TagVO tagVO : tags) {
            System.out.println(tagVO.getColor());
        }
    }
}
