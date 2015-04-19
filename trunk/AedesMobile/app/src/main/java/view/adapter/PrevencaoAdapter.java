package view.adapter;

import android.content.Context;
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
import java.util.List;

import bean.Prevencao;
import controller.PrevencaoController;
import info.androidhive.view.R;

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

        Calendar c = Calendar.getInstance();

        // Recupera o estado da posição atual
        Prevencao prevencao = PrevencaoList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.fragment_agenda, null);

        Typeface bebas = Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf");

        // Titulo
        final TextView textTitulo = (TextView) view.findViewById(R.id.txtNmFoco);
        textTitulo.setTypeface(bebas);
        textTitulo.setText(prevencao.getFoco().getNome());

        final TextView txtDiaPrevencao = (TextView) view.findViewById(R.id.txtDiaPrevencao);
        txtDiaPrevencao.setTypeface(bebas);
        txtDiaPrevencao.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));

        final TextView txtHrPrevencao = (TextView) view.findViewById(R.id.txtHrPrevencao);
        //txtHrPrevencao.setTypeface(bebas);
        txtHrPrevencao.setText(c.get(Calendar.HOUR_OF_DAY) + ":" + (c.get(Calendar.MINUTE)));


        //Imagem
        final ImageView img = (ImageView) view.findViewById(R.id.imgIconFoco);
        img.setImageResource(prevencao.getFoco().getIcone());


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

    }
}