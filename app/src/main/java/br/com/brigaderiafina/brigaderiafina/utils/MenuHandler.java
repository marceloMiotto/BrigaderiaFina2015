package br.com.brigaderiafina.brigaderiafina.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import br.com.brigaderiafina.brigaderiafina.MenuInfoActivity;
import br.com.brigaderiafina.brigaderiafina.R;

public class MenuHandler {

    private Context mContext;
    private int     mMenuAction;

    public MenuHandler(Context context,int menuAction){
        mContext    = context;
        mMenuAction = menuAction;
    }

    public void getActionMenuHandler(){

        Intent intent = new Intent(mContext, MenuInfoActivity.class);
        String[] menuArray;
         if(mMenuAction == R.id.action_suggestion){
             menuArray = mContext.getResources().getStringArray(R.array.suggestion);
             intent.putExtra(Constants.MENU_INFO_ARRAY, menuArray);
             mContext.startActivity(intent);
         }

        if(mMenuAction == R.id.action_order){
            menuArray = mContext.getResources().getStringArray(R.array.order);
            intent.putExtra(Constants.MENU_INFO_ARRAY, menuArray);
            mContext.startActivity(intent);
        }

        if(mMenuAction == R.id.action_contact){
          //contact
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(mContext.getResources().getString(R.string.mailto)
                    + mContext.getResources().getString(R.string.email)));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.email_subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, R.string.email_body);
            mContext.startActivity(emailIntent.createChooser(emailIntent
                    ,mContext.getResources().getString(R.string.email_chooser_title)));

        }


    }


}
