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

import app.tecsol.easymeeting.Models.UserModel;
import app.tecsol.easymeeting.R;

public class SignupActivity extends AppCompatActivity {

    Button btnsignup;
    EditText edtfullnamesignup,edtemailsignup,edtpasswordsignup;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String nameSTr, PasswordStr, EmailStr;
    RotateLoading loading;

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
        loading = findViewById(R.id.loader);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameSTr = edtfullnamesignup.getText().toString();
                PasswordStr = edtpasswordsignup.getText().toString();
                EmailStr = edtemailsignup.getText().toString();

                loading.setVisibility(View.VISIBLE);
                loading.start();

                if (nameSTr.isEmpty())
                {
                    edtfullnamesignup.setError("Enter Full Name");
                    edtfullnamesignup.requestFocus();
                    loading.stop();
                    return;
                }

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                  if(EmailStr.matches(emailPattern)) {

                        mAuth.createUserWithEmailAndPassword(EmailStr, PasswordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String UserId = mAuth.getCurrentUser().getUid();
                                    UserModel model = new UserModel(nameSTr, EmailStr, PasswordStr, UserId);
                                    userRef.child(UserId).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(getBaseContext(), Login.class));
                                                loading.stop();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            loading.setVisibility(View.GONE);
                                            loading.stop();
                                            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                loading.stop();
                            }
                        });

                    }

                else
                {
                    edtemailsignup.setError("Enter Valid Email ");
                    edtemailsignup.requestFocus();
                    loading.stop();
                }
            }
        });
        loading.stop();
    }
}
