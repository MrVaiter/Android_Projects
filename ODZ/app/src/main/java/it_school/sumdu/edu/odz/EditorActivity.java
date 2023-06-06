package it_school.sumdu.edu.odz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
        Boolean result;

        switch (view.getId()){
            case R.id.rectangle_header:
                Intent intent = new Intent(EditorActivity.this, MainPageActivity.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                finish();
                break;
            case R.id.editBtn:
                editContent(title, userID);
                break;
            case R.id.deleteBtn:
                result = db.deleteContent(title, userID);
                contentList.remove(title);
                updateList();

                if(result){
                    Toast.makeText(this, title + " deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Deleting failed", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    public void editContent(String title, String user){

        Cursor result = db.getContent(title, user);
        result.moveToNext();
        String type = result.getString(4);

        Intent intent = null;

        switch (type){
            case "film":
                intent = new Intent(EditorActivity.this, EditFilmActivity.class);
                break;
            case "series":
                intent = new Intent(EditorActivity.this, EditSeriesActivity.class);
                intent.putExtra("amount", result.getString(2));
                break;
            case "book":
                intent = new Intent(EditorActivity.this, EditBookActivity.class);
                intent.putExtra("amount", result.getString(2));
                break;
        }

        intent.putExtra("userID", userID);
        intent.putExtra("id", result.getString(0));
        intent.putExtra("title", result.getString(1));
        intent.putExtra("date", result.getString(4));

        startActivity(intent);
        finish();
    }


    public void uploadList(){
        contentList = new ArrayList<>();

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