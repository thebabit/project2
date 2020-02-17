package com.revature.movie.services;

import com.revature.movie.model.MovieLikes;
import com.revature.movie.repos.LikeRepos;
import com.revature.movie.web.dtos.MovieLikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {
    private LikeRepos likeRepos;

    @Autowired
    public LikeService(LikeRepos likeRepos) {
        super();
        this.likeRepos = likeRepos;
    }

    @Transactional
    public MovieLikes getLikesService(MovieLikeDto mld){
        return likeRepos.findLikesById(mld.getId());
    }

    @Transactional
    public void incrementLikes(MovieLikeDto movieLikeDto){
        MovieLikes movieLikes = new MovieLikes();
        movieLikes.setId(movieLikeDto.getId());
        movieLikes.setTitle(movieLikeDto.getTitle());
        movieLikes.add(movieLikeDto.getUserId());
        likeRepos.add(movieLikes);
    }
}
