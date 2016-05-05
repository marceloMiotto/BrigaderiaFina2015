package br.com.brigaderiafina.brigaderiafina;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.brigaderiafina.brigaderiafina.adapters.SubgroupPhotos;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;


public class TabPageActivityPhotosFragment extends Fragment {


    String mSubgroupChoosen;
    String mLineChoosen;

    public TabPageActivityPhotosFragment() {
        // Required empty public constructor
    }

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subgroup_screen_slide, container, false);
       // setContentView(R.layout.subgroup_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) view.findViewById(R.id.pager);
        Intent intent = getActivity().getIntent();

        SubgroupPhotos subPhotos = new SubgroupPhotos(getActivity());
        mSubgroupChoosen = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
        mLineChoosen     = intent.getStringExtra(Constants.LINE_NAME);

        List<SubgroupPhotos> s = subPhotos.getSubgroupPhotos(mLineChoosen,mSubgroupChoosen);
        //test

        for(SubgroupPhotos f : s){
            Log.i(Constants.LOG_TAG, "SubgroupPhotos debug " + f.subgroupPhotos);
        }

        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        return view;
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create("http://www.brigaderiafina.com.br/mobile/fotos/categorias/chocolat/trufas/trufa.png");
        }

        @Override
        public int getCount() {
            return 1;//NUM_PAGES;
        }
    }
}