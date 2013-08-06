package com.simu.ilearn.server.service;

import com.simu.ilearn.common.shared.vo.UserVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface RegistrationService {
    Long create(UserVO user);

    void delete(Long id);

    void show(@Valid UserVO user);
}
