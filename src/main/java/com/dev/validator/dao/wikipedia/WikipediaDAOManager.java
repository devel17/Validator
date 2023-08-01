/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.dao.wikipedia;

import com.dev.validator.dao.BaseRepository;
import com.dev.validator.dao.DAOManager;
import com.dev.validator.model.CountryCode;

/**
 * Реализация Data Access Object (чтение с сайта wikipedia.org , если возникнет такая необходимость)
 *
 * @author devel
 */
public abstract class WikipediaDAOManager implements DAOManager {

    @Override
    public BaseRepository<CountryCode, Long> getCountryCodeRepository() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
