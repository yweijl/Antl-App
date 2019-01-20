package com.avansprojects.antl.ui.createEvent;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.avansprojects.antl.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;
import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class CreateEventFragment extends Fragment {

    private CreateEventViewModel mViewModel;
    private static final int NUM_PAGES = 4;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private Button mNextButton;
    private Button mSaveButton;
    private ImageButton mBackButton;
    private int mPictureLocation;

    public static CreateEventFragment newInstance() {
        return new CreateEventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_event_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        BottomNavigationView menu = getActivity().findViewById(R.id.bottom_nav);
        menu.setVisibility(View.GONE);
        setEventButtons();
        setEventAdapter();
    }

    private void setEventAdapter() {
        mPager = getView().findViewById(R.id.CreateEventPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private void setEventButtons() {
        mSaveButton = getActivity().findViewById(R.id.saveEventButton);
        mSaveButton.setOnClickListener(v -> {
            try {
                saveEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Navigation.findNavController(v).navigate(R.id.action_Destination_create_event_pop);
        });

        mNextButton = getActivity().findViewById(R.id.createEventNextButton);
        mNextButton.setOnClickListener(v -> {
            bindTextViews(mPager.getCurrentItem());
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);

            if (imm.isActive()){
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getRootView().getWindowToken(), 0);
            }
            int newPosition = setNewViewPagerPosition(+1);
            setButtonVisibility(newPosition);
            mPager.setCurrentItem(newPosition, true);
        });

        mBackButton = getActivity().findViewById(R.id.createEventBackButton);
        mBackButton.setOnClickListener(v -> {
            bindTextViews(mPager.getCurrentItem());
            int newPosition = setNewViewPagerPosition(-1);
            setButtonVisibility(newPosition);
            mPager.setCurrentItem(newPosition, true);
        });
    }

    private void bindTextViews(int position) {
        switch (position){
            case 0:
                mNameTextView = mPager.findViewById(R.id.enterEventName);
            break;
            case 1:
                mDescriptionTextView = mPager.findViewById(R.id.enterEventDescription);
            break;
            case 2:
                break;
            case 3:
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(CreateEventViewModel.class);
        mViewModel.syncData();
        setHasOptionsMenu(true);
    }

    private void saveEvent() {
        mViewModel.saveEvent(
                mNameTextView.getText().toString(),
                mDescriptionTextView.getText().toString(),
                "locatie");
    }

    private void setButtonVisibility(int position) {
        if (position == 0){
            mBackButton.setVisibility(View.INVISIBLE);
        }

        if (position == 1){
            mBackButton.setVisibility(View.VISIBLE);
        }

        if (position == 3) {
            mNextButton.setVisibility(View.GONE);
            mSaveButton.setVisibility(View.VISIBLE);
        } else {
            if (mNextButton.getVisibility() == View.GONE) {
                mNextButton.setVisibility(View.VISIBLE);
            }
            if (mSaveButton.getVisibility() == View.VISIBLE) {
                mSaveButton.setVisibility(View.GONE);
            }
        }
    }

    private int setNewViewPagerPosition(int position) {
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