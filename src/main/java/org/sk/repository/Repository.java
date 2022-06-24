package org.sk.repository;

import org.sk.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Entity, Integer> {
}
