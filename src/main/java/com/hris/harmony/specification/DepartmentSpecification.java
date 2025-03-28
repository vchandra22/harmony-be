package com.hris.harmony.specification;

import com.hris.harmony.dto.request.SearchDepartmentRequest;
import com.hris.harmony.dto.request.SearchPositionRequest;
import com.hris.harmony.entity.Department;
import com.hris.harmony.entity.Position;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DepartmentSpecification {
    public static Specification<Department> getSpecification(SearchDepartmentRequest searchDepartmentRequest) {
        return new Specification<Department>() {

            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(searchDepartmentRequest.getName())) {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + searchDepartmentRequest.getName().toLowerCase() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
