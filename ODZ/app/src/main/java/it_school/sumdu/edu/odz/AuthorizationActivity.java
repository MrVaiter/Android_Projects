package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AuthorizationActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);

        db = new DatabaseHelper(this);

        findViewById(R.id.submitBtn).setOnClickListener(this);
        findViewById(R.id.regBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.regBtn){
            Intent intent = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.submitBtn) {

            String login = ((EditText) findViewById(R.id.loginInput)).getText().toString();
            String password = ((EditText) findViewById(R.id.passInput)).getText().toString();

            if(login.contains(" ")){
                Toast.makeText(this, "Login cannot contain space", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor result = db.getAccount(login);

            if(result.getCount() != 0){
                result.moveToNext();
                if(result.getString(3).equals(password)){
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AuthorizationActivity.this, MainPageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Login doesn't exist", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
