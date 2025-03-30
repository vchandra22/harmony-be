package com.hris.harmony.repository;

import com.hris.harmony.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnnouncementRepository extends JpaRepository<Announcement, String>, JpaSpecificationExecutor<Announcement> {
}
