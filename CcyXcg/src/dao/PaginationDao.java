package dao;

import java.sql.*;

public class PaginationDao {
    public static int pagination(int pageindex) {
        return pageindex*10;
    }
    public static int add(int pageindex){

        return pageindex++;
    }
    public static int minus(int pageindex){
        return pageindex--;
    }

}