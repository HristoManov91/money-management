package com.example.money_management_be.validation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.reflections.ReflectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

    //TODO test

    private final EntityManager entityManager;
    private Class<?> entityClass;
    private String dtoFieldName;

    public UniqueFieldValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        entityClass = constraintAnnotation.entityClass();
        dtoFieldName = constraintAnnotation.dtoFieldName();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        Object dtoFieldValue;
        try {
            dtoFieldValue = getObjectFieldValue(target, dtoFieldName);
        } catch (NoSuchElementException ex) {
            return true;
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> cq = criteriaBuilder.createQuery();
        Root<?> root = cq.from(entityClass);
        cq.where(criteriaBuilder.equal(root.get(dtoFieldName), dtoFieldValue));
        cq.select(root);

        TypedQuery<Object> typedQuery = entityManager.createQuery(cq);

        List<Object> resultSet = typedQuery.getResultList();

        return resultSet.isEmpty();
    }

    private Object getObjectFieldValue(Object target, String dtoFieldName) {

        Set<Method> methods =
            ReflectionUtils.getMethods(
                target.getClass(),
                ReflectionUtils.withName("get" + StringUtils.capitalize(dtoFieldName)),
                ReflectionUtils.withParametersCount(0));

        if (CollectionUtils.isEmpty(methods)) {
            if (target.getClass().getSuperclass() != null) {
                methods = ReflectionUtils.getMethods(
                    target.getClass().getSuperclass(),
                    ReflectionUtils.withName("get" + StringUtils.capitalize(dtoFieldName)),
                    ReflectionUtils.withParametersCount(0));
            }

            if (CollectionUtils.isEmpty(methods)) {
                log.error("No getter found for field {}", dtoFieldName);
                throw new RuntimeException();
            }
        }

        return methods.stream().findFirst().map(m -> {
            try {
                return m.invoke(target);
            } catch (Exception ex) {
                throw new RuntimeException();
            }
        }).get();
    }
}

