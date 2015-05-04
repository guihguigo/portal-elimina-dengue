package view.adapter;

import android.app.Dialog;
import android.content.Context;
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

public class PrevencaoAdapter extends BaseAdapter {
    private Context context;
    private List<Prevencao> PrevencaoList;
    Handler handler;
    private PrevencaoController pc;


    private Typeface bebas;
    private Prevencao prevAdapter;
    private Date dtPrazo;


    public PrevencaoAdapter(Context context) {
        this.context = context;
        this.pc = new PrevencaoController(context);
        this.PrevencaoList = new ArrayList<Prevencao>();
        popularList();
        redefineDtPrazo();

        prevAdapter = new Prevencao();

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
            Calendar calPrazo = Calendar.getInstance();
            calPrazo.setTime(prevencao.getDataPrazo());


            txtMes.setVisibility(View.GONE);
            txtTitulo.setTypeface(bebas);
            txtTitulo.setText(prevencao.getFoco().getNome());

            txtDiaPrevencao.setTypeface(bebas);
            txtDiaPrevencao.setText(String.format("%02d", calPrazo.get(Calendar.DAY_OF_MONTH)));

            //txtHrPrevencao.setTypeface(bebas);
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
            }
        });


        btnFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redefineDtPrazo();
                MaterialDialogYesNo("Efetuar Prevenção", "Deseja marcar essa prevenção como feita?", prevencao);
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

    private void redefineDtPrazo(){
        dtPrazo = new Date();
        dtPrazo.setHours(00);
        dtPrazo.setMinutes(00);
    }

    //Mudar a cor da row caso o dia da prevenção seja a data atual, ou esteja atrasada
    private void indicaPrevencaoDia(Date dtPrazo, View view) {
      /*  if (new Date().getMonth() == dtPrazo.getMonth() && new Date().getDay() == dtPrazo.getDay()) {
            view.setBackgroundColor(Color.parseColor("#255BFFF5"));
        } else if ((new Date().getMonth() == dtPrazo.getMonth())) {
            if (new Date().getDate() < dtPrazo.getDate()) {
                view.setBackgroundColor(Color.parseColor("#FF9999"));
            }
        } else if (new Date().getMonth() > dtPrazo.getMonth()) {
            view.setBackgroundColor(Color.parseColor("#FF9999"));
        }*/

        if(!new Date().before(dtPrazo)){
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

    private void hideYear(DatePicker dpDefineData){
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
        }
        catch (SecurityException e) {
            Log.d("ERROR", e.getMessage());
        }
        catch (IllegalArgumentException e) {
            Log.d("ERROR", e.getMessage());
        }
        catch (IllegalAccessException e) {
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




    private void MaterialDialogYesNo(String titulo, String pergunta,final Prevencao prevencao){
        CustomDialog.Builder builder = new CustomDialog.Builder(context, titulo, "Sim");

        builder.content(pergunta);
        builder.negativeText("Não");
        builder.typeface(Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf"));
        builder.contentTextSize(18);
        builder.buttonTextSize(20);
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
}