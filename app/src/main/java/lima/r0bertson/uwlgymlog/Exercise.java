package lima.r0bertson.uwlgymlog;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by r0bertson on 09/12/2015.
 */
public class Exercise {
    private int _id;
    private String _name;
    private int _load;
    private int _repetitions;
    private String _date;


    public Exercise(){ }

    public Exercise(/*int id,*/ String name, int load, int repetitions){
       // this._id = id;
        this._name = name;
        this._load = load;
        this._repetitions = repetitions;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String text = df.format(new Date(Calendar.getInstance().getTimeInMillis()));

        //System.out.println("The date is: " + text)
        this._date =  text;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_load() {
        return _load;
    }

    public void set_load(int _load) {
        this._load = _load;
    }

    public int get_repetitions() {
        return _repetitions;
    }

    public void set_repetitions(int _repetitions) {
        this._repetitions = _repetitions;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
