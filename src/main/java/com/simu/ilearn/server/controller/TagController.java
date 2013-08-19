package com.simu.ilearn.server.controller;

import com.simu.ilearn.common.shared.dispatch.GetResults;
import com.simu.ilearn.common.shared.rest.ResourcesPath;
import com.simu.ilearn.common.shared.vo.TagVO;
import com.simu.ilearn.server.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping(ResourcesPath.TAG)
public class TagController {
    @Inject
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GetResults<TagVO> loadAll() {
        return new GetResults<TagVO>(tagService.loadAll());
    }
}
