package br.com.eliminadengue.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroidanimations.library.BlinkAnimation;

import java.util.List;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.view.ComoLimpar;


public class AgendamentoAdapter extends BaseAdapter {
    private Context context;
    private List<Foco> FocoList;
    private final LayoutInflater mInflater;


    public AgendamentoAdapter(Context context, List<Foco> arr_foco) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.FocoList = arr_foco;
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
                //  new HighlightAnimation(v).setColor(Color.parseColor("#0099FF")).animate();
                new BlinkAnimation(v).setNumOfBlinks(1).animate();
                Intent prevencaoFoco = new Intent(context, ComoLimpar.class);
                prevencaoFoco.putExtra("id_foco", String.valueOf(foco.getCodigo()));
                context.startActivity(prevencaoFoco);
            }
        });


        return v;
    }


}