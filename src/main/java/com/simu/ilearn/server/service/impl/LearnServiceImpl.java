package com.simu.ilearn.server.service.impl;

import com.google.common.collect.Lists;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.server.business.Learn;
import com.simu.ilearn.server.repos.LearnRepo;
import com.simu.ilearn.server.security.SecurityContextProvider;
import com.simu.ilearn.server.service.LearnService;
import com.simu.ilearn.server.util.MyModelMapper;
import org.drools.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static com.simu.ilearn.server.repos.spec.LearnSpec.ownerIs;
import static com.simu.ilearn.server.repos.spec.LearnSpec.statusIn;
import static org.springframework.data.jpa.domain.Specifications.where;

@Service
@Transactional
public class LearnServiceImpl implements LearnService {
    @Inject
    private LearnRepo learnRepo;
    @Inject
    private MyModelMapper mapper;
    @Inject
    private SecurityContextProvider securityProvider;
    @Inject
    private DroolsServiceImpl droolsService;

    @Override
    public Long create(LearnVO learn) {
        learn.setCreated(new Date());
        learn.setStatus(LearnVO.Status.ACTIVE);
        learn.setOwner(securityProvider.getCurrentUser());

        droolsService.getKsession().execute(CommandFactory.newInsertElements(learn.getTags()));

        return learnRepo.save(mapper.map(learn, Learn.class)).getId();
    }

    @Override
    public void delete(Long id) {
        learnRepo.delete(id);
    }

    @Override
    public void show(LearnVO myEntity) {
        System.out.println("this is a valid myEntity" + myEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearnVO> loadAll() {
        List<LearnVO> result = Lists.newArrayList();
        for (Learn entity : learnRepo.findAll(where(ownerIs(securityProvider.getConnectedUser()))
                .and(statusIn(Learn.Status.ACTIVE)))) {
            result.add(mapper.map(entity, LearnVO.class));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public LearnVO loadOne(Long id) {
        return mapper.map(learnRepo.findOne(id), LearnVO.class);
    }

    @Override
    public void archive(Long id) {
        Learn learn = learnRepo.findOne(id);
        learn.setStatus(Learn.Status.ARCHIVED);
        learnRepo.save(learn);
    }
}
