/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.backend.util;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

/**
 * Утилиты для работы с текстовым содержимым
 *
 * @author devel
 */
@UtilityClass
public class TextContentUtils {

    public static final String NON_ASCII_CHARACTERS = "[^\\x00-\\x7F]";

    public static final String ASCII_CONTROL_CHARACTERS = "[\\p{Cntrl}&&[^\r\n\t]]";

    public static final String NON_PRINTABLE_CHARACTERS = "\\p{C}";

    /**
     * Удаляет спецсимволы и непечатные символы
     *
     * @param text
     * @return
     */
    public String clean(String text) {
        text = text.replaceAll(NON_ASCII_CHARACTERS
                + "|" + ASCII_CONTROL_CHARACTERS
                + "|" + NON_PRINTABLE_CHARACTERS, "").trim();
        return text;
    }

    /**
     * Удаляет символ по коду
     * 
     * @param text
     * @param codePointValue
     * @return 
     */
    public String deleteCharByCodePoint(String text, int codePointValue) {
        text = text.codePoints()
                .filter(codePoint -> codePoint != codePointValue)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return text;
    }

    /**
     * Проверяет, содержит ли текст спецсимволы и непечатные символы
     *
     * @param text
     * @return true/false
     */
    public Boolean check(String text) {
        return Pattern.compile(NON_ASCII_CHARACTERS
                + "|" + ASCII_CONTROL_CHARACTERS
                + "|" + NON_PRINTABLE_CHARACTERS).matcher(text).find();
    }

}
