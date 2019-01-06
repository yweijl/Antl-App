package com.avansprojects.antl.ui.createEvent;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.avansprojects.antl.R;

public class CreateEventFragment extends Fragment {

    private CreateEventViewModel mViewModel;

    private static final int NUM_PAGES = 4;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public static CreateEventFragment newInstance() {
        return new CreateEventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Instantiate a ViewPager and a PagerAdapter.
        return inflater.inflate(R.layout.create_event_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){

        Button _nextButton = getActivity().findViewById(R.id.createEventNextButton);
        _nextButton.setOnClickListener(v -> {
            mPager.setCurrentItem(getItem(+1), true);
        });

        ImageButton _backButton = getActivity().findViewById(R.id.createEventBackButton);
        _backButton.setOnClickListener(v -> {
            mPager.setCurrentItem(getItem(-1), true);
        });

        mPager = getView().findViewById(R.id.CreateEventPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(CreateEventViewModel.class);
        // TODO: Use the ViewModel
    }

    private int getItem(int i) {
        return mPager.getCurrentItem() + i;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CreateEventNameFragment();
                case 1:
                    return new CreateEventDescriptionFragment();
                case 2:
                    return new CreateEventDateFragment();
                case 3:
                    return new CreateEventImageFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}