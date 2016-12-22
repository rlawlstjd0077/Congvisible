package com.example.a10312.congvisible;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 이재빈10312 on 2016-12-19.
 */

public class MyParsing extends AsyncTask<String, Bitmap, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... example) {
        URL path = null;
        Bitmap bitmap = null;
        try {
            String str = "http://www.gettyimages.com/photos/" + example[0];
            Log.d("doInBackground: ", str);
            Document doc = Jsoup.connect(str).get();
            Elements images = doc.select("img");
            String url = images.get(0).attr("src");
            path = new URL(url);
//                    for(Element i:images){
//                        String imageUrl=i.attr("src");
//                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) path.openConnection();
            conn.setDoInput(true);
            conn.connect();
            int length = conn.getContentLength(); // 받아온 컨텐츠의 길이를 length 변수에 저장합니다.
            InputStream is = (InputStream) conn.getInputStream(); // InputStream is 변수에 받아온 InputStream을 저장합니다.

            bitmap = BitmapFactory.decodeStream(is); // 받아온 이미지를 bmImg에 넣어둡니다.
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bitmap;
    }


}
