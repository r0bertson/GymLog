package lima.r0bertson.uwlgymlog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class AboutActivity extends Activity {
    WebView web_about;
    TextView contactme;
    /*
    WebView is used instead of TextView in order to align the text.
    It is necessary to use HTML to achieve this.
    The reference for this is located in:
    http://www.seal.io/2010/12/only-way-how-to-align-text-in-block-in.html
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        web_about = (WebView) findViewById(R.id.webview_about);
        String htmlText = "<html><body><p align=\"justify\">"
                          + getString(R.string.about)
                          + "</p> </body></html>";
        web_about.setBackgroundColor(0x00000000);
        web_about.loadData(htmlText, "text/html", "utf-8");

        contactme = (TextView) findViewById(R.id.txt_contactme);
        //Linkify.addLinks(contactme, Linkify.EMAIL_ADDRESSES);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
     * Method that allows the invocation of the menu in this activity
     */
    public void clickIconMenu(View view){
        Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
