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
    }

    @Override
    public void onClick(View view) {

        Integer ID = db.getNewID();
        String login = ((EditText) findViewById(R.id.loginInput)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailInput)).getText().toString();
        String password = ((EditText) findViewById(R.id.passInput)).getText().toString();

        Boolean result = db.insertUserData(ID, login, email, password);

        if(result){
            Intent intent = new Intent(RegistrationActivity.this, AuthorizationActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }
}
