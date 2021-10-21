package com.behl.dragonera.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.behl.dragonera.entity.Character;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CharacterService {

    private final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Character> findAllMatching(final String keyword, Integer pageNumber, Integer count) {
        if (keyword == null) {
            return entityManager.createNativeQuery("SELECT * FROM characters", Character.class).getResultList();
        }
        if (count == null) {
            count = 10;
        }
        if (pageNumber == null) {
            pageNumber = 1;
        }

        final var query = entityManager.createNativeQuery("SELECT * FROM characters WHERE MATCH(name,bio) AGAINST(?1)",
                Character.class);
        query.setParameter(1, keyword);
        query.setFirstResult((pageNumber - 1) * count);
        query.setMaxResults(count);

        return query.getResultList();
    }

}
