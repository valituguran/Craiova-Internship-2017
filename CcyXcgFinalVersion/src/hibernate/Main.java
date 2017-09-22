package hibernate;

import hibernate.Currencypairs;
import hibernate.HibernateUtil;
import hibernate.Users;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Configuration configuration = new Configuration();
        Currencypairs object = new Currencypairs();
        Users user = new Users();
        user.setBalance(100.0);
        user.setCurrency("EUR");
        user.setEmail("email");
        user.setId(111);
        user.setName("alexz");
        user.setPassword("123a");
        user.setType(0);
        user.setUsername("alexz");
        session.save(user);
        session.getTransaction().commit();
        session.close();


    }
}
