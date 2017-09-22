package dao;

import com.ymens.DeleteDao;
import com.ymens.Parse;
import com.ymens.ParsePairs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lucian.Nicolescu on 9/13/2017.
 */
public class TimerDao extends TimerTask {

    public  void run() {
            System.out.println("Thread id: "+Thread.currentThread().getId());
            String a="E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\download.xml";
            String url = "http://www.bnr.ro/nbrfxrates.xml";
            try {
                DownloadDao.downloadUsingStream(url, "E:/workspace/Craiova-Internship-2017/CcyXcg/web/WEB-INF/download.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            DeleteDao.delete();
            Parse.pairs(a);//parsarea
            //System.out.println("vberb " + Parse.pairs(a).size());
            for (Map.Entry i:Parse.currencypairs.entrySet()) {
               dao.ParseDao.addcurrency(String.valueOf(i.getKey()),Double.valueOf((Double) i.getValue()));
               // dao.ParseHistoryDao.addcurrency(ParsePairs.pairs(a).get(i), Parse.pairs(a).get(i));
            }
            SelectActualValueDao.select();
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
