package com.example.android.helloconstraint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button zeroButton, countButton;
    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);

        zeroButton = (Button) findViewById(R.id.button_zero);
        countButton = (Button) findViewById(R.id.button_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));

        if(mCount % 2 == 1)
            countButton.setBackgroundColor(CustomColors.RED);
        else
            countButton.setBackgroundColor(CustomColors.GREEN);

        zeroButton.setBackgroundColor(CustomColors.BLUE);
    }

    public void setZero(View view) {
        mCount = 0;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));

        zeroButton.setBackgroundColor(CustomColors.GRAY);
        countButton.setBackgroundColor(CustomColors.GREEN);
    }
}
