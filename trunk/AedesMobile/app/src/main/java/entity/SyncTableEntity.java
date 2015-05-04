package entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bean.Foco;
import bean.Prevencao;
import entity.db.EliminaDengueDb;
import utils.DateUtils;

/**
 * Created by Alexandre on 22/02/2015.
 */
public class SyncTableEntity extends EliminaDengueDb {

    //  private static final String TABELA_SYNC = "foco";
    private static final String ID_SYNC = "id_sync";
    private static final String ID_FOCO = "id_foco";
    private static final String DATA_CRIACAO = "dt_criacao";
    private static final String DATA_PRAZO = "dt_prazo";
    private static final String DATA_EFETUADA = "dt_efetuada";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";


    public SyncTableEntity(Context context) {
        super(context);
    }

    public String createSyncTable() {
        String CREATE_TABLE_SYNC = "CREATE TABLE " + TABELA_SYNC + "("
                + ID_SYNC + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_FOCO + " INTEGER, "
                + LATITUDE + " FLOAT, "
                + LONGITUDE + " FLOAT, "
                + DATA_CRIACAO + " DATE, " + DATA_EFETUADA + " DATE, " + DATA_PRAZO + " DATE);";

        return CREATE_TABLE_SYNC;
    }


    public void addSync(Prevencao prevencao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FOCO, prevencao.getFoco().getCodigo());
        values.put(LATITUDE, prevencao.getLatitude());
        values.put(LONGITUDE, prevencao.getLongitude());
        values.put(DATA_CRIACAO, new DateUtils().DateToString(prevencao.getDataCriacao()));
        values.put(DATA_PRAZO, new DateUtils().DateToString(prevencao.getDataPrazo()));
        values.put(DATA_EFETUADA, new DateUtils().DateToString(prevencao.getDataEfetuada()));

        db.insert(TABELA_SYNC, null, values);
        db.close();
    }


    public Prevencao getFirstPrevencao() {
        Prevencao prev = new Prevencao();

        Foco f = new Foco();
        f.setCodigo(-1);
        prev.setFoco(f);


        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABELA_SYNC + " ORDER BY " + ID_SYNC;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            prev.getFoco().setCodigo((c.getInt(c.getColumnIndex(ID_FOCO))));
            prev.setDataCriacao(new DateUtils().StringToDate(c.getString(c.getColumnIndex(DATA_CRIACAO))));
            prev.setDataEfetuada(new DateUtils().StringToDate(c.getString(c.getColumnIndex(DATA_EFETUADA))));
            prev.setDataPrazo(new DateUtils().StringToDate(c.getString(c.getColumnIndex(DATA_PRAZO))));
        }

        return prev;
    }

    public void removeFirstSync(){
        SQLiteDatabase db = this.getReadableDatabase();
        int idSync = 0;

        String selectQuery = "SELECT "+ ID_SYNC +" FROM " + TABELA_SYNC + " ORDER BY " + ID_SYNC;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            idSync = c.getInt((c.getColumnIndex(ID_SYNC)));
        }

        if(idSync > 0){
            db.delete(TABELA_SYNC, ID_SYNC + "=" + idSync, null);
        }

    }


}