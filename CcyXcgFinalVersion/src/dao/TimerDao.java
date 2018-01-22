package dao;
import hibernate.HibernateUtil;
import org.hibernate.SessionFactory;
import com.ymens.DeleteDao;
import com.ymens.ParseFunction;
import hibernate.Currencypairs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.*;

/**
 * Created by lucian.Nicolescu on 9/13/2017.
 */
public class TimerDao extends TimerTask {

    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());
        String a = "E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\download.xml";
        String url = "http://www.bnr.ro/nbrfxrates.xml";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Currencypairs object = new Currencypairs();
        object.setValues(10.0);
        object.setPair("Ionut");
        session.save(object);
        session.getTransaction().commit();
        session.close();


        /*try {
            DownloadDao.downloadUsingStream(url, "E:/workspace/Craiova-Internship-2017/CcyXcg/web/WEB-INF/download.xml");
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        DeleteDao.delete();
        ParseFunction.pairs(a);//parsarea
        for (Map.Entry i:ParseFunction.currencypairs.entrySet()) {
           // object.setPair((String) i.getKey());
           // object.setValues((Double) i.getValue());
           // session.save(object);

            //dao.ParseDao.addcurrency(String.valueOf(i.getKey()),Double.valueOf((Double) i.getValue()));

            // dao.ParseHistoryDao.addcurrency(ParsePairs.pairs(a).get(i), Parse.pairs(a).get(i));
        }

    }*/
    }
}


   /* public static void main(String[] args) throws ParseException {

        //the Date and time at which you want to execute
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormatter .parse("2012-07-06 13:05:45");

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it once
        timer.schedule(new MyTimeTask(), date);

        //Use this if you want to execute it repeatedly
        //int period = 10000;//10secs
        //timer.schedule(new MyTimeTask(), date, period );
    }
}*/
