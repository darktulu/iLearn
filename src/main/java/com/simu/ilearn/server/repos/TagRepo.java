package com.simu.ilearn.server.repos;

import com.simu.ilearn.server.business.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepo extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
}
