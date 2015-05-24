package view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bean.Prevencao;
import br.com.aedes.R;
import controller.PrevencaoController;
import uk.me.lewisdeane.ldialogs.CustomDialog;
import utils.DateUtils;
import utils.DialogUtils;
import utils.SharedPreferencesHelper;

public class PrevencaoAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Prevencao> PrevencaoList;
    Handler handler;
    private PrevencaoController pc;


    private Typeface bebas;
    private Prevencao prevAdapter;
    private Date dtPrazo;
    private SharedPreferences prefs;
    private ShowcaseView showcaseView;
    private int seqTutorial = 0;
    private Target menuAdd = null;


    public PrevencaoAdapter(Context context) {
        this.context = context;
        this.pc = new PrevencaoController(context);
        this.PrevencaoList = new ArrayList<Prevencao>();
        popularList();
        redefineDtPrazo();
        prefs = context.getSharedPreferences(SharedPreferencesHelper.PREFS, 0);

        prevAdapter = new Prevencao();

        tutorialPrevencao();

    }

    @Override
    public int getCount() {
        return PrevencaoList.size();
    }

    @Override
    public Object getItem(int position) {
        return PrevencaoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        bebas = Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf");

        // Recupera o estado da posição atual
        final Prevencao prevencao = PrevencaoList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.fragment_agenda, null);
        // Titulo
        final TextView txtMes = (TextView) view.findViewById(R.id.txtMes);
        final TextView txtTitulo = (TextView) view.findViewById(R.id.txtNmFoco);
        final TextView txtDiaPrevencao = (TextView) view.findViewById(R.id.txtDiaPrevencao);
        final TextView txtHrPrevencao = (TextView) view.findViewById(R.id.txtHrPrevencao);
        final ImageView img = (ImageView) view.findViewById(R.id.imgIconFoco);


        final LinearLayout layoutChildButtons = (LinearLayout) view.findViewById(R.id.layoutChild);
        layoutChildButtons.setVisibility(View.GONE);

        // Views de LinearLayout Child

        final Button btnFeito = (Button) view.findViewById(R.id.btnFeito);
        final Button btnEditar = (Button) view.findViewById(R.id.btnEditar);
        final Button btnInfo = (Button) view.findViewById(R.id.btnInfo);


        if (prevencao.getDataCriacao() == null) {
            txtMes.setText(new DateUtils().convertMesView(prevencao.getDataPrazo()).toUpperCase());

            txtTitulo.setVisibility(View.GONE);
            txtDiaPrevencao.setVisibility(View.GONE);
            txtHrPrevencao.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            view.setEnabled(false);
            view.setOnClickListener(null);
        } else {
            if (SharedPreferencesHelper.primeiroUso(prefs, "lista_prevencao")) {
                view.setBackgroundColor(Color.parseColor("#BBDEFB"));
            }
            Calendar calPrazo = Calendar.getInstance();
            calPrazo.setTime(prevencao.getDataPrazo());


            txtMes.setVisibility(View.GONE);
            txtTitulo.setTypeface(bebas);
            txtTitulo.setText(prevencao.getFoco().getNome());

            txtDiaPrevencao.setTypeface(bebas);
            txtDiaPrevencao.setText(String.format("%02d", calPrazo.get(Calendar.DAY_OF_MONTH)));

            txtHrPrevencao.setTypeface(bebas);
            txtHrPrevencao.setText(String.format("%02d", calPrazo.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", calPrazo.get(Calendar.MINUTE)));


            //Imagem
            img.setImageResource(prevencao.getFoco().getIcone());

            indicaPrevencaoDia(prevencao.getDataPrazo(), view);

        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutChildButtons.getVisibility() == View.VISIBLE) {
                    layoutChildButtons.setVisibility(View.GONE);
                } else {
                    YoYo.with(Techniques.BounceInLeft).playOn(layoutChildButtons);
                    layoutChildButtons.setVisibility(View.VISIBLE);
                }

                if (SharedPreferencesHelper.primeiroUso(prefs, "clique_longo")) {
                    view.setBackgroundColor(Color.parseColor("#E0E0E0"));
                    showcaseView = new ShowcaseView.Builder((android.app.Activity) context)
                            .setTarget(Target.NONE)
                            .setStyle(R.style.TutorialLayoutAdapter)
                            .setOnClickListener(this)
                            .setContentTitle("Caso queira remover...")
                            .setContentText("Selecione o item e segure "
                                    + " quando quiser \n deletar uma prevenção de sua lista.")
                            .build();

                    showcaseView.setButtonText("Fim");
                    seqTutorial = -1;

                    SharedPreferencesHelper.atualizarSharedPreferences(prefs, "clique_longo", false);
                } else if (seqTutorial == -1) {
                    showcaseView.hide();
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MaterialDialogYesNoRemover(prevencao);
                return false;
            }
        });


        btnFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevAdapter = prevencao;
                redefineDtPrazo();
                MaterialDialogYesNoEfetuar("Efetuar Prevenção", "Deseja marcar essa prevenção como feita?", prevencao);
            }
        });


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevAdapter = prevencao;
                dtPrazo = prevAdapter.getDataPrazo();
                setDataPrevencao();
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogUtils(context).MaterialDialogOk(prevencao.getFoco().getNome(), prevencao.getFoco().getComoLimpar());
            }
        });


        return view;
    }


    /**
     * Atualizar view, reprocessando consulta para preenchimento de List
     */
    public void atualizaAdapter() {
        handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        popularList();
                        notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    /*
     * Preenche ArrayList responsável por popular Adapter
     */
    private void popularList() {
        this.PrevencaoList = pc.getPrevencoes();
        for (int i = 0; i < this.PrevencaoList.size(); i++) {
            if (pc.verificaTituloMes((ArrayList) PrevencaoList, i)) {
                Prevencao p = new Prevencao();
                p.setDataPrazo(this.PrevencaoList.get(i).getDataPrazo());
                this.PrevencaoList.add(i, p);
                i++;
            }
        }
    }

    private void redefineDtPrazo() {
        dtPrazo = new Date();
        dtPrazo.setHours(00);
        dtPrazo.setMinutes(00);
    }

    //Mudar a cor da row caso o dia da prevenção seja a data atual, ou esteja atrasada
    private void indicaPrevencaoDia(Date dtPrazo, View view) {
        if (!new Date().before(dtPrazo)) {
            view.setBackgroundColor(Color.parseColor("#FF9999"));
        }
    }


    //Modal DatePicker
    private void setDataPrevencao() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_datepicker);
        final DatePicker dpDefineData = (DatePicker) dialog.findViewById(R.id.dpDiaPrevencao);
        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);
        dpDefineData.updateDate(dtPrazo.getYear() + 1900, dtPrazo.getMonth(), dtPrazo.getDate());

        hideYear(dpDefineData);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Seta data para prevenção
                dtPrazo.setYear(dpDefineData.getYear() - 1900);
                dtPrazo.setMonth(dpDefineData.getMonth());
                dtPrazo.setDate(dpDefineData.getDayOfMonth());
                if (new DateUtils().validaDtPrazo(dtPrazo)) {
                    setHoraPrevencao();
                } else {
                    new DialogUtils(context).MsgToast("O prazo informado é menor que a data atual. \n Favor preencher corretamente.");
                }

            }
        });
        dialog.show();
    }

    private void hideYear(DatePicker dpDefineData) {
        try {
            Field f[] = dpDefineData.getClass().getDeclaredFields();
            for (Field field : f) {
                if (field.getName().equals("mYearSpinner")) {
                    field.setAccessible(true);
                    Object yearPicker = new Object();
                    yearPicker = field.get(dpDefineData);
                    ((View) yearPicker).setEnabled(false);
                }
            }
        } catch (SecurityException e) {
            Log.d("ERROR", e.getMessage());
        } catch (IllegalArgumentException e) {
            Log.d("ERROR", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.d("ERROR", e.getMessage());
        }

    }


    //Modal TimePicker
    private void setHoraPrevencao() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_timepicker);
        final TimePicker tpDefineData = (TimePicker) dialog.findViewById(R.id.tpHorarioPrevencao);

        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);
        tpDefineData.setIs24HourView(true);
        tpDefineData.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        tpDefineData.setCurrentMinute(Calendar.getInstance().get(Calendar.MINUTE) + 1);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Seta data para prevenção
                dtPrazo.setHours(tpDefineData.getCurrentHour());
                dtPrazo.setMinutes(tpDefineData.getCurrentMinute());
                if (new DateUtils().validaHrPrazo(dtPrazo)) {
                    prevAdapter.setDataPrazo(dtPrazo);
                    pc.atualizaPrevencao(prevAdapter);
                    atualizaAdapter();
                } else {
                    new DialogUtils(context).MsgToast("O prazo informado é menor que a data atual. \n Favor preencher corretamente.");
                }


            }
        });
        dialog.show();
    }

    @Deprecated
    private void MaterialDialogYesNoEditar(String titulo, String pergunta, final Prevencao prevencao) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context, titulo, "Editar");
        builder.content(pergunta);
        builder.negativeText("Remover");
        // builder.typeface(Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(16);
        builder.buttonTextSize(18);
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");

        CustomDialog customDialog = builder.build();

        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                prevAdapter = prevencao;
                dtPrazo = prevAdapter.getDataPrazo();
                setDataPrevencao();
            }

            @Override
            public void onCancelClick() {
                pc.removerPrevencao(prevencao);
                atualizaAdapter();
            }
        });

        customDialog.show();


    }

    private void MaterialDialogYesNoRemover(final Prevencao prevencao) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context, "Remover prevenção", "Sim");
        builder.content("Deseja remover essa prevenção de sua lista?");
        builder.negativeText("Não");
        // builder.typeface(Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(16);
        builder.buttonTextSize(18);
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");

        CustomDialog customDialog = builder.build();

        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                pc.removerPrevencao(prevencao);
                atualizaAdapter();
            }

            @Override
            public void onCancelClick() {

            }
        });

        customDialog.show();


    }


    private void MaterialDialogYesNoEfetuar(String titulo, String pergunta, final Prevencao prevencao) {
        CustomDialog.Builder builder = new CustomDialog.Builder(context, titulo, "Sim");

        builder.content(pergunta);
        builder.negativeText("Não");
        // builder.typeface(Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(16);
        builder.buttonTextSize(18);
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");

        CustomDialog customDialog = builder.build();

        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                prevencao.setDataEfetuada(new Date());
                pc.efetuarPrevencao(prevencao);
                dtPrazo = pc.atualizarDtPrevencao(prevencao);
                setDataPrevencao();
            }

            @Override
            public void onCancelClick() {
            }
        });

        customDialog.show();


    }

    public void repeteShowCase(Target menuAdd) {
        if(getCount() > 0) {
            SharedPreferencesHelper.atualizarSharedPreferences(prefs, "lista_prevencao", true);
            SharedPreferencesHelper.atualizarSharedPreferences(prefs, "clique_longo", true);
        }

        showcaseView = new ShowcaseView.Builder((android.app.Activity) context)
                .setTarget(menuAdd)
                .setStyle(R.style.TutorialLayoutAdapter)
                .setOnClickListener(this)
                .setContentTitle("Adicionar Novos Focos")
                .setContentText("Clicando aqui você será capaz de \n" +
                        "adicionar novos focos que possuem em sua residência")
                .build();
        showcaseView.setButtonText("Próximo");
        showcaseView.setButtonText("Próximo");
        seqTutorial = -1; // -1 -> Flag para desabilitar showcase ao entrar no evento onClick


    }


    private void tutorialPrevencao() {
        if (getCount() > 0) {
            if (SharedPreferencesHelper.primeiroUso(prefs, "inicia_tutorial")) {
                showcaseView = new ShowcaseView.Builder((android.app.Activity) context)
                        .setTarget(Target.NONE)
                        .setStyle(R.style.FirstTutorialLayout)
                        .setOnClickListener(this)
                        .setContentTitle("Parabéns!")
                        .setContentText("Pelo visto você está motivado!\n" +
                                " Irei te dar umas dicas nesse início...")
                        .build();
                showcaseView.setButtonText("Próximo");

                seqTutorial = -1;

                SharedPreferencesHelper.atualizarSharedPreferences(prefs, "inicia_tutorial", false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (seqTutorial == -1) {
            showcaseView.hide();
            seqTutorial = -2;
        }
        if (getCount() > 0) {
            if (SharedPreferencesHelper.primeiroUso(prefs, "lista_prevencao")) {
                showcaseView = new ShowcaseView.Builder((android.app.Activity) context)
                        .setTarget(Target.NONE)
                        .setStyle(R.style.FirstTutorialLayout)
                        .setOnClickListener(this)
                        .setContentTitle("Gerenciar prevenção...")
                        .setContentText("Clicando em sua prevenção você \n poderá ver as opções disponíveis.")
                        .build();
                showcaseView.setButtonText("Próximo");
                SharedPreferencesHelper.atualizarSharedPreferences(prefs, "lista_prevencao", false);
                seqTutorial = -1;
            }
        }
    }
}