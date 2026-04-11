package com.project.FitnessTracker.Service;


import com.project.FitnessTracker.DTO.RecommendationRequest;
import com.project.FitnessTracker.Repositories.ActivityRepository;
import com.project.FitnessTracker.Repositories.RecommendationRepository;
import com.project.FitnessTracker.Repositories.UserRepository;
import com.project.FitnessTracker.model.Activity;
import com.project.FitnessTracker.model.Recommendation;
import com.project.FitnessTracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    public Recommendation generateRecommendation(RecommendationRequest request) {
        User tempuser = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found.."));
        Activity tempactivity = activityRepository.findById(request.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not found"));
        Recommendation temp = Recommendation.builder().user(tempuser)
                .activity(tempactivity)
                .suggestions(request.getSuggestions())
                .improvements(request.getImprovements())
                .safety(request.getSafety())
                .build();
        return recommendationRepository.save(temp);
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
