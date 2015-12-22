package br.com.brigaderiafina.brigaderiafina.adapters;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;

public class Lines {

    public String lineName;
    public int linePhotoId;

    public Lines(String lineName, int linePhoto){
        this.lineName  = lineName;
        this.linePhotoId = linePhoto;
    }

    public static ArrayList<Lines> getLines(){

        ArrayList<Lines> lines = new ArrayList<Lines>();
        lines.add(new Lines("Linha Gourmet",R.drawable.linha_gourmet));
        lines.add(new Lines("Linha Chocolat",R.drawable.linha_chocolat));
        lines.add(new Lines("Linha Dessert",R.drawable.linha_dessert));
        lines.add(new Lines("Linha Sugar",R.drawable.linha_sugar));
        lines.add(new Lines("Linha Cake",R.drawable.linha_cake));
        lines.add(new Lines("Eventos",R.drawable.eventos));

        return lines;

    }

}
