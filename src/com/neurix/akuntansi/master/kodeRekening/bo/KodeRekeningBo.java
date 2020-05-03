package com.neurix.akuntansi.master.kodeRekening.bo;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface KodeRekeningBo extends BaseMasterBo<KodeRekening> {

    List<KodeRekening> typeaheadKodeRekening(String coa) throws GeneralBOException;
    List<KodeRekening> getPostByKodeRekening(String coa) throws GeneralBOException;
}
