package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
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

        Integer id = db.getNewContentId();
        String title = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        String userID = getIntent().getStringExtra("userID");

        long datestamp = ((CalendarView) findViewById(R.id.releaseDatePicker)).getDate();
        Date date = new Date(datestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = sdf.format(date);

        Boolean result = db.insertContentData(id, title, 1, 1, formattedDate, Integer.parseInt(userID));

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
