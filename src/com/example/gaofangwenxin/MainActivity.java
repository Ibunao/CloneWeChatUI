package com.example.gaofangwenxin;

import java.util.ArrayList;
import java.util.List;

import com.jauker.widget.BadgeView;

import android.R.array;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnPageChangeListener {
	private ViewPager pager;
	private MyFragmentPageAdapter adapter;
	private List<Fragment> list;
	private TextView mChat,mFind,mContacts;
	private List<TextView>tvList;
	private LinearLayout mChatLinear;
	private BadgeView badgeView;
	private ImageView mTabline;	
	private int mScreen1_3;
	
	private int mCurrentPageIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initTableLine();
		
		initView();
	}
	//指示条
	private void initTableLine() {
		mTabline=(ImageView) findViewById(R.id.id_iv_tabline);
		//获取屏的宽高
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics=new DisplayMetrics();
		display.getMetrics(outMetrics);
		//outMetrics存放着宽高的值
		mScreen1_3=outMetrics.widthPixels/3;
		//设置imageView的宽度
		//获取参数
		LayoutParams lp = mTabline.getLayoutParams();
		lp.width=mScreen1_3;
		//设置参数
		mTabline.setLayoutParams(lp);
	}
	public void initView(){
		tvList=new ArrayList<TextView>();
		mChat=(TextView) findViewById(R.id.id_tv_chat);
		mFind=(TextView) findViewById(R.id.id_tv_find);
		mContacts=(TextView) findViewById(R.id.id_tv_contact);
		tvList=new ArrayList<TextView>();
		tvList.add(mChat);
		tvList.add(mFind);
		tvList.add(mContacts);
		
		pager=(ViewPager) findViewById(R.id.id_viewpager);
		pager.setOnPageChangeListener(this);
		list=new ArrayList<Fragment>();
		list.add(new PagerFragment1());
		list.add(new PagerFragment2());
		list.add(new PagerFragment3());
		adapter=new MyFragmentPageAdapter(getSupportFragmentManager(),list);
		pager.setAdapter(adapter);
		mChatLinear=(LinearLayout) findViewById(R.id.id_ll_chat);
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO 自动生成的方法存根
		
	}
	//页面变化的过程
	/**
	 * 通过改变leftMargin来使显示条变动的
	 * 向右滑动的时候
	 * 参数：position从当前页到下一页，当前页为0下一页为1
	 * positionOffset一个逐渐从0到无限接近于1的过程
	 * positionOffsetPx偏移的像素数
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
		//查看输出
		Log.i("bunao", position +"  "+positionOffset+"  "+positionOffsetPx);
		//需要添加LinearLayout，单独的LayoutParams没有leftMargin属性
		LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline.getLayoutParams();
		
		if (mCurrentPageIndex==0&&position==0) {//0 --> 1
			lp.leftMargin=(int) (positionOffset*mScreen1_3+mCurrentPageIndex*mScreen1_3);
		}else if (mCurrentPageIndex==1&&position==0) {//1 --> 0
			lp.leftMargin=(int) ((positionOffset-1)*mScreen1_3+mCurrentPageIndex*mScreen1_3);
		}else if (mCurrentPageIndex==1&&position==1) {//1 --> 2
			lp.leftMargin=(int) (positionOffset*mScreen1_3+mCurrentPageIndex*mScreen1_3);
		}else if (mCurrentPageIndex==2&&position==1) {//2 --> 1
			lp.leftMargin=(int) ((positionOffset-1)*mScreen1_3+mCurrentPageIndex*mScreen1_3);
		}
		mTabline.setLayoutParams(lp);
	}
	//页面变化的结果，就是到第几页
	@Override
	public void onPageSelected(int arg0) {
		// TODO 自动生成的方法存根
		resetColor();
		//也可以使用Color.parseColor("#008000")
		if (arg0==1) {
			//创建小消息小图标
			badgeView=new BadgeView(this);
			badgeView.setBadgeCount(2);
			badgeView.setTargetView(mChat);
			badgeView.setBadgeGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
			badgeView.setBadgeMargin(30, 0,0 , 0);

			
		}
		tvList.get(arg0).setTextColor(getResources().getColor(R.color.green));
		mCurrentPageIndex=arg0;
	}
	private void resetColor() {
		for (int i = 0; i < tvList.size(); i++) {
			tvList.get(i).setTextColor(getResources().getColor(R.color.block));
			
		}
		
	}
}
