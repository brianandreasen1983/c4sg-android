package com.code4socialgood.code4socialgood;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.Adapter.ProjectRecyclerViewAdapter;
import com.code4socialgood.code4socialgood.Adapter.VolunteerRecyclerViewAdapter;
import com.code4socialgood.code4socialgood.models.Project;
import com.code4socialgood.code4socialgood.models.Skill;
import com.code4socialgood.code4socialgood.models.Volunteer;
import com.code4socialgood.code4socialgood.utilities.NetworkUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{

    private TextView tvDisplayData;
    private EditText etUrl;
    private Button btnGetData;
    private TextView tvErrorMessage;
    private AsyncHttpClient client;
    private ArrayList<Project> projects;
    private ArrayList<Volunteer> volunteers;
    private ArrayList<Skill> skills;
    private RecyclerView recycler;
    private ProjectRecyclerViewAdapter projectRecyclerViewAdapter;
    private VolunteerRecyclerViewAdapter volunteerRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    private void initialize(){
        tvDisplayData = (TextView) findViewById(R.id.tv_getProjectsJSON);
        tvErrorMessage = (TextView) findViewById(R.id.tv_errorMsg);
        etUrl =(EditText) findViewById(R.id.etUrl);
        btnGetData=(Button)findViewById(R.id.btnGetData);
        client = new AsyncHttpClient();
        projects = new ArrayList<>();
        volunteers = new ArrayList<>();
        skills = new ArrayList<>();
        recycler = (RecyclerView)findViewById(R.id.recyclerView);
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
             getDataAsync(query);
             displayResult();
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

    private void displayResult(){
        tvErrorMessage.setVisibility(View.GONE);
        etUrl.setVisibility(View.GONE);
        etUrl.setText("");
        btnGetData.setVisibility(View.GONE);
        tvDisplayData.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
    }

    public void getProjects(){

        projectRecyclerViewAdapter = new ProjectRecyclerViewAdapter(this,projects);
        tvDisplayData.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        recycler.setAdapter(projectRecyclerViewAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        client.get(getString(R.string.projectDataQueryURL),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                JSONArray projectJsonResult = null;
                projectJsonResult = response;
                projects.addAll(Project.fromJsonArray(projectJsonResult));
                projectRecyclerViewAdapter.notifyDataSetChanged();
                Log.d("Debug", projects.toString());

            }
        });
    }


    public void getVolunteers() {

        volunteerRecyclerViewAdapter = new VolunteerRecyclerViewAdapter(this, volunteers);
        tvDisplayData.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        recycler.setAdapter(volunteerRecyclerViewAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        client.get(getString(R.string.userDataQueryURL),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                JSONArray volunteerJsonResult = null;
                volunteerJsonResult = response;
                volunteers.addAll(Volunteer.fromJsonArray(volunteerJsonResult));
                volunteerRecyclerViewAdapter.notifyDataSetChanged();
                Log.d("Debug", volunteers.toString());
            }
        });
    }

    public void getSkills() {
        client.get(getString(R.string.skillsDataQueryURL),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                JSONArray skillJsonResult = null;
                skillJsonResult = response;
                skills.addAll(Skill.fromJsonArray(skillJsonResult));
                Log.d("Debug", skills.toString());
            }
        });
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
                    displayResult();
                    getDataAsync(orgDataQuery);
                }else{
                    showConnectionError();
                }
                break;
            case R.id.action_getProjectData:
                if(isOnline()){
                    String projectDataQuery = getString(R.string.projectDataQueryURL);
                    displayResult();
                    getDataAsync(projectDataQuery);
                    getProjects();
                }else{
                    showConnectionError();
                }
                break;
            case R.id.action_getUserData:
                if(isOnline()){
                    String userDataQuery = getString(R.string.userDataQueryURL);
                    displayResult();
                    getDataAsync(userDataQuery);
                    getVolunteers();
                }else{
                    showConnectionError();
                }
                break;
            case R.id.action_getSkills:
                if(isOnline()){
                    String skillsDataQuery = getString(R.string.skillsDataQueryURL);
                    displayResult();
                    getDataAsync(skillsDataQuery);
                    getSkills();
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
