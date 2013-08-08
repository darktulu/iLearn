package com.simu.ilearn.server.repos.spec;

import com.simu.ilearn.server.business.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpec {
    public static Specification<User> usernameIs(final String username) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("username"), username);
            }
        };
    }

    public static Specification<User> emailIs(final String email) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("email"), email);
            }
        };
    }

    public static Specification<User> lastNameLike(final String lastName) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("lastName"), lastName);
            }
        };
    }

    public static Specification<User> firstNameLike(final String firstName) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("firstName"), firstName);
            }
        };
    }

    public static Specification<User> statusIn(final User.Status... statut) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return root.<User.Status>get("status").in(statut);
            }
        };
    }
}
