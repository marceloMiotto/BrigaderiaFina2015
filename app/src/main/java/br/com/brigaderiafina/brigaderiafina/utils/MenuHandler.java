package br.com.brigaderiafina.brigaderiafina.utils;


import android.content.Context;
import android.content.Intent;

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

         if(mMenuAction == R.id.action_suggestion){
             intent.putExtra(Constants.MENU_INFO_ID, mMenuAction);
         }

        if(mMenuAction == R.id.action_order){
            intent.putExtra(Constants.MENU_INFO_ID,mMenuAction);
        }

        if(mMenuAction == R.id.action_suggestion){
            intent.putExtra(Constants.MENU_INFO_ID,mMenuAction);
        }

        mContext.startActivity(intent);

    }


}
