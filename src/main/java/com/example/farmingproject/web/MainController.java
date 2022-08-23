package com.example.farmingproject.web;

import com.example.farmingproject.security.MyUserDetails;
import com.example.farmingproject.security.tracking.Tracking;
import com.example.farmingproject.security.tracking.TrackingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sound.midi.Track;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@AllArgsConstructor
public class MainController {

    private final TrackingService trackingService;

    @GetMapping("")
    public String showHomePage(){
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Tracking track = new Tracking(user.getUsername(), String.valueOf(user.getAuthorities()), LocalDateTime.now());
        trackingService.saveTracking(track);
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/userActivity")
    public String getLoggedUsers(Model model) {
        List<Tracking> trackings = trackingService.findAllTracks();
        model.addAttribute("tracks", trackings);
        return "users";
    }
}