package view.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroidanimations.library.BlinkAnimation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Foco;
import bean.Prevencao;
import controller.PrevencaoController;
import entity.FocoEntity;
import entity.PrevencaoEntity;
import info.androidhive.view.R;


public class AddFocoAdapter extends BaseAdapter {
    private Context context;
    private List<Foco> FocoList;
    private final LayoutInflater mInflater;
    private PrevencaoController pc;
    private Prevencao prevencao;
    private Handler handler;
    private Date dtPrazo;

    public AddFocoAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        popularList();
        this.pc = new PrevencaoController(context);
        this.prevencao = new Prevencao();

        dtPrazo = new Date();
        dtPrazo.setHours(00);
        dtPrazo.setMinutes(00);
    }

    @Override
    public int getCount() {
        return FocoList.size();
    }

    @Override
    public Object getItem(int position) {
        return FocoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final Foco foco = this.FocoList.get(position);
        View v = convertView;
        ImageView iconFoco;
        final TextView nomeFoco;

        if (v == null) {
            v = mInflater.inflate(R.layout.tile_image_item, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        iconFoco = (ImageView) v.getTag(R.id.picture);
        nomeFoco = (TextView) v.getTag(R.id.text);
        iconFoco.setImageResource(foco.getIcone());
        nomeFoco.setText(foco.getNome());

        iconFoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BlinkAnimation(v).setNumOfBlinks(1).animate();
             //   showModalAgendamento(foco);
                //Intent prevencaoFoco = new Intent(context, ComoLimpar.class);

                //prevencaoFoco.putExtra("id_foco", String.valueOf(foco.getCodigo()));
                //context.startActivity(prevencaoFoco);
            }
        });


        return v;
    }


    // Dialogs Methods

    //Modal Prevenção
   /* private void showModalAgendamento(final Foco foco) {
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
                new HighlightAnimation(v).animate();
                dialog.dismiss();
                // Seta informações da prevenção
                prevencao.setFoco(foco);
                prevencao.setSync(0);
                prevencao.setDataCriacao(new Date());
                //
            //    setDataPrevencao();
            }
        });

        dialog.show();
    }*/


    //Modal DatePicker
  /*  private void setDataPrevencao() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_datepicker);
        final DatePicker dpDefineData = (DatePicker) dialog.findViewById(R.id.dpDiaPrevencao);
        Button btnSalvar = (Button) dialog.findViewById(R.id.btnConfirma);

        //dpDefineData.set(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Seta data para prevenção
                dtPrazo.setYear(dpDefineData.getYear() - 1900);
                dtPrazo.setMonth(dpDefineData.getMonth());
                dtPrazo.setDate(dpDefineData.getDayOfMonth());
                // prevencao.setDataPrazo(pc.setDataPrevencaoAgendamento(tpDefineData.getCurrentHour(), tpDefineData.getCurrentMinute()));
                if(new DateUtils().validaDtPrazo(dtPrazo)) {
                    //setHoraPrevencao();
                }else{
                    new DialogUtils(context).MsgToast("O prazo informado é menor que a data atual. \n Favor preencher corretamente.");
                }
                //new PrevencaoAdapter(context).atualizaAdapter();

            }
        });
        dialog.show();
    }*/


    //Modal TimePicker
    /*private void setHoraPrevencao() {
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
                if(new DateUtils().validaHrPrazo(dtPrazo)){
                    prevencao.setDataPrazo(dtPrazo);
                    pc.salvaPrevencao(prevencao);
                    atualizaAdapter();
                }else{
                    new DialogUtils(context).MsgToast("O prazo informado é menor que a data atual. \n Favor preencher corretamente.");
                }


            }
        });
        dialog.show();
    }*/

    public void atualizaAdapter() {
        handler = new Handler();
        new Thread() {
            @Override
            public void run() {
                popularList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    private void popularList() {
        PrevencaoEntity pe = new PrevencaoEntity(context);
        FocoEntity fe = new FocoEntity(context);
        FocoList = new ArrayList<Foco>();
        int i = 0;
        while (fe.getFoco(++i).getCodigo() != -1) {
            if (pe.getPrevencao(i).getFoco().getCodigo() == -1) {
                this.FocoList.add(fe.getFoco(i));
            }
        }
    }


}