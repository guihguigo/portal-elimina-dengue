package br.com.eliminadengue.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Foco;

public class AgendamentoAdapter extends BaseAdapter {
    private Context context;
    private List<Foco> FocoList;

    public AgendamentoAdapter(Context context, List<Foco> arr_foco) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        Foco foco = this.FocoList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.fragment_agendamento, null);

        // Titulo
        final TextView textTitulo = (TextView) view.findViewById(R.id.textTitulo);
        textTitulo.setText(foco.getNome());

        // Descricao
        final TextView textDescricao = (TextView) view.findViewById(R.id.textDescricao);
        textDescricao.setText(String.valueOf(foco.getComoLimpar()));

        //Imagem
        final ImageView img = (ImageView) view.findViewById(R.id.imagePrevencao);
        img.setImageResource(foco.getIcone());

        //Checkbox
        final CheckBox ckbAgendar = (CheckBox) view.findViewById(R.id.ckbAgendar);
        ckbAgendar.setEnabled(false);


        //Evento click - Exibir Caixa de Dialogo
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                geraDialogBuilder(textTitulo.getText().toString(), ckbAgendar);

                //Toast.makeText(context, textTitulo.getText().toString(),
                //       Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


    public void geraDialogBuilder(String tituloPrevencao, final CheckBox agendar) {
        final boolean[] setarPrevencao = new boolean[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String pergunta = null;

        if (!agendar.isChecked())
            pergunta = "Deseja agendar esta prevenção?";
        else
            pergunta = "Deseja desmarcar esta prevenção?";

        builder.setTitle(tituloPrevencao);
        builder.setMessage(pergunta);


        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                agendar.setChecked(!agendar.isChecked());
            }


        });


        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        AlertDialog alert = builder.create();
        alert.show();
    }
}