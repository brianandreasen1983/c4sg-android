package com.code4socialgood.code4socialgood;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends AppCompatActivity{

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

    private boolean isOnline(){
        ConnectivityManager connManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void showConnectionError(){
        tvDisplayData.setText(getString(R.string.connection_error));
    }

    public void getData(View view){
         String query = etUrl.getText().toString();
         if(isValidURL(query)){
             etUrl.setVisibility(View.GONE);
             etUrl.setText("");
             btnGetData.setVisibility(View.GONE);
             tvDisplayData.setVisibility(View.VISIBLE);
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

    private void getProjectsHeroes(){
        String query = getString(R.string.projectHeroesURL);
        getDataAsync(query);
    }

    private void getProjectsJobTitles(){
        String query = getString(R.string.projectJobTitlesURL);
        getDataAsync(query);
    }

    private void getOrganizationsTotalCountries(){
        String query = getString(R.string.organizationTotalCountriesURL);
        getDataAsync(query);
    }

    private void getStories(){
        String query = getString(R.string.storiesURL);
        getDataAsync(query);
    }

    private Boolean isValidURL(String query){
        if (query.contains(getString(R.string.cs4gDevURL))){
            return true;
        }
        else{
            onURLError();
            return false;
        }
    }

    private void onURLError(){
        tvErrorMessage.setText(R.string.URL_error);
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
                if(isOnline()){
                    String orgDataQuery = getString(R.string.orgDataQueryURL);
                    getDataAsync(orgDataQuery);
                }else{
                    showConnectionError();
                }
                break;
            case R.id.action_getProjectData:
                if(isOnline()){
                    String projectDataQuery = getString(R.string.projectDataQueryURL);
                    getDataAsync(projectDataQuery);
                }else{
                    showConnectionError();
                }
                break;
            case R.id.action_getUserData:
                if(isOnline()){
                    String userDataQuery = getString(R.string.userDataQueryURL);
                    getDataAsync(userDataQuery);
                }else{
                    showConnectionError();
                }
                break;
            case R.id.action_getSkills:
                if(isOnline()){
                    String skillsDataQuery = getString(R.string.skillsDataQueryURL);
                    getDataAsync(skillsDataQuery);
                }else{
                    showConnectionError();
                }
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
