package com.socialmovie.backend.api;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/video")
public class MovieController {

    @PostMapping
    public void postNewMovie(@RequestBody Class<?> body){
        System.out.println(body);
    }
}
