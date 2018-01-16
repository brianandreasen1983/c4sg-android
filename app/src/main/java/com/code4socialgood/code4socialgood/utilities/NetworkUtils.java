package com.code4socialgood.code4socialgood.utilities;

/**
 * Created by Brian Andreasen on 1/16/2018.
 */

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    //REST API URLs

    //PROJECT URLS
    final static String devBaseURL = "http://dev-api.code4socialgood.org/api/";
    //private static final String prodBaseURL = "";

    final static String projectsURL = devBaseURL + "projects/";
    final static String projApplications = projectsURL + "applications";
    final static String projHeroes = projectsURL + "heroes";
    final static String projOrganization = projectsURL + "organization";
    final static String projSearch = projectsURL + "search";
    final static String projUser = projectsURL + "user";

    //Organization URLs
    final static String orgsURL = devBaseURL + "organizations/";
    final static String orgCountriesTotal = orgsURL + "countries/ total";
    final static String orgSearch = orgsURL + "search/";

    //Volunteers URLs


    //Builds a projects URL to test getting data.
    public static URL buildURL(String projectsURL){
        Uri builtUri = Uri.parse(projectsURL);

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
        return url;
    }

//method for response returned from HttpUrl
    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream inStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inStream);
            scanner.useDelimiter("//A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }
            else{
                return null;
            }
        }
        finally{
            urlConnection.disconnect();
        }
    }
}



