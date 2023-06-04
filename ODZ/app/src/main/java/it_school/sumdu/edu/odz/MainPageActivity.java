package it_school.sumdu.edu.odz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
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

        Cursor result = db.getContentData();

        if(result.getCount() == 0){
            return;
        }

        LinearLayout list = findViewById(R.id.list);

        while (result.moveToNext()) {
            switch (result.getString(5)){
                case "film":
                    showFilm(list, result);
                    break;
                case "series":
                    showSeries(list, result);
                    break;
                case "book":
                    showBook(list, result);
                    break;
            }
        }

    }

    private void showFilm(LinearLayout list, Cursor raw){
        int id = Integer.parseInt(raw.getString(0));
        String date = raw.getString(4);

        // ListItem
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(id);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(396), dpToPx(85));
        params.setMargins(dpToPx(7), dpToPx(7), 0, 0);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.list_item));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Icon
        View icon = new View(this);
        icon.setBackground(ContextCompat.getDrawable(this, R.drawable.book2));
        LinearLayout.LayoutParams icon_params = new LinearLayout.LayoutParams(dpToPx(68), dpToPx(68));
        icon_params.setMargins(dpToPx(10), dpToPx(7), 0, 0);
        icon.setLayoutParams(icon_params);
        icon.setElevation(dpToPx(4));
        linearLayout.addView(icon);

        // Title
        TextView title = new TextView(this);
        title.setText(raw.getString(1));
        LinearLayout.LayoutParams title_param = new LinearLayout.LayoutParams(dpToPx(240), dpToPx(44));
        title_param.setMargins(dpToPx(15), dpToPx(25), 0, 0);
        title.setLayoutParams(title_param);
        title.setTextAppearance(R.style.listItem);
        title.setMaxLines(1);
        title.setEllipsize(TextUtils.TruncateAt.END);
        linearLayout.addView(title);

        // Status
        TextView status = new TextView(this);
        if(raw.getString(2).equals(raw.getString(3))){
            status.setText("✔");
        } else {
            status.setText("✖");
        }
        LinearLayout.LayoutParams status_param = new LinearLayout.LayoutParams(dpToPx(50), dpToPx(50));
        status_param.setMargins(dpToPx(15), dpToPx(25), 0, 0);
        status.setLayoutParams(status_param);
        status.setTextAppearance(R.style.listItem);
        linearLayout.addView(status);

        linearLayout.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Release date: " + date, Toast.LENGTH_SHORT).show());

        list.addView(linearLayout);
    }

    private void showSeries(LinearLayout list, Cursor raw){
        Integer id = Integer.parseInt(raw.getString(0));

        // ListItem
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(id);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(396), dpToPx(85));
        params.setMargins(dpToPx(7), dpToPx(7), 0, 0);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.list_item));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Icon
        View icon = new View(this);
        icon.setBackground(ContextCompat.getDrawable(this, R.drawable.series2));
        LinearLayout.LayoutParams icon_params = new LinearLayout.LayoutParams(dpToPx(68), dpToPx(68));
        icon_params.setMargins(dpToPx(10), dpToPx(7), 0, 0);
        icon.setLayoutParams(icon_params);
        icon.setElevation(dpToPx(4));
        linearLayout.addView(icon);

        // Title
        TextView title = new TextView(this);
        title.setText(raw.getString(1));
        LinearLayout.LayoutParams title_param = new LinearLayout.LayoutParams(dpToPx(240), dpToPx(44));
        title_param.setMargins(dpToPx(15), dpToPx(25), 0, 0);
        title.setLayoutParams(title_param);
        title.setTextAppearance(R.style.listItem);
        title.setMaxLines(1);
        title.setEllipsize(TextUtils.TruncateAt.END);
        linearLayout.addView(title);

        // Status
        TextView status = new TextView(this);
        if(raw.getString(2).equals(raw.getString(3))){
            status.setText("✔");
        } else {
            status.setText("✖");
        }
        LinearLayout.LayoutParams status_param = new LinearLayout.LayoutParams(dpToPx(50), dpToPx(50));
        status_param.setMargins(dpToPx(15), dpToPx(25), 0, 0);
        status.setLayoutParams(status_param);
        status.setTextAppearance(R.style.listItem);
        linearLayout.addView(status);

        list.addView(linearLayout);
    }

    private void showBook(LinearLayout list, Cursor raw){
        Integer id = Integer.parseInt(raw.getString(0));

        // ListItem
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(id);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(396), dpToPx(85));
        params.setMargins(dpToPx(7), dpToPx(7), 0, 0);
        linearLayout.setLayoutParams(params);
        linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.list_item));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Icon
        View icon = new View(this);
        icon.setBackground(ContextCompat.getDrawable(this, R.drawable.book2));
        LinearLayout.LayoutParams icon_params = new LinearLayout.LayoutParams(dpToPx(68), dpToPx(68));
        icon_params.setMargins(dpToPx(10), dpToPx(7), 0, 0);
        icon.setLayoutParams(icon_params);
        icon.setElevation(dpToPx(4));
        linearLayout.addView(icon);

        // Title
        TextView title = new TextView(this);
        title.setText(raw.getString(1));
        LinearLayout.LayoutParams title_param = new LinearLayout.LayoutParams(dpToPx(240), dpToPx(44));
        title_param.setMargins(dpToPx(15), dpToPx(25), 0, 0);
        title.setLayoutParams(title_param);
        title.setTextAppearance(R.style.listItem);
        title.setMaxLines(1);
        title.setEllipsize(TextUtils.TruncateAt.END);
        linearLayout.addView(title);

        // Status
        TextView status = new TextView(this);
        if(raw.getString(2).equals(raw.getString(3))){
            status.setText("✔");
        } else {
            status.setText("✖");
        }
        LinearLayout.LayoutParams status_param = new LinearLayout.LayoutParams(dpToPx(50), dpToPx(50));
        status_param.setMargins(dpToPx(15), dpToPx(25), 0, 0);
        status.setLayoutParams(status_param);
        status.setTextAppearance(R.style.listItem);
        linearLayout.addView(status);

        list.addView(linearLayout);
    }
    private int dpToPx(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
