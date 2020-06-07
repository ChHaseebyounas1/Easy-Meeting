package app.tecsol.easymeeting.Activites;

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
import com.victor.loading.rotate.RotateLoading;

import app.tecsol.easymeeting.R;

public class Login extends AppCompatActivity {
    Button btnlogin;
    EditText edtname,edtpassword;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String  PasswordStr, EmailStr;
    RotateLoading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin=findViewById(R.id.btnlogin);
        edtname=findViewById(R.id.edtname);
        edtpassword=findViewById(R.id.edtpassword);
        loading=findViewById(R.id.rotateloading);
        userRef= FirebaseDatabase.getInstance().getReference("User");
        mAuth=FirebaseAuth.getInstance();




        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailStr=edtname.getText().toString();
                PasswordStr=edtpassword.getText().toString();

                loading.setVisibility(View.VISIBLE);
                loading.start();
                if (EmailStr.isEmpty())
                {
                    edtname.setError("please enter mail");
                    edtname.requestFocus();
                    loading.stop();
                    return;
                }
                if (PasswordStr.isEmpty())
                {
                    edtpassword.setError("please enter mail");
                    edtpassword.requestFocus();
                    loading.stop();
                    return;
                }


                loading.setVisibility(View.VISIBLE);
                loading.start();
                mAuth.signInWithEmailAndPassword(EmailStr,PasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            loading.stop();
                            Toast.makeText(Login.this, "Sign-In Succesfully", Toast.LENGTH_SHORT).show();
                            Intent mIntent =new Intent(getApplicationContext(),AgendaActivity.class);
                            startActivity(mIntent);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        loading.stop();
                    }
                });

            }
        });


    }
}
