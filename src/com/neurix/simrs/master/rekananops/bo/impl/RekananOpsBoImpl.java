package com.neurix.simrs.master.rekananops.bo.impl;

import com.neurix.akuntansi.master.master.dao.MasterDao;
import com.neurix.akuntansi.master.master.model.ImMasterEntity;
import com.neurix.authorization.company.dao.BranchDao;
import com.neurix.authorization.company.model.Branch;
import com.neurix.authorization.company.model.ImBranches;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.rekananops.bo.RekananOpsBo;
import com.neurix.simrs.master.rekananops.dao.DetailRekananOpsDao;
import com.neurix.simrs.master.rekananops.dao.RekananOpsDao;
import com.neurix.simrs.master.rekananops.model.DetailRekananOps;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import com.neurix.simrs.master.rekananops.model.RekananOps;
import com.neurix.simrs.master.tindakan.model.ImSimrsKategoriTindakanPelayananEntity;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RekananOpsBoImpl implements RekananOpsBo {
    protected static transient Logger logger = Logger.getLogger(RekananOpsBoImpl.class);
    private RekananOpsDao rekananOpsDao;
    private DetailRekananOpsDao detailRekananOpsDao;
    private BranchDao branchDao;
    private MasterDao masterDao;

    public MasterDao getMasterDao() {
        return masterDao;
    }

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    public void setDetailRekananOpsDao(DetailRekananOpsDao detailRekananOpsDao) {
        this.detailRekananOpsDao = detailRekananOpsDao;
    }

    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<RekananOps> getByCriteria(RekananOps bean) throws GeneralBOException {
        logger.info("[RekananOpsBoImpl.getByCriteria] Start >>>>>>");
        List<RekananOps> listOfResultRekananOps = new ArrayList<>();
        if(bean != null){
            Map hsCriteria = new HashMap();
            if (bean.getIdRekananOps() != null && !"".equalsIgnoreCase(bean.getIdRekananOps())) {
                hsCriteria.put("id_rekanan_ops", bean.getIdRekananOps());
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

            List<ImSimrsRekananOpsEntity> imSimrsRekananOpsEntities = null;
            try {
                imSimrsRekananOpsEntities = rekananOpsDao.getByCriteria(hsCriteria);
            } catch (HibernateException e) {
                logger.error("[RekananOpsBoImpl.getByCriteria] Error get ruangan data " + e.getMessage());
            }

            if (imSimrsRekananOpsEntities.size() > 0) {
                for (ImSimrsRekananOpsEntity listEntity : imSimrsRekananOpsEntities) {
                    RekananOps rekananOps = new RekananOps();
                    rekananOps.setIdRekananOps(listEntity.getIdRekananOps());
                    rekananOps.setNomorMaster(listEntity.getNomorMaster());
                    rekananOps.setNamaRekanan(listEntity.getNamaRekanan());

                    rekananOps.setAction(listEntity.getAction());
                    rekananOps.setFlag(listEntity.getFlag());
                    rekananOps.setCreatedDate(listEntity.getCreatedDate());
                    rekananOps.setCreatedWho(listEntity.getCreatedWho());
                    rekananOps.setLastUpdate(listEntity.getLastUpdate());
                    rekananOps.setLastUpdateWho(listEntity.getLastUpdateWho());
                    rekananOps.setTipe(listEntity.getTipe());
                    listOfResultRekananOps.add(rekananOps);
                }
            }
        }
        logger.info("[RekananOpsBoImpl.getByCriteria] End <<<<<<");
        return listOfResultRekananOps;

    }

    @Override
    public List<DetailRekananOps> getSearchByCriteria(RekananOps bean) throws GeneralBOException {
        return null;
    }

    @Override
    public RekananOps saveAdd(RekananOps bean) throws GeneralBOException{
        RekananOps rekananOps = new RekananOps();
        if (bean!=null) {
            List<ImSimrsRekananOpsEntity> cekList = new ArrayList<>();
            try {
                cekList = rekananOpsDao.getRekananOps(bean.getNomorMaster());
            }catch (HibernateException e){
                logger.error(e.getMessage());
            }
            if(cekList.size() > 0){
                throw new GeneralBOException("Nama Rekanan Ops sudah ada...!");
            }else{
                String rekananId;
                try {
                    // Generating ID, get from postgre sequence
                    rekananId = rekananOpsDao.getNextId();
                } catch (HibernateException e) {
                    logger.error("[rekananOpsDaoBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when getting sequence Asuransi id, please info to your admin..." + e.getMessage());
                }
                // mengambil nama master / nama asuransi dari masterdao berdasarkan no master;
                ImMasterEntity masterEntity = new ImMasterEntity();
                try {
                    masterEntity = masterDao.getById("primaryKey.nomorMaster", bean.getNomorMaster());
                }catch (HibernateException e){
                    logger.error("Found Error when search nomorMaster "+e.getMessage());
                }
                // creating object entity serializable
                ImSimrsRekananOpsEntity imSimrsRekananOpsEntity = new ImSimrsRekananOpsEntity();
                imSimrsRekananOpsEntity.setIdRekananOps(rekananId);
                //cari ke dao akun Master berdasarkan no master
                imSimrsRekananOpsEntity.setNomorMaster(bean.getNomorMaster());

                imSimrsRekananOpsEntity.setFlag(bean.getFlag());
                imSimrsRekananOpsEntity.setAction(bean.getAction());
                imSimrsRekananOpsEntity.setCreatedWho(bean.getCreatedWho());
                imSimrsRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsRekananOpsEntity.setCreatedDate(bean.getCreatedDate());
                imSimrsRekananOpsEntity.setLastUpdate(bean.getLastUpdate());
                if (masterEntity != null){
                    imSimrsRekananOpsEntity.setNamaRekanan(masterEntity.getNama());
                    //set berdasarkan kebutuhan fild yang di ambil dari tabel lain
                }
                try {
                    // insert into database
                    rekananOpsDao.addAndSave(imSimrsRekananOpsEntity);
                    rekananOps.setIdRekananOps(imSimrsRekananOpsEntity.getIdRekananOps());
                } catch (HibernateException e) {
                    logger.error("[AsuransiiBoImpl.saveAdd] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving new data Asuransi, please info to your admin..." + e.getMessage());
                }
            }

        }
        return rekananOps;
    }

    @Override
    public CrudResponse saveEdit(RekananOps bean) throws GeneralBOException {
        logger.info("[RekananOpsBoImpl.saveEdit] start process >>>");
        if (bean!=null) {
            String  idRekananOps = bean.getIdRekananOps();
            ImSimrsRekananOpsEntity imSimrsRekananOpsEntity = null;
            try {
                // Get data from database by ID
                imSimrsRekananOpsEntity = rekananOpsDao.getById("idRekananOps", idRekananOps);
                //historyId = payrollSkalaGajiDao.getNextSkalaGaji();
            } catch (HibernateException e) {
                logger.error("[RekananOpsBoImpl.saveEdit] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data RekananOps by Kode RekananOps, please inform to your admin...," + e.getMessage());
            }
//          mengambil nama master / nama asuransi dari masterdao berdasarkan no master;
            ImMasterEntity masterEntity = new ImMasterEntity();
            try {
                masterEntity = masterDao.getById("primaryKey.nomorMaster", bean.getNomorMaster());
            }catch (HibernateException e){
                logger.error("Found Error when search asuransi "+e.getMessage());
            }
//           imSimrsAsuransiEntity.setIdAsuransi(asuransiId);
            //cari ke dao akun Master berdasarkan no master

            if (imSimrsRekananOpsEntity != null) {

//                    imSimrsRekananOpsEntity.setNomorMaster(bean.getNomorMaster());
                if (masterEntity != null){
                    imSimrsRekananOpsEntity.setNomorMaster(bean.getNomorMaster());
                    imSimrsRekananOpsEntity.setNamaRekanan(masterEntity.getNama());
                    //set berdasarkan kebutuhan fild yang di ambil dari tabel lain
                }
                    imSimrsRekananOpsEntity.setFlag(bean.getFlag());
                    imSimrsRekananOpsEntity.setAction(bean.getAction());
                    imSimrsRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    imSimrsRekananOpsEntity.setLastUpdate(bean.getLastUpdate());

                    try {
                        // Update into database
                        rekananOpsDao.updateAndSave(imSimrsRekananOpsEntity);
                        //payrollSkalaGajiDao.addAndSaveHistory(imRekananOpsHistoryEntity);
                    } catch (HibernateException e) {
                        logger.error("[AsuransiBoImpl.saveEdit] Error, " + e.getMessage());
                        throw new GeneralBOException("Found problem when saving update data Asuransi, please info to your admin..." + e.getMessage());
                    }
                }
            } else {
                logger.error("[AsuransiBoImpl.saveEdit] Error, not found data Asuransi with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data Asuransi with request id, please check again your data ...");
            }
        return null;
    }

    @Override
    public CrudResponse saveDelete(RekananOps bean) throws GeneralBOException {

        logger.info("[saveDelete.saveDelete] start process >>>");
        if (bean != null) {
            String idRekananOps = bean.getIdRekananOps();

            ImSimrsRekananOpsEntity imSimrsRekananOpsEntity = null;

            try {
                // Get data from database by ID
                imSimrsRekananOpsEntity = rekananOpsDao.getById("idRekananOps", idRekananOps);
            } catch (HibernateException e) {
                logger.error("[PayrollPtkpBoImpl.saveDelete] Error, " + e.getMessage());
                throw new GeneralBOException("Found problem when searching data alat by Kode alat, please inform to your admin...," + e.getMessage());
            }

            if (imSimrsRekananOpsEntity != null) {
                imSimrsRekananOpsEntity.setIdRekananOps(bean.getIdRekananOps());

                imSimrsRekananOpsEntity.setFlag(bean.getFlag());
                imSimrsRekananOpsEntity.setAction(bean.getAction());
                imSimrsRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                imSimrsRekananOpsEntity.setLastUpdate(bean.getLastUpdate());

                try {
                    // Delete (Edit) into database
                    rekananOpsDao.updateAndSave(imSimrsRekananOpsEntity);
                } catch (HibernateException e) {
                    logger.error("[RekananOpsBoImpl.saveDelete] Error, " + e.getMessage());
                    throw new GeneralBOException("Found problem when saving update data PayrollPtkp, please info to your admin..." + e.getMessage());
                }


            } else {
                logger.error("[RekananOpsBoImpl.saveDelete] Error, not found data PayrollPtkp with request id, please check again your data ...");
                throw new GeneralBOException("Error, not found data PayrollPtkp with request id, please check again your data ...");
            }
        }
        logger.info("[RekananOpsBoImpl.saveDelete] end process <<<");
        return null;

    }

    @Override
    public RekananOps getDetailRekananOps(String id, String branchId) throws GeneralBOException {
        RekananOps rekananOps = new RekananOps();
        try {
            rekananOps = rekananOpsDao.getDetailRekananOps(id, branchId);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
        }
        return rekananOps;
    }

    @Override
    public RekananOps getDetailRekananOpsByDetail(String id, String branchId) throws GeneralBOException {
        RekananOps rekananOps = new RekananOps();
        try {
            rekananOps = rekananOpsDao.getRekananOpsByIdDetail(id, branchId);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
        }
        return rekananOps;
    }

    @Override
    public void saveAllListTarifRekanan(RekananOps bean, List<DetailRekananOps> listDetailRekanan) throws GeneralBOException {
        logger.info("[RekanaOpsBoImpl.saveAllListTarifRekanan] START >>>");

        // first round, dahulukan data lama yg diupdate;
        List<DetailRekananOps> listDetailEdit = listDetailRekanan.stream().filter(
                p->p.getIdDetailRekananOps() != null && !"".equalsIgnoreCase(p.getIdDetailRekananOps())
        ).collect(Collectors.toList());

        if (listDetailEdit.size() > 0){
            for (DetailRekananOps detailEdit : listDetailEdit){

                ImSimrsDetailRekananOpsEntity detailRekananOpsEntity = new ImSimrsDetailRekananOpsEntity();

                try {
                    detailRekananOpsEntity = detailRekananOpsDao.getById("idDetailRekananOps", detailEdit.getIdDetailRekananOps());
                } catch (HibernateException e){
                    logger.error("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR," + e.getMessage());
                    throw new GeneralBOException("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR, " + e.getMessage());
                }

                if (detailRekananOpsEntity == null || detailRekananOpsEntity.getIdDetailRekananOps() == null || !"".equalsIgnoreCase(detailRekananOpsEntity.getIdDetailRekananOps())){
                    logger.error("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR, tidak ditemukan data pada database saat akan update");
                    throw new GeneralBOException("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR, tidak ditemukan data pada database saat akan update" );
                }

                detailRekananOpsEntity.setLastUpdate(bean.getLastUpdate());
                detailRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());
                detailRekananOpsEntity.setFlag(detailEdit.getFlag());
                detailRekananOpsEntity.setAction("U");

                // jika edit ada set tarif untuk diupdate
                // selain edit adalah delete dimana merubah flag menjadi N saja tanpa merubah yg lain
                if ("edit".equalsIgnoreCase(detailEdit.getTipe())){
                    detailRekananOpsEntity.setTarif(detailEdit.getTarif());
                }
                // END

                try {
                    detailRekananOpsDao.updateAndSave(detailRekananOpsEntity);
                } catch (HibernateException e){
                    logger.error("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR," + e.getMessage());
                    throw new GeneralBOException("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR, " + e.getMessage());
                }
            }
        }

        // END

        // second round, setelah mendahulukan data lama insert data baru
        List<DetailRekananOps> listDetailAdd = listDetailRekanan.stream().filter(
                p -> "add".equalsIgnoreCase(p.getTipe())
        ).collect(Collectors.toList());

        if (listDetailAdd.size() > 0){
            for (DetailRekananOps detailAdd : listDetailAdd){

                ImSimrsDetailRekananOpsEntity detailRekananOpsEntity = new ImSimrsDetailRekananOpsEntity();

                detailRekananOpsEntity.setIdDetailRekananOps("DRK"+getNextDetailRekanan());
                detailRekananOpsEntity.setTarif(detailAdd.getTarif());
                detailRekananOpsEntity.setIdRekananOps(detailAdd.getIdRekananOps());
                detailRekananOpsEntity.setParentId(detailAdd.getParentId());
                detailRekananOpsEntity.setIdItem(detailAdd.getIdItem());
                detailRekananOpsEntity.setFlag(detailAdd.getFlag());
                detailRekananOpsEntity.setAction("C");
                detailRekananOpsEntity.setCreatedDate(bean.getCreatedDate());
                detailRekananOpsEntity.setCreatedWho(bean.getCreatedWho());
                detailRekananOpsEntity.setLastUpdate(bean.getLastUpdate());
                detailRekananOpsEntity.setLastUpdateWho(bean.getLastUpdateWho());

                try {
                    detailRekananOpsDao.addAndSave(detailRekananOpsEntity);
                } catch (HibernateException e){
                    logger.error("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR," + e.getMessage());
                    throw new GeneralBOException("[RekananOpsBoImpl.saveAllListTarifRekanan] ERROR, " + e.getMessage());
                }
            }
        }
        // END

        logger.info("[RekanaOpsBoImpl.saveAllListTarifRekanan] END <<<");
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public List<RekananOps> getComboRekananOps(String branchId, String isBpjs) throws GeneralBOException {
        List<RekananOps> rekananOps = new ArrayList<>();
        try {
            rekananOps = rekananOpsDao.getComboRekananOps(branchId, isBpjs);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
        }
        return rekananOps;
    }

    @Override
    public ImSimrsRekananOpsEntity getRekananEntityById(String id) throws GeneralBOException {
        return rekananOpsDao.getById("idRekananOps", id);
    }

    @Override
    public DetailRekananOps getTarifRekanan(String idRekananOps, String branchId, String idTindakn) throws GeneralBOException {
        logger.info("[RekanaOpsBoImpl.getTarifRekanan] START >>>");

        DetailRekananOps detailRekananOps = new DetailRekananOps();

        try {
            detailRekananOps = rekananOpsDao.getTarifRekananByIdRekanan(idRekananOps, branchId, idTindakn);
        }catch (HibernateException e){
            logger.error("Error when search detail rekanan ops,"+e.getMessage());
            throw new GeneralBOException("[RekanaOpsBoImpl.getTarifRekanan] Error, "+e.getMessage());
        }

        logger.info("[RekanaOpsBoImpl.getTarifRekanan] END <<<");
        return detailRekananOps;
    }

    private String getNextDetailRekanan(){
        String id = "";

        try {
            id = detailRekananOpsDao.getNextId();
        } catch (HibernateException e){
            logger.error("[RekananOpsBoImpl.getRekananEntityById] ERROR when get seq ,"+e.getMessage());
        }

        return id;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setRekananOpsDao(RekananOpsDao rekananOpsDao) {
        this.rekananOpsDao = rekananOpsDao;
    }




//    public String cekStatus(String nomorMaster)throws GeneralBOException{
//        String status ="";
//        List<ImSimrsRekananOpsEntity> entities = new ArrayList<>();
//        try {
//            entities = rekananOpsDao.getRekananOps(nomorMaster);
//        } catch (HibernateException e) {
//            logger.error("[KategoriTindakanPelayananBoImpl.cekStatus] Error, " + e.getMessage());
//            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
//        }
//        if (entities.size()>0){
//            status = "exist";
//        }else{
//            status="notExits";
//        }
//        return status;
//    }
}
