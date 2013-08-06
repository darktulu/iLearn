package com.simu.ilearn.server.service.impl;

import com.simu.ilearn.common.shared.type.Authority;
import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.server.business.User;
import com.simu.ilearn.server.repos.UserRepo;
import com.simu.ilearn.server.service.RegistrationService;
import com.simu.ilearn.server.util.MyModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    @Inject
    private UserRepo userRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    public Long create(UserVO user) {
        user.setStatus(UserVO.Status.ACTIVE);
        user.setAuthority(Authority.ROLE_USER);
        return userRepo.save(mapper.map(user, User.class)).getId();
    }

    @Override
    public void delete(Long id) {
        userRepo.delete(id);
    }

    @Override
    public void show(UserVO user) {
        System.out.println("this is a valid myEntity" + user);
    }
}
