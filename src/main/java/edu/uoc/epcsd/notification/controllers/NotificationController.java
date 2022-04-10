package edu.uoc.epcsd.notification.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody
    String getNotificationsTest(@PathVariable int id) {
        return "your id is " + id + "!!!";
    }

}
