package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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

            Cursor cursor = db.getData();

            if(cursor.getCount() == 0){
                Toast.makeText(AuthorizationActivity.this,"No entries Exists",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(AuthorizationActivity.this,"Some entries exists",Toast.LENGTH_LONG).show();
            }

//            Intent intent = new Intent(AuthorizationActivity.this, MainPageActivity.class);
//            startActivity(intent);
//            finish();
        }
    }
}
