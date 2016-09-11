package com.example.administrator.gamenew;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import until.JWAdapter;
import until.ZMList;


public class JTmeituFragment extends Fragment {
    private UltimateRecyclerView mRecyclerView;
    private ArrayList<ZMList> zmarraylist = new ArrayList<ZMList>();//新闻列表
    private JWAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String Url;
    private String msgc;
    private int page = 1;

    public JTmeituFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Url = "http://www.jx3tong.com/?m=api&c=info&a=content_list&category_id=7&num=10&p=" + page;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_jtmeitu, container, false);
        initData();
        ininView(view);
        return view;
    }

    private void ininView(View view) {
        mRecyclerView = (UltimateRecyclerView) view.findViewById(R.id.Jt_recyclerview);
        mAdapter = new JWAdapter(getActivity(), zmarraylist);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setAdapter(mAdapter);


        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //下拉刷新
        mRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Url = "http://www.jx3tong.com/?m=api&c=info&a=content_list&category_id=7&num=10&p=1";
                        initData();
                        mAdapter.notifyDataSetChanged();

                    }
                }, 1000);
            }
        });

        mAdapter.swipeCustomLoadMoreView(LayoutInflater.from(getActivity())
                .inflate(R.layout.custom_bottom_progressbar, null));
        mRecyclerView.enableLoadmore();

        //加载更多
        mRecyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        //加载条
                        page = page + 1;
                        Url = "http://www.jx3tong.com/?m=api&c=info&a=content_list&category_id=7&num=10&p=" + page;
                        initData();
                        mAdapter.notifyDataSetChanged();

                    }
                }, 1000);
            }
        });


        mAdapter.setmOnItemClickLitener(new JWAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("type", false);
                intent.putExtra("url", zmarraylist.get(position).getArturl());
                intent.putExtra("what", "3");
                intent.setClass(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        RequestQueue mQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Log.i("opp", response);
                    JSONObject jb = new JSONObject(response);


                    JSONArray jarr = jb.getJSONArray("info_content_list");
                    for (int i = 0; i < jarr.length(); i++) {
                        ZMList zmlist = new ZMList();

                        msgc = jarr.getString(i);
                        JSONObject jb2 = new JSONObject(msgc);
                        zmlist.setName(jb2.getString("title"));
                        zmlist.setArturl(jb2.getString("url"));
                        String temp = jb2.getString("browser");
                        zmlist.setData(temp);
                        zmlist.setImageUel(jb2.getString("icon_url"));
                        zmlist.setSummary(jb2.getString("intro"));
                        zmarraylist.add(zmlist);

                    }

                    mAdapter.notifyDataSetChanged();


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
