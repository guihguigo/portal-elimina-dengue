package br.com.eliminadengue.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.eliminadengue.view.fragment.AgendamentoFragment;
import br.com.eliminadengue.view.fragment.PrevencaoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
            return new PrevencaoFragment();
		case 1:
            return new AgendamentoFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
