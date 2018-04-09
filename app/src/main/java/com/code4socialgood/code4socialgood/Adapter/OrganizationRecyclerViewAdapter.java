package com.code4socialgood.code4socialgood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.R;
import com.code4socialgood.code4socialgood.models.Project;

import java.util.List;

/**
 * Created by Sayali on 4/7/2018.
 */

public class OrganizationRecyclerViewAdapter extends RecyclerView.Adapter<OrganizationRecyclerViewAdapter.OrganizationViewHolder> {


    List<Project> projects;

    public OrganizationRecyclerViewAdapter(List<Project> projects){

        this.projects = projects;

    }

    @Override
    public OrganizationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        int layoutIdForListItem = R.layout.item_organization;
        boolean shouldAttachToParentImmediately = false;

        View listItemView = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        OrganizationViewHolder viewHolder = new OrganizationViewHolder(listItemView);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(OrganizationViewHolder holder, int position) {
        holder.bind(projects.get(position));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class OrganizationViewHolder extends RecyclerView.ViewHolder{

        ImageView imLogo;
        TextView tvName;
        TextView tvDescription;
        TextView tvLocation;

        public OrganizationViewHolder(View itemView){
            super(itemView);

            imLogo = (ImageView) itemView.findViewById(R.id.imOrgLogo);
            tvName = (TextView) itemView.findViewById(R.id.tvOrgName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvOrgDescription);
            tvLocation = (TextView) itemView.findViewById(R.id.tvOrgLocation);

        }

        void bind(Project project){


            imLogo.setImageURI(Uri.parse(project.getImageUrl()));
            tvName.setText(project.getName());
            tvDescription.setText(project.getDescription());
            tvLocation.setText(project.getCity() + "," + project.getCountry());

        }
    }
}
