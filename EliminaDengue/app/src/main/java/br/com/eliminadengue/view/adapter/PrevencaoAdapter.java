package br.com.eliminadengue.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroidanimations.library.BlinkAnimation;

import java.util.List;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Prevencao;
import br.com.eliminadengue.controller.FocoController;
import br.com.eliminadengue.controller.PrevencaoController;
import br.com.eliminadengue.entity.PrevencaoEntity;
import br.com.eliminadengue.utils.DateUtils;


public class PrevencaoAdapter extends BaseAdapter{
    private Context context;
    private List<Prevencao> PrevencaoList;
    private final LayoutInflater mInflater;
    private PrevencaoController pc;
    private FocoController fc;
    private Prevencao prevencao;
    private Handler handler;


    public PrevencaoAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.prevencao = new Prevencao();
        this.fc = new FocoController(this.context);
        this.pc = new PrevencaoController(this.context);
        this.pc.atualizaNotificador(new PrevencaoEntity(context).getUltimaPrevencao());
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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final Prevencao prevencao = this.PrevencaoList.get(position);
        prevencao.setFoco(fc.getFoco(String.valueOf(prevencao.getFoco().getCodigo())));

        View v = convertView;
        ImageView iconFoco;
        final TextView txtDadosPrevencao;

        if (v == null) {
            v = mInflater.inflate(R.layout.tile_image_item, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        iconFoco = (ImageView) v.getTag(R.id.picture);
        txtDadosPrevencao = (TextView) v.getTag(R.id.text);
        iconFoco.setImageResource(prevencao.getFoco().getIcone());
        txtDadosPrevencao.setText(prevencao.getFoco().getNome() + "\n" + new DateUtils().DateViewFormatted(prevencao.getDataPrazo()));

        iconFoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BlinkAnimation(v).setNumOfBlinks(1).animate();
                atualizaAdapter();
            }
        });


        return v;
    }

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

    private void popularList(){
        this.PrevencaoList = pc.getPrevencoes();
    }

   /* private void popularList() {
        PrevencaoEntity pe = new PrevencaoEntity(this.context);
        FocoEntity fe = new FocoEntity(this.context);

        PrevencaoList = new ArrayList<Prevencao>();

        int count = fe.getFocoCount();
        for (int i = 1; i <= count; i++) {
            if (pe.getPrevencao(i).getFoco().getCodigo() != -1) {
                this.PrevencaoList.add(pe.getPrevencao(i));
            }
        }

*/

    }