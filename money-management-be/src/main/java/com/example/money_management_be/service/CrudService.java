package com.example.money_management_be.service;

import com.example.money_management_be.dto.BaseDto;
import com.example.money_management_be.entity.BaseEntity;
import com.example.money_management_be.exception.exceptionClasses.ResourceNotFoundException;
import com.example.money_management_be.mapper.ResourceEntityTransformer;
import com.example.money_management_be.repository.BaseRepository;
import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface CrudService<D extends BaseDto, E extends BaseEntity> {

    BaseRepository<E, Long> repository();

    ResourceEntityTransformer<D, E> resourceTransformer();

    default D save(D dto) {
        return resourceTransformer().transformToResource(repository().save(resourceTransformer().transformToEntity(dto)));
    }

    default D saveAndFlush(D dto) {
        Optional<E> entityOpt = repository().findById(dto.getId());

        if (entityOpt.isEmpty()) {
            throw new ResourceNotFoundException("id", dto.getId().toString());
        }

        return resourceTransformer().transformToResource(entityOpt.get());
    }

    default E saveAndFlushEntity(D dto) {
        return repository().saveAndFlush(resourceTransformer().transformToEntity(dto));
    }

    default D findById(Long id) {
        E entity = repository().findById(id).orElseThrow(() -> new ResourceNotFoundException("id", id.toString()));
        return resourceTransformer().transformToResource(entity);
    }

    default Page<D> findAll(Predicate predicate, Pageable pageable) {
        return repository().findAll(predicate, pageable).map(e -> resourceTransformer().transformToResource(e));
    }

    default List<E> findAllById(List<Long> ids) {
        return repository().findAllById(ids);
    }

    default D deleteById(Long id) {
        Optional<E> optionalE = repository().findById(id);
        if (optionalE.isPresent()) {
            repository().deleteById(id);
        }

        return resourceTransformer().transformToResource(optionalE.orElseThrow(() -> new ResourceNotFoundException("id", id.toString())));
    }

    @Transactional
    default void deleteAll(List<Long> ids) {
        repository().deleteAllById(ids);
    }
}
