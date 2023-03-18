package org.example.thirdLess.tranliterator;

import java.util.HashMap;
import java.util.Map;

public class TransliteratorImpl implements Transliterator{

    private Map<Character, String> letters;

    public void setLetters(Map<Character, String> letters) {
        this.letters = letters;
        letters.put('А', "A");
        letters.put('Б', "B");
        letters.put('В', "V");
        letters.put('Г', "G");
        letters.put('Д', "D");
        letters.put('Е', "E");
        letters.put('Ё', "E");
        letters.put('Ж', "ZH");
        letters.put('З', "Z");
        letters.put('И', "I");
        letters.put('Й', "I");
        letters.put('К', "K");
        letters.put('Л', "L");
        letters.put('М', "M");
        letters.put('Н', "N");
        letters.put('О', "O");
        letters.put('П', "P");
        letters.put('Р', "R");
        letters.put('С', "S");
        letters.put('Т', "T");
        letters.put('У', "U");
        letters.put('Ф', "F");
        letters.put('Х', "KH");
        letters.put('Ц', "TS");
        letters.put('Ч', "CH");
        letters.put('Ш', "SH");
        letters.put('Щ', "SHCH");
        letters.put('Ы', "Y");
        letters.put('Ь', "");
        letters.put('Ъ', "IE");
        letters.put('Э', "E");
        letters.put('Ю', "IU");
        letters.put('Я', "IA");
    }

    public TransliteratorImpl() {
        letters = new HashMap<>();
        setLetters(letters);
    }

    @Override
    public String transliterate(String source) {
        int len = source.length();
        String result = source;
        for (int i = 0; i < len; i++) {
            if (letters.containsKey(source.charAt(i))) {
                result = result.replace(String.valueOf(source.charAt(i)), letters.get(source.charAt(i)));
            }
        }
        return result;
    }
}
