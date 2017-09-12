package com.ymens.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbUtil {
   private static final SessionFactory sessionFactory = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            try {
                Configuration configuration = new Configuration();
                configuration.configure();

                return configuration
                        .buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(
                        "There was an error building the factory");
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
    }