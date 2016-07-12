package com.example.gaofangwenxin;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
	private List<Fragment>fragList;
	public MyFragmentPageAdapter(FragmentManager fm,List<Fragment>fragList) {
		super(fm);
		this.fragList=fragList;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO �Զ����ɵķ������
		return fragList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return fragList.size();
	}

}
