package com.web.auction.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component    //@Service  @Controller
public class DataConverter implements Converter<String, Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String time) {
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
