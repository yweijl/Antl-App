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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.avansprojects.antl.infrastructure.entities.Event;
import java.util.Date;

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
        setEventButtons();
        setEventAdapter();
//        bindTextViews(mPager.getCurrentItem());
    }

    private void setEventAdapter() {
        mPager = getView().findViewById(R.id.CreateEventPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private void setEventButtons() {
        _saveButton = getActivity().findViewById(R.id.saveEventButton);
        _saveButton.setOnClickListener(v -> {
            try {
                saveEvent();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Navigation.findNavController(v).navigate(R.id.action_Destination_create_event_pop);
        });

        _nextButton = getActivity().findViewById(R.id.createEventNextButton);
        _nextButton.setOnClickListener(v -> {
            bindTextViews(mPager.getCurrentItem());
            int newPosition = setNewViewPagerPosition(+1);
            setButtonVisibility(newPosition);
            mPager.setCurrentItem(newPosition, true);
        });

        _backButton = getActivity().findViewById(R.id.createEventBackButton);
        _backButton.setOnClickListener(v -> {
            bindTextViews(mPager.getCurrentItem());
            int newPosition = setNewViewPagerPosition(-1);
            setButtonVisibility(newPosition);
            mPager.setCurrentItem(newPosition, true);
        });
    }

    private void bindTextViews(int position) {
        switch (position){
            case 0:
                _nameTextView = mPager.findViewById(R.id.enterEventName);
            break;
            case 1:
            _DescriptionTextView = mPager.findViewById(R.id.enterEventDescription);
            break;
            case 2:
                _firstDateTextView = mPager.findViewById(R.id.firstEventDate);
                _secondDateTextView = mPager.findViewById(R.id.secondEventDate);
                _thirdDateTextView = mPager.findViewById(R.id.thirdEventDate);
                _fourthDateTextView = mPager.findViewById(R.id.fourthEventDate);
                _firstTimeTextView = mPager.findViewById(R.id.firstEventTime);
                _secondTimeTextView = mPager.findViewById(R.id.secondEventTime);
                _thirdTimeTextView = mPager.findViewById(R.id.thirdEventTime);
                _fourthTimeTextView = mPager.findViewById(R.id.fourthEventTime);
            break;
            case 3:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(CreateEventViewModel.class);
        // TODO: Use the ViewModel
    }

    private Date getEventDate() throws Exception{
        return CalendarHelper.joinDateTime(
                _firstDateTextView.getText().toString(),
                _firstTimeTextView.getText().toString());
    }

    private void saveEvent() throws Exception{
        Event event = getEventFromInput();
        mViewModel.insert(event);
    }

    private Event getEventFromInput() throws Exception {
        String eventName = _nameTextView.getText().toString();
        return new Event(
                eventName,
                getEventDate(),
                "Tijdelijke locatie",
                _pictureLocation);
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
                _saveButton.setVisibility(View.GONE);
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