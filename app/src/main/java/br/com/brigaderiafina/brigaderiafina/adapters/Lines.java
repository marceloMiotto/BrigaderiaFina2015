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
        lines.add(new Lines("Linha Gourmet",R.drawable.sample_2));
        lines.add(new Lines("Linha Chocolat",R.drawable.sample_3));
        lines.add(new Lines("Linha Cake",R.drawable.sample_4));
        lines.add(new Lines("Linha Gourmet",R.drawable.sample_5));
        lines.add(new Lines("Eventos",R.drawable.sample_7));

        return lines;

    }

}
