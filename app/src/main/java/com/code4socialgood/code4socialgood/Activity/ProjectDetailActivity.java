package com.code4socialgood.code4socialgood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.R;
import com.code4socialgood.code4socialgood.utilities.Constants;
import com.squareup.picasso.Picasso;

public class ProjectDetailActivity extends AppCompatActivity {

    private TextView tvNameProject;
    private TextView tvLocationProject;
    private TextView tvPostedProject;
    private TextView tvDescriptionProject;
    private ImageView imLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        Intent intent = getIntent();
        tvNameProject =(TextView)findViewById(R.id.tvNameProject);
        tvLocationProject =(TextView)findViewById(R.id.tvLocationProject);
        tvPostedProject =(TextView)findViewById(R.id.tvPostedProject);
        tvDescriptionProject =(TextView)findViewById(R.id.tvDescriptionProject);
        imLogo =(ImageView)findViewById(R.id.imLogo);
        String image = intent.getStringExtra(Constants.PROJECT_ORGANIZATION_LOGO_URL);
        if(image!=null && image.length()>0){
            Picasso.with(this)
                    .load(image)
                    .placeholder(R.drawable.placeholder)
                    .into(imLogo);
        }
        tvNameProject.setText(intent.getStringExtra(Constants.PROJECT_NAME));
        tvLocationProject.setText(intent.getStringExtra(Constants.PROJECT_LOCATION));
        tvPostedProject.setText(intent.getStringExtra(Constants.PROJECT_CREATED_TIME).split(" ")[0]);
        tvDescriptionProject.setText(intent.getStringExtra(Constants.PROJECT_DESCRIPTION));
        tvDescriptionProject.setMovementMethod(new ScrollingMovementMethod());
    }


}
