package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        findViewById(R.id.add_film).setOnClickListener(this);
        findViewById(R.id.add_series).setOnClickListener(this);
        findViewById(R.id.add_book).setOnClickListener(this);

        db = new DatabaseHelper(this);

        ViewContent();
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

        String userID = getIntent().getStringExtra("userID");
        intent.putExtra("userID", userID);
        startActivity(intent);
        finish();
    }

    public void ViewContent(){
        LinearLayout list = findViewById(R.id.list);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(R.id.listItem1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                dpToPx(396), dpToPx(85)));
        linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.list_item));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        list.addView(linearLayout);
    }

    private int dpToPx(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
