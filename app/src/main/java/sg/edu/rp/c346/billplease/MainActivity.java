package sg.edu.rp.c346.billplease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText billamt;
    EditText person;
    EditText discount;
    CheckBox gst;
    CheckBox servicecharge;
    TextView total;
    TextView perpax;
    double billcost;
    int pax;
    int disamount;
    boolean verify;
    boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button calculate = (Button) findViewById(R.id.cal);
        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                billamt = (EditText) findViewById(R.id.money);
                person = (EditText) findViewById(R.id.people);
                gst = (CheckBox) findViewById(R.id.gst);
                servicecharge = (CheckBox) findViewById(R.id.sc);
                total = (TextView) findViewById(R.id.totvalue);
                perpax = (TextView) findViewById(R.id.splitvalue);
                discount = (EditText) findViewById(R.id.discounted);

                if (billamt.getText() == null)
                {
                    billamt.setError("Please enter your bill amount");
                }
                 else
                {
                     billcost =  Double.valueOf(billamt.getText().toString());
                }

                if (person.getText().toString().matches(""))
                {
                    person.setError("Please enter the number of person");
                }
                else
                {
                    pax = Integer.parseInt(person.getText().toString());
                }

                if (discount.getText().toString().matches(""))
                {
                    discount.setError("Please enter the discount given");
                }
                else
                {
                    disamount = Integer.parseInt(discount.getText().toString());
                }

                if (pax >=2 && disamount !=0)
                {
                    checked = gst.isChecked();
                    if (checked)
                    {
                        double sum = (billcost * 0.07) + billcost;
                        double disprice = sum - (sum * disamount / 100);
                        double persum = (disprice / pax);
                        String totsum = String.format("%.2f",disprice);
                        String totpersum = String.format("%.2f",persum);
                        total.setText("$" + totsum);
                        perpax.setText("$" + totpersum);
                    }

                    else
                    {
                        checked = false;
                    }

                    verify = servicecharge.isChecked();
                    if (verify)
                    {
                        double sum = (billcost * 0.1) + billcost;
                        double discounted = sum - (sum * disamount /100);
                        double persum = (discounted / pax);
                        String totsum = String.format("%.2f",discounted);
                        String totpersum = String.format("%.2f",persum);
                        total.setText("$" + totsum);
                        perpax.setText("$" + totpersum);
                    }

                    else
                    {
                        verify = false;
                    }

                    if (gst.isChecked() && servicecharge.isChecked() == true)
                    {
                        double sum = (billcost * 0.1) + billcost;
                        double consum = (sum * 0.07) + sum;
                        double disgot = consum - (sum * disamount /100);
                        double persum = (disgot / pax);
                        String totsum = String.format("%.2f",disgot);
                        String totpersum = String.format("%.2f",persum);
                        total.setText("$" + totsum);
                        perpax.setText("$" + totpersum);
                    }

                }
                else
                {
                    double totdis = billcost - (billcost * disamount /100);
                    String orignal = String.format("%.2f",totdis);
                    double percost = (totdis / pax);
                    String expenses = String.format("%.2f",percost);
                    total.setText("$" + orignal);
                    perpax.setText("$" + expenses);

                }
            }
        });

         Button reset = (Button) findViewById(R.id.blank);
         reset.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 billamt.setText("");
                 person.setText("");
                 total.setText("");
                 perpax.setText("");
             }
         });
    }
}
