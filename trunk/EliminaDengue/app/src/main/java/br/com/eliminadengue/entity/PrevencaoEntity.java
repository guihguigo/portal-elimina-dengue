package br.com.eliminadengue.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.eliminadengue.bean.Prevencao;
import br.com.eliminadengue.utils.DateUtils;

/**
 * Created by Alexandre on 04/03/2015.
 */
public class PrevencaoEntity extends SQLiteOpenHelper {


    private static final int BANCO_VERSAO = 1;

    private static final String NOME_BANCO = "eliminadengue";

    private static final String TABELA_PREVENCAO = "prevencao";

    private static final String ID_FOCO = "id_foco";
    private static final String DATA_CRIACAO = "dt_criacao";
    private static final String DATA_PRAZO = "dt_prazo";
    private static final String DATA_EFETUADA = "dt_efetuada";
    private static final String SYNC = "sync"; // Campo que indica se conteúdo foi sincronizado com a Central [1/0]

    //Criado dessa forma pois a lógica segue que: Para pegar endereço completo precisaria da internet,
    // e esse recurso precisa funcionar offline. Por isso são gravadas as coordenadas apenas e o endereço é
    // enviado no JSON para a Central.
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";


    public PrevencaoEntity(Context context) {
        super(context, NOME_BANCO, null, BANCO_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PREVENCAO = "CREATE TABLE " + TABELA_PREVENCAO + "("
                + ID_FOCO + " INTEGER PRIMARY KEY,"
                + DATA_CRIACAO + " DATE, " + DATA_EFETUADA + " DATE, " + DATA_PRAZO + " DATE, " + SYNC + " CHAR,"
                + LATITUDE + " FLOAT, " + LONGITUDE + " FLOAT " + ");";
        db.execSQL(CREATE_TABLE_PREVENCAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PREVENCAO);

        onCreate(db);
    }


    public void addPrevencao(Prevencao prevencao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FOCO, prevencao.getFoco().getCodigo());
        values.put(DATA_CRIACAO, new DateUtils().DateToString(prevencao.getDataCriacao()));
        values.put(DATA_PRAZO, new DateUtils().DateToString(prevencao.getDataPrazo()));
        values.put(DATA_EFETUADA, new DateUtils().DateToString(prevencao.getDataEfetuada()));
        values.put(SYNC, prevencao.getSync());
        values.put(LATITUDE, prevencao.getLatitude());
        values.put(LONGITUDE, prevencao.getLongitude());

        db.insert(TABELA_PREVENCAO, null, values);
        db.close();
    }

    public Prevencao getPrevencao(int idFoco) {
        Prevencao prev = new Prevencao();
        prev.getFoco().getCodigo();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABELA_PREVENCAO + " WHERE id_foco = " + idFoco;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            prev.getFoco().setCodigo((c.getInt(c.getColumnIndex(ID_FOCO))));
            prev.setDataCriacao(new DateUtils().StringToDate(c.getString(c.getColumnIndex(DATA_CRIACAO))));
            prev.setDataEfetuada(new DateUtils().StringToDate(c.getString(c.getColumnIndex(DATA_EFETUADA))));
            prev.setDataPrazo(new DateUtils().StringToDate(c.getString(c.getColumnIndex(DATA_PRAZO))));
            prev.setSync(c.getInt(c.getColumnIndex(SYNC)));
            prev.setLatitude(c.getDouble(c.getColumnIndex(LATITUDE)));
            prev.setLongitude(c.getDouble(c.getColumnIndex(LONGITUDE)));

        }


        return prev;
    }


    public int updatePrevencao(Prevencao prev) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FOCO, prev.getFoco().getCodigo());
        values.put(DATA_EFETUADA, new DateUtils().DateToString(prev.getDataEfetuada()));
        values.put(DATA_PRAZO, new DateUtils().DateToString(prev.getDataPrazo()));
        values.put(DATA_CRIACAO, new DateUtils().DateToString(prev.getDataCriacao()));
        values.put(SYNC, prev.getSync());
        values.put(LATITUDE, prev.getLatitude());
        values.put(LONGITUDE, prev.getLongitude());

        return db.update(TABELA_PREVENCAO, values, ID_FOCO + " = ?",
                new String[]{String.valueOf(prev.getFoco().getCodigo())});
    }

}