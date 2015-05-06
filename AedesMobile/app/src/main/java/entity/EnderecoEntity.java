package entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bean.Endereco;
import entity.db.EliminaDengueDb;

/**
 * Created by Alexandre on 04/05/2015.
 */
public class EnderecoEntity extends EliminaDengueDb {

    private static final String BAIRRO = "bairro";
    private static final String CIDADE = "cidade";
    private static final String ESTADO = "estado";


    public EnderecoEntity(Context context) {
        super(context);
    }


    public String createEnderecoTable() {
        String CREATE_TABLE_FOCO = "CREATE TABLE " + TABELA_ENDERECO + "("
                + BAIRRO + " TEXT, " + CIDADE + " TEXT," + ESTADO + " TEXT);";

        //db.execSQL(CREATE_TABLE_FOCO);
        return CREATE_TABLE_FOCO;
    }

    public int getFocoCount() {
        String selectQuery = "SELECT * FROM " + TABELA_FOCO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        return c.getCount();
    }


    public void addEndereco(Endereco endereco) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BAIRRO, endereco.getBairro());
        values.put(CIDADE, endereco.getCidade());
        values.put(ESTADO, endereco.getEstado());
        // Inserting Row
        db.insert(TABELA_ENDERECO, null, values);
        db.close();
    }

    public Endereco getEndereco() {
        Endereco e = new Endereco();


        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABELA_ENDERECO;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            e.setBairro(c.getString(c.getColumnIndex(BAIRRO)));
            e.setBairro(c.getString(c.getColumnIndex(CIDADE)));
            e.setBairro(c.getString(c.getColumnIndex(ESTADO)));
        }else{
            e = null;
        }


        return e;
    }


    public int updateEndereco(Endereco endereco) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BAIRRO, endereco.getBairro());
        values.put(CIDADE, endereco.getCidade());
        values.put(ESTADO, endereco.getEstado());

        return db.update(TABELA_ENDERECO, values,null,null);
    }


}