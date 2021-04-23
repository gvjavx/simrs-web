package com.neurix.authorization.position.bo;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.bo.BaseMasterBo;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.transaksi.personilPosition.model.PersonilPosition;

import java.util.List;

/**
 * Created by Ferdi on 02/02/2015.
 */
public interface PositionBo extends BaseMasterBo<Position> {

    List<Position> getComboDivisiWithCriteria(String query) throws GeneralBOException;

    public Position getPositionById(String positionId, String flag) throws GeneralBOException;
    public List<Position> searchPosition2Sys(Position position) throws GeneralBOException;

    List<Position> searchPositionMutasi(Position position) throws GeneralBOException;

    List<Position> searchDivisi(Position position) throws GeneralBOException;

    public List<Position> searchPosition2Sys(String unitId) throws GeneralBOException;
    public List<Position> searchPositionBiodataSys(String divisiId) throws GeneralBOException;
    public List<Position> searchPositionBiodataSysHistory(String divisiId) throws GeneralBOException;

    public ImPosition getPositionEntityById(String id) throws GeneralBOException;
    public List<ImPosition> getPositionByString(String query) throws GeneralBOException;

    List<Position> typeAheadPosition(String key) throws GeneralBOException;

    List<Position> getComboBodBoc() throws GeneralBOException;
    public PersonilPosition getAndCheckJabatanTerpakai(String positionId, String branchId) throws GeneralBOException;

    public List<Position> getComboPositionWithCriteria(String query);

    public String cekStatus(String positionName, String flagCostUnit);

    public String cekStatusEdit(String positionName, String department, String bagian, String kelompok);
    public List<Position> getUnitCostByBagian(String bagianId) throws GeneralBOException;
    public Position getOnePositionByKodering(String kodering) throws GeneralBOException;
    public String sugestLastKoderingBySubbidId(String subBidId) throws GeneralBOException;
}