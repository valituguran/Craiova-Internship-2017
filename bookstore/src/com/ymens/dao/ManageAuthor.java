package com.ymens.dao;


import com.ymens.hibernate.Authors;
import com.ymens.hibernate.HbUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class ManageAuthor {
    private static SessionFactory factory;

    public Integer addAuthor(String name, int age, String nationality, String description, long cnp){
        factory = HbUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer authorId = null;
        try{
            tx = session.beginTransaction();
            Authors author=new Authors(name, age, nationality, description, cnp);
            authorId = (Integer) session.save(author);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return authorId;
    }

    public static List listAuthors( ){
        factory = HbUtil.getSessionFactory();
        Session session = factory.openSession();

        List<Authors> authors = new LinkedList <>();
        try{

             authors = session.createQuery("FROM com.ymens.Authors ").list();
            /*for (Iterator iterator = authors.iterator(); iterator.hasNext();){
                Authors author = (Authors) iterator.next();
                listAuthors.add(author);
            }*/

        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return authors;
    }
}

