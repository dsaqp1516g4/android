package edu.upc.eetac.dsa.music4you;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.music4you.R;
import edu.upc.eetac.dsa.music4you.client.entity.AnuncioCollection;

/**
 * Created by hicham.az on 10/06/2016.
 */
public class AnuncioCollectionAdapter extends BaseAdapter {
    private AnuncioCollection anuncioCollection;
    private LayoutInflater layoutInflater;


    public AnuncioCollectionAdapter(Context context, AnuncioCollection anuncioCollection){
        layoutInflater = LayoutInflater.from(context);
        this.anuncioCollection = anuncioCollection;
    }

    @Override
    public int getCount() {
        return anuncioCollection.getAnuncios().size();
    }

    @Override
    public Object getItem(int position) {
        return anuncioCollection.getAnuncios().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_ads_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String mainText = anuncioCollection.getAnuncios().get(position).getSubject();
        String leftText = anuncioCollection.getAnuncios().get(position).getSubject();
        String rightText = anuncioCollection.getAnuncios().get(position).getSubject();

        viewHolder.textViewMainText.setText(mainText);
        viewHolder.textViewLeftText.setText(leftText);
        viewHolder.textViewRightText.setText(rightText);
        return convertView;
    }

    class ViewHolder{
        TextView textViewMainText;
        TextView textViewLeftText;
        TextView textViewRightText;

        ViewHolder(View row){
            this.textViewMainText = (TextView) row
                    .findViewById(R.id.textViewMainText);
            this.textViewLeftText = (TextView) row
                    .findViewById(R.id.textViewLeftText);
            this.textViewRightText = (TextView) row
                    .findViewById(R.id.textViewRightText);
        }
    }
}