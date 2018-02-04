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

/**
 * Created by gabriellecozart on 2/1/18.
 */

public class VolunteerDetailActivity extends AppCompatActivity {

    private TextView tvNameVolunteer;
    private TextView tvLocationVolunteer;
    private TextView tvTitleVolunteer;
    private TextView tvIntroductionVolunteer;
    private ImageView imAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_detail);
        Intent intent = getIntent();
        tvNameVolunteer = (TextView) findViewById(R.id.tvNameVolunteer);
        tvLocationVolunteer = (TextView) findViewById(R.id.tvLocationVolunteer);
        tvTitleVolunteer = (TextView) findViewById(R.id.tvTitleVolunteer);
        tvIntroductionVolunteer = (TextView) findViewById(R.id.tvIntroductionVolunteer);
        imAvatar = (ImageView) findViewById(R.id.imAvatar);
        String avatar = intent.getStringExtra(Constants.VOLUNTEER_AVATAR_URL);
        if(avatar != null && avatar.length() > 0) {
            Picasso.with(this)
                    .load(avatar)
                    .placeholder(R.drawable.placeholder)
                    .into(imAvatar);
        } else {
            Picasso.with(this)
                    .load(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(imAvatar);
        }
        tvNameVolunteer.setText(intent.getStringExtra(Constants.NAME));
        tvLocationVolunteer.setText(intent.getStringExtra(Constants.LOCATION));
        tvTitleVolunteer.setText(intent.getStringExtra(Constants.VOLUNTEER_TITLE));
        tvIntroductionVolunteer.setText(intent.getStringExtra(Constants.VOLUNTEER_INTRODUCTION));
        tvIntroductionVolunteer.setMovementMethod(new ScrollingMovementMethod());

    }

}
