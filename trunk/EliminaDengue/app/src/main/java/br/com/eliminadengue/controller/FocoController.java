package br.com.eliminadengue.controller;

import android.content.Context;

import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.entity.FocoEntity;

/**
 * Created by Alexandre on 22/02/2015.
 */
public class FocoController {

    private Context ctx;


    public FocoController(Context ctx){
        this.ctx = ctx;
    }


    public Foco getFoco(String idFoco){
       return new FocoEntity(this.ctx).getFoco(Integer.parseInt(idFoco));
    }

}
