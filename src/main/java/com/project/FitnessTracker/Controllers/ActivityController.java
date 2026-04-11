package com.project.FitnessTracker.Controllers;

import com.project.FitnessTracker.DTO.ActivityRequest;
import com.project.FitnessTracker.DTO.ActivityResponse;
import com.project.FitnessTracker.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityService.trackActivity(request));

    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities (@RequestHeader(name = "X-User-ID") String UserId)
    {
        return ResponseEntity.ok(activityService.getUserActivites(UserId));
    }

}
