package my.app.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private final User user;

    public UserSpecification(User user) {
        this.user = user;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (user.getId() != null) predicates.add(criteriaBuilder.equal(root.get("id"), user.getId()));
        if (user.getFirstName() != null) predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + user.getFirstName() + "%"));
        if (user.getLastName() != null) predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + user.getLastName() + "%"));
        if (user.getEmail() != null) predicates.add(criteriaBuilder.like(root.get("email"), "%" + user.getEmail() + "%"));
        if (user.getAge() != null) predicates.add(criteriaBuilder.equal(root.get("age"), user.getAge()));
        Predicate[] predicatesArr = new Predicate[predicates.size()];
        predicates.toArray(predicatesArr);
        return criteriaBuilder.and(predicatesArr);
    }
}