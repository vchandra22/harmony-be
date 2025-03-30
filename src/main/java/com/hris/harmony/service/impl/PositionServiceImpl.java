package com.hris.harmony.service.impl;

import com.hris.harmony.dto.request.PositionRequest;
import com.hris.harmony.dto.request.SearchPositionRequest;
import com.hris.harmony.dto.response.PositionResponse;
import com.hris.harmony.entity.Position;
import com.hris.harmony.repository.PositionRepository;
import com.hris.harmony.service.PositionService;
import com.hris.harmony.specification.PositionSpecification;
import com.hris.harmony.util.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Override
    public PositionResponse createPosition(PositionRequest positionRequest) {
        Position position = Position.builder()
                .name(positionRequest.getName())
                .build();

        positionRepository.saveAndFlush(position);

        return toPositionResponse(position);
    }

    @Override
    public Page<PositionResponse> getAllPositions(SearchPositionRequest searchPositionRequest) {
        Pageable positionPageable = PageRequest.of(
                (searchPositionRequest.getPage() - 1),
                searchPositionRequest.getSize(),
                SortUtil.parseSortFromQueryParam(searchPositionRequest.getSort())
        );

        Specification<Position> positionSpecification = PositionSpecification.getSpecification(searchPositionRequest);
        Page<Position> positionPage = positionRepository.findAll(positionSpecification, positionPageable);

        return positionPage.map(this::toPositionResponse);
    }

    @Override
    public PositionResponse getPositionById(String id) {
        Position position = getOne(id);

        return toPositionResponse(position);
    }

    @Override
    public PositionResponse updatePosition(String id, PositionRequest positionRequest) {
        Position currentPosition = getOne(id);

        currentPosition.setId(id);
        currentPosition.setName(positionRequest.getName());

        positionRepository.save(currentPosition);

        return toPositionResponse(currentPosition);
    }

    @Override
    public Position getOne(String id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position with id " + id + " not found"));
    }

    @Override
    public void deletePosition(String id) {
        positionRepository.deleteById(id);
    }

    private PositionResponse toPositionResponse(Position position) {
        PositionResponse positionResponse = new PositionResponse();
        
        positionResponse.setId(position.getId());
        positionResponse.setName(position.getName());

        return positionResponse;
    }
}
