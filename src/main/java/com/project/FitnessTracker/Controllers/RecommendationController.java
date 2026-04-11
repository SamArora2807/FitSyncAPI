package com.project.FitnessTracker.Controllers;

import com.project.FitnessTracker.DTO.RecommendationRequest;
import com.project.FitnessTracker.Service.RecommendationService;
import com.project.FitnessTracker.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest request)
    {

        Recommendation recommendation1=recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation1);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId)
    {
        List<Recommendation> recommendationList=recommendationService.getUserRecommendation(userId);
        return ResponseEntity.ok(recommendationList);
    }
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String activityId)
    {
        List<Recommendation> recommendationList=recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationList);
    }
}
