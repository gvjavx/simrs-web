package com.neurix.simrs.master.obatgejala.bo;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obatgejala.model.ObatGejala;

import java.util.List;

/**
 * Created by Toshiba on 03/12/2019.
 */
public interface ObatGejalaBo {
    public List<ObatGejala> getByCriteria(ObatGejala bean) throws GeneralBOException;
    public void saveAdd(ObatGejala bean) throws GeneralBOException;
    public void saveEdit(ObatGejala bean) throws GeneralBOException;
}
