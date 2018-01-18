package com.code4socialgood.code4socialgood;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

    private TextView tvDisplayData;
    private EditText etUrl;
    private Button btnGetData;
    private TextView tvErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplayData = (TextView) findViewById(R.id.tv_getProjectsJSON);
        tvErrorMessage = (TextView) findViewById(R.id.tv_errorMsg);
        etUrl =(EditText) findViewById(R.id.etUrl);
        btnGetData=(Button)findViewById(R.id.btnGetData);
    }

    public void getData(View view){
        String query = etUrl.getText().toString();
        if(isValidURL(query)){
            etUrl.setVisibility(View.GONE);
            etUrl.setText("");
            btnGetData.setVisibility(View.GONE);
            tvDisplayData.setVisibility(View.VISIBLE);
            tvErrorMessage.setVisibility(View.INVISIBLE);
            getDataAsync(query);
        }else{
            onURLError();
        }
    }

    /**
     * Retrieve data asynchronously based on query parameter
     * @param query
     */
    private void getDataAsync(String query){
        URL url = NetworkUtils.buildURL(query);
        tvDisplayData.setText(url.toString());
        new DataAsync().execute(url);
    }

    private void getProjectsData(){
        String query = "http://dev-api.code4socialgood.org/api/projects";
        getDataAsync(query);
    }

    private void getProjectsHeroes(){
        String query = "http://dev-api.code4socialgood.org/api/projects/heroes";
        getDataAsync(query);
    }

    private void getProjectsJobTitles(){
        String query = "http://dev-api.code4socialgood.org/api/projects/jobTitles";
        getDataAsync(query);
    }

    private void getOrganizationsData(){
        String query = "http://dev-api.code4socialgood.org/api/organizations";
        getDataAsync(query);
    }

    private void getOrganizationsTotalCountries(){
        String query = "http://dev-api.code4socialgood.org/api/organizations/countries/total";
        getDataAsync(query);
    }

    private void getStories(){
        String query = "http://dev-api.code4socialgood.org/api/stories";
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

    private Boolean isValidURL(String query){
        if (query.contains("http://dev-api.code4socialgood.org/api/")){
            return true;
        }
        else{
            onURLError();
            return false;
        }
    }

    private void onURLError(){
        tvErrorMessage.setText("You must provide a valid URL.");
        tvErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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
                tvDisplayData.setVisibility(View.GONE);
                etUrl.setVisibility(View.VISIBLE);
                btnGetData.setVisibility(View.VISIBLE);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

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
                tvDisplayData.setText(s);
            }
        }
    }
}
