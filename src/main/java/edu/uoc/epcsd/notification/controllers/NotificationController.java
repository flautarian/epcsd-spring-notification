package edu.uoc.epcsd.notification.controllers;

import edu.uoc.epcsd.notification.pojos.Category;
import edu.uoc.epcsd.notification.pojos.User;
import edu.uoc.epcsd.notification.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private UserService userService;
    @PostMapping(path = "/enviarNotificacio", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void enviarNotificacio(@PathVariable("idUser") Long idUser) {
        // send email / push notification / etc.
        List<User> userList = userService.getUsersByFavouriteCategory(new Category());
        log.info("Notifying the user \"" + userList.get(0).getFullName() + "\"");
    }

}
