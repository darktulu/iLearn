package com.simu.ilearn.server.service;

import com.simu.ilearn.common.shared.vo.TagVO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface TagService {
    List<TagVO> loadAll();
}
