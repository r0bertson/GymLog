package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        txt_name = (EditText) findViewById(R.id.t_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_routine, menu);
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

    public void cancelClick(View view){
        finish();
    }
    public void addExerciseToRoutineClick(View view){
        String name = txt_name.getText().toString();
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        if(name.equals("")){
            final Dialog dialog = new Dialog(AddExerciseToRoutine.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);

            // set the custom dialog components - text and button
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
            //MESSAGE WARNING THAT FIELD CANNOT BE NULL

        }else if(db.exerciseNameIsValid(name)){
            db.addExerciseOnRoutine(name);
        }
        else{

            final Dialog dialog = new Dialog(AddExerciseToRoutine.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_box);

            // set the custom dialog components - text and button
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
            //MESSAGE WARNING THAT THE EXERCISE IS ALREADY IN DATABASE
        }

    }
}
