package com.neurix.simrs.master.pelayanan.bo.impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.position.bo.PositionBo;
import com.neurix.authorization.position.dao.PositionDao;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.strukturJabatan.model.StrukturJabatan;
import com.neurix.simrs.master.pelayanan.bo.PelayananBo;
import com.neurix.simrs.master.pelayanan.dao.PelayananDao;
import com.neurix.simrs.master.pelayanan.dao.PelayananSpesialisDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 12/11/2019.
 */
public class PelayananBoImpl implements PelayananBo{
    protected static transient Logger logger = Logger.getLogger(PelayananBoImpl.class);
    private PelayananDao pelayananDao;
    private PelayananSpesialisDao pelayananSpesialisDao;
    private PositionDao positionDao;
    private BranchDao branchDao;

    public void setPelayananDao(PelayananDao pelayananDao) {

        this.pelayananDao = pelayananDao;
    }

    public void setPelayananSpesialisDao(PelayananSpesialisDao pelayananSpesialisDao) {
        this.pelayananSpesialisDao = pelayananSpesialisDao;
    }

    public PelayananDao getPelayananDao() {
        return pelayananDao;
    }

    public PositionDao getPositionDao() {
        return positionDao;
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public BranchDao getBranchDao() {
        return branchDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<Pelayanan> getListAllPelayanan() throws GeneralBOException {
        logger.info("[pelayananBoImpl.getListAllPelayanan] Start >>>>>>");
        List<Pelayanan> result = new ArrayList<>();
        Map hsCriteria = new HashMap();
        hsCriteria.put("flag", "Y");

        List<ImSimrsPelayananEntity> imSimrsPelayananEntities = null;
        List<ImSimrsPoliSpesialisEntity> imSimrsPoliSpesialisEntities = null;
        try {
            imSimrsPelayananEntities = pelayananDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data "+e.getMessage());
        }


        try {
            imSimrsPoliSpesialisEntities = pelayananSpesialisDao.getByCriteria(hsCriteria);
        } catch (HibernateException e) {
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data "+e.getMessage());
        }

        if (!imSimrsPelayananEntities.isEmpty()){
            Pelayanan pelayanan;
            for (ImSimrsPelayananEntity listEntity : imSimrsPelayananEntities){
                pelayanan = new Pelayanan();
                pelayanan.setIdPelayanan(listEntity.getIdPelayanan());
                pelayanan.setNamaPelayanan(listEntity.getNamaPelayanan());
                result.add(pelayanan);
            }
        }
        logger.info("[pelayananBoImpl.getListAllPelayanan] End <<<<<<");
        return result;
    }

    @Override
    public List<Pelayanan> getListApotek(String branch, String tipeApotek) throws GeneralBOException {

        String tipe = "apotek%";
        if (!"".equalsIgnoreCase(tipeApotek) && tipeApotek != null){
            tipe = tipeApotek;
        }

        List<Pelayanan> listApotek = new ArrayList<>();
        try {
            listApotek =  pelayananDao.getListApotek(branch, tipe);
        }catch (HibernateException e){
            logger.error("[pelayananBoImpl.getListAllPelayanan] Error get pelayanan data apotek "+e.getMessage());
        }

        return listApotek;
    }

    @Override
    public void saveDelete(Pelayanan bean) throws GeneralBOException {

        logger.info("[saveDelete.PelayananBoImpl] start process >>>");

        if (bean!=null) {
            String status = cekBeforeDelete(bean.getIdPelayanan());
            if (!status.equalsIgnoreCase("exist")){
                String idPelayanan = bean.getIdPelayanan();

                ImSimrsPelayananEntity entity = null;

                try {
                    // Get data from database by ID
                    entity = pelayananDao.getById("idPelayanan", idPelayanan);
                } catch (HibernateException e) {
                    logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
                }

                if (entity != null) {

                    // Modify from bean to entity serializable
                    entity.setIdPelayanan(bean.getIdPelayanan());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Delete (Edit) into database
                        pelayananDao.updateAndSave(entity);
                    } catch (HibernateException e) {
                        logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                    }


                } else {
                    logger.error("[PayrollSkalaGajiBoImpl.saveDelete] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");

                }
            }else {
                throw new GeneralBOException("Maaf Data tidak dapat dihapus, karna masih digunakan pada data Transaksi");
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveDelete] end process <<<");

    }

    @Override
    public void saveEdit(Pelayanan bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String historyId = "";
            String koderingBranch, koderingPosition, seqKodering;
            String pelayananId = bean.getIdPelayanan();

            ImSimrsPelayananEntity imSimrsPelayananEntity = null;
            try {
                // Get data from database by ID
                imSimrsPelayananEntity = pelayananDao.getById("idPelayanan", pelayananId);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data PayrollSkalaGaji by Kode PayrollSkalaGaji, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsPelayananEntity.getNamaPelayanan().equalsIgnoreCase(bean.getNamaPelayanan())){
                String kode = imSimrsPelayananEntity.getKodering();
                if (kode != null){
                    String[] arrOfStr = kode.split("\\.");
                    seqKodering = arrOfStr[4];

                    Map map = new HashMap<>();
                    map.put("position_id", bean.getDivisiId());
                    koderingPosition = positionDao.getKodringPosition(map);
                    Map map1 = new HashMap<>();
                    map1.put("branch_id", bean.getBranchId());
                    koderingBranch = branchDao.getKodringBranches(map1);
                }else {
                    seqKodering = pelayananDao.getNextKodering();
                    Map map = new HashMap<>();
                    map.put("position_id", bean.getPositionId());
                    koderingPosition = positionDao.getKodringPosition(map);
                    Map map1 = new HashMap<>();
                    map1.put("branch_id", bean.getBranchId());
                    koderingBranch = branchDao.getKodringBranches(map1);
                }

                String kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;

                if (imSimrsPelayananEntity != null) {
                    imSimrsPelayananEntity.setIdPelayanan(bean.getIdPelayanan());
                    imSimrsPelayananEntity.setNamaPelayanan(bean.getNamaPelayanan());
                    imSimrsPelayananEntity.setIsEksekutif(bean.getIsEksekutif());
                    imSimrsPelayananEntity.setTipePelayanan(bean.getTipePelayanan());
                    imSimrsPelayananEntity.setBranchId(bean.getBranchId());
                    imSimrsPelayananEntity.setKodering(kodering);
                    imSimrsPelayananEntity.setDivisiId(bean.getDivisiId());
                    imSimrsPelayananEntity.setFlag(bean.getFlag());
                    imSimrsPelayananEntity.setAction(bean.getAction());
                    imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());

                    String flag;
                    try {
                        // Update into database
                        pelayananDao.updateAndSave(imSimrsPelayananEntity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                    }
                } else {
                    logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                    throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                }
            }else {
                String status = cekStatus(bean.getNamaPelayanan());
                if (!status.equalsIgnoreCase("exist")){
                    String kode = imSimrsPelayananEntity.getKodering();
                    if (kode != null){
                        String[] arrOfStr = kode.split("\\.");
                        seqKodering = arrOfStr[4];

                        Map map = new HashMap<>();
                        map.put("position_id", bean.getDivisiId());
                        koderingPosition = positionDao.getKodringPosition(map);
                        Map map1 = new HashMap<>();
                        map1.put("branch_id", bean.getBranchId());
                        koderingBranch = branchDao.getKodringBranches(map1);
                    }else {
                        seqKodering = pelayananDao.getNextKodering();
                        Map map = new HashMap<>();
                        map.put("position_id", bean.getPositionId());
                        koderingPosition = positionDao.getKodringPosition(map);
                        Map map1 = new HashMap<>();
                        map1.put("branch_id", bean.getBranchId());
                        koderingBranch = branchDao.getKodringBranches(map1);
                    }

                    String kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;

                    if (imSimrsPelayananEntity != null) {
                        imSimrsPelayananEntity.setIdPelayanan(bean.getIdPelayanan());
                        imSimrsPelayananEntity.setNamaPelayanan(bean.getNamaPelayanan());
                        imSimrsPelayananEntity.setIsEksekutif(bean.getIsEksekutif());
                        imSimrsPelayananEntity.setTipePelayanan(bean.getTipePelayanan());
                        imSimrsPelayananEntity.setBranchId(bean.getBranchId());
                        imSimrsPelayananEntity.setKodering(kodering);
                        imSimrsPelayananEntity.setDivisiId(bean.getDivisiId());
                        imSimrsPelayananEntity.setFlag(bean.getFlag());
                        imSimrsPelayananEntity.setAction(bean.getAction());
                        imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                        imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());

                        String flag;
                        try {
                            // Update into database
                            pelayananDao.updateAndSave(imSimrsPelayananEntity);
                            //payrollSkalaGajiDao.addAndSaveHistory(imPayrollSkalaGajiHistoryEntity);
                        } catch (HibernateException e) {
                            logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, " + e.getMessage());
                            throw new GeneralBOException("Found problem when saving update data PayrollSkalaGaji, please info to your admin..." + e.getMessage());
                        }
                    } else {
                        logger.error("[PayrollSkalaGajiBoImpl.saveEdit] Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                        throw new GeneralBOException("Error, not found data PayrollSkalaGaji with request id, please check again your data ...");
                    }
                }else {
                    throw new GeneralBOException("Maaf Data dengan Nama Pelayanan Tersebut Sudah Ada");
                }
            }
        }
        logger.info("[PayrollSkalaGajiBoImpl.saveEdit] end process <<<");
    }

    @Override
    public Pelayanan saveAdd(Pelayanan bean) throws GeneralBOException {
        logger.info("[PayrollSkalaGajiBoImpl.saveAdd] start process >>>");
        if (bean!=null) {
            String status = cekStatus(bean.getNamaPelayanan());
            String pelayananId, seqKodering;
            if (!status.equalsIgnoreCase("exist")){
                try {
                    // Generating ID, get from postgre sequence
                    pelayananId = pelayananDao.getNextPelayananId();
                    seqKodering = pelayananDao.getNextKodering();
                } catch (HibernateException e) {
                    logger.error("[PelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence payrollSkalaGajiId id, please info to your admin..." + e.getMessage());
                }
                Map map = new HashMap<>();
                map.put("position_id", bean.getPositionId());
                String koderingPosition = positionDao.getKodringPosition(map);
                Map map1 = new HashMap<>();
                map1.put("branch_id", bean.getBranchId());
                String koderingBranch = branchDao.getKodringBranches(map1);

                String kodering = koderingBranch+"."+koderingPosition+"."+seqKodering;

                // creating object entity serializable
                ImSimrsPelayananEntity imSimrsPelayananEntity = new ImSimrsPelayananEntity();

                imSimrsPelayananEntity.setIdPelayanan(pelayananId);
                imSimrsPelayananEntity.setNamaPelayanan(bean.getNamaPelayanan());
                imSimrsPelayananEntity.setIsEksekutif(bean.getIsEksekutif());
                imSimrsPelayananEntity.setTipePelayanan(bean.getTipePelayanan());
                imSimrsPelayananEntity.setBranchId(bean.getBranchId());
                imSimrsPelayananEntity.setDivisiId(bean.getPositionId());
                imSimrsPelayananEntity.setKodering(kodering);
                imSimrsPelayananEntity.setFlag(bean.getFlag());
                imSimrsPelayananEntity.setAction(bean.getAction());
                imSimrsPelayananEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsPelayananEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsPelayananEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsPelayananEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // insert into database
                    pelayananDao.addAndSave(imSimrsPelayananEntity);
                } catch (HibernateException e) {
                    logger.error("[PelayananBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data PelayananGaji, please info to your admin..." + e.getMessage());
                }
            }else{
                throw new GeneralBOException("Maaf Data dengan Nama Pelayanan Tersebut Sudah Ada");
            }
        }

        logger.info("[PelayananBoImpl.saveAdd] end process <<<");
        return null;
    }

    @Override
    public List<Pelayanan> getByCriteria(Pelayanan bean) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        List<Pelayanan> result = new ArrayList<>();

        if(bean != null){

            Map hsCriteria = new HashMap();

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())){
                hsCriteria.put("id_pelayanan", bean.getIdPelayanan());
            }
            if (bean.getNamaPelayanan() != null && !"".equalsIgnoreCase(bean.getNamaPelayanan())){
                hsCriteria.put("nama_pelayanan", bean.getNamaPelayanan());
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                hsCriteria.put("branch_id", bean.getBranchId());
            }
            if (bean.getPositionId() != null && !"".equalsIgnoreCase(bean.getPositionId())){
                hsCriteria.put("divisi_id", bean.getPositionId());
            }
            if (bean.getTipePelayanan() != null && !"".equalsIgnoreCase(bean.getTipePelayanan())){
                hsCriteria.put("tipe_pelayanan", bean.getTipePelayanan());
            }

            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                if ("N".equalsIgnoreCase(bean.getFlag())) {
                    hsCriteria.put("flag", "N");
                } else {
                    hsCriteria.put("flag", bean.getFlag());
                }
            } else {
                hsCriteria.put("flag", "Y");
            }

            if (bean.getNotOwnBranch() != null){
                hsCriteria.put("not_own_branch", bean.getNotOwnBranch());
            }



            List<ImSimrsPelayananEntity> entityList = new ArrayList<>();

            try {
                entityList = pelayananDao.getByCriteria(hsCriteria);
            } catch (HibernateException e){
                logger.error("[PelayananBoImpl.getByCriteria] Error get pelayanan data "+e.getMessage());
            }

            if (!entityList.isEmpty()){
                Pelayanan pelayanan;
                for (ImSimrsPelayananEntity entity : entityList){
                    pelayanan = new Pelayanan();
                    pelayanan.setIdPelayanan(entity.getIdPelayanan());
                    pelayanan.setNamaPelayanan(entity.getNamaPelayanan());
                    pelayanan.setAction(entity.getAction());
                    pelayanan.setFlag(entity.getFlag());
                    pelayanan.setCreatedDate(entity.getCreatedDate());
                    pelayanan.setStCreatedDate(entity.getCreatedDate().toString());
                    pelayanan.setCreatedWho(entity.getCreatedWho());
                    pelayanan.setStLastUpdate(entity.getLastUpdate().toString());
                    pelayanan.setLastUpdate(entity.getLastUpdate());
                    pelayanan.setLastUpdateWho(entity.getLastUpdateWho());
                    if (entity.getTipePelayanan() != null){
                        pelayanan.setTipePelayanan(entity.getTipePelayanan());
                    }
                    pelayanan.setBranchId(entity.getBranchId());
                    pelayanan.setTipePelayanan(entity.getTipePelayanan());
                    pelayanan.setKodering(entity.getKodering());
                    if (entity.getDivisiId() != null){
                        pelayanan.setDivisiId(entity.getDivisiId());
                    }
                    pelayanan.setIsEksekutif(entity.getIsEksekutif());

                    ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
                    if (entity.getBranchId() != null){
                        Branch branch = new Branch();
                        BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                        branch.setBranchId(entity.getBranchId());
                        branch.setFlag("Y");
                        List<Branch> branches = branchBo.getByCriteria(branch);
                        if(branches.size() > 0){
                            String branchName = branches.get(0).getBranchName();
                            pelayanan.setBranchName(branchName);
                        }
                    }

                    if (entity.getDivisiId() != null){
                        Position position = new Position();
                        PositionBo positionBo = (PositionBo) context.getBean("positionBoProxy");
                        position.setPositionId(entity.getDivisiId());
                        position.setFlag("Y");
                        List<Position> positions = positionBo.getByCriteria(position);
                        if(positions.size() > 0 ){
                            String positionName = positions.get(0).getPositionName();
                            pelayanan.setDivisiName(positionName);
                        }
                    }

                    pelayanan.setKategoriPelayanan(entity.getKategoriPelayanan());
                    result.add(pelayanan);
                }
            }
        }

        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return result;
    }

    @Override
    public List<Pelayanan> getAll() throws GeneralBOException {

        return null;
    }

    @Override
    public ImSimrsPelayananEntity getPelayananById(String id) throws GeneralBOException {
        logger.info("[PelayananBoImpl.getByCriteria] Start >>>>>>");
        logger.info("[PelayananBoImpl.getByCriteria] End <<<<<<");
        return pelayananDao.getById("idPelayanan", id);
    }

    @Override
    public ImSimrsPelayananEntity getPelayananByDivisiId(String id,String branchId) throws GeneralBOException {
        ImSimrsPelayananEntity result = new ImSimrsPelayananEntity();
        Map criteria = new HashMap();
        criteria.put("branch_id",branchId);
        criteria.put("divisi_id",id);
        criteria.put("flag","Y");

        List<ImSimrsPelayananEntity> pelayananEntityList = pelayananDao.getByCriteria(criteria);

        for (ImSimrsPelayananEntity pelayananEntity : pelayananEntityList){
            result = pelayananEntity;
        }
        return result;
    }

    @Override
    public List<Pelayanan> getListPelayananPaketPeriksa(String branch) throws GeneralBOException {
        List<Pelayanan> pelayananList = new ArrayList<>();
        if(branch != null){
            try {
                pelayananList = pelayananDao.getListPelayananPaket(branch);
            }catch (HibernateException e){
                logger.error("Found Error "+e.getMessage());
            }
        }
        return pelayananList;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    public String cekStatus(String namaPelayanan)throws GeneralBOException{
        String status ="";
        List<ImSimrsPelayananEntity> entities = new ArrayList<>();
        try {
            entities = pelayananDao.getDataPelayanan(namaPelayanan);
        } catch (HibernateException e) {
            logger.error("[PelayananBoImpl.cekStatus] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idPelayanan)throws GeneralBOException{
        String status ="";
        List<ImSimrsPelayananEntity> entities = new ArrayList<>();
        try {
            entities = pelayananDao.cekData(idPelayanan);
        } catch (HibernateException e) {
            logger.error("[PelayananBoImpl.cekBeforeDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size()>0){
            status = "exist";
        }else{
            status="notExits";
        }
        return status;
    }

    @Override
    public List<Pelayanan> getListPelayananFarmasi(String branchId) throws GeneralBOException {
        return pelayananDao.getListPelayananFarmasi(branchId);
    }

    @Override
    public List<Pelayanan> getListPelayananWithLab(String tipe) throws GeneralBOException {
        return pelayananDao.getListPelayananWithLab(tipe);
    }
    @Override
    public List<ImSimrsPelayananEntity> getByCriteria(Map criteria) throws GeneralBOException {
        return pelayananDao.getByCriteria(criteria);
    }

    @Override
    public List<ImSimrsPelayananEntity> getPelayananByBranch(String branchId) throws GeneralBOException {
        return pelayananDao.getPelayananByBranch(branchId);
    }
}
