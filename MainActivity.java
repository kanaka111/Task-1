package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String [] lengthUnits = {"mm", "cm", "m", "km"};
    String unitChoice = "mm";

    double currentInput = 0;
    double valueMM = 0;
    double valueCM = 0;
    double valueM = 0;
    double valueKM =0;

    EditText input;
    TextView textMM;
    TextView textCM;
    TextView textM;
    TextView textKM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.input);
        textMM = (TextView) findViewById(R.id.textMM);
        textCM = (TextView) findViewById(R.id.textCM);
        textM = (TextView) findViewById(R.id.text);
        textKM = (TextView) findViewById(R.id.textKM);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, lengthUnits);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l){
                unitChoice = lengthUnits[position];
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2 ) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2 ) {
                currentInput = Double.parseDouble(input.getText().toString());
                convert();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void convert(){
        switch(unitChoice){
            case "mm":
                valueMM = currentInput;
                valueCM = currentInput * 0.1;
                valueM = currentInput * 0.001;
                valueKM = currentInput * 0.0001;
                break;
            case "cm":
                valueMM = currentInput * 10;
                valueCM = currentInput;
                valueM = currentInput * 0.01;
                valueKM = currentInput * 0.001;
                break;
            case "m":
                valueMM = currentInput * 1000;
                valueCM = currentInput * 100;
                valueM = currentInput;
                valueKM = currentInput * 0.1;
                break;
            case "km":
                valueMM = currentInput * 1000;
                valueCM = currentInput * 100;
                valueM = currentInput;
                valueKM = currentInput * 0.01;
                break;
            default:
                break;
        }
        textMM.setText(valueMM+"mm");
        textCM.setText(valueCM+"cm");
        textM.setText(valueM+"m");
        textKM.setText(valueKM+"km");
    }
}