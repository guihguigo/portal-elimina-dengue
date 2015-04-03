package br.com.eliminadengue.entity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.eliminadengue.entity.FocoEntity;
import br.com.eliminadengue.entity.PrevencaoEntity;

/**
 * Created by Alexandre on 07/03/2015.
 */

public class EliminaDengueDb extends SQLiteOpenHelper {
    private static final int BANCO_VERSAO = 1;

    private static final String NOME_BANCO = "eliminadengue";

    //Tables
    protected static final String TABELA_FOCO = "foco";
    protected static final String TABELA_PREVENCAO = "prevencao";

    // Popular FOCOS
    protected String[] DMLFocos;


    protected Context context;

    public EliminaDengueDb(Context context) {
        super(context, NOME_BANCO, null, BANCO_VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(new FocoEntity(this.context).createFocoTable());
        db.execSQL(new PrevencaoEntity(this.context).createPrevencaoTable());

        //Popular Focos
        this.DMLFocos = new FocoEntity(this.context).DmlFocos();
        for(String query : this.DMLFocos){
            db.execSQL(query);
        }
    }

    // Sobre a estrutura do método acima. Gostaria de deixar bem claro que essa forma de trabalhar é minha, e não é POG
    // Apenas achei mais legal de utilizar getter do script de criação das tabelas

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PREVENCAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FOCO);
        onCreate(db);
    }
}
