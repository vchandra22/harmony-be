package com.hris.harmony.service;

import com.hris.harmony.dto.request.PositionRequest;
import com.hris.harmony.dto.request.SearchPositionRequest;
import com.hris.harmony.dto.response.PositionResponse;
import com.hris.harmony.entity.Position;
import org.springframework.data.domain.Page;

public interface PositionService {
    PositionResponse createPosition(PositionRequest positionRequest);

    Page<PositionResponse> getAllPositions(SearchPositionRequest searchPositionRequest);

    PositionResponse getPositionById(String id);

    PositionResponse updatePosition(String id, PositionRequest positionRequest);

    Position getOne(String id);

    void deletePosition(String id);

}
