package com.hpms.repositories.inmemory;

import com.hpms.repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public abstract class AbstractInMemoryRepository<T, ID> implements Repository<T, ID> {
    private final Map<ID, T> storage;
    private final Function<T, ID> idExtractor;

    protected AbstractInMemoryRepository(Function<T, ID> idExtractor) {
        this.storage = new ConcurrentHashMap<>();
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) {
        storage.put(idExtractor.apply(entity), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(ID id) {
        storage.remove(id);
    }
}