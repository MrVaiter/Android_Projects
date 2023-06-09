package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        db = new DatabaseHelper(getApplicationContext());

        findViewById(R.id.submitBtn).setOnClickListener(this);
        findViewById(R.id.backBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // Back to authorization
        if(view.getId() == R.id.backBtn){
            Intent intent = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Integer ID = db.getNewUserId();
        String login = ((EditText) findViewById(R.id.loginInput)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailInput)).getText().toString();
        String password = ((EditText) findViewById(R.id.passInput)).getText().toString();
        String repPassword = ((EditText) findViewById(R.id.repeatPassInput)).getText().toString();

        // Login unique check
        if(db.getAccount(login).getCount() != 0){
            Toast.makeText(getApplicationContext(), "Login already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // Repeat password check
        if(!password.equals(repPassword)){
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Email format check
        if(!email.contains("@")){
            Toast.makeText(getApplicationContext(), "Wrong email format", Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean result = db.insertUserData(ID, login, email, password);

        // Success check
        if(result){
            Intent intent = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
        }

    }
}
