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
	//ָʾ��
	private void initTableLine() {
		mTabline=(ImageView) findViewById(R.id.id_iv_tabline);
		//��ȡ���Ŀ��
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics=new DisplayMetrics();
		display.getMetrics(outMetrics);
		//outMetrics����ſ�ߵ�ֵ
		mScreen1_3=outMetrics.widthPixels/3;
		//����imageView�Ŀ��
		//��ȡ����
		LayoutParams lp = mTabline.getLayoutParams();
		lp.width=mScreen1_3;
		//���ò���
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
		// TODO �Զ����ɵķ������
		
	}
	//ҳ��仯�Ĺ���
	/**
	 * ͨ���ı�leftMargin��ʹ��ʾ���䶯��
	 * ���һ�����ʱ��
	 * ������position�ӵ�ǰҳ����һҳ����ǰҳΪ0��һҳΪ1
	 * positionOffsetһ���𽥴�0�����޽ӽ���1�Ĺ���
	 * positionOffsetPxƫ�Ƶ�������
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
		//�鿴���
		Log.i("bunao", position +"  "+positionOffset+"  "+positionOffsetPx);
		//��Ҫ���LinearLayout��������LayoutParamsû��leftMargin����
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
	//ҳ��仯�Ľ�������ǵ��ڼ�ҳ
	@Override
	public void onPageSelected(int arg0) {
		// TODO �Զ����ɵķ������
		resetColor();
		//Ҳ����ʹ��Color.parseColor("#008000")
		if (arg0==1) {
			//����С��ϢСͼ��
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
