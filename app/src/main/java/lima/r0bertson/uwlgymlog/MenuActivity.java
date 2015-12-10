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
public class MenuActivity extends ListActivity {
    String[] options;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        options = getResources().getStringArray(R.array.options);
        ListView listOptions = getListView();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                options);
        listOptions.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
       switch(position){
           case 0:{

           }
           case 1:{

           }
           case 2:{

           }
           case 3:{

           }
       }
    }
}
