package com.code4socialgood.code4socialgood;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mGetProjectsDataTextView;
    private EditText etUrl;
    private Button btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGetProjectsDataTextView = (TextView) findViewById(R.id.tv_getProjectsJSON);
        etUrl =(EditText) findViewById(R.id.etUrl);
        btnGetData=(Button)findViewById(R.id.btnGetData);
    }

    public void getData(View view){
        String query =etUrl.getText().toString();
        if(query!=null){
            etUrl.setVisibility(View.GONE);
            etUrl.setText("");
            btnGetData.setVisibility(View.GONE);
            mGetProjectsDataTextView.setVisibility(View.VISIBLE);
            getDataAsync(query);
        }else{
            etUrl.setHint("Please enter Url");
        }

    }

    /**
     * Retrieve data asynchronously based on query parameter
     * @param query
     */
    private void getDataAsync(String query){
        URL url = NetworkUtils.buildURL(query);
        mGetProjectsDataTextView.setText(url.toString());
        new DataAsync().execute(url);
    }

    private void getProjectsData(){
        String query = "http://dev-api.code4socialgood.org/api/projects";
        getDataAsync(query);
    }

    private void getOrganizationsData(){
        String query = "http://dev-api.code4socialgood.org/api/organizations";
        getDataAsync(query);
    }

    private void getUserData(){
        String query = "http://dev-api.code4socialgood.org/api/users";
        getDataAsync(query);
    }

    private void getSkills(){
        String query = "http://dev-api.code4socialgood.org/api/skills";
        getDataAsync(query);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Comment out the data method call you want to test as the Get Data menu item is bound here.
    //If you find more than one uncommented the text will be overwritten and still pull data I would not recommend using this on Cellular.
    //you select any menu item that will be displayed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicked = item.getItemId();
        switch (itemClicked){
            case R.id.action_getOrganizationsData:
                getOrganizationsData();
                break;
            case R.id.action_getProjectData:
                getProjectsData();
                break;
            case R.id.action_getUserData:
                getUserData();
                break;
            case R.id.action_getSkills:
                getSkills();
                break;
            case R.id.action_getData:
                mGetProjectsDataTextView.setVisibility(View.GONE);
                etUrl.setVisibility(View.VISIBLE);
                btnGetData.setVisibility(View.VISIBLE);
                break;

        }
      /*  if(itemClicked == item.getItemId()){
            //getProjectsData();
            getOrganizationsData();
        }*/
        return super.onOptionsItemSelected(item);
    }

    //Temp class used for testing getting Projects data asynchronously.
   /* public class ProjectsDataAsync extends AsyncTask<URL,Void, String>{

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
    }*/

    //Temp class used for testing getting Organizations data asynchronously.
    public class DataAsync extends AsyncTask<URL, Void, String>{
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String results = null;
            try{
                results = NetworkUtils.getResponseFromHttpUrl(url);
            }catch (IOException e){
                e.printStackTrace();
            }

            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
                mGetProjectsDataTextView.setText(s);
            }
        }
    }
}
