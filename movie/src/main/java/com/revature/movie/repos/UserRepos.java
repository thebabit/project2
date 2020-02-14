package com.revature.movie.repos;

import com.revature.movie.model.FavoriteList;
import com.revature.movie.model.User;
import com.revature.movie.web.dtos.Credentials;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepos implements CrudRepository {


    private SessionFactory sessionFactory;

    public User findUserByCredentials(Credentials creds) {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser au where au.username = :un and au.password = :pw", User.class)
                .setParameter("un", creds.getUsername())
                .setParameter("pw", creds.getPassword())
                .getSingleResult();

    }

    @Autowired
    public UserRepos(SessionFactory factory) {
        super();
        this.sessionFactory = factory;
    }


    public void updateFavoriteList (FavoriteList l, int id){

        Session session = sessionFactory.getCurrentSession();
        User u = session.load(User.class, id);
        u.addLists(l);
        session.update(u);

    }


    public List<FavoriteList> findFavoriteByUserId(int id){
        List<FavoriteList> fl = new ArrayList<>();
        User u = findUserById(id);

        for(FavoriteList l : u.getList()){
            fl.add(l);
        }



        return  fl;
    }

    public User auth(String username, String password) {

        User u = new User();
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder queryBuilder = session.getCriteriaBuilder();


        CriteriaQuery<User> critQuery = queryBuilder.createQuery(User.class);


        Root<User> queryRoot = critQuery.from(User.class);


        critQuery.select(queryRoot);


        critQuery.where(
                queryBuilder.equal(queryRoot.get("username"), username),
                queryBuilder.equal(queryRoot.get("password"), password)
        );

        List<User> userList = session.createQuery(critQuery).getResultList();
        u = userList.get(0);
        return u;

    }


    public void register(User u){
        Session session = sessionFactory.getCurrentSession();
        session.save(u);

    }

    public User findUserById(int id){

        User l = new User();
        Session session = sessionFactory.getCurrentSession();
        l = session.get(User.class, id);

        return l;

    }


    @Override
    public void save(Object newObj) {

    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AppUser", User.class).getResultList();
    }

    @Override
    public Optional findById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean update(Object updatedObj) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
