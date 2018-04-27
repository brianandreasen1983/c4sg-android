package com.code4socialgood.code4socialgood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code4socialgood.code4socialgood.Activity.OrganizationDetailActivity;
import com.code4socialgood.code4socialgood.R;
import com.code4socialgood.code4socialgood.models.Organization;
import com.code4socialgood.code4socialgood.models.Project;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sayali on 4/7/2018.
 */

public class OrganizationRecyclerViewAdapter extends RecyclerView.Adapter<OrganizationRecyclerViewAdapter.OrganizationViewHolder> {

    Context context;
    List<Organization> organizations;

    public OrganizationRecyclerViewAdapter(Context context, List<Organization> organizations){

        this.context = context;
        this.organizations = organizations;

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
        holder.bind(organizations.get(position));
    }

    @Override
    public int getItemCount() {
        return organizations.size();
    }

    public class OrganizationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            itemView.setOnClickListener(this);

        }

        void bind(Organization organization){

            String image= organization.getLogoUrl();
            if(image!=null && image.length()>0) {
                Picasso.with(context).load(image).placeholder(R.drawable.placeholder).into(imLogo);
            }
            //imLogo.setImageURI(Uri.parse(organization.getLogoUrl()));
            tvName.setText(organization.getName());
            tvDescription.setText(organization.getDescription());
            tvLocation.setText(organization.getCity() + "," + organization.getCountry());

        }


        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){

                Context context = view.getContext();
                Class activityClass = OrganizationDetailActivity.class;
                Intent startOrganizationDetailActivity = new Intent(context, activityClass);
                context.startActivity(startOrganizationDetailActivity);
            }
        }
    }
}
