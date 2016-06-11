package edu.upc.eetac.dsa.music4you;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upc.eetac.dsa.music4you.R;
import edu.upc.eetac.dsa.music4you.client.entity.Anuncio;

/**
 * Created by hicham.az on 11/06/2016.
 */
public class AnuncioAdapter extends BaseAdapter {

    private ArrayList<Anuncio> data;
    private LayoutInflater inflater;

    public AnuncioAdapter(Context context, ArrayList<Anuncio> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_ads_data, null);
            viewHolder = new ViewHolder();

            viewHolder.tvId = (TextView) convertView
                    .findViewById(R.id.textAddress);
            viewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.textName);
            viewHolder.tvDescription = (TextView) convertView
                    .findViewById(R.id.textViewDescription);
            viewHolder.tvLikes = (TextView) convertView
                    .findViewById(R.id.textViewLikes);

            viewHolder.tvPhone = (TextView) convertView
                    .findViewById(R.id.textViewPhone);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String id = data.get(position).getId();
        String name = data.get(position).getCreator();
        String description = data.get(position).getDescription();
        Integer likes = data.get(position).getType();
        String address = data.get(position).getCreator();
        String phone = data.get(position).getCreator();

        viewHolder.tvId.setText(id);
        viewHolder.tvName.setText(name);
        viewHolder.tvDescription.setText(description);
        viewHolder.tvLikes.setText(likes);
        viewHolder.tvAddress.setText(address);
        viewHolder.tvPhone.setText(phone);
        return convertView;
    }

    private static class ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvDescription;
        TextView tvOwner;
        TextView tvLikes;
        TextView tvAddress;
        TextView tvPhone;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(((Anuncio) getItem(position)).getId());
    }



}
