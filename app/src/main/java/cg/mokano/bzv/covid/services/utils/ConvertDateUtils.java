package cg.mokano.bzv.covid.services.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConvertDateUtils {
    String convertLongToString(long date){
        String dateString = DateFormat
                .format("dd/MM/yyyy", new Date(date)).toString();

        return dateString;
    }

    public static Date convertirDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Date getDateSystem(){
        return Calendar.getInstance().getTime();
    }

    public static String convertToDate(long dateTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = new Date(dateTime);

        return dateFormat.format(date);
    }
}
