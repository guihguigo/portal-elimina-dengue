package view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import bean.Foco;
import br.com.aedes.R;
import controller.AgenteFocoController;
import controller.FocoController;
import entity.PrevencaoAgenteEntity;
import utils.DateUtils;

/**
 * Created by Alexandre on 25/05/2015.
 */
public class AgenteAddFocoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Foco> FocosList;
    private FocoController fc;
    private AgenteFocoController afc;
    private Typeface bebas;
    private HashMap<String, String> arrMetaData;
    private Date dtPrevencao;

    public AgenteAddFocoAdapter(Context mContext,HashMap<String, String> arrMetaData) {
        this.mContext = mContext;
        this.fc = new FocoController(mContext);
        this.afc = new AgenteFocoController(mContext);
        this.FocosList = new ArrayList<Foco>();
        this.arrMetaData = arrMetaData;
        dtPrevencao = new Date();
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
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.fragment_add_foco, null);

        bebas = Typeface.createFromAsset(mContext.getAssets(), "fonts/bebas.otf");

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

        if(verificaPrevencaoAgendada(foco.getCodigo())){
            ckbAdd.setChecked(true);
        }

        ckbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ckbAdd.isChecked()) {
                    atualizaLista(foco.getCodigo());
                    ckbAdd.setChecked(true);
                }else{
                    removeItemLista(foco.getCodigo());
                    ckbAdd.setChecked(false);                }
            }
        });


        return view;
    }

    private void popularList() {
        this.FocosList = fc.getAllFocos();
    }

    private void atualizaLista(int idFoco){
        HashMap<String, String> newPrevencao;
        newPrevencao = arrMetaData;
        newPrevencao.put(PrevencaoAgenteEntity.ID_FOCO, String.valueOf(idFoco));
        newPrevencao.put(PrevencaoAgenteEntity.DATA_CRIACAO, new DateUtils().DateToString(dtPrevencao));

        afc.salvarAgentePrevencao(newPrevencao);

    }

    private void removeItemLista(int idFoco){
        HashMap<String, String> newPrevencao;
        newPrevencao = arrMetaData;
        newPrevencao.put(PrevencaoAgenteEntity.ID_FOCO, String.valueOf(idFoco));
        afc.removerAgentePrevencao(newPrevencao);

    }


    private boolean verificaPrevencaoAgendada(int idFoco){
        HashMap<String, String> newPrevencao;
        newPrevencao = arrMetaData;
        newPrevencao.put(PrevencaoAgenteEntity.ID_FOCO, String.valueOf(idFoco));
        newPrevencao = afc.getAgentePrevencao(newPrevencao);

        if(newPrevencao.get(PrevencaoAgenteEntity.ID_FOCO).equals("-1")){
            return false;
        }

        return true;
    }
}
