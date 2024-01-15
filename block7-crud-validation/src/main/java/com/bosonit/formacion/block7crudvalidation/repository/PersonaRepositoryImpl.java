package com.bosonit.formacion.block7crudvalidation.repository;

import com.bosonit.formacion.block7crudvalidation.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.bosonit.formacion.block7crudvalidation.controller.PersonaController.*;


public class PersonaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Page<PersonaOutputDto> findPersonaByManyFields(HashMap<String, Object> conditions, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Conteo de query que cumplan condiciones
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Persona> countRoot = countQuery.from(Persona.class);  //Entidad raiz
        countQuery.select(cb.count(countRoot));
        applyConditionsToQuery(countQuery, countRoot, conditions);

        // countTypedQuery (Ejecución del conteo)
        TypedQuery<Long> countTypedQuery = entityManager.createQuery(countQuery);
        Long totalCount = countTypedQuery.getSingleResult();

        // dataQuery (Consulta datos paginados)
        CriteriaQuery<Persona> dataQuery = cb.createQuery(Persona.class);
        Root<Persona> dataRoot = dataQuery.from(Persona.class);
        dataQuery.select(dataRoot);
        applyConditionsToQuery(dataQuery, dataRoot, conditions);

        // Ordenación
        if (pageable.getSort().isSorted()) {
            dataQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), dataRoot, cb));
        }

        // Ejecución de la consulta de datos con los límites(offset y pageSize)
        TypedQuery<Persona> typedQuery = entityManager.createQuery(dataQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        // Conversión a DTO
        List<Persona> personas = typedQuery.getResultList();
        List<PersonaOutputDto> personasOutputDto = personas.stream().map(Persona::personaToPersonaOutputDto).toList();

        return new PageImpl<>(personasOutputDto, pageable, totalCount);
    }

    //Condiciones de búsqueda
    private void applyConditionsToQuery(CriteriaQuery<?> query, Root<Persona> root, HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();
        conditions.forEach((field, value) -> {
            switch (field) {
                case "id_persona":
                    predicates.add(cb.equal(root.get(field), (Integer) value));
                    break;
                case "usuario":
                case "name":
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "created_date":
                    String dateCondition = (String) conditions.get("dateCondition");
                    switch (dateCondition) {
                        case GREATER_THAN:
                            predicates.add(cb.greaterThan(root.<Date>get(field), (Date) value));
                            break;
                        case LESS_THAN:
                            predicates.add(cb.lessThan(root.<Date>get(field), (Date) value));
                            break;
                        case EQUAL:
                            predicates.add(cb.equal(root.<Date>get(field), (Date) value));
                            break;
                    }
                    break;
            }
        });
        query.where(predicates.toArray(new Predicate[0]));
    }
}