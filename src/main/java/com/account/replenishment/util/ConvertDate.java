package com.account.replenishment.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 14.07.2016
 */
@Component
public class ConvertDate {
    /**
     * Method convert string to date (if string have a right format)
     * or return default time ['January 1, 1970, 00:00:00 GMT','NOW']
     */
    public Date[] convertStringToDate(String dateStartStr, String dateEndStr) {
        Date dateStart = new Date(0L);
        Date dateEnd = new Date();
        if (dateStartStr != null & dateEndStr != null & dateStartStr != "" & dateEndStr != "") {
            dateStart = dateCheck(dateStartStr);
            dateEnd = dateCheck(dateEndStr);
        } else if (dateStartStr != null & dateStartStr != "") {
            dateStart = dateCheck(dateStartStr);
        } else if (dateStartStr != null & dateEndStr != "") {
            dateEnd = dateCheck(dateEndStr);
        }
        return new Date[]{dateStart, dateEnd};
    }

    private Date dateCheck(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCheck = new Date();
        try {
            dateCheck = sdf.parse(date);
        } catch (ParseException e) {
            System.err.println(e + "::Parsing exception");
        }
        return dateCheck;
    }
}
