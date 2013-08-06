package com.simu.ilearn.server.security;

import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.server.business.User;
import com.simu.ilearn.server.repos.UserRepo;
import com.simu.ilearn.server.util.MyModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Component
public class SecurityContextProviderImpl implements SecurityContextProvider {
    @Inject
    private UserRepo userRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserVO getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            User user = userRepo.findByEmail(securityContext.getAuthentication().getName());
            if (user != null) {
                return mapper.map(user, UserVO.class);
            }
        }

        return null;
    }

    @Override
    public Boolean logout() {
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setAuthentication(Authentication authenticate) {
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
