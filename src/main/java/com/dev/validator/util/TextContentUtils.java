/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.util;

import lombok.experimental.UtilityClass;

/**
 * Утилиты для работы с текстовым содержимым
 *
 * @author devel
 */
@UtilityClass
public class TextContentUtils {

    /**
     * Обрезает, очищает, удаляет спецсимволы и непечатные символы
     *
     * @param text
     * @return
     */
    public String clean(String text) {
        // strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", "");

        // erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", "");

        return text.trim();
    }

}
