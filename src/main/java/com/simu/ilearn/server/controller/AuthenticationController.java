package com.simu.ilearn.server.controller;

import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dto.UserCredentials;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.server.security.AuthenticationService;
import com.simu.ilearn.server.util.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;

@Controller
@RequestMapping(ResourcesPath.AUTHENTICATION)
public class AuthenticationController extends BaseController {
    @Inject
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GetResult<Boolean> authenticate(@RequestBody UserCredentials credentials) {
        Boolean result = authenticationService.authenticate(credentials.getUsername(), credentials.getPassword());
        return new GetResult<Boolean>(result);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GetResult<UserVO> currentUser() {
        return new GetResult<UserVO>(authenticationService.currentUser());
    }
}
