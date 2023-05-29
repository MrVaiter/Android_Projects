package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        findViewById(R.id.add_film).setOnClickListener(this);
        findViewById(R.id.add_series).setOnClickListener(this);
        findViewById(R.id.add_book).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        
        switch (view.getId()){
            case R.id.add_film:
                intent = new Intent(MainPageActivity.this, AddFilmActivity.class);
                break;
            case R.id.add_series:
                intent = new Intent(MainPageActivity.this, AddSeriesActivity.class);
                break;
            case R.id.add_book:
                intent = new Intent(MainPageActivity.this, AddBookActivity.class);
                break;    
        }

        startActivity(intent);
    }
}
