package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddExerciseToRoutine extends Activity {
    private EditText txt_name;
    private EditText txt_loadunit;
    private EditText txt_repunit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        txt_name = (EditText) findViewById(R.id.t_name);
        txt_loadunit = (EditText) findViewById(R.id.t_load_unit);
        txt_repunit = (EditText) findViewById(R.id.t_rep_unit);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_routine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //method of the cancel button
    public void cancelClick(View view){
        finish();
    }

    /**
     * Add exercise to the database, storing its name, load and repetition units
     * this method do not allow the user to let the name blank
     * and also query the db to see if the exercise is already in it
     */
    public void addExerciseToRoutineClick(View view){
        String name = txt_name.getText().toString();
        String load_unit = txt_loadunit.getText().toString();
        String rep_unit = txt_repunit.getText().toString();
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        if(name.equals("")){ //MESSAGE SHOWING THAT THE FIELD CANNOT BE BLANK
            final Dialog dialog = new Dialog(AddExerciseToRoutine.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);
            TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
            text.setText("Alert message:");
            TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
            image.setText("You must provide a name to the exercise.");
            Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
            // if button is clicked, close the custom dialog
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else if(db.exerciseNameIsValid(name)){
            db.addExerciseOnRoutine(name, load_unit, rep_unit);
        }
        else{ //MESSAGE SHOWING THAT THE EXERCISE IS ALREADY IN THE DB
            final Dialog dialog = new Dialog(AddExerciseToRoutine.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);
            TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
            text.setText("Alert message:");
            TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
            image.setText("The exercise "+ name + " is already in our database. If you want to add another exercise, try a different name.");
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

    /**
     * Method that allows the invocation of the menu in this activity
     */
    public void clickIconMenu(View view){
        Intent intent = new Intent(AddExerciseToRoutine.this, MenuActivity.class);
        startActivity(intent);
    }
}
