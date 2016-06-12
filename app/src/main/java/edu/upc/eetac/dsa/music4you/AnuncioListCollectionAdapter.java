/*package edu.upc.eetac.dsa.music4you;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.music4you.client.entity.AnuncioCollection;

/**
 * Created by hicham.az on 12/06/2016.
 */
/*
public class AnuncioListCollectionAdapter extends BaseAdapter {
    private AnuncioCollection anuncioCollection;
    private LayoutInflater layoutInflater;;

    public AnuncioListCollectionAdapter(Context context, AnuncioCollection anuncioCollection){
        layoutInflater = LayoutInflater.from(context);
        this.anuncioCollection = anuncioCollection;
    }

    @Override
    public int getCount() {
        return anuncioCollection.getStings().size();
    }

    @Override
    public Object getItem(int position) {
        return anuncioCollection.getStings().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = anuncioCollection.getStings().get(position).getSubject();
        String Description = anuncioCollection.getStings().get(position).getDescription();
        String Address = anuncioCollection.getStings().get(position).getSubject();


        viewHolder.textViewName.setText(name);
        viewHolder.textViewDescrition.setText(Description);
        viewHolder.textViewAddress.setText(Address);
        return convertView;
    }

    class ViewHolder{
        TextView textViewName;
        TextView textViewDescrition;
        TextView textViewAddress;

        ViewHolder(View row){
            this.textViewName = (TextView) row
                    .findViewById(R.id.textName);
            this.textViewDescrition = (TextView) row
                    .findViewById(R.id.textDescription);
            this.textViewAddress = (TextView) row
                    .findViewById(R.id.textAddress);
        }
    }

}
*/