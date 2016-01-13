package br.com.brigaderiafina.brigaderiafina;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SubgroupDetailActivityPhotosFragment extends Fragment {

    private Integer images[] = {R.drawable.linha_cake, R.drawable.linha_chocolat, R.drawable.linha_dessert};

    public SubgroupDetailActivityPhotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_subgroup_detail_photos, container, false);
        LinearLayout imageGallery = (LinearLayout) view.findViewById(R.id.imageGallery);
        for (Integer image : images) {
            imageGallery.addView(getImageView(image));
        }
        //addImagesToThegallery();

        return view;

    }

    private View getImageView(Integer image) {
        ImageView imageView = new ImageView(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
       // lp.setMargins(0, 0, 10, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        return imageView;
    }
}




