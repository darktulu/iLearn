package com.simu.ilearn.app.client.rest;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.rest.RestService;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.vo.TagVO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(ResourcesPath.TAG)
public interface TagService extends RestService {
    @GET
    Action<GetResults<TagVO>> loadAll();
}
