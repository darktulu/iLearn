package com.simu.ilearn.server.service.impl;

import com.google.common.collect.Lists;
import com.simu.ilearn.common.shared.vo.TagVO;
import com.simu.ilearn.server.business.Tag;
import com.simu.ilearn.server.repos.TagRepo;
import com.simu.ilearn.server.service.TagService;
import com.simu.ilearn.server.util.MyModelMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Inject
    private TagRepo tagRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    public List<TagVO> loadAll() {
        List<TagVO> result = Lists.newArrayList();
        for (Tag tag : tagRepo.findAll()) {
            result.add(mapper.map(tag, TagVO.class));
        }
        return result;
    }
}
