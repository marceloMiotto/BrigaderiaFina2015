package br.com.brigaderiafina.brigaderiafina;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.brigaderiafina.brigaderiafina.adapters.EventPhotos;
import br.com.brigaderiafina.brigaderiafina.adapters.SubgroupPhotos;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;


public class TabPageActivityPhotosFragment extends Fragment {


    String mSubgroupChoosen;
    String mLineChoosen;

    public TabPageActivityPhotosFragment() {
        // Required empty public constructor
    }

    int mNumPhotos;

    private ViewPager mPager;
    List<SubgroupPhotos> mS;
    List<EventPhotos> mE;

    private PagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subgroup_screen_slide, container, false);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        Intent intent = getActivity().getIntent();


        mSubgroupChoosen = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
        mLineChoosen     = intent.getStringExtra(Constants.LINE_NAME);

        if(mSubgroupChoosen.contains("Evento")){
            EventPhotos evePhotos = new EventPhotos(getActivity());
            mE = evePhotos.getEventPhotos(mLineChoosen); //using the same logic the store the event code
            for(EventPhotos f : mE){
                mNumPhotos = Integer.parseInt(f.eventNumPhotos);
            }
        }else{
            SubgroupPhotos subPhotos = new SubgroupPhotos(getActivity());
            mS = subPhotos.getSubgroupPhotos(mLineChoosen,mSubgroupChoosen);
            for(SubgroupPhotos f : mS){
                mNumPhotos = Integer.parseInt(f.subgroupNumPhoto);
            }
        }

        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        return view;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        String mUrl = "http://www.brigaderiafina.com.br";

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(mSubgroupChoosen.contains("Evento")){
                EventPhotos test = mE.get(position);
                return ScreenSlidePageFragment.create(mUrl + test.eventPhotoPath + test.eventPhotosName);
            }else{
                SubgroupPhotos test = mS.get(position);
                return ScreenSlidePageFragment.create(mUrl + test.subgroupPhotoPath + test.subgroupPhotos);
            }

        }

        @Override
        public int getCount() {
            return mNumPhotos;
        }
    }
}