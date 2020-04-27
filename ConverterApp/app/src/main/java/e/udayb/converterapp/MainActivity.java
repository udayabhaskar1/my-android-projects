package e.udayb.converterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView output;
    private EditText input;
    private TextView inputUnit;
    private TextView outputUnit;
    private double constant = 0.001;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.outputText);
        input = (EditText) findViewById(R.id.inputText);
        inputUnit = (TextView) findViewById(R.id.inputUnit);
        outputUnit = (TextView) findViewById(R.id.outputUnit);
        preferences = getSharedPreferences("value", MODE_PRIVATE);
    }

    public void buttonConvert(View view) {
        try {
            DecimalFormat df = new DecimalFormat("###,###.00");
            String strValue = input.getText().toString();
            double value = Double.parseDouble(strValue);

            double result = value * constant;
            String strResult = String.valueOf(df.format(result));

            output.setText(strResult);
        } catch (Exception e) {
            output.setText("(Enter Num)");
        }
    }

    public void buttonSettings(View view) {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String strCons = preferences.getString("constant", "0");
        constant = Double.parseDouble(strCons);
        String from = preferences.getString("from", "M");
        String to = preferences.getString("to", "KM");
        if (from.equals("M")) {
            inputUnit.setText("(meter)");
        } else if (from.equals("KM")) {
            inputUnit.setText("(kilometer)");
        } else if (from.equals("ML")) {
            inputUnit.setText("(miles)");
        } else if (from.equals("I")) {
            inputUnit.setText("(inches)");
        } else if (from.equals("F")) {
            inputUnit.setText("(feet)");
        } else if (from.equals("Y")) {
            inputUnit.setText("(yards)");
        }

        if (to.equals("M")) {
            outputUnit.setText("(meter)");
        } else if (to.equals("KM")) {
            outputUnit.setText("(kilometer)");
        } else if (to.equals("ML")) {
            outputUnit.setText("(miles)");
        } else if (to.equals("I")) {
            outputUnit.setText("(inches)");
        } else if (to.equals("F")) {
            outputUnit.setText("(feet)");
        } else if (to.equals("Y")) {
            outputUnit.setText("(yards)");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        input.setText("");
        output.setText("");
    }
}
