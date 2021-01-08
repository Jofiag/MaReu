package com.example.mareu.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import com.example.mareu.adapter.MeetingListRecyclerViewAdapter;

import static org.hamcrest.CoreMatchers.is;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

public class AssertRecyclerItemCount implements ViewAssertion{
    private final int expectedCount;

    public AssertRecyclerItemCount(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null)
            throw noViewFoundException;

        RecyclerView recyclerView = (RecyclerView) view;
        MeetingListRecyclerViewAdapter adapter = (MeetingListRecyclerViewAdapter) recyclerView.getAdapter();
        assert adapter != null;
        assertThat(adapter.getItemCount(), is(expectedCount));
    }
}
