package lima.r0bertson.uwlgymlog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FormatData extends AppCompatActivity {
    TextView formula;
    int[] values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_format_data, menu);
        formula = (TextView) findViewById(R.id.txt_formula);
        values = newFormula();
        formula.setText(values[0]+" + "+values[1]+" - "+values[2]+" = ?");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int[] newFormula(){
        Random r = new Random();
        int x, y, z;
        int total;
        x = r.nextInt(9);
        y = r.nextInt(9);
        z = r.nextInt(9);
        total = x + y - z;

        int[] values = new int[4];
        values[0]=x;
        values[1]=y;
        values[2]=z;
        values[3]=total;
        return values;

    }
    public void formatClick(){
        int result = Integer.parseInt(formula.getText().toString());
        if(validateFormula(values[3], result)){
            //TODO
            //FORMAT DATABASES
        }
        else{
            final Dialog dialog = new Dialog(FormatData.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);

            // set the custom dialog components - text and button
            TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
            text.setText("Alert message:");
            TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
            image.setText("The provided result is incorrect.");
            Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
            //message
        }
    }
    public boolean validateFormula(int a, int b){
        if (a == b){
            return true;
        }
        else{
            return false;
        }
    }
}