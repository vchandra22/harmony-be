package com.hris.harmony.controller.v1;

import com.hris.harmony.constant.Constant;
import com.hris.harmony.dto.request.PositionRequest;
import com.hris.harmony.dto.request.SearchPositionRequest;
import com.hris.harmony.dto.response.PositionResponse;
import com.hris.harmony.service.PositionService;
import com.hris.harmony.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.POSITION_API)
@CrossOrigin(origins = "*")
public class PositionController {
    private final PositionService positionService;
    
    @GetMapping
    public ResponseEntity<?> findAllPositions(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "name") String sort
    ) {
        SearchPositionRequest searchPositionRequest = SearchPositionRequest.builder()
                .name(name)
                .page(page)
                .size(size)
                .sort(sort)
                .build();
        
        Page<PositionResponse> positionPage = positionService.getAllPositions(searchPositionRequest);
        
        return ResponseUtil.buildPageResponse(HttpStatus.OK, Constant.SUCCESS_GET_ALL_POSITION, positionPage);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPositionById(@PathVariable String id) {
        PositionResponse positionResponse = positionService.getPositionById(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_GET_POSITION_BY_ID, positionResponse);
    }
    
    @PostMapping
    public ResponseEntity<?> createPosition(@Valid @RequestBody PositionRequest positionRequest) {
        PositionResponse positionResponse = positionService.createPosition(positionRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_POSITION, positionResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePosition(@PathVariable String id, @Valid @RequestBody PositionRequest positionRequest) {
        PositionResponse positionResponse = positionService.updatePosition(id, positionRequest);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_POSITION, positionResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable String id) {
        positionService.deletePosition(id);
        
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_POSITION, null);
    }
}
