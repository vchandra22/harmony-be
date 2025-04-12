package com.hris.harmony.specification;

import com.hris.harmony.dto.request.SearchAttendanceRequest;
import com.hris.harmony.entity.Attendance;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AttendanceSpecification {
    public static Specification<Attendance> getSpecification(SearchAttendanceRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getStartDate() != null && request.getEndDate() != null) {
                predicates.add(cb.between(root.get("date"),
                        request.getStartDate().atStartOfDay(),
                        request.getEndDate().plusDays(1).atStartOfDay()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
