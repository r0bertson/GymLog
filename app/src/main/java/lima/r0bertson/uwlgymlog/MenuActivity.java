package lima.r0bertson.uwlgymlog;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by r0bertson on 10/12/2015.
 */

/**
 * Activity responsible for showing the menu when the user tap the gym button.
 *
 * ListActivity was chosen instead of a DrawerLayout, because of the apps
 * simplicity. It was not desired to make an fancy layout, since the use
 * of the app is mainly record exercises done at the gym in a fast way.
 */
public class MenuActivity extends ListActivity {
    String[] options;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        options = getResources().getStringArray(R.array.options); // load options on strings.xml
        ListView listOptions = getListView();
        //set the type of adapter and the way of showing the items
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                options);
        listOptions.setAdapter(listAdapter);
    }


    //does the business behind the click on the options.
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
       switch(position){
           case 0:{
               //home
               finish(); //finish the activity (MenuActivity)
            return;
           }
           case 1:{
            //statistics
               //create and call a new activity using intent
               Intent intent = new Intent(MenuActivity.this, StatisticsActivity.class);
               startActivity(intent);
               break;
           }
           case 2:{
               //format data
               //create and call a new activity using intent
               Intent intent = new Intent(MenuActivity.this, FormatData.class);
               startActivity(intent);
               break;
           }
           case 3:{
            //about
               //create and call a new activity using intent
               Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
               startActivity(intent);
               break;
           }
       }
    }
}
