package com.simu.ilearn.server.security;

import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.server.business.User;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

public interface SecurityContextProvider {
    UserVO getCurrentUser();

    Boolean logout();

    void setAuthentication(Authentication authenticate);

    User getConnectedUser();
}
