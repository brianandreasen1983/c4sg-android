package com.code4socialgood.code4socialgood.models;

import com.code4socialgood.code4socialgood.utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sayali on 4/13/2018.
 */

public class Organization {

    String id;
    String name;
    String logoUrl;
    String websiteUrl;
    String description;
    String address1;
    String address2;
    String city;
    String zip;
    String state;
    String country;
    String category;
    String ein;
    String status;
    String lattitude;
    String longitude;
    String facebookUrl;
    String twitterUrl;
    String linkedInUrl;
    String createdTime;
    String projectUpdatedTime;

    public String getId(){return id;}
    public String getName() {return  name;}
    public String getLogoUrl() {return  logoUrl;}
    public String getWebsiteUrl(){return websiteUrl;}
    public String getDescription(){return description;}
    public String getAddress1(){return address1;}
    public String getAddress2() {return address2;}
    public String getCity(){return  city;}
    public String getZip() {return zip;}
    public String getState(){return  state;}
    public String getCountry(){return country;}
    public String getCategory(){return category;}
    public String getEin(){return ein;}
    public String getStatus(){return  status;}
    public String getLattitude(){return lattitude;}
    public String getLongitude(){return  longitude;}
    public String getFacebookUrl(){return facebookUrl;}
    public String getTwitterUrl(){return twitterUrl;}
    public String getLinkedInUrl(){return linkedInUrl;}
    public String getCreatedTime(){return createdTime;}
    public String getProjectUpdatedTime(){return projectUpdatedTime;}

    public  Organization(JSONObject jsonObject) throws JSONException {

        this.id=jsonObject.getString(Constants.ORGANIZATION_ID);
        this.name=jsonObject.getString(Constants.ORGANIZATION_NAME);
        this.websiteUrl=jsonObject.getString(Constants.ORGANIZATION_WEBSITE_URL);
        this.logoUrl=jsonObject.getString(Constants.ORGANIZATION_LOGO_URL);
        this.description=jsonObject.getString(Constants.ORGANIZATION_DESCRIPTION);

        this.address1=jsonObject.getString(Constants.ORGANIZATION_ADDRESS1);
        this.address2=jsonObject.getString(Constants.ORGANIZATION_ADDRESS2);
        this.city=jsonObject.getString(Constants.ORGANIZATION_CITY);
        this.zip=jsonObject.getString(Constants.ORGANIZATION_ZIP);
        this.state=jsonObject.getString(Constants.ORGANIZATION_STATE);
        this.country=jsonObject.getString(Constants.ORGANIZATION_COUNTRY);

        this.category=jsonObject.getString(Constants.ORGANIZATION_CATEGORY);
        this.ein=jsonObject.getString(Constants.ORGANIZATION_EIN);
        this.status=jsonObject.getString(Constants.ORGANIZATION_STATUS);
        //this.lattitude=jsonObject.getString(Constants.ORGANIZATION_LATTITUDE);
        //this.longitude=jsonObject.getString(Constants.ORGANIZATION_LONGITUDE);

        this.facebookUrl=jsonObject.getString(Constants.ORGANIZATION_FACEBOOK_URL);
        this.twitterUrl=jsonObject.getString(Constants.ORGANIZATION_TWITTER_URL);
        this.linkedInUrl=jsonObject.getString(Constants.ORGANIZATION_LINKEDIN_URL);

        this.createdTime=jsonObject.getString(Constants.ORGANIZATION_CREATED_TIME);
        this.projectUpdatedTime=jsonObject.getString(Constants.ORGANIZATION_PROJECT_UPDATED_TIME);

    }

    public static ArrayList<Organization> fromJsonArray(JSONArray array){
        ArrayList<Organization> orgs = new ArrayList<Organization>();
        for(int i=0; i< array.length(); i++){
            try{
                orgs.add(new Organization(array.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return orgs;
    }

}
