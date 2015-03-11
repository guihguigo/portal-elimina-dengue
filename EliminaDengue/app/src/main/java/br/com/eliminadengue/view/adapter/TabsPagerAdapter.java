package br.com.eliminadengue.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import br.com.eliminadengue.view.fragment.AgendamentoFragment;
import br.com.eliminadengue.view.fragment.PrevencaoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private PrevencaoFragment prevencaoFragment;
    private AgendamentoFragment agendamentoFragment;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        prevencaoFragment = new PrevencaoFragment();
        agendamentoFragment = new AgendamentoFragment();

    }

    public void atualizaAdapter(final int index) {
        new Thread() {
            @Override
            public void run() {
                try {
                    switch (index) {
                        case 0:
                            prevencaoFragment.atualizaAdapter();
                            break;
                        case 1:
                            agendamentoFragment.atualizaAdapter();
                            break;
                    }

                } catch (Exception ex) {
                    Log.d("Log","Não se abale. Se passou por aqui é só porque não foi instanciado o fragment");
                }
            }
        }.start();

    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return prevencaoFragment;
            case 1:
                return agendamentoFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
