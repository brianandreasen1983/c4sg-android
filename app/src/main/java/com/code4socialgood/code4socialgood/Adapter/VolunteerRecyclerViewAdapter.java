package com.code4socialgood.code4socialgood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.Activity.VolunteerDetailActivity;
import com.code4socialgood.code4socialgood.R;
import com.code4socialgood.code4socialgood.models.Volunteer;
import com.code4socialgood.code4socialgood.utilities.Constants;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.List;

/**
 * Created by gabriellecozart on 2/1/18.
 */

public class VolunteerRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Volunteer> items;

    public VolunteerRecyclerViewAdapter(Context context, List<Volunteer> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.item_volunteer,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String avatar = null;
        ViewHolder viewHolder = (ViewHolder) holder;
        Volunteer volunteer = items.get(position);
        if(volunteer != null) {
            viewHolder.getTvIntroduction().setText(volunteer.getIntroduction());
            viewHolder.getTvName().setText(volunteer.getName());
            viewHolder.getTvLocation().setText(volunteer.getLocation());
            avatar = volunteer.getAvatarUrl();
            if(avatar != null && avatar.length() > 0) {
                Picasso.with(context)
                        .load(avatar)
                        .placeholder(R.drawable.placeholder)
                        .into(viewHolder.getImAvatar());
            } else {
                Picasso.with(context)
                        .load(R.drawable.placeholder)
                        .placeholder(R.drawable.placeholder)
                        .into(viewHolder.getImAvatar());
            }

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imAvatar;
        private TextView tvName;
        private TextView tvLocation;
        private TextView tvIntroduction;

        public ViewHolder(View view) {
            super(view);
            imAvatar = (ImageView) view.findViewById(R.id.imAvatar);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvLocation = (TextView) view.findViewById(R.id.tvLocation);
            tvIntroduction = (TextView) view.findViewById(R.id.tvIntroduction);
            view.setOnClickListener(this);
        }

        public ImageView getImAvatar() {
            return imAvatar;
        }

        public void setImAvatar(ImageView imAvatar) {
            this.imAvatar = imAvatar;
        }

        public TextView getTvName() {
            return tvName;
        }

        public void setTvName(TextView tvName) {
            this.tvName = tvName;
        }

        public TextView getTvLocation() {
            return tvLocation;
        }

        public void setTvLocation(TextView tvLocation) {
            this.tvLocation = tvLocation;
        }

        public TextView getTvIntroduction() {
            return tvIntroduction;
        }

        public void setTvIntroduction(TextView tvIntroduction) {
            this.tvIntroduction = tvIntroduction;
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position!= RecyclerView.NO_POSITION) {
                Intent intent = new Intent(view.getContext(), VolunteerDetailActivity.class);
                intent.putExtra(Constants.VOLUNTEER_NAME, items.get(position).getName());
                intent.putExtra(Constants.VOLUNTEER_AVATAR_URL, items.get(position).getAvatarUrl());
                intent.putExtra(Constants.VOLUNTEER_TITLE, items.get(position).getTitle());
                intent.putExtra(Constants.VOLUNTEER_INTRODUCTION, items.get(position).getIntroduction());
                intent.putExtra(Constants.VOLUNTEER_LOCATION, items.get(position).getLocation());
                view.getContext().startActivity(intent);
            }

        }
    }
}
