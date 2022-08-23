package com.example.farmingproject.service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface GeneralContent {

    default void setPdfParams(HttpServletResponse response, String name) {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateFormat = dateFormatter.format(new java.util.Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + name + "_" + currentDateFormat + ".pdf";
        response.setHeader(headerKey, headerValue);
    }
}
