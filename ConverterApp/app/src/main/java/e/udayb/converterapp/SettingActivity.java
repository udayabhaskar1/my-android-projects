package e.udayb.converterapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    private String from;
    private String to;
    private double constant;
    private TextView fromText;
    private TextView toText;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        from = "M";
        to = "KM";
        constant = 0.001;

        fromText = (TextView) findViewById(R.id.fromText);
        toText = (TextView) findViewById(R.id.toText);

        preferences = getSharedPreferences("value", MODE_PRIVATE);
    }

    public void buttonSet(View view) {
        Button button = (Button) view;
        String btnText = button.getText().toString();

        if (btnText.equals("METER")) {
            from = "M";
            fromText.setText("METER");
        } else if (btnText.equals("KILOMETER")) {
            from = "KM";
            fromText.setText("KILOMETER");
        } else if (btnText.equals("MILES")) {
            from = "ML";
            fromText.setText("MILES");
        } else if (btnText.equals("INCHES")) {
            from = "I";
            fromText.setText("INCHES");
        } else if (btnText.equals("FEET")) {
            from = "F";
            fromText.setText("FEET");
        } else if (btnText.equals("YARDS")) {
            from = "Y";
            fromText.setText("YARDS");
        }
        updateInfo();
    }

    public void buttonTo(View view) {
        Button button = (Button) view;
        String btnText = button.getText().toString();

        if (btnText.equals("METER")) {
            to = "M";
            toText.setText("METER");
        } else if (btnText.equals("KILOMETER")) {
            to = "KM";
            toText.setText("KILOMETER");
        } else if (btnText.equals("MILES")) {
            to = "ML";
            toText.setText("MILES");
        } else if (btnText.equals("INCHES")) {
            to = "I";
            toText.setText("INCHES");
        } else if (btnText.equals("FEET")) {
            to = "F";
            toText.setText("FEET");
        } else if (btnText.equals("YARDS")) {
            to = "Y";
            toText.setText("YARDS");
        }
        updateInfo();
    }

    public void updateInfo() {
        if (from.equals("M") && to.equals("M")) {
            constant = 1;
        } else if (from.equals("M") && to.equals("KM")) {
            constant = 0.001;
        } else if (from.equals("M") && to.equals("ML")) {
            constant = 0.00062;
        } else if (from.equals("M") && to.equals("I")) {
            constant = 39.3701;
        } else if (from.equals("M") && to.equals("F")) {
            constant = 3.28084;
        } else if (from.equals("M") && to.equals("Y")) {
            constant = 1.09361;
        } else if (from.equals("KM") && to.equals("M")) {
            constant = 1000;
        } else if (from.equals("KM") && to.equals("KM")) {
            constant = 1;
        } else if (from.equals("KM") && to.equals("ML")) {
            constant = 0.6214;
        } else if (from.equals("KM") && to.equals("I")) {
            constant = 39370.1;
        } else if (from.equals("KM") && to.equals("F")) {
            constant = 3280.84;
        } else if (from.equals("KM") && to.equals("Y")) {
            constant = 1093.61;
        } else if (from.equals("ML") && to.equals("M")) {
            constant = 1609.34;
        } else if (from.equals("ML") && to.equals("KM")) {
            constant = 1.609;
        } else if (from.equals("ML") && to.equals("ML")) {
            constant = 1;
        } else if (from.equals("ML") && to.equals("I")) {
            constant = 63360;
        } else if (from.equals("ML") && to.equals("F")) {
            constant = 5280;
        } else if (from.equals("ML") && to.equals("Y")) {
            constant = 1760;
        } else if (from.equals("I") && to.equals("M")) {
            constant = 0.0254;
        } else if (from.equals("I") && to.equals("KM")) {
            constant = 0.0000254;
        } else if (from.equals("I") && to.equals("ML")) {
            constant = 0.0000158;
        } else if (from.equals("I") && to.equals("I")) {
            constant = 1;
        } else if (from.equals("I") && to.equals("F")) {
            constant = 0.08333;
        } else if (from.equals("I") && to.equals("Y")) {
            constant = 0.2777;
        } else if (from.equals("F") && to.equals("M")) {
            constant = 0.3048;
        } else if (from.equals("F") && to.equals("KM")) {
            constant = 0.0003048;
        } else if (from.equals("F") && to.equals("ML")) {
            constant = 0.000189;
        } else if (from.equals("F") && to.equals("I")) {
            constant = 12;
        } else if (from.equals("F") && to.equals("F")) {
            constant = 1;
        } else if (from.equals("F") && to.equals("Y")) {
            constant = 0.3333;
        } else if (from.equals("Y") && to.equals("M")) {
            constant = 0.9144;
        } else if (from.equals("Y") && to.equals("KM")) {
            constant = 0.0009144;
        } else if (from.equals("Y") && to.equals("ML")) {
            constant = 0.000568;
        } else if (from.equals("Y") && to.equals("I")) {
            constant = 36;
        } else if (from.equals("Y") && to.equals("F")) {
            constant = 3;
        } else if (from.equals("Y") && to.equals("Y")) {
            constant = 1;
        }

        String strCons = String.valueOf(constant);
        preferences.edit().putString("constant",strCons).apply();
        preferences.edit().putString("from", from).apply();
        preferences.edit().putString("to",to).apply();
    }

    public void buttonBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateInfo();
    }
}
