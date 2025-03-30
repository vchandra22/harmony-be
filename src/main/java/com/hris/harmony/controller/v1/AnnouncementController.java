package com.hris.harmony.controller.v1;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.dto.request.AnnouncementRequest;
import com.hris.harmony.dto.request.SearchAnnouncementRequest;
import com.hris.harmony.dto.response.AnnouncementResponse;
import com.hris.harmony.service.AnnouncementService;
import com.hris.harmony.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.ANNOUNCEMENT_API)
@CrossOrigin(origins = "*")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    
    @GetMapping
    public ResponseEntity<?> findAllAnnouncements(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "title") String sort
    ) {
        SearchAnnouncementRequest searchAnnouncementRequest = SearchAnnouncementRequest.builder()
                .title(title)
                .page(page)
                .size(size)
                .sort(sort)
                .build();

        Page<AnnouncementResponse> announcementResponsePage = announcementService.getAllAnnouncements(searchAnnouncementRequest);
        
        return ResponseUtil.buildPageResponse(HttpStatus.OK, Constant.SUCCESS_GET_ALL_ANNOUNCEMENT, announcementResponsePage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable("id") String id) {
        AnnouncementResponse announcementResponse = announcementService.getAnnouncementById(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_ANNOUNCEMENT_BY_ID, announcementResponse);
    }
    
    @PostMapping
    public ResponseEntity<?> createAnnouncement(@Valid @RequestBody AnnouncementRequest announcementRequest) {
        AnnouncementResponse announcementResponse = announcementService.createAnnouncement(announcementRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_CREATE_ANNOUNCEMENT, announcementResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable String id, @Valid @RequestBody AnnouncementRequest announcementRequest) {
        AnnouncementResponse announcementResponse = announcementService.updateAnnouncement(id, announcementRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_ANNOUNCEMENT, announcementResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable String id) {
        announcementService.deleteAnnouncement(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_ANNOUNCEMENT, null);
    }
}
