package com.ymens.spring.interfaces;

import com.ymens.spring.beans.User;

public interface IUser {

     boolean validateUser(String name, String password);

      int getid(User user) ;
      User getUser(String name, String password);

     int getId(String name, String password) ;

     int insertUser(User u);
    int UpdatePassword(String pass, String user);

     int UpdateEmail(String email, String user);

     int UpdateRealName(String rn, String user);


    String getRealName(String n, String p);
}
