package br.com.eliminadengue.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import br.com.eliminadengue.R;
import br.com.eliminadengue.view.adapter.TabsPagerAdapter;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private int[] tabs = {R.drawable.home_prevencoes, R.drawable.pesquisar_focos};
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        for (int tab_icon : tabs) {
            actionBar.addTab(actionBar.newTab().setIcon(tab_icon)
                    .setTabListener(this));
        }


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }


        });
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(final Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
        updateDataSet(tab.getPosition());
    }


    /**
     * @param indActivity Passe 0 como parâmetro caso seja activity Prevenção e 1 para Agendamento
     */
    private void updateDataSet(final int indActivity) {
        mAdapter.atualizaAdapter(indActivity);

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }


}
