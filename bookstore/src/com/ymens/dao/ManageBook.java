package com.ymens.dao;


import com.ymens.hibernate.Books;
import com.ymens.hibernate.HbUtil;
import org.hibernate.*;

import java.util.LinkedList;
import java.util.List;


public class ManageBook {
    private static SessionFactory factory;

    public static Integer addBook(String name, int author_id, long isbn, double price, String description, byte[] image){
        factory = HbUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer bookId = null;
        try{

            tx = session.beginTransaction();
            Books book = new Books(name, author_id, isbn, price, description, image);
            bookId = (Integer) session.save(book);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return bookId;
    }

    public static List listBooks( ){
        factory = HbUtil.getSessionFactory();
        Session session = factory.openSession();
        LinkedList<Books> listBooks = new LinkedList <>();
        Books book = new Books();
        List<Books[]> books = new LinkedList<>();
        try{

           Query query=session.createQuery("from com.ymens.Books ");
            books=(List<Books[]>) query.list();
           /* for (Iterator iterator = books.iterator(); iterator.hasNext();) {
               book = (Books) iterator.next();
               listBooks.add(book);
           }*/

        }catch (HibernateException e) {
           e.printStackTrace();
        }finally {
            session.close();
        }
        return books;
    }
}


