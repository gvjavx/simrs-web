package com.neurix.hris.transaksi.ijinKeluar.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biodata.model.Biodata;
import com.neurix.hris.transaksi.ijinKeluar.model.IjinKeluarAnggotaEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class IjinKeluarAnggotaDao extends GenericDao<IjinKeluarAnggotaEntity, String> {

    @Override
    protected Class<IjinKeluarAnggotaEntity> getEntityClass() {
        return IjinKeluarAnggotaEntity.class;
    }

    @Override
    public List<IjinKeluarAnggotaEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextIjinKeluarAnggotaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ijin_keluar_anggota')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());
        return "IJA"+sId;
    }

    public List<IjinKeluarAnggotaEntity> getIjinKeluarAnggotaById(String id) throws HibernateException {

        List<IjinKeluarAnggotaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(IjinKeluarAnggotaEntity.class)
                .add(Restrictions.eq("ijinKeluarKantorId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("ijinKeluarAnggotaId"))
                .list();

        return results;
    }

    public List<Biodata> getBranchDivisiPosisi(String nip) throws HibernateException {
        List<Biodata> listOfResult = new ArrayList<Biodata>();



        String query = "select distinct it_hris_pegawai_position.branch_id, im_position.department_id, it_hris_pegawai_position.position_id,im_position.bagian_id \n" +
                "from it_hris_pegawai_position\n" +
                "inner join im_position on it_hris_pegawai_position.position_id = im_position.position_id\n" +
                "where\n" +
                "it_hris_pegawai_position.flag = 'Y'\n" +
                "and nip = '"+nip+"'";
        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        Biodata biodata;
        for(Object[] rows: results){
            biodata = new Biodata();
            biodata.setBranch(rows[0].toString());
            biodata.setDivisi(rows[1].toString());
            biodata.setPositionId(rows[2].toString());
            biodata.setBagianId(rows[3].toString());
            listOfResult.add(biodata);
        }
        return listOfResult;
    }
}
