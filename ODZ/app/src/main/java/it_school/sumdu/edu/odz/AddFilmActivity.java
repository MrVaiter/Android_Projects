package it_school.sumdu.edu.odz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddFilmActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_film);

        db = new DatabaseHelper(this);

        findViewById(R.id.saveBtn).setOnClickListener(this);
        findViewById(R.id.backBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.backBtn){
            Intent intent = new Intent(AddFilmActivity.this, MainPageActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        String title = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        String userID = getIntent().getStringExtra("userID");
        String day = ((Spinner) findViewById(R.id.day_spinner)).getSelectedItem().toString();
        String month = ((Spinner) findViewById(R.id.month_spinner)).getSelectedItem().toString();
        String year = ((Spinner) findViewById(R.id.year_spinner)).getSelectedItem().toString();
        String formattedDate = day + "-" + month + "-" + year;

        Integer id = db.getNewContentId(userID);

        if(db.getContent(title, userID).getCount() != 0){
            Toast.makeText(this, "Film already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean result = db.insertContentData(id, title, 1, 0, formattedDate, "film", Integer.parseInt(userID));

        if(result){
            Intent intent = new Intent(AddFilmActivity.this, MainPageActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Insert error", Toast.LENGTH_SHORT).show();
        }
    }
}
