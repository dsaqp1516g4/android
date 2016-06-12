package edu.upc.eetac.dsa.music4you;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.music4you.client.entity.AnuncioCollection;

/**
 * Created by root on 12/06/16.
 */
public class AnuncioListAdapter extends BaseAdapter {
    private AnuncioCollection anuncioCollection;
    private LayoutInflater layoutInflater;


    public AnuncioListAdapter(Context context, AnuncioCollection anuncioCollection){
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
            convertView = layoutInflater.inflate(R.layout.list_row_anuncio, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String mainText = anuncioCollection.getStings().get(position).getSubject();
        int leftText = anuncioCollection.getStings().get(position).getType();
        double rightText = anuncioCollection.getStings().get(position).getPrecio();


        /* DEBUGEA ESTO
        * mainText, leftText y rightText no puede ser nulo en ningún caso
        * */

        viewHolder.textViewMainText.setText(mainText);
        viewHolder.textViewLeftText.setText(String.valueOf(leftText));
        viewHolder.textViewRightText.setText(String.valueOf(rightText));
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
