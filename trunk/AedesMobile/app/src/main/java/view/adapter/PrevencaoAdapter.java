package view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bean.Prevencao;
import br.com.aedes.R;
import controller.PrevencaoController;
import utils.DateUtils;

public class PrevencaoAdapter extends BaseAdapter {
    private Context context;
    private List<Prevencao> PrevencaoList;
    Handler handler;
    private PrevencaoController pc;

    public PrevencaoAdapter(Context context) {
        this.context = context;
        this.pc = new PrevencaoController(context);
        this.PrevencaoList = new ArrayList<Prevencao>();
        popularList();
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
        Typeface bebas = Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf");

        // Recupera o estado da posição atual
        Prevencao prevencao = PrevencaoList.get(position);

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
            txtHrPrevencao.setText(String.format("%02d",calPrazo.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d",calPrazo.get(Calendar.MINUTE)));


            //Imagem
            img.setImageResource(prevencao.getFoco().getIcone());

            indicaPrevencaoDia(prevencao.getDataPrazo(), view);

        }

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

    //Mudar a cor da row caso o dia da prevenção seja a data atual
    private void indicaPrevencaoDia(Date dtPrazo, View view){
        if(new Date().getMonth() == dtPrazo.getMonth() && new Date().getDay() == dtPrazo.getDay())
            view.setBackgroundColor(Color.parseColor("#255BFFF5"));
    }
}