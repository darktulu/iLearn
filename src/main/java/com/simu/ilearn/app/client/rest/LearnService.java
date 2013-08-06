package com.simu.ilearn.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.common.shared.rest.PathParameter;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.rest.RestParameter;
import com.simu.ilearn.common.shared.vo.LearnVO;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path(ResourcesPath.LEARN)
public interface LearnService extends RestService {
    @POST
    Action<ValidatedResponse> create(LearnVO entity);

    @DELETE
    @Path(PathParameter.ID)
    Action<GetResult<Void>> delete(@PathParam(RestParameter.ID) Long id);

    @GET
    Action<GetResults<LearnVO>> loadAll();
}
