package com.example.scoreservice.repos;

import com.example.scoreservice.models.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score,Integer> {
    List<Score> findAllByUserIdEquals(String userId);
}
