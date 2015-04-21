package view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyandroidanimations.library.RotationAnimation;

import java.util.ArrayList;
import java.util.List;

import bean.Foco;
import br.com.aedes.R;
import controller.FocoController;

public class AddFocoAdapter extends BaseAdapter {
    private Context context;
    private List<Foco> FocosList;
    Handler handler;
    private FocoController fc;

    private List<Integer> idFocos;

    public AddFocoAdapter(Context context) {
        this.context = context;
        this.fc = new FocoController(context);
        this.FocosList = new ArrayList<Foco>();
        this.idFocos = new ArrayList<Integer>();
        popularList();
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

        Typeface bebas = Typeface.createFromAsset(context.getAssets(), "fonts/bebas.otf");

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
        final Button btnAdd = (Button) view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RotationAnimation(view).animate();
                view.setBackgroundResource(R.drawable.ic_checked);

                idFocos.add(foco.getCodigo());
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


}