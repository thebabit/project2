package com.revature.movie.repos;

import com.revature.movie.exceptions.InvalidRequestException;
import com.revature.movie.model.FavoriteList;
import com.revature.movie.model.User;
import com.revature.movie.util.HibernateUtil;
import javafx.collections.transformation.FilteredList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserRepos implements CrudRepository {




    public void updateFavoriteList (FavoriteList l, int id){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User u = session.load(User.class, id);
        u.addLists(l);
        session.update(u);
        session.getTransaction().commit();
        session.close();


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
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();



            CriteriaBuilder queryBuilder = session.getCriteriaBuilder();


            CriteriaQuery<User> critQuery = queryBuilder.createQuery(User.class);


            Root<User> queryRoot = critQuery.from(User.class);


            critQuery.select(queryRoot);


            critQuery.where(
                    queryBuilder.equal(queryRoot.get("username"), username),
                    queryBuilder.equal(queryRoot.get("password"), password)
            );

//            critQuery.where(
//                    queryBuilder.equal(queryRoot.get("password"), password)
//            );

             List<User> userList = session.createQuery(critQuery).getResultList();
             u = userList.get(0);
                return u;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;

    }


    public void register(User u){
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();

            session.save(u);

            session.getTransaction().commit();



        } catch (Exception e){
            System.out.println("This username is already taken");
        }
    }

    public User findUserById(int id){

        User l = new User();

        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {

            session.beginTransaction();

            // .get() returns the actual persistent object associated with the DB records (eagerly-fetched)
            l = session.get(User.class, id); // returns null if not found


        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;

    }


    @Override
    public void save(Object newObj) {

    }

    @Override
    public Set findAll() {
        return null;
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
