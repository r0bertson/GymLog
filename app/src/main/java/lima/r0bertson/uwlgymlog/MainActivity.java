package lima.r0bertson.uwlgymlog;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        db.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    //click on the add exercise button
    public void newExerciseClick(View view){
        Intent intent = new Intent(MainActivity.this, AddExerciseToRoutine.class);
        startActivity(intent);
}
    //click on the register button
    public void registerActivityClick(View view){
        Intent intent = new Intent(MainActivity.this, RegisterNewActivity.class);
        startActivity(intent);
        }


    /**
     * Method that allows the invocation of the menu in this activity
     */
    public void clickIconMenu(View view){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
