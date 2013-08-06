/**
 * Copyright 2012 Nuvola Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.simu.ilearn.server.service.impl;

import com.google.common.collect.Lists;
import com.simu.ilearn.common.shared.vo.LearnVO;
import com.simu.ilearn.server.business.Learn;
import com.simu.ilearn.server.repos.LearnRepo;
import com.simu.ilearn.server.service.LearnService;
import com.simu.ilearn.server.util.MyModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LearnServiceImpl implements LearnService {
    @Autowired
    private LearnRepo learnRepo;
    @Inject
    private MyModelMapper mapper;

    @Override
    public Long create(LearnVO entity) {
        entity.setCreated(new Date());
        return learnRepo.save(mapper.map(entity, Learn.class)).getId();
    }

    @Override
    public void delete(Long id) {
        learnRepo.delete(id);
    }

    @Override
    public void show(LearnVO myEntity) {
        System.out.println("this is a valid myEntity" + myEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LearnVO> loadAll() {
        List<LearnVO> result = Lists.newArrayList();
        for (Learn entity : learnRepo.findAll()) {
            result.add(mapper.map(entity, LearnVO.class));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public LearnVO loadOne(Long id) {
        return mapper.map(learnRepo.findOne(id), LearnVO.class);
    }
}
