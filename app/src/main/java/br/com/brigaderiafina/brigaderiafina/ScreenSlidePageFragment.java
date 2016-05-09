
package br.com.brigaderiafina.brigaderiafina;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ScreenSlidePageFragment extends Fragment {

    ImageView mImageView;
    static String    mUrl;

    public static final String ARG_URL  = "url";

    public static ScreenSlidePageFragment create(String url) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL,url);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment(){}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        View rootView = inflater.inflate(R.layout.fragment_subgroup_slide_page, container, false);

          mUrl        = getArguments().getString(ARG_URL);

        // Set the title view to show the page number.
        mImageView = (ImageView) rootView.findViewById(R.id.subgroupPhotosImageView);
        Picasso.with(getActivity().getApplicationContext()).load(mUrl).into(mImageView);
        return rootView;
    }

}
