package com.revature.movie.repos;


import com.revature.movie.model.MovieLikes;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LikeRepos {

    private SessionFactory sessionFactory;

    @Autowired
    public LikeRepos(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MovieLikes findLikesById(Integer apiId){
        Session session = sessionFactory.getCurrentSession();
        MovieLikes movieLikes = new MovieLikes();
        try {
        movieLikes = session.load(MovieLikes.class, apiId);
        return movieLikes;
        }catch(ObjectNotFoundException e){
            movieLikes.setLikes(0);
        }finally {
            return movieLikes;
        }
    }

    public MovieLikes add(MovieLikes movieLikes){
        Session session = sessionFactory.getCurrentSession();
        MovieLikes ml  = session.load(MovieLikes.class, movieLikes.getId());
        ml.setLikes(ml.getLikes()+1);
        session.save(ml);
        return ml;
    }
}
