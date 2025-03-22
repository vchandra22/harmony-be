package com.hris.harmony.specification;

import com.hris.harmony.dto.request.SearchPositionRequest;
import com.hris.harmony.entity.Position;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PositionSpecification {
    public static Specification<Position> getSpecification(SearchPositionRequest searchPositionRequest) {
        return new Specification<Position>() {

            public Predicate toPredicate(Root<Position> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(searchPositionRequest.getName())) {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + searchPositionRequest.getName().toLowerCase() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
