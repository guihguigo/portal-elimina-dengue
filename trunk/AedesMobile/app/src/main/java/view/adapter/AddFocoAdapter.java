package view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.easyandroidanimations.library.RotationAnimation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bean.Foco;
import bean.Prevencao;
import br.com.aedes.R;
import controller.FocoController;
import uk.me.lewisdeane.ldialogs.CustomDialog;
import utils.DateUtils;
import utils.DialogUtils;

public class AddFocoAdapter extends BaseAdapter {
    private Context context;
    private List<Foco> FocosList;
    Handler handler;
    private FocoController fc;

    private Typeface bebas;
    private Prevencao prevencao;
    private Date dtPrazo;


    private List<Integer> idFocos;

    public AddFocoAdapter(Context context) {
        this.context = context;
        this.fc = new FocoController(context);
        this.FocosList = new ArrayList<Foco>();
        this.idFocos = new ArrayList<Integer>();
        popularList();


        prevencao = new Prevencao();
        dtPrazo = new Date();
        dtPrazo.setHours(00);
        dtPrazo.setMinutes(00);
    }

    @Override
    public int getCount() {
        return FocosList.size();
    }

    @Override
    public Object getItem(int position) {
        return FocosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        final Foco foco = FocosList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.fragment_add_foco, null);

        bebas = Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf");

        // Titulo
        final TextView textTitulo = (TextView) view.findViewById(R.id.txtNmFoco);
        textTitulo.setTypeface(bebas);
        textTitulo.setText(foco.getNome());

        final TextView txtPeriodicidade = (TextView) view.findViewById(R.id.txtPeriodicidade);
        txtPeriodicidade.setTypeface(bebas);
        txtPeriodicidade.setText(fc.definePeriodicidade(foco.getPrazo()));

        //Imagem
        final ImageView img = (ImageView) view.findViewById(R.id.imgIconFoco);
        img.setImageResource(foco.getIcone());

        //Botão para adicionar
        final CheckBox ckbAdd = (CheckBox) view.findViewById(R.id.ckbAdd);
        ckbAdd.setChecked(fc.verificaAgendamento(foco.getCodigo()));

        ckbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ckbAdd.setChecked(false);

                Prevencao p = new Prevencao();
                p.setFoco(foco);

                if (!fc.verificaAgendamento(foco.getCodigo())) {
                    showModalAgendamento(foco);
                } else {
                    MaterialDialogYesNo("Remover Prevenção", "Deseja remover essa prevenção da agenda?", p);
                }

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

    private void popularList() {
        this.FocosList = fc.getAllFocos();
    }

    private void showModalAgendamento(final Foco foco) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_agendamento);

        TextView txtNmFoco = (TextView) dialog.findViewById(R.id.txtNmFoco);
        TextView txtPrazo = (TextView) dialog.findViewById(R.id.txtPeriodicidade);
        TextView txtComoLimpar = (TextView) dialog.findViewById(R.id.txtComoLimpar);
        ImageView imgIconFoco = (ImageView) dialog.findViewById(R.id.imgFocoIcon);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAddFoco);

        txtNmFoco.setText(foco.getNome());
        txtPrazo.setText(txtPrazo.getText().toString() + " " + foco.getPrazo() + " dia(s)");
        txtComoLimpar.setText(foco.getComoLimpar());

        imgIconFoco.setImageResource(foco.getIcone());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RotationAnimation(v).animate();
                dialog.dismiss();
                // Seta informações da prevenção
                prevencao.setFoco(foco);
                prevencao.setSync(0);
                prevencao.setDataCriacao(new Date());
                //
                setDataPrevencao();
            }
        });

        dialog.show();
    }


    //Modal DatePicker
    private void setDataPrevencao() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_datepicker);
        final DatePicker dpDefineData = (DatePicker) dialog.findViewById(R.id.dpDiaPrevencao);
        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);


        hideYear(dpDefineData);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Seta data para prevenção
                dtPrazo.setYear(dpDefineData.getYear() - 1900);
                dtPrazo.setMonth(dpDefineData.getMonth());
                dtPrazo.setDate(dpDefineData.getDayOfMonth());
                // prevencao.setDataPrazo(pc.setDataPrevencaoAgendamento(tpDefineData.getCurrentHour(), tpDefineData.getCurrentMinute()));
                if (new DateUtils().validaDtPrazo(dtPrazo)) {
                    setHoraPrevencao();
                } else {
                    new DialogUtils(context).MsgToast("O prazo informado é menor que a data atual. \n Favor preencher corretamente.");
                }
                //new PrevencaoAdapter(context).atualizaAdapter();

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
                    prevencao.setDataPrazo(dtPrazo);
                    fc.atualizaAgendamento(prevencao);
                    atualizaAdapter();
                } else {
                    new DialogUtils(context).MsgToast("O prazo informado é menor que a data atual. \n Favor preencher corretamente.");
                }


            }
        });
        dialog.show();
    }


    private void MaterialDialogYesNo(String titulo, String pergunta, final Prevencao prevencao) {
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
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                fc.atualizaAgendamento(prevencao);
                atualizaAdapter();

            }


            @Override
            public void onCancelClick() {
                atualizaAdapter();
            }
        });

        customDialog.show();


    }


}