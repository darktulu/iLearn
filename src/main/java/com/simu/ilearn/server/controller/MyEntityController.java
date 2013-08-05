package com.simu.ilearn.server.controller;

import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.dispatch.ValidatedResponse;
import com.simu.ilearn.common.shared.rest.PathParameter;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.rest.RestParameter;
import com.simu.ilearn.common.shared.vo.MyEntityVO;
import com.simu.ilearn.server.service.MyService;
import com.simu.ilearn.server.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@RequestMapping(ResourcesPath.ENTITY)
public class MyEntityController extends BaseController {
    @Autowired
    private MyService myService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ValidatedResponse create(@RequestBody @Valid MyEntityVO entity) {
        myService.create(entity);
        return new ValidatedResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = PathParameter.ID)
    @ResponseStatus(HttpStatus.OK)
    public GetResult<Void> delete(@PathVariable(RestParameter.ID) Long id) {
        myService.delete(id);
        return new GetResult<Void>();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GetResults<MyEntityVO> loadAll() {
        return new GetResults<MyEntityVO>(myService.loadAll());
    }
}
