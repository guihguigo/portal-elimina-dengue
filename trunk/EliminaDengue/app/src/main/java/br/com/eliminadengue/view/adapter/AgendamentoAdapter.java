package br.com.eliminadengue.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.easyandroidanimations.library.BlinkAnimation;
import com.easyandroidanimations.library.HighlightAnimation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.bean.Prevencao;
import br.com.eliminadengue.controller.PrevencaoController;
import br.com.eliminadengue.view.fragment.AgendamentoFragment;


public class AgendamentoAdapter extends BaseAdapter{
    private Context context;
    private List<Foco> FocoList;
    private final LayoutInflater mInflater;
    private PrevencaoController pc;
    private Prevencao prevencao;


    public AgendamentoAdapter(Context context, List<Foco> arr_foco) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.FocoList = arr_foco;
        this.pc = new PrevencaoController(context);
        this.prevencao = new Prevencao();
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
                showModalAgendamento(foco);
                //Intent prevencaoFoco = new Intent(context, ComoLimpar.class);

                //prevencaoFoco.putExtra("id_foco", String.valueOf(foco.getCodigo()));
                //context.startActivity(prevencaoFoco);
            }
        });


        return v;
    }


    // Dialogs Methods

    //Modal Prevenção
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
                new HighlightAnimation(v).animate();
                dialog.dismiss();
                // Seta informações da prevenção
                prevencao.setFoco(foco);
                prevencao.setSync(0);
                prevencao.setDataCriacao(new Date());
                //
                setHoraPrevencao();
            }
        });

        dialog.show();
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
                prevencao.setDataPrazo(pc.setDataPrevencaoAgendamento(tpDefineData.getCurrentHour(), tpDefineData.getCurrentMinute()));
                pc.salvaPrevencao(prevencao);
                atualizaAdapter();
                //new PrevencaoAdapter(context).atualizaAdapter();

            }
        });
        dialog.show();
    }


    public void atualizaAdapter(){
        this.FocoList = new AgendamentoFragment().popularList(this.context);
        notifyDataSetChanged();
    }


}