package com.ymens.dao;

import com.ymens.hibernate.HbUtil;
import com.ymens.hibernate.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManageUser {
    private static SessionFactory factory;
    public static Integer addUser(String username, String password, String realname, String email){
        factory = HbUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userId = null;
        try{
            tx = session.beginTransaction();
           int type = 1;
           Users user = new Users();
           user.setUsername(username);
           user.setEmail(email);
           user.setPassword(password);
           user.setRealName(realname);
           user.setType(type);
           userId = (Integer) session.save(user);
           tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userId;
    }
}
