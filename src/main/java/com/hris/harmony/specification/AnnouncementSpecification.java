package com.hris.harmony.specification;

import com.hris.harmony.dto.request.SearchAnnouncementRequest;
import com.hris.harmony.dto.request.SearchDepartmentRequest;
import com.hris.harmony.entity.Announcement;
import com.hris.harmony.entity.Department;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementSpecification {
    public static Specification<Announcement> getSpecification(SearchAnnouncementRequest searchAnnouncement) {
        return new Specification<Announcement>() {

            public Predicate toPredicate(Root<Announcement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(searchAnnouncement.getTitle())) {
                    predicates.add(cb.like(cb.lower(root.get("title")), "%" + searchAnnouncement.getTitle().toLowerCase() + "%"));
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
