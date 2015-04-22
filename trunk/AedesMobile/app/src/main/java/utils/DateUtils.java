package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alexandre on 04/03/2015.
 */
public class DateUtils {

    public String DateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        if(date != null) {
            return dateFormat.format(date);
        }
        return "";
    }

    public Date StringToDate(String date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formatDate;
        try {
            formatDate = df.parse(date);
            return formatDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String DateViewFormatted(Date date){
        String data = "";
        String hora = "";
        SimpleDateFormat formatoData = new SimpleDateFormat(
                "dd/MM/yyyy", Locale.getDefault());

        SimpleDateFormat formatoHora = new SimpleDateFormat(
                "HH:mm", Locale.getDefault());
        if(date != null) {
            data =  formatoData.format(date);
            hora = formatoHora.format(date);
        }
        return data + " às " + hora;
    }


    public boolean validaDtPrazo(Date dt){
        Calendar dtAtual = Calendar.getInstance();
        Calendar dtPrazo = Calendar.getInstance();
        dt.setHours(dtAtual.getTime().getHours() + 1);
        dt.setMinutes(dtAtual.getTime().getMinutes());
        dt.setSeconds(dtAtual.getTime().getSeconds());
        dtPrazo.setTime(dt);

        if(dtPrazo.getTime().before(dtAtual.getTime())){
            return false;
        }

        return true;

    }

    public boolean validaHrPrazo(Date dt){
        Calendar dtAtual = Calendar.getInstance();
        Calendar dtPrazo = Calendar.getInstance();
        dtPrazo.setTime(dt);
        if(dtPrazo.getTime().before(dtAtual.getTime())){
            return false;
        }

        return true;
    }

    public String convertMesView(Date dt){
        switch (dt.getMonth() + 1){

            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "Erro";


        }


    }






}
