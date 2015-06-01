package entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import entity.db.EliminaDengueDb;

/**
 * Created by Alexandre on 24/05/2015.
 */
public class PrevencaoAgenteEntity extends EliminaDengueDb {
    public static final String ID_SYNC = "id_sync";
    public static final String ID_USUARIO = "id_usuario";
    public static final String ID_FOCO = "id_foco";
    public static final String DATA_CRIACAO = "dt_criacao";
    //Endereco
    public static final String RUA = "rua";
    public static final String NUMERO = "numero";
    public static final String BAIRRO = "bairro";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";
    //
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    public PrevencaoAgenteEntity(Context context) {
        super(context);
    }

    public String createPrevencaoAgenteTable() {
        String CREATE_TABLE_PREVENCAO_AGENTE = "CREATE TABLE " + TABELA_PREVENCAO_AGENTE + "("
                + ID_SYNC + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ID_USUARIO + " TEXT,"
                + ID_FOCO + " INTEGER,"
                + DATA_CRIACAO + " DATE, "
                + LATITUDE + " FLOAT,"
                + LONGITUDE + " FLOAT,"
                + RUA + " TEXT,"
                + NUMERO + " TEXT,"
                + BAIRRO + " TEXT,"
                + CIDADE + " TEXT,"
                + ESTADO + " TEXT);";

        return CREATE_TABLE_PREVENCAO_AGENTE;
    }


    public void addPrevencaoAgente(HashMap<String, String> arrPrev) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FOCO, arrPrev.get(ID_FOCO));
        values.put(ID_USUARIO, arrPrev.get(ID_USUARIO));
        values.put(LATITUDE, arrPrev.get(LATITUDE));
        values.put(LONGITUDE, arrPrev.get(LONGITUDE));
        values.put(DATA_CRIACAO, arrPrev.get(DATA_CRIACAO));
        values.put(RUA, arrPrev.get(RUA));
        values.put(NUMERO, arrPrev.get(NUMERO));
        values.put(BAIRRO, arrPrev.get(BAIRRO));
        values.put(CIDADE, arrPrev.get(CIDADE));
        values.put(ESTADO, arrPrev.get(ESTADO));
        db.insert(TABELA_PREVENCAO_AGENTE, null, values);
        db.close();

    }

    public void delPrevencaoAgente(int idSync) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PREVENCAO_AGENTE, ID_SYNC + "=" + idSync, null);
    }

    public void delPrevencaoAgente(int idFoco, String idUsuario, String rua, String numero) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PREVENCAO_AGENTE, ID_FOCO + "=" + idFoco
                + " AND " + RUA + " = '" + rua + "'"
                + " AND " + NUMERO + " = '" + numero + "'"
                + " AND " + ID_USUARIO + " = '" + idUsuario + "'", null);
    }

    public void delPrevencaoAgente(String idUsuario, String rua, String numero) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PREVENCAO_AGENTE, ID_USUARIO + " = '" + idUsuario + "'"
                + " AND " + RUA + " = '" + rua + "'"
                + " AND " + NUMERO + " = '" + numero + "'", null);
    }


    public HashMap<String, String> getPrevencaoAgente(int idFoco, String idUsuario, String rua, String numero) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABELA_PREVENCAO_AGENTE
                + " WHERE " + ID_FOCO + "=" + idFoco
                + " AND " + RUA + " = '" + rua + "'"
                + " AND " + ID_USUARIO + " = '" + idUsuario + "'"
                + " AND " + NUMERO + " = '" + numero + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        HashMap<String, String> prev = new HashMap<String, String>();
        prev.put(ID_FOCO, "-1");

        if (c.getCount() > 0) {
            c.moveToFirst();
            prev.put(DATA_CRIACAO, (c.getString(c.getColumnIndex(DATA_CRIACAO))));
            prev.put(RUA, (c.getString(c.getColumnIndex(RUA))));
            prev.put(NUMERO, (c.getString(c.getColumnIndex(NUMERO))));
            prev.put(BAIRRO, (c.getString(c.getColumnIndex(BAIRRO))));
            prev.put(CIDADE, (c.getString(c.getColumnIndex(CIDADE))));
            prev.put(ESTADO, (c.getString(c.getColumnIndex(ESTADO))));
            prev.put(ID_FOCO, (c.getString(c.getColumnIndex(ID_FOCO))));
            prev.put(ID_USUARIO, (c.getString(c.getColumnIndex(ID_USUARIO))));
        }

        return prev;
    }

    public ArrayList<HashMap<String, String>> getAllPrevencoesAgente(String idUsuario) {
        ArrayList<HashMap<String, String>> arrPrev = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT DISTINCT "
                + ID_USUARIO + ","
                + DATA_CRIACAO + ","
                + RUA + ","
                + NUMERO + ","
                + CIDADE + ","
                + BAIRRO + ","
                + ESTADO
                + " FROM " + TABELA_PREVENCAO_AGENTE
                + " WHERE " + ID_USUARIO + " = '" + idUsuario
                + "' ORDER BY " + DATA_CRIACAO + " ASC";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                HashMap<String, String> prev = new HashMap<String, String>();
                prev.put(ID_USUARIO, (c.getString(c.getColumnIndex(ID_USUARIO))));
                prev.put(DATA_CRIACAO, (c.getString(c.getColumnIndex(DATA_CRIACAO))));
                prev.put(RUA, (c.getString(c.getColumnIndex(RUA))));
                prev.put(NUMERO, (c.getString(c.getColumnIndex(NUMERO))));
                prev.put(BAIRRO, (c.getString(c.getColumnIndex(BAIRRO))));
                prev.put(CIDADE, (c.getString(c.getColumnIndex(CIDADE))));
                prev.put(ESTADO, (c.getString(c.getColumnIndex(ESTADO))));
                arrPrev.add(prev);
            } while (c.moveToNext());

        }

        return arrPrev;
    }

    public HashMap<String, String> getFirstPrevencaoAgente(String idUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABELA_PREVENCAO_AGENTE
                + " WHERE " + ID_USUARIO + " = '" + idUsuario + "'"
                + " ORDER BY " + ID_SYNC + " ASC";

        Cursor c = db.rawQuery(selectQuery, null);

        HashMap<String, String> prev = new HashMap<String, String>();
        prev.put(ID_FOCO, "-1");

        if (c.getCount() > 0) {
            c.moveToFirst();
            prev.put(ID_SYNC , (c.getString(c.getColumnIndex(ID_SYNC))));
            prev.put(DATA_CRIACAO, (c.getString(c.getColumnIndex(DATA_CRIACAO))));
            prev.put(RUA, (c.getString(c.getColumnIndex(RUA))));
            prev.put(NUMERO, (c.getString(c.getColumnIndex(NUMERO))));
            prev.put(BAIRRO, (c.getString(c.getColumnIndex(BAIRRO))));
            prev.put(CIDADE, (c.getString(c.getColumnIndex(CIDADE))));
            prev.put(ESTADO, (c.getString(c.getColumnIndex(ESTADO))));
            prev.put(ID_FOCO, (c.getString(c.getColumnIndex(ID_FOCO))));
            prev.put(ID_USUARIO, (c.getString(c.getColumnIndex(ID_USUARIO))));
        }

        return prev;
    }


}
