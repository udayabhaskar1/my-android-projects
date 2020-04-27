package e.udayb.quicksum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textBox;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonFrac;
    private Button buttonSet;
    private double sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBox = (TextView) findViewById(R.id.sum);
        button1 = (Button) findViewById(R.id.buttonOne);
        button2 = (Button) findViewById(R.id.buttonTwo);
        button3 = (Button) findViewById(R.id.buttonThree);
        buttonFrac = (Button) findViewById(R.id.buttonFrac);



    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String btnText = (String) button.getText();
        if (btnText.equals("OTHER")) {
            button1.setText("1/2");
            button2.setText("1/3");
            button3.setText("1/4");
        } else if (btnText.equals("1/2")) {
            button1.setText("1");
            button2.setText("2");
            button3.setText("3");
            additionNums(0.5);
        } else if (btnText.equals("1/3")) {
            button1.setText("1");
            button2.setText("2");
            button3.setText("3");
            additionNums(0.33);
        } else if (btnText.equals("1/4")) {
            button1.setText("1");
            button2.setText("2");
            button3.setText("3");
            additionNums(0.25);
        } else {
            double value = Double.parseDouble(btnText);
            additionNums(value);
        }

    }

    public void additionNums(double value){
        sum = sum + value;
        String answer = String.valueOf(sum);
        textBox.setText(answer);

    }
}
