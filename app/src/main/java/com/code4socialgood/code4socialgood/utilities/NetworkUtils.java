package com.code4socialgood.code4socialgood.utilities;

/**
 * Created by Brian Andreasen on 1/16/2018.
 */

import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    Context mContext;

    public NetworkUtils(Context mContext){
        this.mContext = mContext;
    }

    public static URL buildURL(String urlString){
        Uri builtUri = Uri.parse(urlString);

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
        return url;
    }

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



