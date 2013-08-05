package com.simu.ilearn.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dto.UserCredentials;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.vo.UserVO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path(ResourcesPath.AUTHENTICATION)
public interface AuthenticationService extends RestService {
    @POST
    Action<GetResult<Boolean>> authenticate(UserCredentials credentials);

    @GET
    Action<GetResult<UserVO>> currentUser();
}
