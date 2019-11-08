package com.neurix.simrs.transaksi.antrianonline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsRegistrasiOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class RegistrasiOnlineDao extends GenericDao<ItSimrsRegistrasiOnlineEntity, String> {
    @Override
    protected Class<ItSimrsRegistrasiOnlineEntity> getEntityClass() {
        return ItSimrsRegistrasiOnlineEntity.class;
    }

    @Override
    public List<ItSimrsRegistrasiOnlineEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRegistrasiOnlineEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("no_checkup_online")!=null) {
                criteria.add(Restrictions.eq("noCheckupOnline", (String) mapCriteria.get("no_checkup_online")));
            }
            if (mapCriteria.get("id_pasien")!=null) {
                criteria.add(Restrictions.eq("idPasien", (String) mapCriteria.get("id_pasien")));
            }
            if (mapCriteria.get("nama")!=null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String)mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("jenis_kelamin")!=null) {
                criteria.add(Restrictions.eq("jenisKelamin", (String) mapCriteria.get("jenisKelamin")));
            }
            if (mapCriteria.get("no_ktp")!=null) {
                criteria.add(Restrictions.eq("noKtp", (String) mapCriteria.get("no_ktp")));
            }
            if (mapCriteria.get("tempat_lahir")!=null) {
                criteria.add(Restrictions.eq("tempatLahir", (String) mapCriteria.get("tempat_lahir")));
            }
            if (mapCriteria.get("tgl_lahir")!=null) {
                criteria.add(Restrictions.eq("tglLahir", (String) mapCriteria.get("tgl_lahir")));
            }
            if (mapCriteria.get("desa_id")!=null) {
                criteria.add(Restrictions.eq("desaId", (String) mapCriteria.get("desa_id")));
            }
            if (mapCriteria.get("jalan")!=null) {
                criteria.add(Restrictions.eq("jalan", (String) mapCriteria.get("jalan")));
            }
            if (mapCriteria.get("suku")!=null) {
                criteria.add(Restrictions.eq("suku", (String) mapCriteria.get("suku")));
            }
            if (mapCriteria.get("agama")!=null) {
                criteria.add(Restrictions.eq("agama", (String) mapCriteria.get("agama")));
            }
            if (mapCriteria.get("profesi")!=null) {
                criteria.add(Restrictions.eq("profesi", (String) mapCriteria.get("profesi")));
            }
            if (mapCriteria.get("no_telp")!=null) {
                criteria.add(Restrictions.eq("noTelp", (String) mapCriteria.get("no_telp")));
            }
            if (mapCriteria.get("id_jenis_periksa_pasien")!=null) {
                criteria.add(Restrictions.eq("idJenisPeriksaPasien", (String) mapCriteria.get("id_jenis_periksa_pasien")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("noCheckupOnline"));

        List<ItSimrsRegistrasiOnlineEntity> results = criteria.list();
        return results;
    }
}