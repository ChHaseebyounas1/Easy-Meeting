package app.tecsol.easymeeting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.tecsol.easymeeting.Models.UserModel;

public class Signup extends AppCompatActivity {

    Button btnsignup;
    EditText edtfullnamesignup,edtemailsignup,edtpasswordsignup;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String nameSTr, PasswordStr, EmailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userRef=FirebaseDatabase.getInstance().getReference("User");
        mAuth=FirebaseAuth.getInstance();

        btnsignup=findViewById(R.id.btnsignup);
        edtfullnamesignup=findViewById(R.id.edtfullnamesignup);
        edtemailsignup=findViewById(R.id.edtemailsignup);
        edtpasswordsignup=findViewById(R.id.edtfullnamesignup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameSTr=edtfullnamesignup.getText().toString();
                PasswordStr=edtpasswordsignup.getText().toString();
                EmailStr=edtemailsignup.getText().toString();
                mAuth.createUserWithEmailAndPassword(EmailStr, PasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String UserId=mAuth.getCurrentUser().getUid();
                            UserModel model=new UserModel(nameSTr, EmailStr,PasswordStr, UserId);
                            userRef.child(UserId).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(getBaseContext(), Agenda.class));
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
