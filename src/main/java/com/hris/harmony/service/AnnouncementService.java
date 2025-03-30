package com.hris.harmony.service;

import com.hris.harmony.dto.request.AnnouncementRequest;
import com.hris.harmony.dto.request.SearchAnnouncementRequest;
import com.hris.harmony.dto.response.AnnouncementResponse;
import com.hris.harmony.entity.Announcement;
import org.springframework.data.domain.Page;

public interface AnnouncementService {
    AnnouncementResponse createAnnouncement(AnnouncementRequest announcementRequest);
    Page<AnnouncementResponse> getAllAnnouncements(SearchAnnouncementRequest searchAnnouncementRequest);
    AnnouncementResponse getAnnouncementById(String id);
    AnnouncementResponse updateAnnouncement(String id, AnnouncementRequest announcementRequest);
    Announcement getOne(String id);
    void deleteAnnouncement(String id);
}
