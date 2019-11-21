package com.neurix.simrs.master.jenisperiksapasien.bo.impl;

import com.neurix.simrs.master.jenisperiksapasien.bo.JenisPerksaPasienBo;
import com.neurix.simrs.master.jenisperiksapasien.dao.JenisPeriksaPasienDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.jenisperiksapasien.model.JenisPriksaPasien;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 12/11/2019.
 */
public class JenisPriksaPasienImpl implements JenisPerksaPasienBo {
    protected static transient Logger logger = Logger.getLogger(JenisPriksaPasienImpl.class);

    private JenisPeriksaPasienDao jenisPeriksaPasienDao;

    public void setJenisPeriksaPasienDao(JenisPeriksaPasienDao jenisPeriksaPasienDao) {
        this.jenisPeriksaPasienDao = jenisPeriksaPasienDao;
    }

    @Override
    public List<JenisPriksaPasien> getListAllJenisPeriksa(JenisPriksaPasien bean) {
        logger.info("[jenisPriksaPasienImpl.getListAllJenisPeriksa] Start >>>>>>");
        List<JenisPriksaPasien> result = new ArrayList<>();
        Map hsCriteria = new HashMap();

        if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())){
            hsCriteria.put("id_jenis_periksa_pasien", bean.getIdJenisPeriksaPasien());
        }

        hsCriteria.put("flag","Y");

        List<ImJenisPeriksaPasienEntity> imJenisPeriksaPasienEntityList = null;
        try {
            imJenisPeriksaPasienEntityList = jenisPeriksaPasienDao.getByCriteria(hsCriteria);
        } catch (HibernateException e){
            logger.error("[jenisPriksaPasienImpl.getListAllJenisPeriksa] Error get jenis periksa data "+e.getMessage());
        }

        if (!imJenisPeriksaPasienEntityList.isEmpty()){
            JenisPriksaPasien jenisPriksaPasien;
            for (ImJenisPeriksaPasienEntity listEntity : imJenisPeriksaPasienEntityList){
                jenisPriksaPasien = new JenisPriksaPasien();
                jenisPriksaPasien.setIdJenisPeriksaPasien(listEntity.getIdJenisPeriksaPasien());
                jenisPriksaPasien.setKeterangan(listEntity.getKeterangan());
                result.add(jenisPriksaPasien);
            }
        }
        logger.info("[jenisPriksaPasienImpl.getListAllJenisPeriksa] End <<<<<<");

        return result;
    }
}
