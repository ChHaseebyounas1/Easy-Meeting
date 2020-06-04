package app.tecsol.easymeeting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Agenda extends AppCompatActivity {
    Button btnagenda,btnmom,btntodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);


        btnagenda=findViewById(R.id.btnagenda);
        btnmom=findViewById(R.id.btnmom);
        btntodo=findViewById(R.id.btntodo);


        btnagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Agenda.this, Agenda.class);
                startActivity(i);
            }
        });


        btnmom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Agenda.this, Mom.class);
                startActivity(i);
            }
        });


        btnagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Agenda.this, Agenda.class);
                startActivity(i);
            }
        });
    }
}
