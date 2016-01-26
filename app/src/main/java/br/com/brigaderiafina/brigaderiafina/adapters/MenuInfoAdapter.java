package br.com.brigaderiafina.brigaderiafina.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;

public class MenuInfoAdapter extends ArrayAdapter<MenuInfo> {

    public MenuInfoAdapter(Context c, ArrayList<MenuInfo> menuInfo) {
        super(c,0,menuInfo);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        MenuInfo menuInfo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_menu_info, parent,false);
        }

        TextView menuInfoTextView  = (TextView) convertView.findViewById(R.id.menuInfoTextView);

        menuInfoTextView.setText(menuInfo.mMenuInfo);

        return convertView;
    }
}
