package app.tecsol.easymeeting;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Splashscreenactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreenactivity);
        gotoMainpage();
    }


    private void gotoMainpage() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(4000);
                    Intent mintent=new Intent(Splashscreenactivity.this,DropList.class);
                    startActivity(mintent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
