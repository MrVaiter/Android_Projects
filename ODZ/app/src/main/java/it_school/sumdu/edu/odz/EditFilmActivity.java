package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class EditFilmActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper db;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_film);

        db = new DatabaseHelper(this);
        userID = getIntent().getStringExtra("userID");

        loadInfo();

        findViewById(R.id.saveBtn).setOnClickListener(this);
        findViewById(R.id.backBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.backBtn){
            Intent intent = new Intent( EditFilmActivity.this, EditorActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
            finish();
        }

        String title = ((EditText)findViewById(R.id.nameInput)).getText().toString();
        String day = ((Spinner) findViewById(R.id.day_spinner)).getSelectedItem().toString();
        String month = ((Spinner) findViewById(R.id.month_spinner)).getSelectedItem().toString();
        String year = ((Spinner) findViewById(R.id.year_spinner)).getSelectedItem().toString();
        String formattedDate = day + "-" + month + "-" + year;

        db.updateContent(getIntent().getStringExtra("id"),
                         title,
                         formattedDate,
                         getIntent().getStringExtra("userID"));

        Intent intent = new Intent( EditFilmActivity.this, EditorActivity.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
        finish();
    }

    public void loadInfo(){
        EditText title = findViewById(R.id.nameInput);

        title.setText(getIntent().getStringExtra("title"));
    }
}
