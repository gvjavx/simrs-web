package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.bo;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.master.master.model.Master;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.PendaftaranJasa;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;

import java.util.List;

public interface PendaftaranJasaRekananBo {
    public List<PendaftaranJasa> getSearchByCriteria(PendaftaranJasa bean) throws GeneralBOException;
    public void saveAdd(PendaftaranJasa bean) throws GeneralBOException;
    public void saveEdit(PendaftaranJasa bean) throws GeneralBOException;
    public void saveApprove(PendaftaranJasa bean) throws GeneralBOException;
    public KodeRekening getKodeRekeningPropsByKodeRekening(String kodering) throws GeneralBOException;
    public Position getPositionPropsByKodering(String kodering) throws GeneralBOException;
    public List<KodeRekening> getListKodeRekeningBebanJasa() throws GeneralBOException;
    public List<KodeRekening> getListKodeRekeningSetaraKas() throws GeneralBOException;
    public List<Position> getListPosition() throws GeneralBOException;
    public List<Master> getListMaster() throws GeneralBOException;
}
