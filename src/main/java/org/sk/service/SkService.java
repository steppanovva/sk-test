package org.sk.service;

import org.sk.entity.Entity;

public interface SkService {
    Entity update(int id, Entity updated);
    Entity findById(int id);
}
