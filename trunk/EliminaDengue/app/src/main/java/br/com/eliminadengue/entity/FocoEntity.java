package br.com.eliminadengue.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.eliminadengue.R;
import br.com.eliminadengue.bean.Foco;
import br.com.eliminadengue.entity.db.EliminaDengueDb;

/**
 * Created by Alexandre on 22/02/2015.
 */
public class FocoEntity extends EliminaDengueDb {

    //  private static final String TABELA_FOCO = "foco";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String COMOLIMPAR = "comolimpar";
    private static final String ICONE = "icone";
    private static final String PRAZO = "prazo";



    public FocoEntity(Context context) {
        super(context);
    }


    public String createFocoTable() {
        String CREATE_TABLE_FOCO = "CREATE TABLE " + TABELA_FOCO + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOME + " TEXT,"
                + COMOLIMPAR + " TEXT, " + ICONE + " INTEGER," + PRAZO + " INTEGER" + ");";
        //db.execSQL(CREATE_TABLE_FOCO);
        return CREATE_TABLE_FOCO;
    }

    public String[] DmlFocos(){
        String[] DMLFocos = {"INSERT INTO "
                + TABELA_FOCO
                + "("
                + NOME
                + ", "
                + COMOLIMPAR
                + ", "
                + PRAZO
                + ", "
                + ICONE
                + ")"
                + "VALUES('Vasos (Flores e Plantas)', 'Use água, esponja e sabão para limpeza. Deposite areia na vasilha sob o vaso a cada limpeza.', 0, "
                + R.drawable.vazos_plantas + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Ralos', 'Limpe-os jogando água sanitária. Mantenha-os vedados caso não os utilize.', 0, "
                        + R.drawable.ralos + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Recipientes para Armazenamento de Água', 'Use água, esponja e sabão para limpeza. (Jarras, garrafas, potes e baldes).', 7, "
                        + R.drawable.recipientes_armazenamento_agua + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Caixa d’água', 'Use água, sabão e água sanitária para a limpeza. Mantenha a mesma tampada durante todo o tempo.', 180, "
                        + R.drawable.caixa_dagua + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Bebedouros de Animais', 'Use água, esponja e sabão para limpeza. Mantenha-os limpos, trocando a água diariamente.', 1, "
                        + R.drawable.bebedouros_animais + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Piscinas', 'Use água, sabão e água sanitária para limpeza. O cloro deve estar sempre no nível adequado, tratando a água. Manter coberta, caso não esteja utilizando.', 15, "
                        + R.drawable.piscina + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Calhas', 'Remova tudo que possa impedir a passagem da água.', 3, "
                        + R.drawable.calhas + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Bromélias (Planta)', 'Dilua uma colher (sopa) de água sanitária em 1 litro de água limpa e regue-as. Eliminá-las poderia afetar o equilíbrio ecológico, portanto, o melhor é preservar de maneira correta.', 3, "
                        + R.drawable.plantas + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Pneus Velhos', 'Mantenha-os em lugar coberto ou fure-os. Caso queira descartá-los, separe-os para serem levados pela coleta de lixo. Nunca os jogue em terrenos baldios.', 7, "
                        + R.drawable.pneus + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Sacos de Lixo', 'Deve ser bem fechado e DIARIAMENTE depositado no lugar adequado para ser recolhidos pela coleta de lixo.', 1, "
                        + R.drawable.saco_lixo + ");",
                "INSERT INTO "
                        + TABELA_FOCO
                        + "("
                        + NOME
                        + ", "
                        + COMOLIMPAR
                        + ", "
                        + PRAZO
                        + ", "
                        + ICONE
                        + ")"
                        + "VALUES('Geladeiras', 'A cafeína é um método natural, não prejudicial e fatal contra a dengue. Deposite na gaveta, abaixo, no exterior da geladeira, 4 colheres de sopa de borra de café, para cada 300 ml de água. ', 1, "
                        + R.drawable.geladeira + ");"};
        return DMLFocos;
    }

    public int getFocoCount(){
        String selectQuery = "SELECT * FROM " + TABELA_FOCO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        return c.getCount();
    }


 /*   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_FOCO);
        onCreate(db);
    }*/


    public void addFoco(Foco foco) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, foco.getNome());
        values.put(COMOLIMPAR, foco.getComoLimpar());
        values.put(ICONE, foco.getIcone());
        values.put(PRAZO, foco.getPrazo());
        // Inserting Row
        db.insert(TABELA_FOCO, null, values);
        db.close();
    }

    public Foco getFoco(int idFoco) {
        Foco f = new Foco();
        f.setCodigo(-1);

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABELA_FOCO + " WHERE id = " + idFoco;
       // String selectQuery = "SELECT * FROM foco";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.getCount() > 0) {
            c.moveToFirst();

            f.setCodigo(c.getInt(c.getColumnIndex(ID)));
            f.setNome((c.getString(c.getColumnIndex(NOME))));
            f.setComoLimpar((c.getString(c.getColumnIndex(COMOLIMPAR))));
            f.setPrazo((c.getInt((c.getColumnIndex(PRAZO)))));
            f.setIcone(c.getInt(c.getColumnIndex(ICONE)));
        }


        return f;
    }


    public int updateFoco(Foco foco) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, foco.getNome());
        values.put(COMOLIMPAR, foco.getComoLimpar());
        values.put(ICONE, foco.getIcone());
        values.put(PRAZO, foco.getPrazo());

        return db.update(TABELA_FOCO, values, ID + " = ?",
                new String[]{String.valueOf(foco.getCodigo())});
    }

}