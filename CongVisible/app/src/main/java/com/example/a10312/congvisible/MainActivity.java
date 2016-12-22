package com.example.a10312.congvisible;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView round;
    TextView Cor_num;
    TextView Wor_num;
    TextView ResultOf;
    TextView cornum;
    TextView wornum;
    Bitmap bitmap;
    ImageView imageView;
    EditText editText;
    Button button;
    Thread t;
    URL imageUrl;
    ImageView iv;
    String answer;
    int tmp;
    int Round_cnt=1;
    String RoundCnt;

    @Override
    protected void onPause() {

        super.onPause();
    }

    int cor_num=0;
    int wor_num=0;
    String Result;
    String cor;
    String wor;
    String[] list={"apple", "car", "palace", "note", "computer", "phone", "music", "piano", "eye", "bag", "dinosaur", "doll", "lion", "mouse", "shoes", "heart", "chair", "desk", "camera", "egg"};
    Random rand = new Random(20);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        editText = (EditText)findViewById(R.id.insert);
        iv = (ImageView)findViewById(R.id.result_image);
        round = (TextView)findViewById(R.id.round);
        Cor_num = (TextView)findViewById(R.id.Cor_num);
        Wor_num = (TextView)findViewById(R.id.Wor_num);
        button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Round_cnt == 10) {
                    Round_cnt = 0;

                    Intent i=new Intent(MainActivity.this, ResultActivity.class);
                    i.putExtra("cor_num",cor_num);
                    i.putExtra("wor_num",wor_num);
                    cor_num = 0;
                    wor_num = -1;

                    startActivity(i);
//
                }
                editText.setText("");
                answer=editText.getText().toString();
                if(answer.equals(list[tmp])) {
                    cor_num++;
                }
                else{
                    wor_num++;
                }
                Round_cnt++;

                RoundCnt=String.valueOf(Round_cnt);
                cor=String.valueOf(cor_num);
                wor=String.valueOf(wor_num);

                Cor_num.setText(cor);
                Wor_num.setText(wor);
                round.setText(RoundCnt);

                random();
            }
        });



        random();
    }
    public class MyParsing extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... example) {
            URL path = null;
            Bitmap bitmap = null;
            try {
                String str = "http://www.gettyimages.com/photos/" + example[0];
                Log.d("doInBackground: ", str);
                Document doc = Jsoup.connect(str).get();
                Elements images = doc.select("img");

                Element image2 = doc.select("img").get(6);
                String image = image2.attr("src");
                URL temp=new URL(image);
                imageUrl=temp;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void random(){
        tmp=rand.nextInt(20);
        String example = list[tmp];
        MyParsing myParsing = new MyParsing();
        try {
            myParsing.execute(example).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    // 걍 외우는게 좋다 -_-;
                    InputStream is = imageUrl.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    iv.setImageBitmap(bm); //비트맵 객체로 보여주기
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}