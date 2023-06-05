package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        db = new DatabaseHelper(this);

        findViewById(R.id.saveBtn).setOnClickListener(this);
        findViewById(R.id.backBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn) {
            Intent intent = new Intent(AddBookActivity.this, MainPageActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Integer id = db.getNewContentId();
        String title = ((EditText) findViewById(R.id.nameInput)).getText().toString();
        String userID = getIntent().getStringExtra("userID");
        String amount = ((EditText) findViewById(R.id.amountNumber)).getText().toString();

        String day = ((Spinner) findViewById(R.id.day_spinner)).getSelectedItem().toString();
        String month = ((Spinner) findViewById(R.id.month_spinner)).getSelectedItem().toString();
        String year = ((Spinner) findViewById(R.id.year_spinner)).getSelectedItem().toString();
        String formattedDate = day + "-" + month + "-" + year;

        if(db.getContent(title).getCount() != 0){
            Toast.makeText(this, "Series already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean result = db.insertContentData(id,
                title,
                Integer.parseInt(amount),
                0,
                formattedDate,
                "book",
                Integer.parseInt(userID));

        if(result){
            Intent intent = new Intent(AddBookActivity.this, MainPageActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Insert error", Toast.LENGTH_SHORT).show();
        }
    }
}
