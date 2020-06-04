package app.tecsol.easymeeting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Mom extends AppCompatActivity {
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
                Intent i = new Intent(Mom.this, Agenda.class);
                startActivity(i);
            }
        });

        btnmommom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mom.this, Mom.class);
                startActivity(i);
            }
        });


    }
}
