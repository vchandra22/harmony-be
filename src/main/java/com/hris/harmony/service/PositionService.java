package com.hris.harmony.service;

import com.hris.harmony.dto.request.PositionRequest;
import com.hris.harmony.dto.response.PositionResponse;
import com.hris.harmony.entity.Position;

import java.util.List;

public interface PositionService {
    PositionResponse createPosition(PositionRequest positionRequest);
    List<PositionResponse> getAllPositions();
    PositionResponse getPositionById(String positionId);
    PositionResponse updatePosition(String positionId, PositionRequest positionRequest);
    Position getOne(String id);
    void deletePosition(String positionId);
    
}
