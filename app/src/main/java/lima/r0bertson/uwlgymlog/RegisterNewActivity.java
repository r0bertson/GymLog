package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RegisterNewActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private EditText txt_load;// = (EditText) findViewById(R.id.t_load);
    private EditText txt_repetition; //= (EditText) findViewById(R.id.t_repetition);
    private Spinner spinner; //= //(Spinner) findViewById(R.id.spinnerExerciseList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_activity);
        txt_load = (EditText) findViewById(R.id.t_load);
        txt_repetition = (EditText) findViewById(R.id.t_repetition);
        spinner = (Spinner) findViewById(R.id.spinnerExerciseList);
        loadSpinnerData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_exercise, menu);

        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String exercise = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + exercise,
                Toast.LENGTH_LONG).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

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


    private void loadSpinnerData() {
        // database handler
        MyDBHandler db = new MyDBHandler(this, null, null, 1);

        // Spinner Drop down elements
        List<String> exercises = db.getAllExercises();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exercises);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }


    public void addActivity(View view){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String name = spinner.getSelectedItem().toString();
        int load = Integer.parseInt(txt_load.getText().toString());
        int repetition = Integer.parseInt(txt_repetition.getText().toString());
        if(txt_load.toString().equals("")){
            //SEND MESSAGE WARNING
            final Dialog dialog = new Dialog(RegisterNewActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);

            // set the custom dialog components - text and button
            TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
            text.setText("Alert message:");
            TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
            image.setText("You must provide a value to the load field.");
            Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }else if(txt_repetition.toString().equals("")){
            //SEND MESSAGE WARNING

            final Dialog dialog = new Dialog(RegisterNewActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);

            // set the custom dialog components - text and button
            TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
            text.setText("Alert message:");
            TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
            image.setText("You must provide a value to the repetition field.");
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
        else {

            Exercise ex = new Exercise(name, load, repetition);
            //Product product =  new Product(productBox.getText().toString(), quantity);

            dbHandler.addExercise(ex);
            finish();
            //DECIDE IF FINISH THE INTENT OR BLANK THE FIELDS TO ADD ANOTHER
        }
    }
    public void cancelClick(View view){
        finish();
    }
}
