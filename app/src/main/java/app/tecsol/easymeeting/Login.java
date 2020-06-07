package app.tecsol.easymeeting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    Button btnlogin;
    EditText edtname,edtpassword;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String  PasswordStr, EmailStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin=findViewById(R.id.btnlogin);
        edtname=findViewById(R.id.edtname);
        edtpassword=findViewById(R.id.edtpassword);

        userRef= FirebaseDatabase.getInstance().getReference("User");
        mAuth=FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordStr=edtname.getText().toString();
                EmailStr=edtpassword.getText().toString();
                mAuth.signInWithEmailAndPassword(EmailStr,PasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(Login.this, Agenda.class);
                            startActivity(i);
                        }
                    }
                });

            }
        });


    }
}
