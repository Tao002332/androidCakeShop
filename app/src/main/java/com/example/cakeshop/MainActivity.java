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
    private IndexFragment indexFragment= new IndexFragment();
    private OrderSearchFragment orderSearchFragment= new OrderSearchFragment();
    private CartFragment cartFragment= new CartFragment();
    private UserFragment userFragment= new UserFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm=getSupportFragmentManager();
        changeFragment(indexFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ResultFragmentCode.INDEX: changeFragment(indexFragment);break;
            case ResultFragmentCode.ORDER_SEARCH: changeFragment(orderSearchFragment);break;
            case ResultFragmentCode.CART: changeFragment(cartFragment);break;
            case ResultFragmentCode.USER: changeFragment(userFragment);break;
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
        changeFragment(indexFragment);
    }

    /**
     * 跳转查询订单页
     * @param view
     */
    public void toOrderSearch(View view) {
        changeFragment(orderSearchFragment);
    }

    /**
     * 跳转购物车页
     * @param view
     */
    public void toCart(View view) {
        changeFragment(cartFragment);
    }

    /**
     * 跳转用户页
     * @param view
     */
    public void toUser(View view) {
        changeFragment(userFragment);
    }

}
