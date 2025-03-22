package com.hris.harmony.repository;

import com.hris.harmony.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PositionRepository extends JpaRepository<Position, String>, JpaSpecificationExecutor<Position> {
}
