
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

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";
    public static final String ARG_URL  = "url";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
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

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
