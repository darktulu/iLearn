package com.simu.ilearn.server.repos;

import com.simu.ilearn.server.business.Learn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LearnRepo extends JpaRepository<Learn, Long>, JpaSpecificationExecutor<Learn> {
}
