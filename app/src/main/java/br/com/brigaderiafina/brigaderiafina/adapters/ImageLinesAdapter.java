package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.brigaderiafina.brigaderiafina.R;

public class ImageLinesAdapter extends ArrayAdapter<Lines> {

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
        photoIdImageView.setImageResource(line.linePhotoId);

        return convertView;
    }

}
