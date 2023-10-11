package com.videosTek.backend.api;


import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/topic")
public class TaskProgressionController {

    @PostMapping(path = "new/:id")
    public ResponseEntity<String> createTopic(@PathParam("id") Integer id){


        return null;
    }

}
