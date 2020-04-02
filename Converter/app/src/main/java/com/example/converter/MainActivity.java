package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView output;
    Spinner operation_spinner;
    Button convert_button;
    int int_input;
    RadioButton d2b, b2d;
    ArrayList<Integer> arr_input = new ArrayList<>();
    ArrayList<Integer> bin_output = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
        convert_button = (Button) findViewById(R.id.convert);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        d2b = (RadioButton) findViewById(R.id.d2b);
        b2d = (RadioButton) findViewById(R.id.b2d);

try {
    convert_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                int_input = Integer.parseInt(input.getText().toString());
            }
            catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please Enter Something To Convert", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, "Please Use Some Common Sense", Toast.LENGTH_SHORT).show();
            }
            if(d2b.isChecked()) {
                Log.i("value", "*** int value is " + int_input + " ***");
                String val = toBin(int_input);
                output.setText(val);
                Log.i("value", "*** done setting " + int_input + " ***");
            }
            else if(b2d.isChecked()) {
                Log.i("value", "*** bin value is " + int_input + " ***");
                String val = String.valueOf(toDec(int_input));
                output.setText(val);
            }
            else {
                Toast.makeText(MainActivity.this, "Please Select An Option Before Converting", Toast.LENGTH_SHORT).show();
            }

        }
    });
}
catch(Exception e) {
    Toast.makeText(this, "Play With Numbers, Not With App", Toast.LENGTH_SHORT).show();
}

    }

    public static String toBin(int num_form) {
        ArrayList<Integer> bin_list = new ArrayList<>();
        int buffer = 64;
        int z = buffer - 1;
        int place = num_form;
        for (int i = 0; i < buffer; i++) {
            int h = (int) Math.pow(2, z);
            if (place >= h) {
                bin_list.add(1);
                place -= h;
                z--;
            } else {
                bin_list.add(0);
                z--;
            }
        }
        String num = "";
        boolean is_first = false;
        for (int i = 0; i < bin_list.size(); i++) {

            if (bin_list.get(i) == 1) {
                is_first = true;
            }

            if (is_first) {
                num = num + String.valueOf(bin_list.get(i));
            }

        }
        return num;
    }

    public static int toDec(int bin_form) {
        int holding = 1;
        int res = 0;
        int num;
        while(bin_form > 0) {
            num = bin_form % 10;
            bin_form /= 10;
            if(num == 1) {
                res += holding;
            }
            holding *= 2;
        }
        return res;

    }
}






/***
        $$$ GRAVEYARD OF MY OLD IDEAS $$$

        List<String> Operations = new ArrayList<String>();
        Operations.add("Decimal --> Binary");
        Operations.add("Binary  --> Decimal");

        ArrayAdapter <String> data_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Operations);
        data_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operation_spinner.setAdapter(data_adapter);
        operation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selected = parentView.getSelectedItem().toString();
                if(position == 0) {

                    Log.i("value", "*** final value is "+val+" ***" );
                    output.setText(val);
                    Log.i("value", "***value entered sucessfully ***" );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        /*convert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });*/

    /***
     public static ArrayList<Integer> to_List(int val) {
     ArrayList <Integer> num_list = new ArrayList<>();
     int z = val;
     int temp;
     temp = 2;
     while(z > 0) {
     z /= 10;
     temp = val % 10;
     num_list.add(temp);
     }
     return num_list;
     }

     public static ArrayList<Integer> rev_List(ArrayList<Integer> my_list) {
     ArrayList <Integer> rev_list = new ArrayList<>();
     for( int i = my_list.size() - 1; i >= 0; i--) {
     rev_list.add(my_list.get(i));
     }
     return rev_list;
     }
     public static int list_To_Int(ArrayList<Integer> mylist) {
     int num = 0;
     int prev = 1;
     int zen = 1;
     for(int i = 0; i < mylist.size(); i++) {
     num = mylist.get(i) + zen*prev;
     prev = mylist.get(i);
     zen *= 10;
     }
     return num;
     }
     ***/

