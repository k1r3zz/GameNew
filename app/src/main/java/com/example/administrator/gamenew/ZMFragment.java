package com.example.administrator.gamenew;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import until.AppBarStateChangeListener;
import until.BannerEventClick;
import until.BannerView;
import until.Munber;
import until.ZMAdapter;
import until.ZMList;

public class ZMFragment extends Fragment {

    public static final String action = "Navigation.action";
    private UltimateRecyclerView mRecyclerView;
    private ArrayList<ZMList> zmarraylist = new ArrayList<ZMList>();//新闻列表
    private ArrayList<ZMList> Bannerlist = new ArrayList<ZMList>();//binner列表
    private ZMAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String msgc;

    private String Url;//加载的URL
    private String BinnerUrl;

    // private RelativeLayout rl_pb;
    private ImageView topim;
    private TextView toptext;
    private CollapsingToolbarLayout coordinatorLayout;
    private Toolbar toolbar;
    private AppBarLayout app_bar;

    private ViewPager viewPager;
    private BannerView mBannerView;
    private ViewGroup group;


    public ZMFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Url = "http://qt.qq.com/static/pages/news/phone/c12_list_1.shtml";
        BinnerUrl = "http://qt.qq.com/static/pages/news/phone/c13_list_1.shtml";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initData();
        initBinnerData();
        View view = inflater.inflate(R.layout.fragment_zm, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        //rl_pb=(RelativeLayout)view.findViewById(R.id.pb);
        topim = (ImageView) view.findViewById(R.id.topim);
        toptext = (TextView) view.findViewById(R.id.toptext);
        app_bar = (AppBarLayout) view.findViewById(R.id.app_bar);
        coordinatorLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        coordinatorLayout.setTitle("英雄联盟");
        coordinatorLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));//设置收缩后Toolbar上字体的颜色
        coordinatorLayout.setExpandedTitleColor(getResources().getColor(R.color.toptitle));//设置还没收缩时状态下字体颜色


        //Toobar滑动监听
        app_bar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {

                    //展开状态
                    toolbar.setBackgroundResource(R.color.toptitle);
                    toptext.setVisibility(View.GONE);

                } else if (state == State.COLLAPSED) {

                    //折叠状态

                    toolbar.setBackgroundResource(R.drawable.toolbar1);
                    toptext.setVisibility(View.VISIBLE);

                } else {

                    //中间状态
                    toolbar.setBackgroundResource(R.color.toptitle);
                    toptext.setVisibility(View.GONE);


                }
            }
        });

        //ToolBar点击
        topim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(action);
                getActivity().sendBroadcast(intent);
            }
        });

//        toolbar.setNavigationIcon(R.drawable.topim);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(action);
//                getActivity().sendBroadcast(intent);
//            }
//        });


        mRecyclerView = (UltimateRecyclerView) view.findViewById(R.id.id_recyclerview);
        mAdapter = new ZMAdapter(getActivity(), zmarraylist);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //  mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //下拉刷新
        mRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Url = "http://qt.qq.com/static/pages/news/phone/c12_list_1.shtml";
                        initData();
                        mAdapter.notifyDataSetChanged();

                    }
                }, 1000);
            }
        });

        mAdapter.swipeCustomLoadMoreView(LayoutInflater.from(getActivity())
                .inflate(R.layout.custom_bottom_progressbar, null));
        mRecyclerView.enableLoadmore();
        mAdapter.setCustomLoadMoreView(LayoutInflater.from(getActivity())
                .inflate(R.layout.custom_bottom_progressbar, null));

        //加载更多
        mRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //加载条
                        //rl_pb.setVisibility(View.VISIBLE);
                        Url = "http://qt.qq.com/static/pages/news/phone/" + Munber.NextPage;
                        initData();
                        //  mAdapter.notifyDataSetChanged();

                    }
                }, 1000);
            }
        });


        mAdapter.setmOnItemClickLitener(new ZMAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("type", zmarraylist.get(position).getIsViedo());
                intent.putExtra("url", zmarraylist.get(position).getArturl());
                intent.putExtra("what", "0");
                intent.setClass(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });


        // Banners
        viewPager = (ViewPager) view.findViewById(R.id.vp_app);
        group = (ViewGroup) view.findViewById(R.id.ll_point_app);
        mBannerView = new BannerView(getActivity(), viewPager, group, null, new BannerEventClick() {
            @Override
            public void eventClick(Boolean type, String url) {
                Intent intent = new Intent();
                intent.putExtra("type", type);
                intent.putExtra("url", url);
                intent.putExtra("what", "0");
                intent.setClass(getActivity(), WebViewActivity.class);

                startActivity(intent);
            }
        });

    }

    //获取Binner数据
    protected void initBinnerData() {
        RequestQueue mQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(BinnerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jb = new JSONObject(response);

                    JSONArray jarr = jb.getJSONArray("list");
                    for (int i = 0; i < jarr.length(); i++) {
                        ZMList zmlist = new ZMList();
                        msgc = jarr.getString(i);
                        JSONObject jb2 = new JSONObject(msgc);
                        zmlist.setName(jb2.getString("title"));
                        zmlist.setArturl(jb2.getString("article_url"));
                        String temp = jb2.getString("insert_date");
                        String[] sArray = temp.split(" ");
                        temp = sArray[0].substring(5);

                        zmlist.setData(temp);
                        zmlist.setImageUel(jb2.getString("image_url_big"));
                        zmlist.setIs_subject(jb2.getInt("is_subject"));
                        zmlist.setSummary(jb2.getString("summary"));
                        zmlist.setIsViedo(jb2.getBoolean("is_direct"));
                        Bannerlist.add(zmlist);

                    }


                    mBannerView.setViewPagerList(Bannerlist);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                String str = null;
                try {
                    str = new String(response.data, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return Response.success(str,
                        HttpHeaderParser.parseCacheHeaders(response));
            }

        };
        mQueue.add(stringRequest);

    }

    protected void initData() {
        RequestQueue mQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Log.i("opp", response);
                    JSONObject jb = new JSONObject(response);
                    Munber.MaxPageNum = jb.getInt("this_page_num");
                    Munber.NextPage = jb.getString("next");

                    JSONArray jarr = jb.getJSONArray("list");
                    for (int i = 0; i < jarr.length(); i++) {
                        ZMList zmlist = new ZMList();

                        msgc = jarr.getString(i);
                        JSONObject jb2 = new JSONObject(msgc);
                        zmlist.setName(jb2.getString("title"));
                        zmlist.setArturl(jb2.getString("article_url"));
                        String temp = jb2.getString("insert_date");
                        String[] sArray = temp.split(" ");
                        temp = sArray[0].substring(5);

                        zmlist.setData(temp);
                        zmlist.setImageUel(jb2.getString("image_url_big"));
                        zmlist.setIs_subject(jb2.getInt("is_subject"));
                        zmlist.setSummary(jb2.getString("summary"));
                        zmlist.setIsViedo(jb2.getBoolean("is_direct"));
                        zmarraylist.add(zmlist);

                    }

                    mAdapter.notifyDataSetChanged();
                    //rl_pb.setVisibility(View.GONE);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                // TODO Auto-generated method stub
                String str = null;
                try {
                    str = new String(response.data, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return Response.success(str,
                        HttpHeaderParser.parseCacheHeaders(response));
            }

        };
        mQueue.add(stringRequest);

    }


}
