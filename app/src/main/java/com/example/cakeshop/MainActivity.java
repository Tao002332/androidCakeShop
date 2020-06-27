package com.example.cakeshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cakeshop.resultType.ResultFragmentCode;

/**
 * created by guigui
 * on 2020/6/24
 */
public class MainActivity extends FragmentActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm=getSupportFragmentManager();
        changeFragment(new IndexFragment());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ResultFragmentCode.INDEX: changeFragment(new IndexFragment());break;
            case ResultFragmentCode.ORDER_SEARCH: changeFragment(new OrderSearchFragment());break;
            case ResultFragmentCode.CART: changeFragment(new CartFragment());break;
            case ResultFragmentCode.USER: changeFragment(new UserFragment());break;
            default:break;
        }
    }

    /**
     * fragment 转换
     * @param fragment
     */
    private void changeFragment(Fragment fragment) {
        ft = fm.beginTransaction();
        ft.replace(R.id.mainFragment, fragment);
        ft.commitNow();
    }


    /**
     * 跳转主页
     * @param view
     */
    public void toIndex(View view) {
        changeFragment(new IndexFragment());
    }

    /**
     * 跳转查询订单页
     * @param view
     */
    public void toOrderSearch(View view) {
        changeFragment(new OrderSearchFragment());
    }

    /**
     * 跳转购物车页
     * @param view
     */
    public void toCart(View view) {
        changeFragment(new CartFragment());
    }

    /**
     * 跳转用户页
     * @param view
     */
    public void toUser(View view) {
        changeFragment(new UserFragment());
    }

}
