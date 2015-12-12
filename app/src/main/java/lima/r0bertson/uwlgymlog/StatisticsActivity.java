package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends Activity {
    private Spinner spinner_stat;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        spinner_stat = (Spinner) findViewById(R.id.spinner_statistics);
        graph = (GraphView) findViewById(R.id.graph_statistics);
        loadSpinnerData();

        spinner_stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code for setting the image based on the item clicked....here
                String selected = spinner_stat.getSelectedItem().toString();
                //do search and get data

                //set the data and make the graph show it
                connectAndRefresh(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
    public void connectAndRefresh(String name){
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        ArrayList<Statistics> st = db.getStatistics(name);
        setGraph(st);
    }

    public void setGraph(ArrayList<Statistics> st){
        DataPoint[] datapoint = new DataPoint[st.size()];
        for(int i = 0; i<st.size();i++){
            datapoint[i] = new DataPoint(st.get(i).getLoad(),st.get(i).getLoad());
        }
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<DataPoint>(datapoint);
        graph.addSeries(series2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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
        spinner_stat.setAdapter(dataAdapter);
    }

}
