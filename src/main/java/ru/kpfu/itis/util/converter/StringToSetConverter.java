package ru.kpfu.itis.util.converter;

import org.springframework.core.convert.converter.Converter;
import ru.kpfu.itis.model.LanguagesEleven;

import java.util.HashSet;
import java.util.Set;

public class StringToSetConverter implements Converter<String, Set<LanguagesEleven>> {
    @Override
    public Set<LanguagesEleven> convert(String s) {
        String result = s.replace("[","").replace("]","");
        String[] values = result.split(",");
        Set<LanguagesEleven> set = new HashSet();
        for  (String value : values) {
            set.add(new LanguagesEleven(value));
        }
        return set;
    }
}
