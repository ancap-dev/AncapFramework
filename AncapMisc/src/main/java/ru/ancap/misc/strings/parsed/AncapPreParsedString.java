package ru.ancap.misc.strings.parsed;

import java.util.HashMap;

public class AncapPreParsedString implements PreParsedString {

    private String string;

    public AncapPreParsedString(String string) {
        this.string = string;
    }

    public ParsedString parse(String valueDesignator, String separator) {
        String[] datas = this.string.split(separator);
        HashMap<String, String> parsed = new HashMap<>();
        for (String data : datas) {
            String[] separatedData = data.split(valueDesignator);
            parsed.put(separatedData[0], separatedData[1]);
        }
        return new AncapParsedString(parsed);
    }
}
