package com.code4socialgood.code4socialgood.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.Activity.ProjectDetailActivity;
import com.code4socialgood.code4socialgood.R;
import com.code4socialgood.code4socialgood.models.Project;
import com.code4socialgood.code4socialgood.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Project> items;

    public ProjectRecyclerViewAdapter(Context context, List<Project> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.item_project,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String image = null;
        ViewHolder viewHolder =(ViewHolder) holder;
        Project project = items.get(position);
        if(project!=null){
            viewHolder.getTvDescription().setText(project.getDescription());
            viewHolder.getTvName().setText(project.getName());
            if(project.getRemoteFlag().equals("Y")){
                viewHolder.getTvLocation().setText("Remote");
            }else if(project.getCity().length()>0 && project.getState().length()>0){
                viewHolder.getTvLocation().setText(project.getCity()+","+project.getState());
            }else if(project.getCountry().length()>0){
                viewHolder.getTvLocation().setText(project.getCountry());
            }
            image= project.getOrganizationLogoUrl();
            if(image!=null && image.length()>0) {
                Picasso.with(context).load(image).placeholder(R.drawable.placeholder).into(viewHolder.getImLogo());
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imLogo;
        private TextView tvName;
        private TextView tvLocation;
        private TextView tvDescription;

        public ViewHolder(View view) {
            super(view);
            imLogo =(ImageView)view.findViewById(R.id.imLogoUrl);
            tvName =(TextView)view.findViewById(R.id.tvName);
            tvLocation =(TextView)view.findViewById(R.id.tvLocation);
            tvDescription =(TextView)view.findViewById(R.id.tvDescription);
            view.setOnClickListener(this);
        }

        public ImageView getImLogo() {
            return imLogo;
        }

        public void setImLogo(ImageView imLogo) {
            this.imLogo = imLogo;
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

        public TextView getTvDescription() {
            return tvDescription;
        }

        public void setTvDescription(TextView tvDescription) {
            this.tvDescription = tvDescription;
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position!= RecyclerView.NO_POSITION) {
                Intent intent = new Intent(view.getContext(), ProjectDetailActivity.class);
                intent.putExtra(Constants.PROJECT_NAME, items.get(position).getName());
                intent.putExtra(Constants.PROJECT_ORGANIZATION_LOGO_URL, items.get(position).getOrganizationLogoUrl());
                intent.putExtra(Constants.PROJECT_CREATED_TIME, items.get(position).getCreatedTime());
                intent.putExtra(Constants.PROJECT_DESCRIPTION, items.get(position).getDescription());
                if(items.get(position).getRemoteFlag().equals("Y")){
                    intent.putExtra(Constants.PROJECT_LOCATION,"Remote");
                }else if(items.get(position).getCity().length()>0 && items.get(position).getState().length()>0){
                    intent.putExtra(Constants.PROJECT_LOCATION,items.get(position).getCity()+","+items.get(position).getState());
                }else if(items.get(position).getCountry().length()>0){
                    intent.putExtra(Constants.PROJECT_LOCATION,items.get(position).getCountry());
                }
                view.getContext().startActivity(intent);
            }

        }
    }
}
