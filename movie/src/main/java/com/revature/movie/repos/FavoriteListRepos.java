package com.revature.movie.repos;

import com.revature.movie.model.FavoriteList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteListRepos  {

    private SessionFactory sessionFactory;

    @Autowired
    public FavoriteListRepos(SessionFactory factory) {
        super();
        this.sessionFactory = factory;
    }

    public FavoriteList findById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(FavoriteList.class, id);

    }
}
