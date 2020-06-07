package app.tecsol.easymeeting.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import app.tecsol.easymeeting.R;

public class MomActivity extends AppCompatActivity {
    Button btnmomagnda,btnmommom,btnmomtodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mom);

        btnmomagnda=findViewById(R.id.btnmomagenda);
        btnmommom=findViewById(R.id.btnmommom);
        btnmomtodo=findViewById(R.id.btnmomtodo);


        btnmomagnda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MomActivity.this, AgendaActivity.class);
                startActivity(i);
            }
        });

        btnmommom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MomActivity.this, MomActivity.class);
                startActivity(i);
            }
        });


    }
}
