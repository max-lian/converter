package yakovlev.m.converter.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UkrainianToEnglishTransliterator implements Transliterator {
    private final Map<String, String> letters = new HashMap<String, String>();
    {
        letters.put("А", "A");
        letters.put("Б", "B");
        letters.put("В", "V");
        letters.put("Г", "H");
        letters.put("Ґ", "G");
        letters.put("Д", "D");
        letters.put("Е", "E");
        letters.put("Є", "Ye");
        letters.put("Ж", "Zh");
        letters.put("З", "Z");
        letters.put("И", "Y");
        letters.put("І", "I");
        letters.put("Ї", "Yi");
        letters.put("Й", "Y");
        letters.put("К", "K");
        letters.put("Л", "L");
        letters.put("М", "M");
        letters.put("Н", "N");
        letters.put("О", "O");
        letters.put("П", "P");
        letters.put("Р", "R");
        letters.put("С", "S");
        letters.put("Т", "T");
        letters.put("У", "U");
        letters.put("Ф", "F");
        letters.put("Х", "Kh");
        letters.put("Ц", "Ts");
        letters.put("Ч", "Ch");
        letters.put("Ш", "Sh");
        letters.put("Щ", "Shch");
        letters.put("Ю", "Yu");
        letters.put("Я", "Ya");
        letters.put("а", "a");
        letters.put("б", "b");
        letters.put("в", "v");
        letters.put("г", "h");
        letters.put("ґ", "g");
        letters.put("д", "d");
        letters.put("е", "e");
        letters.put("є", "ie");
        letters.put("ж", "zh");
        letters.put("з", "z");
        letters.put("и", "y");
        letters.put("і", "i");
        letters.put("ї", "i");
        letters.put("й", "i");
        letters.put("к", "k");
        letters.put("л", "l");
        letters.put("м", "m");
        letters.put("н", "n");
        letters.put("о", "o");
        letters.put("п", "p");
        letters.put("р", "r");
        letters.put("с", "s");
        letters.put("т", "t");
        letters.put("у", "u");
        letters.put("ф", "f");
        letters.put("х", "kh");
        letters.put("ц", "ts");
        letters.put("ч", "ch");
        letters.put("ш", "sh");
        letters.put("щ", "shch");
        letters.put("ю", "iu");
        letters.put("я", "ia");
    }

    public String translitOneWord(String text) {
        text.replace("зг", "zgh");
        text.replace("Зг", "Zgh");
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i<text.length(); i++) {
            String l = text.substring(i, i+1);
            if (letters.containsKey(l)) {
                if(!l.equals("'") && !l.equals("ь")) sb.append(letters.get(l));
            }
            else {
                sb.append(l);
            }
        }
        return sb.toString();
    }
}
