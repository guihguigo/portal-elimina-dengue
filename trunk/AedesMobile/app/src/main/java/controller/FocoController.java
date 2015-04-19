package controller;

import android.content.Context;

import bean.Foco;
import entity.FocoEntity;


/**
 * Created by Alexandre on 22/02/2015.
 */
public class FocoController {

    private FocoEntity fe;
    private Context ctx;


    public FocoController(Context ctx){
        this.ctx = ctx;
        this.fe = new FocoEntity(ctx);
    }


    public Foco getFoco(String idFoco){
       return fe.getFoco(Integer.parseInt(idFoco));
    }



}
