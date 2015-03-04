package br.com.eliminadengue.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alexandre on 04/03/2015.
 */
public class DateUtils {

    public String DateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    public Date StringToDate(String date){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date formatDate;
        try {
            formatDate = df.parse(date);
            return formatDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
