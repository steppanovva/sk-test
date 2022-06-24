package org.sk.service.impl;

import org.sk.service.SkService;
import org.springframework.stereotype.Service;
import org.sk.entity.Entity;
import org.sk.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class SkServiceImpl implements SkService {
    private final Repository repository;

    @Autowired
    public SkServiceImpl(Repository tableRepository) {
        this.repository = tableRepository;
    }

    @Override
    public Entity update(int id, Entity updated) {
        if (!repository.existsById(id))
            return null;
        return repository.save(updated);
    }

    @Override
    public Entity findById(int id) {
        return repository.findById(id).orElse(null);
    }
}
