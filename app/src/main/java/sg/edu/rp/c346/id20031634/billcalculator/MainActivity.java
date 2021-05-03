package sg.edu.rp.c346.id20031634.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button Split;
    Button Reset;
    ToggleButton SVS;
    ToggleButton GST;
    RadioButton Cash;
    RadioButton PayNow;
    EditText Amount;
    EditText Pax;
    EditText Discount;
    TextView ViewTotal;
    TextView ViewSplit;
    RadioGroup RadioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Split = findViewById(R.id.Split);
        Reset = findViewById(R.id.Reset);
        SVS= findViewById(R.id.Toggle3);
        GST = findViewById(R.id.Toggle4);
        Amount = findViewById(R.id.EditAmount);
        Pax = findViewById(R.id.EditPax);
        Discount = findViewById(R.id.Discount);
        ViewTotal = findViewById(R.id.textViewTotal);
        ViewSplit = findViewById(R.id.textViewSplit);
        RadioGroup = findViewById(R.id.RadioGroup);


        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Amount.getText().toString().trim().length()!= 0 && Pax.getText().toString().trim().length()!=0){
                    double newamount =0.0;
                    if(!SVS.isChecked()&&!GST.isChecked()){
                        newamount = Double.parseDouble(Amount.getText().toString());
                    }
                    else if(SVS.isChecked()&& !GST.isChecked()){
                        newamount = Double.parseDouble(Amount.getText().toString()) * 1.1;
                    }
                    else if(!SVS.isChecked()&& GST.isChecked()) {
                        newamount = Double.parseDouble(Amount.getText().toString()) * 1.07;
                    }
                    else if(SVS.isChecked()&& GST.isChecked()) {
                        newamount = Double.parseDouble(Amount.getText().toString()) * 1.17;
                    }

                    if(Discount.getText().toString().trim().length()!=0){
                        newamount*= 1- Double.parseDouble(Discount.getText().toString())/100;
                    }
                    String mode = "in cash";
                    if(RadioGroup.getCheckedRadioButtonId() == R.id.radioButtonPayNow) {
                        mode = "via PayNow to 912345678";
                    }
                        ViewTotal.setText("Total bill: $" + String.format("%.2f", newamount));
                        int num = Integer.parseInt(Pax.getText().toString());
                        if (num != 1) {
                            ViewSplit.setText("Each pay: $" + String.format("%.2f", newamount / num + mode));
                        }
                        else{
                            ViewSplit.setText("Each Pays: $" +String.format("%.2f", newamount) + mode);
                        }

                    }

                    }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount.setText("");
                Pax.setText("");
                SVS.setChecked(false);
                GST.setChecked(false);
                Discount.setText("");
                ViewSplit.setText("");
                ViewTotal.setText("");
                Cash.setChecked(false);
                PayNow.setChecked(false);


            }
        });





    }
}