package com.project.FitnessTracker.Service;

import com.project.FitnessTracker.DTO.ActivityRequest;
import com.project.FitnessTracker.DTO.ActivityResponse;
import com.project.FitnessTracker.Repositories.ActivityRepository;
import com.project.FitnessTracker.Repositories.UserRepository;
import com.project.FitnessTracker.model.Activity;
import com.project.FitnessTracker.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    @Autowired
    public ActivityService(ActivityRepository activityRepository,UserRepository userRepository) {
        this.userRepository=userRepository;
        this.activityRepository = activityRepository;
    }

    public ActivityResponse trackActivity(ActivityRequest request)
    {
        User givenUser=userRepository.findById(request.getUserId()).orElseThrow(()->new RuntimeException("Invalid user with id: "+request.getUserId()));
        Activity temp= Activity.builder()
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurnt(request.getCaloriesBurnt())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .user(givenUser)
                .build();
        Activity savedActivity=activityRepository.save(temp);
        return mapToResponse(savedActivity);
    }
    private ActivityResponse mapToResponse(Activity activity)
    {
        ActivityResponse response=new ActivityResponse();
        response.setId(activity.getId());
        response.setType(activity.getType());
        response.setUserId(activity.getUser().getId());
        response.setCaloriesBurnt(activity.getCaloriesBurnt());
        response.setDuration(activity.getDuration());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        return response;

    }


    public List<ActivityResponse> getUserActivites(String userId) {
        List<Activity> activityList=activityRepository.findByUserId(userId);
        return activityList.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
}
