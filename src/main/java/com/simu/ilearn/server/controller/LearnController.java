package com.simu.ilearn.server.controller;

import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.common.shared.rest.PathParameter;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.rest.RestParameter;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.server.service.LearnService;
import com.simu.ilearn.server.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(ResourcesPath.LEARN)
public class LearnController extends BaseController {
    @Autowired
    private LearnService learnService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ValidatedResponse create(@RequestBody @Valid LearnVO learn) {
        learnService.create(learn);
        return new ValidatedResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = PathParameter.ID)
    @ResponseStatus(HttpStatus.OK)
    public GetResult<Void> delete(@PathVariable(RestParameter.ID) Long id) {
        learnService.delete(id);
        return new GetResult<Void>();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GetResults<LearnVO> loadAll() {
        return new GetResults<LearnVO>(learnService.loadAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = PathParameter.ID)
    @ResponseBody
    public GetResult<LearnVO> loadOne(@PathVariable(RestParameter.ID) Long id) {
        return new GetResult<LearnVO>(learnService.loadOne(id));
    }
}
