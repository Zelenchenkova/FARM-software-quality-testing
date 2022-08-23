package com.example.farmingproject.security.tracking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    public void saveTracking(Tracking track) {
        trackingRepository.save(track);
    }

    public List<Tracking> findAllTracks() {
        return (List<Tracking>) trackingRepository.findAll();
    }
}
