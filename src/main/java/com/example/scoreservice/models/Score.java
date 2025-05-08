package com.example.scoreservice.models;

import com.example.scoreservice.ScoreController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "score", schema = "myquizappdb")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    private Integer id;

    @NotNull
    @Lob
    @Column(name = "user_id", nullable = false)
    private String userId;

    @NotNull
    @Column(name = "quiz_id", nullable = false)
    private Integer quizId;

    @NotNull
    @Column(name = "score", nullable = false)
    private Integer score;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

    public Score() {}

    public Score(ScoreController.ScoreDto scoreDto, String userId) {
        this.id = null;
        this.userId = userId;
        this.quizId = scoreDto.quizId();
        this.score = scoreDto.score();
        this.total = scoreDto.total();
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getQuiz() {return quizId;}

    public Integer getScore() {
        return score;
    }

    public Integer getTotal() {
        return total;
    }

}