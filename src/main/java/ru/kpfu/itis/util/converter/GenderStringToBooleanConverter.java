package ru.kpfu.itis.util.converter;

import org.springframework.core.convert.converter.Converter;

public class GenderStringToBooleanConverter implements Converter<String,Boolean> {
    @Override
    public Boolean convert(String s) {
        return s.equals("Male");
    }
}
