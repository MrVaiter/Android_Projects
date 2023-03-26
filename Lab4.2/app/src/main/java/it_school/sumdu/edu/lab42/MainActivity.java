package it_school.sumdu.edu.lab42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox topping1, topping2, topping3, topping4, topping5;
    private Integer topping = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topping1 = (CheckBox) findViewById(R.id.checkBox);
        topping2 = (CheckBox) findViewById(R.id.checkBox2);
        topping3 = (CheckBox) findViewById(R.id.checkBox3);
        topping4 = (CheckBox) findViewById(R.id.checkBox4);
        topping5 = (CheckBox) findViewById(R.id.checkBox5);
    }

    public void check(StringBuilder str, CheckBox checkBox){
        if(checkBox.isChecked()){
            topping++;
            str.append(checkBox.getText()).append(" ");
        }
    }

    public void showToast(View view){
        StringBuilder toppingList = new StringBuilder();

        check(toppingList, topping1);
        check(toppingList, topping2);
        check(toppingList, topping3);
        check(toppingList, topping4);
        check(toppingList, topping5);

        if(topping > 1){
            toppingList.insert(0, "Toppings: ");
            Toast toast = Toast.makeText(this, toppingList.toString(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}