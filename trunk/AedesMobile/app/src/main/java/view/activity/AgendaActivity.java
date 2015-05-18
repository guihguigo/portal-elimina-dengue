package view.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionItemTarget;
import com.github.amlcurran.showcaseview.targets.Target;

import java.util.Calendar;

import br.com.aedes.R;
import service.CentralSyncService;
import view.adapter.PrevencaoAdapter;

public class AgendaActivity extends ListActivity implements View.OnClickListener {
    private CentralSyncService sync;
    private final String PREFS = "PrimeiraUtilizacao";
    private ShowcaseView showcaseView;
    private ActionItemTarget menuAdd, menuAjuda;
    private int seqTutorial = 0;
    private boolean repetirTutorial = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setListAdapter(new PrevencaoAdapter(this));
        this.getListView().setBackgroundColor(Color.parseColor("#E0E0E0"));
        menuAdd = new ActionItemTarget(this, R.id.action_add_prevencao);
        menuAjuda = new ActionItemTarget(this, R.id.action_ajuda);

        // As sincronizações só irão ocorrer no domingo.
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1) {
            sync = new CentralSyncService(this);
        }

        if (primeiroUso()) {

            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setStyle(R.style.FirstTutorialLayout)
                    .setOnClickListener(this)
                    .setContentTitle("Olá!")
                    .setContentText("Parece que você é novo por aqui...\n" +
                            " Mas não se preocupe, \n pois o uso do app é bem simples!")
                    .build();

            showcaseView.setButtonText("Próximo");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.setListAdapter(new PrevencaoAdapter(this));
    }

    /*
     * Bloco com implementação genérica das ações dos botões da Linear Layout child
     * Visualizável após clique na row.
     */

    public void actionFeito(View v) {
        YoYo.with(Techniques.Pulse).playOn(v);
    }

    public void actionEditar(View v) {
        YoYo.with(Techniques.Pulse).playOn(v);
    }

    public void actionInfo(View v) {
        YoYo.with(Techniques.Pulse).playOn(v);
    }

    //


    private boolean primeiroUso() {
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        if (prefs.getBoolean("primeiro_uso", true)) {
            return true;
        }
        return false;
    }

    private void atualizarSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);

        prefs.edit().putBoolean("primeiro_uso", false).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_prevencao:
                showAddFoco();
                return true;
            case R.id.action_ajuda:
                repeteShowCase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Chamar activity com lista para gerenciamento de cadastro de Prevenções
     */
    private void showAddFoco() {
        Intent i = new Intent(AgendaActivity.this, AddFocoActivity.class);
        this.startActivity(i);

    }

    private void repeteShowCase() {
        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(Target.NONE)
                .setStyle(R.style.FirstTutorialLayout)
                .setOnClickListener(this)
                .setContentTitle("Olá novamente!")
                .setContentText("Parece que você tem algumas dúvidas...\n" +
                        " Vamos lá!")
                .build();

        showcaseView.setButtonText("Próximo");

        repetirTutorial = true;
        seqTutorial = 0;
    }

    @Override
    public void onClick(View v) {
        if (primeiroUso() || repetirTutorial) {
            switch (seqTutorial) {
                case 0:
                    showcaseView.setShowcase(menuAdd, true);
                    showcaseView.setContentTitle("Adicionar Novos Focos");
                    showcaseView.setStyle(R.style.TutorialLayout);
                    showcaseView.setButtonText("Próximo");
                    showcaseView.setContentText("Clicando aqui você será capaz de adicionar novos focos que possuem em sua residência");
                    break;
                case 1:
                    showcaseView.setShowcase(menuAjuda, true);
                    showcaseView.setContentTitle("Qualquer Dúvida...");
                    showcaseView.setStyle(R.style.TutorialLayout);
                    showcaseView.setButtonText("Ok");
                    showcaseView.setContentText("Clique aqui para exibir esse tutorial futuramente.");
                    break;
                case 2:
                    if (!repetirTutorial) {
                        showcaseView.setShowcase(Target.NONE, true);
                        showcaseView.setContentTitle("\n\n\nBem-vindo ao Aedes Mobile!");
                        showcaseView.setStyle(R.style.TutorialLayout);
                        showcaseView.setContentText("E obrigado por se juntar a nós \n nessa batalha contra a dengue.");
                        showcaseView.setButtonText("Fim");
                    } else {
                        showcaseView.hide();
                        repetirTutorial = false;
                    }
                    break;
                default:
                    atualizarSharedPreferences();
                    showcaseView.hide();
                    break;
            }
        }
        seqTutorial++;

    }
}
