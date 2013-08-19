package com.simu.ilearn.server.repos.spec;

import com.simu.ilearn.server.business.Learn;
import com.simu.ilearn.server.business.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LearnSpec {
    public static Specification<Learn> ownerIs(final User user) {
        return new Specification<Learn>() {
            @Override
            public Predicate toPredicate(Root<Learn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.<User>get("owner"), user);
            }
        };
    }

    public static Specification<Learn> titleLike(final String title) {
        return new Specification<Learn>() {
            @Override
            public Predicate toPredicate(Root<Learn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("title"), title);
            }
        };
    }

    public static Specification<Learn> contentLike(final String content) {
        return new Specification<Learn>() {
            @Override
            public Predicate toPredicate(Root<Learn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("content"), content);
            }
        };
    }

    public static Specification<Learn> statusIn(final Learn.Status... status) {
        return new Specification<Learn>() {
            @Override
            public Predicate toPredicate(Root<Learn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return root.<Learn.Status>get("status").in(status);
            }
        };
    }
}
