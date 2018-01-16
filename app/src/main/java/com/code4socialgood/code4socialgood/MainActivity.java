package com.code4socialgood.code4socialgood;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.code4socialgood.code4socialgood.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mGetProjectsDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGetProjectsDataTextView = (TextView) findViewById(R.id.tv_getProjectsJSON);
    }

    private void getTheProjectsData(){
        String projectsQuery = "http://dev-api.code4socialgood.org/api/projects";
        URL projectsURL = NetworkUtils.buildURL(projectsQuery);
        mGetProjectsDataTextView.setText(projectsURL.toString());
        new GetProjectsTask().execute(projectsURL);
    }

    public class GetProjectsTask extends AsyncTask<URL,Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL projectsURL = urls[0];
            String projectsResults = null;
            try{
                projectsResults = NetworkUtils.getResponseFromHttpUrl(projectsURL);
            }catch(IOException e){
                e.printStackTrace();
            }
            return projectsResults;
        }

        //Not Implemented Yet
        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
                mGetProjectsDataTextView.setText(s);
            }
        }
    }

    //Not yet implemented
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Not yet implemented
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicked = item.getItemId();
        if(itemClicked == item.getItemId()){
            getTheProjectsData();
        }
        return super.onOptionsItemSelected(item);
    }
}
