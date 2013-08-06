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

package com.simu.ilearn.server.service;

import com.simu.ilearn.common.shared.vo.LearnVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface LearnService {
    Long create(LearnVO learn);

    void delete(Long id);

    List<LearnVO> loadAll();

    void show(@Valid LearnVO learn);
}