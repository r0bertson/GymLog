package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class FormatData extends Activity {
    TextView formula;
    EditText userInputText;
    int[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_data);
        formula = (TextView) findViewById(R.id.txt_formula);
        values = newFormula(); // creates a new formula
        formula.setText(values[0] + " + " + values[1] + " - " + values[2] + " = ?"); //displays the formula
        userInputText = (EditText) findViewById(R.id.edit_formula);
        userInputText.setRawInputType(InputType.TYPE_CLASS_NUMBER); //set the keyboard to be the numerical type
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_format_data, menu);
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

    /**
     * Creates a new expression to show
     * this method is called in every creation of this activity
     * so, the expression can be different on every load.
     */
    public int[] newFormula() {
        Random r = new Random();
        int x, y, z;
        int total;
        x = r.nextInt(9);
        y = r.nextInt(9);
        z = r.nextInt(9);
        total = x + y - z;

        int[] values = new int[4];
        values[0] = x;
        values[1] = y;
        values[2] = z;
        values[3] = total;
        return values;

    }

    /**
     * Perform the business involving the click on the button FORMAT
     * First, verify  if the user inserted the right value on the field
     * if not, notify. if yes, try to format and recreate the database.
     * Then, show a message and finish the intent.
     */
    public void formatClick(View view) {
        EditText userInput = (EditText) findViewById(R.id.edit_formula);
        System.out.println(userInput.getText().toString() + "     " + values[3]);

        if (!editTextIsEmpty(userInput)) {
            //NOT EMPTY
            int result = Integer.parseInt(userInput.getText().toString());
            if (validateFormula(values[3], result)) { //verifying answer
                MyDBHandler db = new MyDBHandler(this, null, null, 1);
                db.format(); //performing format and recreation


                final Dialog dialog = new Dialog(FormatData.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_box);
                TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
                text.setText(R.string.alert);
                TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
                image.setText(R.string.alert_db_formatted);
                Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();

                    }
                });
                dialog.show();
                //closing the intent
            } else {
                //EMPTY
                final Dialog dialog = new Dialog(FormatData.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_box);
                TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
                text.setText(R.string.alert);
                TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
                image.setText(R.string.alert_incorrect_result);
                Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        } else { //formula is not typed, edittext blank
            final Dialog dialog = new Dialog(FormatData.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);
            TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
            text.setText(R.string.alert);
            TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
            image.setText(R.string.alert_blank_formula);
            Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }
    }

    //VALIDATE IF THE USER INSERTED THE RIGHT ANSWER TO THE EXPRESSION
    public boolean validateFormula(int a, int b) {
        if (a == b) {
            return true;
        } else {
            return false;
        }
    }

    //verifies if the edittext is empty
    public boolean editTextIsEmpty(EditText et) {
        return et.getText().toString().trim().length() == 0;
    }

    /**
     * Method that allows the invocation of the menu in this activity
     */
    public void clickIconMenu(View view) {
        Intent intent = new Intent(FormatData.this, MenuActivity.class);
        startActivity(intent);
    }
}
