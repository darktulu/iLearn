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

package com.simu.ilearn.app.client.security;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.simu.ilearn.app.client.rest.AuthenticationService;
import com.simu.ilearn.common.client.rest.AsyncCallbackImpl;
import com.simu.ilearn.common.client.util.CallbackImpl;
import com.simu.ilearn.common.shared.dispatch.GetResult;
import com.simu.ilearn.common.shared.vo.UserVO;

import javax.inject.Inject;

public class CurrentUserProvider {
    private final DispatchAsync dispatcher;
    private final AuthenticationService authenticationService;

    private UserVO currentUser;

    @Inject
    CurrentUserProvider(DispatchAsync dispatcher,
                        AuthenticationService authenticationService) {
        this.dispatcher = dispatcher;
        this.authenticationService = authenticationService;
    }

    public void load(final CallbackImpl<UserVO> callback) {
        dispatcher.execute(authenticationService.currentUser(), new AsyncCallbackImpl<GetResult<UserVO>>() {
            @Override
            public void onReceive(GetResult<UserVO> response) {
                currentUser = response.getPayload();
                callback.onSuccess(response.getPayload());
            }
        });
    }

    public UserVO get() {
        return currentUser;
    }
}
