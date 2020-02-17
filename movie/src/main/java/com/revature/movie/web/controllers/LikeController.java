package com.revature.movie.web.controllers;

import com.revature.movie.model.MovieLikes;
import com.revature.movie.services.LikeService;
import com.revature.movie.web.dtos.MovieLikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/like")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        super();
        this.likeService = likeService;
    }

    //TODO
    //@ResponseStatus
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void increaseLikes(@RequestBody MovieLikeDto movieLikes){
        likeService.incrementLikes(movieLikes);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer getLikes(@RequestBody MovieLikeDto movieLikeDto){
        MovieLikes movieLikes = likeService.getLikesService(movieLikeDto);
        return movieLikes.getLikes();
    }
}
