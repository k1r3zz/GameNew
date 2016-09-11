package com.example.administrator.gamenew;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import until.Find_tab_Adapter;


public class HZFragment extends Fragment {

    private TabLayout tab_FindFragment_title; // 定义TabLayout
    private ViewPager vp_FindFragment_pager; // 定义viewPager
    private FragmentPagerAdapter fAdapter; // 定义adapter
    private List<android.support.v4.app.Fragment> list_fragment; // 定义要装fragment的列表
    private List<String> list_title; // tab名称列表
    private JThreeNews JTnews;
    private JThreeCos JTcos;
    private JTGonggaoFragment JTgg;
    private JTmanhuaFragment JTmh;
    private JTyuleFragment JTyl;
    private JTmeituFragment JTmt;



    public HZFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInsta24nceState) {
        View view = inflater.inflate(R.layout.fragment_hz, container, false);
        Log.i("OPP","onCreateView");

        init(view);
        return view;
    }

    private void init(View view) {
        tab_FindFragment_title = (TabLayout) view.findViewById(R.id.tablayout);
        vp_FindFragment_pager = (ViewPager) view.findViewById(R.id.vp);

        JTcos=new JThreeCos();
        JTnews=new JThreeNews();
        JTyl=new JTyuleFragment();
        JTgg=new JTGonggaoFragment();
        JTmh=new JTmanhuaFragment();
        JTmt=new JTmeituFragment();

        list_fragment = new ArrayList<>();
        list_fragment.add(JTnews);
        list_fragment.add(JTyl);
        list_fragment.add(JTmt);
        list_fragment.add(JTcos);
        list_fragment.add(JTmh);
        list_fragment.add(JTgg);

        list_title = new ArrayList<>();
        list_title.add("新闻");
        list_title.add("娱乐");
        list_title.add("美图");
        list_title.add("Cos");
        list_title.add("漫画");
        list_title.add("公告");

        // 设置TabLayout的模式
        tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(4)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(5)));

        fAdapter = new Find_tab_Adapter(getActivity(),HZFragment.this.getChildFragmentManager(), list_fragment, list_title);
        vp_FindFragment_pager.setOffscreenPageLimit(6);
        vp_FindFragment_pager.setAdapter(fAdapter);

        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i("OPP", "onDestroyView1111");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("OPP", "onDestroyView2222");
    }
}
