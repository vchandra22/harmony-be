package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.PositionRequest;
import com.hris.harmony.dto.response.PositionResponse;
import com.hris.harmony.entity.Position;
import com.hris.harmony.repository.PositionRepository;
import com.hris.harmony.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    
    @Override
    public PositionResponse createPosition(PositionRequest positionRequest) {
        return null;
    }

    @Override
    public List<PositionResponse> getAllPositions() {
        return List.of();
    }

    @Override
    public PositionResponse getPositionById(String positionId) {
        return null;
    }

    @Override
    public PositionResponse updatePosition(String positionId, PositionRequest positionRequest) {
        return null;
    }

    @Override
    public Position getOne(String id) {
        return null;
    }

    @Override
    public void deletePosition(String positionId) {

    }
}
