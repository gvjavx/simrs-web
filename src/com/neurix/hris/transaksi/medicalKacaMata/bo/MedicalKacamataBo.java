package com.neurix.hris.transaksi.medicalKacamata.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.medicalKacamata.model.MedicalKacamata;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface MedicalKacamataBo extends BaseMasterBo<MedicalKacamata>{
    public void saveAddData(MedicalKacamata medicalKacamata) throws GeneralBOException;
    public void saveEditData(MedicalKacamata medicalKacamata) throws GeneralBOException;
    public String cekMedical(String nip) throws GeneralBOException;
    public String cekMedical(String id, String nip) throws GeneralBOException;
    public MedicalKacamata getDataKacamataById(String kacamataId) throws GeneralBOException;
    public void saveDeleteKacamata(String id) throws GeneralBOException;
}
