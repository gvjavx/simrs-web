package com.neurix.simrs.master.tindakan.bo.impl;

import com.neurix.authorization.company.bo.BranchBo;
import com.neurix.authorization.company.model.Branch;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.kategoritindakan.bo.KategoriTindakanBo;
import com.neurix.simrs.master.kategoritindakan.model.KategoriTindakan;
import com.neurix.simrs.master.kategoritindakanina.bo.KategoriTindakanInaBo;
import com.neurix.simrs.master.kategoritindakanina.model.KategoriTindakanIna;
import com.neurix.simrs.master.tindakan.bo.TindakanBo;
import com.neurix.simrs.master.tindakan.dao.TindakanDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.CrudResponse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 13/11/2019.
 */
public class TindakanBoImpl implements TindakanBo {
    private static transient Logger logger = Logger.getLogger(TindakanBoImpl.class);
    private TindakanDao tindakanDao;

    @Override
    public CrudResponse saveEdit(Tindakan bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.saveEdit] Start >>>>>>>");
        CrudResponse response = new CrudResponse();
        if (bean != null) {
            ImSimrsTindakanEntity simrsTindakanEntity = null;
            try {
                simrsTindakanEntity = tindakanDao.getById("idTindakan", bean.getIdTindakan());
                response.setStatus("success");
                response.setMsg("Oke!");
            } catch (HibernateException e) {
                response.setStatus("error");
                response.setMsg("Mohon maaf... Terjadi kesalahan saat mencari ID tindakan...!");
                logger.error("[TindakanBoImpl.saveEdit] Error, " + e.getMessage());
            }
            if (simrsTindakanEntity != null) {
                if (bean.getTindakan().equalsIgnoreCase(simrsTindakanEntity.getTindakan())) {
                    if(bean.getTarif() != null){
                        simrsTindakanEntity.setTarif(bean.getTarif());
                    }
                    if(bean.getTarifBpjs() != null){
                        simrsTindakanEntity.setTarifBpjs(bean.getTarifBpjs());
                    }
                    if(bean.getDiskon() != null){
                        simrsTindakanEntity.setDiskon(bean.getDiskon());
                    }
                    if(bean.getIdKategoriTindakan() != null){
                        simrsTindakanEntity.setIdKategoriTindakan(bean.getIdKategoriTindakan());
                    }
                    if(bean.getIdHeaderTindakan() != null){
                        simrsTindakanEntity.setIdHeaderTindakan(bean.getIdHeaderTindakan());
                    }

                    simrsTindakanEntity.setFlag(bean.getFlag());
                    simrsTindakanEntity.setAction(bean.getAction());
                    simrsTindakanEntity.setLastUpdate(bean.getLastUpdate());
                    simrsTindakanEntity.setLastUpdateWho(bean.getLastUpdateWho());
                    try {
                        tindakanDao.updateAndSave(simrsTindakanEntity);
                        response.setStatus("success");
                        response.setMsg("Oke!");
                    } catch (HibernateException e) {
                        response.setStatus("error");
                        response.setMsg("Mohon maaf... Terjadi kesalahan saat edit tindakan...!");
                        logger.error("[TindakanBoImpl.saveAdd] Error when Updating data ruangan", e);
                    }
                }
            }else{
                response.setStatus("error");
                response.setMsg("Mohon maaf... Tidak dapat menemukan ID tindakan...!");
            }
        }
        logger.info("[TindakanBoImpl.saveEdit] End <<<<<<<");
        return response;
    }

    @Override
    public CrudResponse saveAdd(List<Tindakan> list) throws GeneralBOException {
        logger.info("[TindakanBoImpl.saveAdd] start process >>>");
        CrudResponse response = new CrudResponse();
        if (list.size() > 0 ) {
            for (Tindakan bean: list){
                String id = null;
                try {
                    id = tindakanDao.getNextPelayananId();
                } catch (HibernateException e) {
                    response.setStatus("error");
                    response.setMsg("Mohon maaf ada kesalhan saat generate ID tindakan...!");
                    logger.error("[TindakanBoImpl.saveAdd] Error, " + e.getMessage());
                }
                if(id != null){
                    ImSimrsTindakanEntity entity = new ImSimrsTindakanEntity();
                    entity.setIdTindakan(id);
                    entity.setTindakan(bean.getTindakan());
                    entity.setIdKategoriTindakan(bean.getIdKategoriTindakan());
                    entity.setTarif(bean.getTarif());
                    entity.setTarifBpjs(bean.getTarifBpjs());
                    entity.setKategoriInaBpjs(bean.getIdKategoriTindakanIna());
                    entity.setBranchId(bean.getBranchId());
                    entity.setDiskon(bean.getDiskon());
                    entity.setFlag(bean.getFlag());
                    entity.setAction(bean.getAction());
                    entity.setCreatedWho(bean.getCreatedWho());
                    entity.setLastUpdateWho(bean.getLastUpdateWho());
                    entity.setCreatedDate(bean.getCreatedDate());
                    entity.setLastUpdate(bean.getLastUpdate());
                    entity.setIdHeaderTindakan(bean.getIdHeaderTindakan());
                    entity.setIdPelayanan(bean.getIdPelayanan());
                    try {
                        tindakanDao.addAndSave(entity);
                        response.setStatus("success");
                        response.setMsg("Berhasil");
                    } catch (HibernateException e) {
                        logger.error("[TindakanImpl.saveAdd] Error, " + e.getMessage());
                        response.setStatus("error");
                        response.setMsg("Gagal menyimpan data tindakan dikarenakan, " + e.getMessage());
                    }
                }else{
                    response.setStatus("error");
                    response.setMsg("Gagal menyimpan data tindakan");
                }
            }
        }
        logger.info("[TindakanImpl.saveAdd] end process <<<");
        return response;
    }

    @Override
    public List<Tindakan> getByCriteria(Tindakan bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.getByCriteria] Start >>>>>>>");
        List<Tindakan> results = new ArrayList<>();

        if (bean != null) {
            List<ImSimrsTindakanEntity> entityList = getListEntityTindakan(bean);
            if (!entityList.isEmpty()) {
                results = setToTindakanTemplate(entityList);
            }
        }

        logger.info("[TindakanBoImpl.getByCriteria] End <<<<<<<");
        return results;
    }

    @Override
    public List<Tindakan> getComboBoxTindakan(Tindakan bean) throws GeneralBOException {
        List<Tindakan> tindakanList = new ArrayList<>();
        if (bean != null) {
            try {
                tindakanList = tindakanDao.getListComboBoxTindakan(bean);
            } catch (HibernateException e) {
                logger.error("Found Error" + e.getMessage());
            }
        }
        return tindakanList;
    }

    @Override
    public ImSimrsTindakanEntity getEntityTindakanById(String idTindakan) throws GeneralBOException {
        return tindakanDao.getById("idTindakan", idTindakan);
    }

    @Override
    public List<Tindakan> getDataTindakan(Tindakan bean) throws GeneralBOException {
        List<Tindakan> tindakanList = new ArrayList<>();
        if (bean != null) {
            try {
                tindakanList = tindakanDao.getListDataTindakan(bean);
            } catch (HibernateException e) {
                logger.error(e.getMessage());
            }
        }
        return tindakanList;
    }

    protected List<ImSimrsTindakanEntity> getListEntityTindakan(Tindakan bean) throws GeneralBOException {
        logger.info("[TindakanBoImpl.getListEntityTindakan] Start >>>>>>>");
        List<ImSimrsTindakanEntity> results = new ArrayList<>();

        Map hsCiteria = new HashMap();

        if (bean.getIdTindakan() != null && !"".equalsIgnoreCase(bean.getIdTindakan())) {
            hsCiteria.put("id_tindakan", bean.getIdTindakan());
        }
        if (bean.getTindakan() != null && !"".equalsIgnoreCase(bean.getTindakan())) {
            hsCiteria.put("tindakan", bean.getTindakan());
        }
        if (bean.getIdKategoriTindakan() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakan())) {
            hsCiteria.put("id_kategori_tindakan", bean.getIdKategoriTindakan());
        }
        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            hsCiteria.put("branch_id", bean.getBranchId());
        }
        if (bean.getIsIna() != null && !"".equalsIgnoreCase(bean.getIsIna())) {
            hsCiteria.put("is_ina", bean.getIsIna());
        }
        hsCiteria.put("flag", "Y");
        if (bean.getIdKategoriTindakanIna() != null && !"".equalsIgnoreCase(bean.getIdKategoriTindakanIna())) {
            hsCiteria.put("kategori_ina_bpjs", bean.getIdKategoriTindakanIna());
        }
        if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
            if ("N".equalsIgnoreCase(bean.getFlag())) {
                hsCiteria.put("flag", "N");
            } else {
                hsCiteria.put("flag", bean.getFlag());
            }
        } else {
            hsCiteria.put("flag", "Y");
        }

        try {
            results = tindakanDao.getByCriteria(hsCiteria);
        } catch (HibernateException e) {
            logger.error("[TindakanBoImpl.getListEntityTindakan] Error when get data tindakan ", e);
        }

        logger.info("[TindakanBoImpl.getListEntityTindakan] End <<<<<<<");
        return results;
    }

    protected List<Tindakan> setToTindakanTemplate(List<ImSimrsTindakanEntity> entities) throws GeneralBOException {
        logger.info("[TindakanBoImpl.setToTindakanTemplate] Start >>>>>>>");
        List<Tindakan> results = new ArrayList<>();

        Tindakan tindakan;
        for (ImSimrsTindakanEntity entity : entities) {
            tindakan = new Tindakan();
            tindakan.setIdTindakan(entity.getIdTindakan());
            tindakan.setTindakan(entity.getTindakan());
            tindakan.setBranchId(entity.getBranchId());
            tindakan.setIdKategoriTindakan(entity.getIdKategoriTindakan());
            tindakan.setIdKategoriTindakanIna(entity.getKategoriInaBpjs());
            tindakan.setKategoriInaBpjs(entity.getKategoriInaBpjs());
            tindakan.setTarif(entity.getTarif());
            tindakan.setTarifBpjs(entity.getTarifBpjs());
            if (entity.getDiskon() != null) {
                tindakan.setStDiskon(CommonUtil.numbericFormat(entity.getDiskon(), "###,###") + "%");
                tindakan.setDiskon(entity.getDiskon());
            } else {
                tindakan.setStDiskon("-");
            }
            tindakan.setFlag(entity.getFlag());
            tindakan.setAction(entity.getAction());
            tindakan.setCreatedDate(entity.getCreatedDate());
            tindakan.setStCreatedDate(entity.getCreatedDate().toString());
            tindakan.setCreatedWho(entity.getCreatedWho());
            tindakan.setLastUpdate(entity.getLastUpdate());
            tindakan.setStLastUpdate(entity.getLastUpdate().toString());
            tindakan.setLastUpdateWho(entity.getLastUpdateWho());

            ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            if (entity.getBranchId() != null) {
                Branch branch = new Branch();
                BranchBo branchBo = (BranchBo) context.getBean("branchBoProxy");
                branch.setBranchId(entity.getBranchId());
                branch.setFlag("Y");
                List<Branch> branches = branchBo.getByCriteria(branch);
                String branchName = branches.get(0).getBranchName();
                tindakan.setBranchName(branchName);
            } else {
                tindakan.setBranchName("-");
            }

            if (entity.getIdKategoriTindakan() != null) {
                KategoriTindakan kategoriTindakan = new KategoriTindakan();
                KategoriTindakanBo kategoriTindakanBo = (KategoriTindakanBo) context.getBean("kategoriTindakanBoProxy");
                kategoriTindakan.setIdKategoriTindakan(entity.getIdKategoriTindakan());
                kategoriTindakan.setFlag("Y");
                List<KategoriTindakan> kategoriTindakans = kategoriTindakanBo.getByCriteria(kategoriTindakan);
                String kategoriTindakanName = kategoriTindakans.get(0).getKategoriTindakan();
                tindakan.setNamaKategoriTindakan(kategoriTindakanName);
            } else {
                tindakan.setNamaKategoriTindakan("-");
            }

            if (entity.getKategoriInaBpjs() != null) {
                KategoriTindakanIna kategoriTindakanIna = new KategoriTindakanIna();
                KategoriTindakanInaBo kategoriTindakanInaBo = (KategoriTindakanInaBo) context.getBean("kategoriTindakanInaBoProxy");
                kategoriTindakanIna.setId(entity.getKategoriInaBpjs());
                kategoriTindakanIna.setFlag("Y");
                List<KategoriTindakanIna> kategoriTindakanInas = kategoriTindakanInaBo.getByCriteria(kategoriTindakanIna);
                String kategoriTindakanInaName = kategoriTindakanInas.get(0).getNama();
                tindakan.setNamaKategoriTindakanIna(kategoriTindakanInaName);
            } else {
                tindakan.setNamaKategoriTindakanIna("-");
            }

            results.add(tindakan);
        }

        logger.info("[TindakanBoImpl.setToTindakanTemplate] End <<<<<<<");
        return results;
    }

    public void setTindakanDao(TindakanDao tindakanDao) {
        this.tindakanDao = tindakanDao;
    }

    public String cekStatus(String namaTindakan) throws GeneralBOException {
        String status = "";
        List<ImSimrsTindakanEntity> entities = new ArrayList<>();
        try {
            entities = tindakanDao.getDataTindakan(namaTindakan);
        } catch (HibernateException e) {
            logger.error("[PayrollSkalaGajiBoImpl.getSearchPayrollSkalaGajiByCriteria] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size() > 0) {
            status = "exist";
        } else {
            status = "notExits";
        }
        return status;
    }

    public String cekBeforeDelete(String idTindakan) throws GeneralBOException {
        String status = "";
        List<ImSimrsTindakanEntity> entities = new ArrayList<>();
        try {
            entities = tindakanDao.cekData(idTindakan);
        } catch (HibernateException e) {
            logger.error("[TindakanBoImpl.cekBeforeDelete] Error, " + e.getMessage());
            throw new GeneralBOException("Found problem when searching data by criteria, please info to your admin..." + e.getMessage());
        }
        if (entities.size() > 0) {
            status = "exist";
        } else {
            status = "notExits";
        }
        return status;
    }
}
