package com.java.spring.service.impl;

import com.java.spring.entity.UserModel;
import com.java.spring.repository.IUserRepository;
import com.java.spring.service.IUserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository IUserRepository;
    @Override
    public List<UserModel> listAll(int caseMap) {
        List<UserModel> list = new ArrayList<>();
        switch (caseMap) {
            case 1:
                // way 1: JPA load the persistence.xml file
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_file"); // search persistence.xml on resources/META-INF/*
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    TypedQuery<UserModel> empByDeptQuery = (TypedQuery<UserModel>) entityManager.createQuery("select u from UserModel u", UserModel.class);
                    for (UserModel user : empByDeptQuery.getResultList()) {
                        list.add(user);
                        System.out.println(user);
                    }
                    transaction.commit();
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
                break;
            case 2:
                // way 2: hibernate load hibernate.cfg.xml
                SessionFactory sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(UserModel.class)
                        .buildSessionFactory();
                Session session = sessionFactory.openSession();
                try {
                    System.out.println("findall");
                    session.beginTransaction();
                    list = session.createQuery("select u from UserModel u").list();
                    for (UserModel usr : list) {
                        System.out.println(usr);
                    }
                    System.out.println("findall");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    session.close();
                    sessionFactory.close();
                }
                break;
            case 3:
                //way 3: jpaRepository
                return IUserRepository.findAll();

        }
        return list;
    }

    @Override
    public void insert(UserModel usr, int caseMap) {
        switch (caseMap) {
            case 1:
                // way 1: JPA load the persistence.xml file // NOT RUN
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_file"); // search persistence.xml on resources/META-INF/*
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
//                    entityManager.createQuery("insert into user (id, name, email, phone) values (20, 'test', 'email', 'phone')");
                    entityManager.createNativeQuery("INSERT INTO user (id, name, email, phone) VALUES (?, ?, ?, ?)", UserModel.class)
                            .setParameter(1, usr.getId())
                            .setParameter(2, usr.getName())
                            .setParameter(3, usr.getEmail())
                            .setParameter(4, usr.getPhone());
                    transaction.commit();
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
                break;
            case 2:
                // way 2: hibernate load hibernate.cfg.xml // NOT RUN
                SessionFactory sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(UserModel.class)
                        .buildSessionFactory();
                Session session = sessionFactory.openSession();
                try {
                    session.beginTransaction();
                    session.persist(usr);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    session.close();
                    sessionFactory.close();
                }
                break;
            case 3:
                //way 3: jpaRepository
                IUserRepository.save(usr);
        }
    }

    @Override
    public void update(UserModel usr, int caseMap) {
        switch (caseMap) {
            case 1:
                // way 1: JPA load the persistence.xml file. // NOT RUN in SQl command
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_file"); // search persistence.xml on resources/META-INF/*
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();
                try {
                    transaction.begin();
                    entityManager.createQuery("update user set name='name', email='email', phone='phone' where id=10", UserModel.class);
//                    entityManager.createQuery("update user set name=:name, email=:email, phone=:phone where id=:id", UserModel.class)
//                            .setParameter("name", usr.getName())
//                            .setParameter("email", usr.getEmail())
//                            .setParameter("phone", usr.getPhone())
//                            .setParameter("id", usr.getId());
                    transaction.commit();
                } finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }
                    entityManager.close();
                    entityManagerFactory.close();
                }
                break;
            case 2:
                // way 2: hibernate load hibernate.cfg.xml // NOT RUN
                SessionFactory sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(UserModel.class)
                        .buildSessionFactory();
                Session session = sessionFactory.openSession();
                try {
                    session.beginTransaction();
                    session.update(usr);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    session.close();
                    sessionFactory.close();
                }
                break;
            case 3:
                //way 3: jpaRepository
                IUserRepository.deleteById(usr.getId());
                IUserRepository.save(usr);
        }
    }

    @Override
    public void delete(UserModel usr, int caseMap) {
        switch (caseMap) {
            case 1:
                break;
            case 2:
                // way 2: hibernate load hibernate.cfg.xml
                SessionFactory sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(UserModel.class)
                        .buildSessionFactory();
                Session session = sessionFactory.openSession();
                try {
                    session.beginTransaction();
                    session.delete(usr);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    session.close();
                    sessionFactory.close();
                }
                break;
            case 3:
                //way 3: jpaRepository
                IUserRepository.deleteById(usr.getId());
                break;
        }
    }


}
