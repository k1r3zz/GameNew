package until;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Find_tab_Adapter extends FragmentPagerAdapter {  
  
    private List<Fragment> list_fragment;                         //fragment列表  
    private List<String> list_Title;                              //tab名的列表  
    private Context context;
  
  
    public Find_tab_Adapter(Context context,FragmentManager fm,List<Fragment> list_fragment,List<String> list_Title) {  
        super(fm);  
        this.context=context;
        this.list_fragment = list_fragment;  
        this.list_Title = list_Title;  
    }  
  
    @Override  
    public Fragment getItem(int position) {  
        return list_fragment.get(position);  
    }  
  
    @Override  
    public int getCount() {  
        return list_Title.size();  
    }  
  
    //此方法用来显示tab上的名字  
    @Override  
    public CharSequence getPageTitle(int position) {  
  
        return list_Title.get(position % list_Title.size());  
    } 
    

    
    
}
