package dao;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadDao {

    public static void main(String[] args) {
        String url = "http://www.bnr.ro/nbrfxrates.xml";

        try {
            downloadUsingStream(url, "E:/workspace/Craiova-Internship-2017/CcyXcg/web/WEB-INF/download.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
}