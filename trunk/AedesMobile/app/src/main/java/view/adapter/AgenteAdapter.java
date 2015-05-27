package view.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import br.com.aedes.R;
import controller.AgentePrevencaoController;
import entity.PrevencaoAgenteEntity;
import uk.me.lewisdeane.ldialogs.CustomDialog;
import utils.DateUtils;
import utils.EstadoHelper;
import utils.SharedPreferencesHelper;

/**
 * Created by Alexandre on 24/05/2015.
 */
public class AgenteAdapter extends BaseAdapter {
    private Context mContext;
    private Handler handler;
    private AgentePrevencaoController ac;
    private ArrayList<HashMap<String, String>> AgenteList;

    public AgenteAdapter(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences(SharedPreferencesHelper.AGENTE, 0);
        this.mContext = mContext;
        this.ac = new AgentePrevencaoController(mContext, SharedPreferencesHelper.getString(prefs, "idUsuario"));
        this.AgenteList = new ArrayList<>();
        popularList();
    }

    @Override
    public int getCount() {
        return AgenteList.size();
    }

    @Override
    public Object getItem(int position) {
        return AgenteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView txtDia, txtMes, txtRua, txtBairro, txtCidade, txtHorario;
        final LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.fragment_agente_item, null);

        final HashMap<String, String> itemPrevencao = AgenteList.get(position);
        final LinearLayout layoutChildButtons = (LinearLayout) view.findViewById(R.id.lstChildLstAgente);
        Typeface bebas = Typeface.createFromAsset(mContext.getAssets(), "fonts/bebas.otf");

        layoutChildButtons.setVisibility(View.GONE);


        txtDia = (TextView) view.findViewById(R.id.txtDiaPrevencao);
        txtMes = (TextView) view.findViewById(R.id.txtMes);
        txtRua = (TextView) view.findViewById(R.id.txtRua);
        txtBairro = (TextView) view.findViewById(R.id.txtBairro);
        txtCidade = (TextView) view.findViewById(R.id.txtCidade);
        txtHorario = (TextView) view.findViewById(R.id.txtHorario);

        txtDia.setTypeface(bebas);
        txtRua.setTypeface(bebas);
        txtBairro.setTypeface(bebas);
        txtCidade.setTypeface(bebas);
        txtHorario.setTypeface(bebas);

        if (itemPrevencao.get(PrevencaoAgenteEntity.NUMERO) == "-1") {
            txtDia.setVisibility(View.GONE);
            txtRua.setVisibility(View.GONE);
            txtBairro.setVisibility(View.GONE);
            txtCidade.setVisibility(View.GONE);
            view.setEnabled(false);
            view.setOnClickListener(null);

            txtMes.setText(new DateUtils().convertMesView(new DateUtils().StringToDate(itemPrevencao.get(PrevencaoAgenteEntity.DATA_CRIACAO))).toUpperCase());
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new DateUtils().StringToDate(itemPrevencao.get(PrevencaoAgenteEntity.DATA_CRIACAO)));

            txtMes.setVisibility(View.GONE);
            txtDia.setText(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
            txtRua.setText(itemPrevencao.get(PrevencaoAgenteEntity.RUA) + ", " + itemPrevencao.get(PrevencaoAgenteEntity.NUMERO));
            txtBairro.setText(itemPrevencao.get(PrevencaoAgenteEntity.BAIRRO));
            txtCidade.setText(itemPrevencao.get(PrevencaoAgenteEntity.CIDADE) + "-"
                    +new EstadoHelper().getEstadoAbreviatura(itemPrevencao.get(PrevencaoAgenteEntity.ESTADO)));

            txtHorario.setText("Prevenção realizada às: " + new DateUtils().DateViewFormattedHora(
                    new DateUtils().StringToDate(itemPrevencao.get(PrevencaoAgenteEntity.DATA_CRIACAO))));

        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutChildButtons.getVisibility() == View.VISIBLE) {
                    layoutChildButtons.setVisibility(View.GONE);
                } else {
                    YoYo.with(Techniques.FadeIn).playOn(layoutChildButtons);
                    layoutChildButtons.setVisibility(View.VISIBLE);
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setBackgroundColor(Color.parseColor("#FF8A80"));
                MaterialDialogYesNoRemover(itemPrevencao, v);
                return false;
            }
        });

        return view;

    }

    private void removerAgentePrevencao(HashMap<String, String> prev){
        ac.removerAllAgentePrevencao(prev);
    }

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
        this.AgenteList = ac.getPrevencoes();
        for (int i = 0; i < this.AgenteList.size(); i++) {
            if (ac.verificaTituloMes((ArrayList) AgenteList, i)) {
                HashMap<String, String> mes = new HashMap<String, String>();
                mes.put(PrevencaoAgenteEntity.DATA_CRIACAO, AgenteList.get(i).get(PrevencaoAgenteEntity.DATA_CRIACAO));
                mes.put(PrevencaoAgenteEntity.NUMERO, "-1");
                this.AgenteList.add(i, mes);
                i++;
            }
        }
    }

    private void MaterialDialogYesNoRemover(final HashMap<String, String> itemPrevencao, final View v) {
        CustomDialog.Builder builder = new CustomDialog.Builder(mContext, "Remover prevenção", "Sim");
        builder.content("Deseja remover essa prevenção de sua lista?");
        builder.negativeText("Não");
        builder.contentTextSize(16);
        builder.buttonTextSize(18);
        builder.contentColor("#363835");
        builder.positiveColor("#2BC230");
        builder.negativeColor("#D95555");

        CustomDialog customDialog = builder.build();
        customDialog.setCanceledOnTouchOutside(false);

        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                removerAgentePrevencao(itemPrevencao);
                atualizaAdapter();
            }

            @Override
            public void onCancelClick() {
                v.setBackgroundColor(Color.parseColor("#E0E0E0"));
            }
        });

        customDialog.show();


    }

}
