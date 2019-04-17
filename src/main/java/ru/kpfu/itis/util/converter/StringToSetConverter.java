package ru.kpfu.itis.util.converter;

import org.springframework.core.convert.converter.Converter;
import ru.kpfu.itis.model.LanguagesEleven;

import java.util.HashSet;
import java.util.Set;

public class StringToSetConverter implements Converter<String, Set<LanguagesEleven>> {
    @Override
    public Set<LanguagesEleven> convert(String s) {
        Set<LanguagesEleven> set = new HashSet();
        LanguagesEleven language = new LanguagesEleven();
        language.setLanguage(s);
        set.add(language);
        return set;
    }
}
