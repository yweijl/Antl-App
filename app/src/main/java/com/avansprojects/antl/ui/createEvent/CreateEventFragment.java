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
import android.widget.TextView;

import com.avansprojects.antl.R;

public class CreateEventFragment extends Fragment {

    private CreateEventViewModel mViewModel;

    private static final int NUM_PAGES = 4;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TextView _nameTextView;
    private TextView _DescriptionTextView;
    private TextView _firstDateTextView;
    private TextView _secondDateTextView;
    private TextView _thirdDateTextView;
    private TextView _fourthDateTextView;
    private TextView _firstTimeTextView;
    private TextView _secondTimeTextView;
    private TextView _thirdTimeTextView;
    private TextView _fourthTimeTextView;
    private Button _nextButton;
    private Button _saveButton;
    private ImageButton _backButton;
    private int _pictureLocation;

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
        findEventViews();
        setEventButtons();
        setEventAdapter();
    }

    private void setEventAdapter() {
        mPager = getView().findViewById(R.id.CreateEventPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private void setEventButtons() {

        _saveButton = getActivity().findViewById(R.id.createEventNextButton);
        _saveButton.setOnClickListener(v -> { });

        _nextButton = getActivity().findViewById(R.id.createEventNextButton);
        _nextButton.setOnClickListener(v -> {
            int newPosition = getItem(+1);
            setButtonVisibility(newPosition);
            mPager.setCurrentItem(newPosition, true);
        });

        _backButton = getActivity().findViewById(R.id.createEventBackButton);
        _backButton.setOnClickListener(v -> {
            mPager.setCurrentItem(getItem(-1), true);
        });
    }

    private void findEventViews() {
        _nameTextView = getView().findViewById(R.id.enterEventName);
        _DescriptionTextView = getView().findViewById(R.id.enterEventDescription);
        _firstDateTextView = getView().findViewById(R.id.firstEventDate);
        _secondDateTextView = getView().findViewById(R.id.secondEventDate);
        _thirdDateTextView = getView().findViewById(R.id.thirdEventDate);
        _fourthDateTextView = getView().findViewById(R.id.fourthEventDate);
        _firstTimeTextView = getView().findViewById(R.id.firstEventTime);
        _secondTimeTextView = getView().findViewById(R.id.secondEventTime);
        _thirdTimeTextView = getView().findViewById(R.id.thirdEventTime);
        _fourthTimeTextView = getView().findViewById(R.id.fourthEventTime);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(CreateEventViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setButtonVisibility(int position) {
        if (position == 3) {
            _nextButton.setVisibility(View.GONE);
            _saveButton.setVisibility(View.VISIBLE);
        } else {
            if (_nextButton.getVisibility() == View.GONE) {
                _nextButton.setVisibility(View.VISIBLE);
            }
            if (_saveButton.getVisibility() == View.VISIBLE) {
                _nextButton.setVisibility(View.GONE);
            }
        }
    }

    private int getItem(int position) {
        return mPager.getCurrentItem() + position;
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