package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;

/**
 * Created by Marcelo_Miotto on 12/16/2015.
 */
public class ImageLinesAdapter extends ArrayAdapter<Lines> {

    private Context mContext;

    public ImageLinesAdapter(Context c, ArrayList<Lines> lines) {
        super(c,0,lines);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

       Lines line = getItem(position);
       if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_main, parent,false);
       }

        TextView lineNameTextView  = (TextView) convertView.findViewById(R.id.lineNameTextView);
        ImageView photoIdImageView = (ImageView)convertView.findViewById(R.id.photoIdImageView);

        lineNameTextView.setText(line.lineName);
        photoIdImageView.setImageResource(line.linePhoto);

        return convertView;
    }



}
