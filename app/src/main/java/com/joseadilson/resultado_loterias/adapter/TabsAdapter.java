package com.joseadilson.resultado_loterias.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joseadilson.resultado_loterias.fragments.DuplaSenaFragment;
import com.joseadilson.resultado_loterias.fragments.LotoFacilFragment;
import com.joseadilson.resultado_loterias.fragments.MegaSenaFragment;
import com.joseadilson.resultado_loterias.fragments.QuinaFragment;

/**
 * Created by jose on 29/01/2017.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = {"DUPLA SENA","LOTO FACIL", "MEGA SENA", "QUINA"};

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new DuplaSenaFragment();
        } else if (position == 1) {
            fragment = new LotoFacilFragment();
        } else if (position == 2) {
            fragment = new MegaSenaFragment();
        } else if (position == 3) {
            fragment = new QuinaFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
