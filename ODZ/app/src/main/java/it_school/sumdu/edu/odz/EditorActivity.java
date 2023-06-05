package it_school.sumdu.edu.odz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditorActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper db;
    private String userID;
    private List<String> contentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_page);

        db = new DatabaseHelper(this);
        userID = getIntent().getStringExtra("userID");

        uploadList();

        findViewById(R.id.editBtn).setOnClickListener(this);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.rectangle_header).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String title = ((Spinner) findViewById(R.id.choose_content)).getSelectedItem().toString();

        switch (view.getId()){
            case R.id.rectangle_header:
                Intent intent = new Intent(EditorActivity.this, MainPageActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                finish();
                break;
            case R.id.editBtn:
                editContent(title);
                break;
            case R.id.deleteBtn:
                db.deleteContent(title);
                break;
        }

    }

    public void editContent(String title){

    }


    public void uploadList(){
        List<String> contentList = new ArrayList<>();

        Cursor result = db.getContentData(userID);
        while (result.moveToNext()){
            contentList.add(result.getString(1));
        }

        updateList();
    }

    public void updateList(){
        Spinner contentSpinner = findViewById(R.id.choose_content);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, contentList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        contentSpinner.setAdapter(adapter);
    }
}