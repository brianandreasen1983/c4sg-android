package com.code4socialgood.code4socialgood.models;


import com.code4socialgood.code4socialgood.utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Project {

    String id;
    String name;
    String organizationId;
    String description;
    String imageUrl;
    String city;
    String state;
    String country;
    String remoteFlag;
    String status;
    String createdTime;
    String updatedTime;
    String organizationName;
    String organizationLogoUrl;
    String jobTitleID;

    public Project(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString(Constants.PROJECT_ID);
        this.name = jsonObject.getString(Constants.PROJECT_NAME);
        this.organizationId = jsonObject.getString(Constants.PROJECT_ORGANIZATION_ID);
        this.description = jsonObject.getString(Constants.PROJECT_DESCRIPTION);
        this.imageUrl = jsonObject.getString(Constants.PROJECT_IMAGE_URL);
        this.city = jsonObject.getString(Constants.PROJECT_CITY);
        this.state = jsonObject.getString(Constants.PROJECT_STATE);
        this.country = jsonObject.getString(Constants.PROJECT_COUNTRY);
        this.remoteFlag = jsonObject.getString(Constants.PROJECT_REMOTE_FLAG);
        this.status = jsonObject.getString(Constants.PROJECT_STATUS);
        this.createdTime =jsonObject.getString(Constants.PROJECT_CREATED_TIME);
        this.updatedTime = jsonObject.getString(Constants.PROJECT_UPDATED_TIME);
        this.organizationName = jsonObject.getString(Constants.PROJECT_ORGANIZATION_NAME);
        this.organizationLogoUrl = jsonObject.getString(Constants.PROJECT_ORGANIZATION_LOGO_URL);
        this.jobTitleID = jsonObject.getString(Constants.PROJECT_JOB_TITLE_ID);
    }

    public static ArrayList<Project> fromJsonArray(JSONArray array){
        ArrayList<Project> results = new ArrayList<>();
        for(int x=0; x< array.length(); x++){
            try {
                results.add(new Project(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getRemoteFlag() {
        return remoteFlag;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationLogoUrl() {
        return organizationLogoUrl;
    }

    public String getJobTitleID() {
        return jobTitleID;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", remoteFlag='" + remoteFlag + '\'' +
                ", status='" + status + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationLogoUrl='" + organizationLogoUrl + '\'' +
                ", jobTitleID='" + jobTitleID + '\'' +
                '}';
    }
}

