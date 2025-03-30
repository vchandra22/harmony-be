package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.AnnouncementRequest;
import com.hris.harmony.dto.request.SearchAnnouncementRequest;
import com.hris.harmony.dto.response.AnnouncementResponse;
import com.hris.harmony.entity.Announcement;
import com.hris.harmony.repository.AnnouncementRepository;
import com.hris.harmony.service.AnnouncementService;
import com.hris.harmony.specification.AnnouncementSpecification;
import com.hris.harmony.util.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    
    @Override
    public AnnouncementResponse createAnnouncement(AnnouncementRequest announcementRequest) {
        Announcement announcement = Announcement.builder()
                .title(announcementRequest.getTitle())
                .content(announcementRequest.getContent())
                .build();
        
        announcementRepository.saveAndFlush(announcement);
        
        return toAnnouncementResponse(announcement);
    }

    @Override
    public Page<AnnouncementResponse> getAllAnnouncements(SearchAnnouncementRequest searchAnnouncementRequest) {
        Pageable announcementPageable = PageRequest.of(
                (searchAnnouncementRequest.getPage() - 1),
                searchAnnouncementRequest.getSize(),
                SortUtil.parseSortFromQueryParam(searchAnnouncementRequest.getSort())
                );

        Specification<Announcement> announcementSpecification = AnnouncementSpecification.getSpecification(searchAnnouncementRequest);
        Page<Announcement> announcementPage = announcementRepository.findAll(announcementSpecification, announcementPageable);
        
        return announcementPage.map(this::toAnnouncementResponse);
    }

    @Override
    public AnnouncementResponse getAnnouncementById(String id) {
        Announcement announcement = getOne(id);
        
        return toAnnouncementResponse(announcement);
    }

    @Override
    public AnnouncementResponse updateAnnouncement(String id, AnnouncementRequest announcementRequest) {
        Announcement currentAnnouncement = getOne(id);
        
        currentAnnouncement.setId(id);
        currentAnnouncement.setTitle(announcementRequest.getTitle());
        currentAnnouncement.setContent(announcementRequest.getContent());
        
        announcementRepository.save(currentAnnouncement);
        
        return toAnnouncementResponse(currentAnnouncement);
    }

    @Override
    public Announcement getOne(String id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No announcement found with id: " + id));
    }

    @Override
    public void deleteAnnouncement(String id) {
        announcementRepository.deleteById(id);
    }
    
    private AnnouncementResponse toAnnouncementResponse(Announcement announcement) {
        AnnouncementResponse announcementResponse = new AnnouncementResponse();
        
        announcementResponse.setId(announcement.getId());
        announcementResponse.setTitle(announcement.getTitle());
        announcementResponse.setContent(announcement.getContent());
        
        return announcementResponse;
    }
}
