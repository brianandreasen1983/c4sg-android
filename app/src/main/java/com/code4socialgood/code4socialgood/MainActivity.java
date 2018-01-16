package com.code4socialgood.code4socialgood;

import android.net.Network;
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

    private void getProjectsData(){
        String projectsQuery = "http://dev-api.code4socialgood.org/api/projects";
        URL projectsURL = NetworkUtils.buildURL(projectsQuery);
        mGetProjectsDataTextView.setText(projectsURL.toString());
        new ProjectsDataAsync().execute(projectsURL);
    }

    private void getOrganizationsData(){
        String organizationsQuery = "http://dev-api.code4socialgood.org/api/organizations";
        URL organizationsURL = NetworkUtils.buildURL(organizationsQuery);
        mGetProjectsDataTextView.setText(organizationsURL.toString());
        new OrganizationsDataAsync().execute(organizationsURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Comment out the data method call you want to test as the Get Data menu item is bound here.
    //If you find more than one uncommented the text will be overwritten and still pull data I would not recommend using this on Cellular.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicked = item.getItemId();
        if(itemClicked == item.getItemId()){
            //getProjectsData();
            getOrganizationsData();
        }
        return super.onOptionsItemSelected(item);
    }

    //Temp class used for testing getting Projects data asynchronously.
    public class ProjectsDataAsync extends AsyncTask<URL,Void, String>{

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

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
                mGetProjectsDataTextView.setText(s);
            }
        }
    }

    //Temp class used for testing getting Organizations data asynchronously.
    public class OrganizationsDataAsync extends AsyncTask<URL, Void, String>{
        @Override
        protected String doInBackground(URL... urls) {
            URL organizationsURL = urls[0];
            String organizationsResults = null;
            try{
                organizationsResults = NetworkUtils.getResponseFromHttpUrl(organizationsURL);
            }catch (IOException e){
                e.printStackTrace();
            }

            return organizationsResults;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
                mGetProjectsDataTextView.setText(s);
            }
        }
    }
}
