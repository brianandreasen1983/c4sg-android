package com.code4socialgood.code4socialgood.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.R;
import com.code4socialgood.code4socialgood.utilities.Constants;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class OrganizationDetailActivity extends AppCompatActivity {

    TextView tvOrganizationName;
    TextView tvOrganizationWebsite;
    TextView tvOrganizationCategory;
    TextView tvOrganizationLocation;
    TextView tvOrganizationIntroduction;
    ImageView imOrganizationLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);


        tvOrganizationName = (TextView) findViewById(R.id.tvOrgDetailName);
        tvOrganizationWebsite = (TextView) findViewById(R.id.tvOrgDetailWebSite);
        tvOrganizationCategory = (TextView) findViewById(R.id.tvOrgDetailCategory);
        tvOrganizationLocation = (TextView) findViewById(R.id.tvOrgDetailLocation);
        tvOrganizationIntroduction = (TextView) findViewById(R.id.tvOrgDetailIntroduction);
        imOrganizationLogo =(ImageView)findViewById(R.id.imOrgDetailLogo);

        //setting image
        Intent intentThatStartedThisActivity = getIntent();
        String image = intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_LOGO_URL);
        if(image!=null && image.length()>0){
            Picasso.with(this).load(image).placeholder(R.drawable.placeholder).into(imOrganizationLogo);
        }

        //setting org name
        tvOrganizationName.setText(intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_NAME));

        //setting org website
        String website = intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_WEBSITE_URL);
        if(website == null)
            tvOrganizationWebsite.setText("Unknown");
        else if(website.equals("null") || website.equals(""))
            tvOrganizationWebsite.setText("Unknown");
        else
            tvOrganizationWebsite.setText(website);

        //setting org category
        String category = intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_CATEGORY);
        if(category.equals("N"))
            tvOrganizationCategory.setText("Non-Profit");
        else if(category.equals("O"))
            tvOrganizationCategory.setText("Open Source");
        else
            tvOrganizationCategory.setText("Unknown");

        //setting org location
        String city = intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_CITY);
        String state = intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_STATE);
        String country = intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_COUNTRY);
        String location = "";
        if(city == null && state == null && country==null)
            location="Unknown";
        else if(city!=null && state!=null&&country!=null){
            if(city.equals("null") || city.equals("") && state.equals("null") || state.equals("") && country.equals("null") || country.equals(""))
                location="Unknown";
            else{
                location = city;
                if(!city.equals(""))
                    location = location + ",";
                location = location + state;
                if(!state.equals(""))
                    location = location + ",";
                location = location+country;
            }
        }
        tvOrganizationLocation.setText( location );

        //setting org description
        tvOrganizationIntroduction.setText(intentThatStartedThisActivity.getStringExtra(Constants.ORGANIZATION_DESCRIPTION));

    }
}
