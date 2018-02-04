package com.code4socialgood.code4socialgood.models;

import com.code4socialgood.code4socialgood.utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gabriellecozart on 1/30/18.
 */

public class Volunteer {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String state;
    private String country;
    private double latitude;
    private double longitude;
    private String phone;
    private String title;
    private String introduction;
    private String avatarUrl;
    private String linkedinUrl;
    private String personalUrl;
    private String githubUrl;
    private String resumeUrl;
    private String facebookUrl;
    private String twitterUrl;
    private String publishFlag;
    private String notifyFlag;
    private String chatUsername;
    private String role;
    private String status;
    private String createdTime;
    private String updatedTime;
    private int jobTitleId;


    public Volunteer(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt(Constants.VOLUNTEER_ID);
        this.userName = jsonObject.getString(Constants.VOLUNTEER_USER_NAME);
        this.firstName = jsonObject.getString(Constants.VOLUNTEER_FIRST_NAME);
        this.lastName = jsonObject.getString(Constants.VOLUNTEER_LAST_NAME);
        this.email = jsonObject.getString(Constants.VOLUNTEER_EMAIL);
        this.state = jsonObject.getString(Constants.VOLUNTEER_STATE);
        this.country = jsonObject.getString(Constants.VOLUNTEER_COUNTRY);
        this.latitude = jsonObject.getDouble(Constants.VOLUNTEER_LATITUDE);
        this.longitude = jsonObject.getDouble(Constants.VOLUNTEER_LONGITUDE);
        this.phone = jsonObject.getString(Constants.VOLUNTEER_PHONE);
        this.title = jsonObject.getString(Constants.VOLUNTEER_TITLE);
        this.introduction = jsonObject.getString(Constants.VOLUNTEER_INTRODUCTION);
        this.avatarUrl = jsonObject.getString(Constants.VOLUNTEER_AVATAR_URL);
        this.linkedinUrl = jsonObject.getString(Constants.VOLUNTEER_LINKEDIN_URL);
        this.personalUrl = jsonObject.getString(Constants.VOLUNTEER_PERSONAL_URL);
        this.githubUrl = jsonObject.getString(Constants.VOLUNTEER_GITHUB_URL);
        this.resumeUrl = jsonObject.getString(Constants.VOLUNTEER_RESUME_URL);
        this.facebookUrl = jsonObject.getString(Constants.VOLUNTEER_FACEBOOK_URL);
        this.twitterUrl = jsonObject.getString(Constants.VOLUNTEER_TWITTER_URL);
        this.publishFlag = jsonObject.getString(Constants.VOLUNTEER_PUBLISH_FLAG);
        this.notifyFlag = jsonObject.getString(Constants.VOLUNTEER_NOTIFY_FLAG);
        this.chatUsername = jsonObject.getString(Constants.VOLUNTEER_CHAT_USERNAME);
        this.role = jsonObject.getString(Constants.VOLUNTEER_ROLE);
        this.status = jsonObject.getString(Constants.VOLUNTEER_STATUS);
        this.createdTime = jsonObject.getString(Constants.VOLUNTEER_CREATED_TIME);
        this.updatedTime = jsonObject.getString(Constants.VOLUNTEER_UPDATED_TIME);
        this.jobTitleId = jsonObject.getInt(Constants.VOLUNTEER_JOB_TITLE_ID);
    }


    public static ArrayList<Volunteer> fromJsonArray(JSONArray array) {
        ArrayList<Volunteer> results = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            try {
                results.add(new Volunteer(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }


    public int getId() { return this.id; }

    public String getUserName() { return this.userName; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getEmail() { return this.email; }

    public String getState() { return this.state; }

    public String getCountry() { return this.country; }

    public double getLatitude() { return this.latitude; }

    public double getLongitude() { return this.longitude; }

    public String getPhone() { return this.phone; }

    public String getTitle() { return this.title; }

    public String getIntroduction() { return this.introduction; }

    public String getAvatarUrl() { return this.avatarUrl; }

    public String getLinkedinUrl() { return this.linkedinUrl; }

    public String getPersonalUrl() { return this.personalUrl; }

    public String getGithubUrl() { return this.githubUrl; }

    public String getResumeUrl() { return this.resumeUrl; }

    public String getFacebookUrl() { return this.facebookUrl; }

    public String getTwitterUrl() { return this.twitterUrl; }

    public String getPublishFlag() { return this.publishFlag; }

    public String getNotifyFlag() { return this.notifyFlag; }

    public String getChatUsername() { return this.chatUsername; }

    public String getRole() { return this.role; }

    public String getStatus() { return this.status; }

    public String getCreatedTime() { return this.createdTime; }

    public String getUpdatedTime() { return this.updatedTime; }

    public int getJobTitleId() { return this.jobTitleId; }


    @Override
    public String toString() {
        return "Volunteer{" +
                Constants.VOLUNTEER_ID + "='" + this.id + '\'' +
                ", " + Constants.VOLUNTEER_USER_NAME + "='" + this.userName + '\'' +
                ", " + Constants.VOLUNTEER_FIRST_NAME + "='" + this.firstName + '\'' +
                ", " + Constants.VOLUNTEER_LAST_NAME + "='" + this.lastName + '\'' +
                ", " + Constants.VOLUNTEER_EMAIL + "='" + this.email + '\'' +
                ", " + Constants.VOLUNTEER_STATE + "='" + this.state + '\'' +
                ", " + Constants.VOLUNTEER_COUNTRY + "='" + this.country + '\'' +
                ", " + Constants.VOLUNTEER_LATITUDE + "='" + this.latitude + '\'' +
                ", " + Constants.VOLUNTEER_LONGITUDE + "='" + this.longitude + '\'' +
                ", " + Constants.VOLUNTEER_PHONE + "='" + this.phone + '\'' +
                ", " + Constants.VOLUNTEER_TITLE + "='" + this.title + '\'' +
                ", " + Constants.VOLUNTEER_INTRODUCTION + "='" + this.introduction + '\'' +
                ", " + Constants.VOLUNTEER_AVATAR_URL + "='" + this.avatarUrl + '\'' +
                ", " + Constants.VOLUNTEER_LINKEDIN_URL + "='" + this.linkedinUrl + '\'' +
                ", " + Constants.VOLUNTEER_PERSONAL_URL + "='" + this.personalUrl + '\'' +
                ", " + Constants.VOLUNTEER_GITHUB_URL + "='" + this.githubUrl + '\'' +
                ", " + Constants.VOLUNTEER_RESUME_URL + "='" + this.resumeUrl + '\'' +
                ", " + Constants.VOLUNTEER_FACEBOOK_URL + "='" + this.facebookUrl + '\'' +
                ", " + Constants.VOLUNTEER_TWITTER_URL + "='" + this.twitterUrl + '\'' +
                ", " + Constants.VOLUNTEER_PUBLISH_FLAG + "='" + this.publishFlag + '\'' +
                ", " + Constants.VOLUNTEER_NOTIFY_FLAG + "='" + this.notifyFlag + '\'' +
                ", " + Constants.VOLUNTEER_CHAT_USERNAME + "='" + this.chatUsername + '\'' +
                ", " + Constants.VOLUNTEER_ROLE + "='" + this.role + '\'' +
                ", " + Constants.VOLUNTEER_STATUS + "='" + this.status + '\'' +
                ", " + Constants.VOLUNTEER_CREATED_TIME + "='" + this.createdTime + '\'' +
                ", " + Constants.VOLUNTEER_UPDATED_TIME + "='" + this.updatedTime + '\'' +
                ", " + Constants.VOLUNTEER_JOB_TITLE_ID + "='" + this.jobTitleId + '\'' +
                '}';
    }

}


