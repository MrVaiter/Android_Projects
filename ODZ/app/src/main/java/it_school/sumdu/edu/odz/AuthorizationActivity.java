package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AuthorizationActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);

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
            Intent intent = new Intent(AuthorizationActivity.this, MainPageActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
