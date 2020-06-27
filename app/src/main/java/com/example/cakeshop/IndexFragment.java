package com.example.cakeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.layout.BidirSlidingLayout;

/**
 * created by guigui
 * on 2020/6/24
 */
/**
 *  主页fragment
 */
public class IndexFragment extends Fragment {

    private BidirSlidingLayout bidirSldingLayout;

    /**
     * 在内容布局上显示的RecyclerView
     */
    private RecyclerView rlv;





    /**
     * 填充index layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.index_fragment, null);
        bidirSldingLayout=view.findViewById(R.id.bidir_sliding_layout);
        rlv=view.findViewById(R.id.rlv);
        bidirSldingLayout.setScrollEvent(rlv);
        return view;
    }

    private void binderData() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
