package dao;

import java.sql.*;

public class PaginationDao {
    public static int pagination(int pageindex) {
        return pageindex*10;
    }
    public static int add(int pageindex){
        if(ParseDao.pairs.size()>pagination(pageindex))
        return ++pageindex;
        else
            return pageindex;
    }
    public static int minus(int pageindex){
        if(pageindex > 1)
        return --pageindex;
        else
            return pageindex;
    }

}