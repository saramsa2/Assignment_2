package nz.ac.unitec.cs.assignment_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPassword1, etPassword2, etName;
    Button btRegister, btCancel;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister);

        etEmail = findViewById(R.id.et_register_email);
        etPassword1 = findViewById(R.id.et_register_pwd_1);
        etPassword2 = findViewById(R.id.et_register_pwd_2);
        etName = findViewById(R.id.et_register_name);

        btRegister = findViewById(R.id.bt_register_ok);
        btCancel = findViewById(R.id.bt_register_cancel);

        mAuth = FirebaseAuth.getInstance();

        addEventListeners();
    }

    private void addEventListeners() {
        btRegister.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(etName.getText().toString().equals("admin")){
                    /// Administrator Register
                    addUser();
                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                            .setDisplayName("admin")
                            .build();
                    return true;
                }
                else {
                    return false;
                }

            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// User Register
                if(!etName.getText().toString().equals("admin")) {
                    addUser();
                }
                else {
                    /// message for can't enroll
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addUser() {
        String email = etEmail.getText().toString();
        String password1 = etPassword1.getText().toString();
        String password2 = etPassword2.getText().toString();
        if(password1.equals(password2)){
            mAuth.createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(etName.getText().toString())
                                        .build();
                                user.updateProfile(profileUpdates);
                                Toast.makeText(RegisterActivity.this, "Authentication succeed.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
//                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
//                                updateUI(null);
                            }
                        }
                    });
        }
    }
}