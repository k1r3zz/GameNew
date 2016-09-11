package com.example.administrator.gamenew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import until.Config;
import until.JTdaily;
import until.JWAdapter;
import until.ZMList;


public class JThreeNews extends Fragment {

    private UltimateRecyclerView mRecyclerView;
    private ArrayList<ZMList> zmarraylist = new ArrayList<ZMList>();//新闻列表
    private ArrayList<JTdaily> jtdailylist = new ArrayList<JTdaily>();//dailtl列表
    private JWAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String Url;
    private String msgc;
    private View headview;
    private int page = 1;
    private String updatatime;

    //head
    private TextView new_data;
    private ArrayList<TextView> headtext = new ArrayList<TextView>();
    private TextView headtext1, headtext2, headtext3, headtext4, headtext5, headtext6;
    private RelativeLayout headrl1, headrl2, headrl3, headrl4, headrl5, headrl6;

    public JThreeNews() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Url = "http://www.jx3tong.com/?m=api&c=info&a=content_list&category_id=0&num=10&p=" + page;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jthree_news, container, false);
        headview = inflater.inflate(R.layout.jtnew_title, container, false);
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

        mRecyclerView.setNormalHeader(headview);


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
                        Url = "http://www.jx3tong.com/?m=api&c=info&a=content_list&category_id=0&num=10&p=1";
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
                        Url = "http://www.jx3tong.com/?m=api&c=info&a=content_list&category_id=0&num=10&p=" + page;
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
                intent.putExtra("url", zmarraylist.get(position-1).getArturl());
                intent.putExtra("what", "3");
                intent.setClass(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        new_data = (TextView) headview.findViewById(R.id.new_data);
        headtext1 = (TextView) headview.findViewById(R.id.acttext1);
        headtext2 = (TextView) headview.findViewById(R.id.acttext2);
        headtext3 = (TextView) headview.findViewById(R.id.acttext3);
        headtext4 = (TextView) headview.findViewById(R.id.acttext4);
        headtext5 = (TextView) headview.findViewById(R.id.acttext5);
        headtext6 = (TextView) headview.findViewById(R.id.acttext6);

        headrl1=(RelativeLayout)headview.findViewById(R.id.head_rl1);
        headrl2=(RelativeLayout)headview.findViewById(R.id.head_rl2);
        headrl3=(RelativeLayout)headview.findViewById(R.id.head_rl3);
        headrl4=(RelativeLayout)headview.findViewById(R.id.head_rl4);
        headrl5=(RelativeLayout)headview.findViewById(R.id.head_rl5);
        headrl6=(RelativeLayout)headview.findViewById(R.id.head_rl6);
        onHeadClick();

    }

    public  void onHeadClick(){
        headrl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",jtdailylist.get(0).getId());
                intent.setClass(getActivity(), JTAcativeActivity.class);
                startActivity(intent);
            }
        });
        headrl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",jtdailylist.get(1).getId());
                intent.setClass(getActivity(), JTAcativeActivity.class);
                startActivity(intent);
            }
        });
        headrl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",jtdailylist.get(2).getId());
                intent.setClass(getActivity(), JTAcativeActivity.class);
                startActivity(intent);
            }
        });
        headrl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",jtdailylist.get(3).getId());
                intent.setClass(getActivity(), JTAcativeActivity.class);
                startActivity(intent);
            }
        });
        headrl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",jtdailylist.get(4).getId());
                intent.setClass(getActivity(), JTAcativeActivity.class);
                startActivity(intent);
            }
        });
        headrl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",jtdailylist.get(5).getId());
                intent.setClass(getActivity(), JTAcativeActivity.class);
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


                    JSONObject news = jb.getJSONObject("daily_info");
                    if (!"".equals(news.getString("daily_update_time"))) {
                        Config.Jtupdatatime = news.getString("daily_update_time");
                    }

                    JSONArray dailyjarr = news.getJSONArray("daily_list");

                    for (int i = 0; i < dailyjarr.length(); i++) {
                        msgc = dailyjarr.getString(i);
                        JSONObject daily = new JSONObject(msgc);
                        JTdaily jTdaily = new JTdaily();
                        jTdaily.setId(daily.getString("id"));
                        jTdaily.setTitle(daily.getString("title"));
                        jtdailylist.add(jTdaily);
                    }

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

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new_data.setText(Config.Jtupdatatime);
                            headtext1.setText(jtdailylist.get(0).getTitle());
                            headtext2.setText(jtdailylist.get(1).getTitle());
                            headtext3.setText(jtdailylist.get(2).getTitle());
                            headtext4.setText(jtdailylist.get(3).getTitle());
                            headtext5.setText(jtdailylist.get(4).getTitle());
                            headtext6.setText(jtdailylist.get(5).getTitle());
                        }
                    });


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
