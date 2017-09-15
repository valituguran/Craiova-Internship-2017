package dao;

import java.sql.*;

public class PaginationDao {
    public static int pagination(int pageindex) {
        if(ParseDao.pairs.size()>pageindex*10){
            return pageindex*10;
        }
        else return ParseDao.pairs.size();
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
    public static int paginationCurrency(int pageindex) {
        if(SearchCurrencyDao.list1.size()>pageindex*10){
            return pageindex*10;
        }
        else return SearchCurrencyDao.list1.size();
    }
    public static int addCurrency(int pageindex){
        if(SearchCurrencyDao.list1.size()>paginationCurrency(pageindex))
            return ++pageindex;
        else
            return pageindex;
    }
    public static int minusCurrency(int pageindex){
        if(pageindex > 1)
            return --pageindex;
        else
            return pageindex;
    }

}