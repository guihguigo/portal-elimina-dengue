package controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import bean.Foco;
import entity.FocoEntity;


/**
 * Created by Alexandre on 22/02/2015.
 */
public class FocoController {

    private FocoEntity fe;
    private Context ctx;


    public FocoController(Context ctx) {
        this.ctx = ctx;
        this.fe = new FocoEntity(ctx);
    }


    public Foco getFoco(String idFoco) {
        return fe.getFoco(Integer.parseInt(idFoco));
    }

    public List<Foco> getAllFocos() {
        ArrayList<Foco> focos = new ArrayList<Foco>();

        int i = 0;
        while (fe.getFoco(++i).getCodigo() != -1) {
            focos.add(fe.getFoco(i));
        }

        return focos;
    }

    /**
     * @param dias Periodicidade do foco
     * @return String formatada para camada de visualização com as instruções de periodicidade para usuário
     *
     */
    public String definePeriodicidade(int dias){
        switch(dias){
            case 0:
                return "Não há periodicidade";
            case 1:
                return "Recomendado limpar todos os dias";
            default:
                return "Limpar a cada " + dias + " dias";
        }
    }


}
