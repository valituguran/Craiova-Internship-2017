package dao;

import com.ymens.DeleteDao;
import com.ymens.Parse;
import com.ymens.ParsePairs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lucian.Nicolescu on 9/13/2017.
 */
public class TimerDao {
    public static LinkedList pairs = new LinkedList();
    public static LinkedList values = new LinkedList();
    public static class MyTimeTask extends TimerTask {

        public void run() {

            String url = "http://www.bnr.ro/nbrfxrates.xml";
            try {
                DownloadDao.downloadUsingStream(url, "E:/workspace/Craiova-Internship-2017/CcyXcg/web/WEB-INF/download.xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            DeleteDao.delete();
            String a = "E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\download.xml";
            for (int i = 0; i < Parse.values(a).size(); i++) {
                dao.ParseDao.addcurrency(ParsePairs.pairs(a).get(i), Parse.values(a).get(i));
               // dao.ParseHistoryDao.addcurrency(ParsePairs.pairs(a).get(i), Parse.values(a).get(i));
            }
            ParseDao.getcurrency();
            pairs = ParseDao.pairs;
            values = ParseDao.values;
        }
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
