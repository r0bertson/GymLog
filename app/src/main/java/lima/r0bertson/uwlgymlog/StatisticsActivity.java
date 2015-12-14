package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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


        //when we select an item on the spinner, reload the information and draw graph
        spinner_stat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String selected = spinner_stat.getSelectedItem().toString();
                //connect to the database and get data
                graph.removeAllSeries();
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

    /**
     * Use the data obtained by the method connectAndRefresh() to draw
     * the graph using the API GraphView
     */
    public void setGraph(ArrayList<Statistics> st){
        //defines an array of datapoint
        BarGraphSeries<DataPoint> series2;
        DataPoint[] datapoint = new DataPoint[st.size()];

        for(int i = 0; i<st.size();i++){
            datapoint[i] = new DataPoint(i,st.get(i).getLoad());
        }
        series2 = new BarGraphSeries<DataPoint>(datapoint);
        series2.resetData(datapoint);
        graph.addSeries(series2);
        series2.setSpacing(25);
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.BLACK);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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

    /**
     * Load the exercises names stored in the database and put them on the spinner
     */
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

    /**
     * Method that allows the invocation of the menu in this activity
     */
    public void clickIconMenu(View view){
        Intent intent = new Intent(StatisticsActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}
