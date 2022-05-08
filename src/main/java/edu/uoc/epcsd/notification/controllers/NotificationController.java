package edu.uoc.epcsd.notification.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.epcsd.notification.pojos.Category;
import edu.uoc.epcsd.notification.pojos.Show;
import edu.uoc.epcsd.notification.pojos.User;
import edu.uoc.epcsd.notification.services.UserService;
import io.swagger.v3.core.util.Json;
import lombok.extern.log4j.Log4j2;
import nonapi.io.github.classgraph.json.JSONDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private UserService userService;
    @PostMapping(path = "/enviarNotificacio")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity enviarNotificacio(@RequestParam Long idShow) throws IOException {
        Show show = getShowById(idShow);
        if(Objects.nonNull(show)){
            processarNotificacions(show);
            return new ResponseEntity( "Notificacions processades correctament.", HttpStatus.OK);
        }
        else return new ResponseEntity( "Error: No s'ha trobat el show especificat", HttpStatus.OK);
    }

    private void processarNotificacions(Show show) {
        show.getCategories().stream().forEach(cat ->{
            List<User> userList = userService.getUsersByFavouriteCategory(cat);
            userList.stream().forEach(user -> {
                log.info("Notifying the user \"" + user.getFullName() + "\"");
            });
        });
    }

    private Show getShowById(Long idShow) throws IOException {
        URL url = new URL("http://localhost:18081/show/?idShow=" + idShow.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.connect();
        if(con.getResponseCode() == 200){
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            Show show = new ObjectMapper().readValue(sb.toString(), Show.class);
            return show;
        }
        return null;
    }

}
