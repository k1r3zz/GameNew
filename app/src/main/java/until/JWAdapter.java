package until;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.gamenew.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/8.
 */
public class JWAdapter extends UltimateViewAdapter<JWAdapter.MyViewHoler> {

    private ArrayList<ZMList> datas;
    private LayoutInflater mflater;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private OnItemClickLitener mOnItemClickLitener;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public JWAdapter(Context context, ArrayList<ZMList> datas) {
        this.datas = datas;
        mflater = LayoutInflater.from(context);

        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.shu1) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.shu1) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.shu1) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(0)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象
    }



    @Override
    public MyViewHoler getViewHolder(View view) {
        return new MyViewHoler(view, false);
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent) {
        View view = mflater.inflate(R.layout.adapter_item, parent, false);
        return new MyViewHoler(view, true);
    }

    @Override
    public int getAdapterItemCount() {
        return datas.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return -1;
    }

    @Override
    public void onBindViewHolder(final MyViewHoler holder, int position) {
        if(position < getItemCount() && (customHeaderView != null ? position <= datas.size() : position < datas.size()) && (customHeaderView != null ? position > 0 : true)){
            holder.Title.setText(datas.get(customHeaderView != null ? position - 1 : position).getName());
            holder.Text.setText(datas.get(customHeaderView != null ? position - 1 : position).getSummary());
            holder.Date.setText(datas.get(customHeaderView != null ? position - 1 : position).getData());
            imageLoader.displayImage(datas.get(customHeaderView != null ? position - 1 : position).getImageUel(), holder.im,
                    options);
            if (mOnItemClickLitener != null) {
                holder.item_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });
            }
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class MyViewHoler extends UltimateRecyclerviewViewHolder {
        public TextView Title, Text, Date;
        public ImageView im;
        public LinearLayout item_ll;

        //        ProgressBar progressBarSample;
        public MyViewHoler(View itemView, boolean isItem) {

            super(itemView);

           // if (isItem) {
                Text = (TextView) itemView.findViewById(R.id.list_text);
                Title = (TextView) itemView.findViewById(R.id.list_title);
                im = (ImageView) itemView.findViewById(R.id.list_im);
                Date = (TextView) itemView.findViewById(R.id.list_data);
                item_ll = (LinearLayout) itemView.findViewById(R.id.item_ll);

           // }
        }
    }
}
