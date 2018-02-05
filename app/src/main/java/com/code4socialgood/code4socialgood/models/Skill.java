package com.code4socialgood.code4socialgood.models;

import com.code4socialgood.code4socialgood.utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gabriellecozart on 2/4/18.
 */

public class Skill {

    private int id;
    private String skillName;


    public Skill(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt(Constants.SKILL_ID);
        this.skillName = jsonObject.getString(Constants.SKILL_NAME);
    }


    public static ArrayList<Skill> fromJsonArray(JSONArray array) {
        ArrayList<Skill> results = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            try {
                results.add(new Skill(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }


    public int getId() { return this.id; }

    public String getSkillName() { return this.skillName; }


    @Override
    public String toString() {
        return "Skills{" +
                Constants.SKILL_ID + "='" + this.id + '\'' +
                ", " + Constants.SKILL_NAME + "='" + this.skillName + '\'' +
                '}';
    }

}
