package com.wanris.module.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.bean.ParamBean;
import com.wanris.business.common.router.RouteManager;
import com.wanris.business.common.router.RouterPath;
import com.wanris.module.home.R;
import com.wanris.module.home.contract.HomeContract;
import com.wanris.module.home.presenter.HomePresenter;

@Route(path = RouterPath.HomeActivityPath)
public class HomeActivity extends BaseActivity<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    private BaseFragment homeFragment;
    private BaseFragment chatFragment;
    private BaseFragment videoFragment;
    private BaseFragment cartFragment;
    private BaseFragment mineFragment;
    private RelativeLayout rlBottomTab;
    private RelativeLayout rlBottom;
    private RadioGroup rgTab;
    private RadioButton rbHomeTab;
    @Autowired
    public ParamBean params;

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        setImmersionBar(false);
        rlBottomTab = findViewById(R.id.rl_bottom_tab);
        rbHomeTab = findViewById(R.id.rb_tab_home);
        rlBottom = findViewById(R.id.rl_bottom);
        rgTab = findViewById(R.id.rg_tab);
        showFragment(0);
        rbHomeTab.setText("");
    }

    @Override
    protected void initListener() {
        super.initListener();
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d("TAG", "onCheckedChanged: index " + i);
                if (i == R.id.rb_tab_home) {
                    showFragment(0);
                } else if (i == R.id.rb_tab_chat) {
                    showFragment(1);
                } else if (i == R.id.rb_tab_video) {
                    showFragment(2);
                } else if (i == R.id.rb_tab_cart) {
                    showFragment(3);
                } else if (i == R.id.rb_tab_mine) {
                    showFragment(4);
                }
                rlBottom.setVisibility(i == R.id.rb_tab_home ? View.VISIBLE : View.GONE);
                rbHomeTab.setText(i == R.id.rb_tab_home ? "" : "首页");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter();
    }

    private void showFragment(int tabIndex) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        rlBottomTab.setBackgroundColor(getResources().getColor(R.color.white));
        switch (tabIndex) {
            case 0:
                setImmersionBar(false);
                if (homeFragment == null) {
                    homeFragment = (BaseFragment) RouteManager.startHomeFragment();
                    ft.add(R.id.layout_fragment, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case 1:
                if (chatFragment == null) {
                    chatFragment = (BaseFragment) RouteManager.startHomeChatFragment();
                    ft.add(R.id.layout_fragment, chatFragment);
                } else {
                    ft.show(chatFragment);
                }
                break;
            case 2:
                rlBottomTab.setBackgroundColor(getResources().getColor(R.color.color_0A0A0A));
                if (videoFragment == null) {
                    videoFragment = (BaseFragment) RouteManager.startHomeVideoFragment();
                    ft.add(R.id.layout_fragment, videoFragment);
                } else {
                    ft.show(videoFragment);
                }
                break;
            case 3:
                if (cartFragment == null) {
                    cartFragment = (BaseFragment) RouteManager.startHomeCartFragment();
                    ft.add(R.id.layout_fragment, cartFragment);
                } else {
                    ft.show(cartFragment);
                }
                break;
            case 4:
                if (mineFragment == null) {
                    mineFragment = (BaseFragment) RouteManager.startHomeMineFragment();
                    ft.add(R.id.layout_fragment, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (chatFragment != null) {
            ft.hide(chatFragment);
        }
        if (videoFragment != null) {
            ft.hide(videoFragment);
        }
        if (cartFragment != null) {
            ft.hide(cartFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }
}
