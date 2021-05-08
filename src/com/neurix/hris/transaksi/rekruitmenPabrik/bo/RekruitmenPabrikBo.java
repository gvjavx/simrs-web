package com.neurix.hris.transaksi.rekruitmenPabrik.bo;

import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.notifikasi.model.Notifikasi;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrik;
import com.neurix.hris.transaksi.rekruitmenPabrik.model.RekruitmenPabrikDetail;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public interface RekruitmenPabrikBo extends BaseMasterBo<RekruitmenPabrik>{
    List<RekruitmenPabrikDetail> getByCriteriaDetail(RekruitmenPabrik searchBean);

    List<PersonilPosition> getByCriteriaHistoriPegawai(PersonilPosition searchBean) throws GeneralBOException;

    List<RekruitmenPabrikDetail> getByCriteriaDetail(RekruitmenPabrikDetail searchRekruitmenPabrikDetail);

    List<RekruitmenPabrikDetail> getComboRekruitmenPabrikPerson2(String nip) throws GeneralBOException;

    List<RekruitmenPabrikDetail> getComboRekruitmenPabrikPerson3(String nip, String rpdId) throws GeneralBOException;

    List<RekruitmenPabrikDetail> editRekruitmenPabrikPerson(String nip, String posisiBaru) throws GeneralBOException;

    void saveClosedRekruitmenPabrik(String id) throws GeneralBOException;

    List<RekruitmenPabrikDetail> hapusRekruitmenPabrikPerson(String nip) throws GeneralBOException;

    List<Notifikasi> approveRekruitmenPabrikPerson(String divisi, String rekId, String approveWho, String approvalFlag) throws GeneralBOException;

    void approveRekruitmenPabrikPersonGm(String rekId, String approveWho, String approvalFlag) throws GeneralBOException;

    void saveApprove(RekruitmenPabrikDetail bean) throws GeneralBOException;

    RekruitmenPabrik saveAdd(RekruitmenPabrikDetail bean2) throws GeneralBOException;

    RekruitmenPabrik saveEdit(RekruitmenPabrik bean, RekruitmenPabrikDetail bean2) throws GeneralBOException;
}
