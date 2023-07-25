package com.wanris.module.home.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.tabs.TabItem;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.bean.TabItemBean;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.TextImageSlidingTabLayout;
import com.wanris.module.home.R;
import com.wanris.module.home.contract.HomeFragmentContract;
import com.wanris.module.home.presenter.HomeFragmentPresenter;
import com.wanris.module.home.widget.FragmentStatePagerAdapterNew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Route(path = RouterPath.HomeHomeFragment)
public class HomeFragment extends BaseFragment<HomeFragmentContract.View, HomeFragmentContract.Presenter> implements HomeFragmentContract.View {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TextImageSlidingTabLayout tlTabLayout;
    private String[] titles;
    private List<TabItemBean> list = new ArrayList<>();
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        viewPager = view.findViewById(R.id.vp_view_pager);
        tlTabLayout = view.findViewById(R.id.tl_tab_layout);

        TabItemBean bean1 = new TabItemBean();
        bean1.setId("1");
        bean1.setName("精选");

        TabItemBean bean2 = new TabItemBean();
        bean2.setId("2");
        bean2.setName("水果");

        TabItemBean bean3 = new TabItemBean();
        bean3.setId("3");
        bean3.setName("服饰");

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);

        titles = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            TabItemBean bean = list.get(i);
            titles[i] = bean.getName();
            HomeCategoryFragment fragment = HomeCategoryFragment.getInstance(bean.getId());
            fragments.add(fragment);
        }
        viewPager.setOffscreenPageLimit(list.size());
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(viewPagerAdapter);
        tlTabLayout.setViewPager(viewPager);

    }

    @Override
    protected HomeFragmentContract.Presenter initPresenter() {
        return new HomeFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected boolean applyImmersionBar() {
        return false;
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapterNew {
        private List<BaseFragment> fragments;
        private String[] mTitles;

        public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
            super(fm);
            this.fragments = fragments;
            mTitles = titles;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}
