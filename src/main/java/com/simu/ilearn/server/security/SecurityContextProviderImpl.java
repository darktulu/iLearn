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

package com.simu.ilearn.server.security;

import com.simu.ilearn.common.shared.vo.UserVO;
import com.simu.ilearn.server.repos.UserRepo;
import com.simu.ilearn.server.util.MyModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Component
public class SecurityContextProviderImpl implements SecurityContextProvider {
    @Inject
    private UserRepo userRepos;
    @Inject
    private MyModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserVO getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            String email = securityContext.getAuthentication().getName();
            return mapper.map(userRepos.findByEmail(email), UserVO.class);
        }

        return null;
    }
}
