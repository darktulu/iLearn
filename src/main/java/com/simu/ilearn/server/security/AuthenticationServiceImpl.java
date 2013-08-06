package com.simu.ilearn.server.security;

import com.simu.ilearn.common.shared.vo.UserVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    @Qualifier("authenticationProvider")
    private AuthenticationManager authenticationManager;
    @Inject
    private SecurityContextProvider securityContext;

    @Override
    @Secured({"ROLE_USER"})
    public UserVO currentUser() {
        return securityContext.getCurrentUser();
    }

    @Override
    public Boolean authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authenticate = authenticationManager.authenticate(authentication);
            if (authenticate.isAuthenticated()) {
                securityContext.setAuthentication(authenticate);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean logout() {
        return securityContext.logout();
    }
}
