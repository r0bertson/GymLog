package lima.r0bertson.uwlgymlog;

import java.util.Date;

/**
 * Created by r0bertson on 10/12/2015.
 */
public class Statistics {
    private int load;
    private String date;

    public Statistics (int load, String date){
        this.load = load;
        this.date = date;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String getDay() {
        return date;
    }

    public void setDay(String date) {
        this.date = date;
    }
}
