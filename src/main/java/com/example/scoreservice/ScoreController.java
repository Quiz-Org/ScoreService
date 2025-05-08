package com.example.scoreservice;

import com.example.scoreservice.models.Score;
import com.example.scoreservice.repos.ScoreRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreController {

    final ScoreRepository scoreRepository;

    public ScoreController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @GetMapping("/me")
    public UserInfoDto getGreeting(JwtAuthenticationToken auth) {
        return new UserInfoDto(
                auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME),
                auth.getToken().getClaimAsString(StandardClaimNames.SUB),
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }


    @PostMapping("/score")
    public ScoreDto sendScore(JwtAuthenticationToken auth,@RequestBody ScoreDto score){
        String userId = auth.getToken().getClaimAsString(StandardClaimNames.SUB);
        scoreRepository.save(new Score(score,userId));
        return score;
    }

    @GetMapping("/score")
    public List<Score> getScores(JwtAuthenticationToken auth){
        return scoreRepository.findAllByUserIdEquals(auth.getToken().getClaimAsString(StandardClaimNames.SUB));
    }

    public record UserInfoDto(String name,String id, List roles) {}
    public record ScoreDto(Integer quizId, Integer score, Integer total) {}
}