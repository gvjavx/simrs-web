package com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.dao;

import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
import com.neurix.akuntansi.transaksi.pendaftaranjasarekanan.model.ItAkunPendaftaranJasaEntity;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

public class PendaftaranJasaRekananDao extends GenericDao<ItAkunPendaftaranJasaEntity, String>{

    @Override
    protected Class<ItAkunPendaftaranJasaEntity> getEntityClass() {
        return ItAkunPendaftaranJasaEntity.class;
    }

    @Override
    public List<ItAkunPendaftaranJasaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPendaftaranJasaEntity.class);

        List<ItAkunPendaftaranJasaEntity> result = new ArrayList<>();
        if (mapCriteria != null) {
            if (mapCriteria.get("id") != null){
                criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
            }
            if(mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId",  mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("approve_ka_keu") != null){
                criteria.add(Restrictions.eq("approveKaKeu", mapCriteria.get("approve_ka_keu")));
            }
            if (mapCriteria.get("approve_kasub_keu") != null){
                criteria.add(Restrictions.eq("approveKasubKeu", mapCriteria.get("approve_kasub_keu")));
            }
            if (mapCriteria.get("approve_keu") != null){
                criteria.add(Restrictions.eq("approveKeu", mapCriteria.get("approve_keu")));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
            result = criteria.list();
        }

        return result;
    }

    public KodeRekening getKodeRekeningPropsByKodeRekening(String id){

        String SQL = "SELECT kode_rekening, nama_kode_rekening FROM im_akun_kode_rekening WHERE kode_rekening = '"+id+"'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (results.size() > 0){
            for (Object[] obj : results){
                KodeRekening kodeRekening = new KodeRekening();
                kodeRekening.setKodeRekening(obj[0].toString());
                kodeRekening.setNamaKodeRekening(obj[1].toString());
                return kodeRekening;
            }
        }

        return null;
    }

    public Position getPositionPropsByKodering(String kodering){

        String SQL = "SELECT kodering, position_name FROM im_position WHERE kodering = '"+kodering+"' AND flag_cost_unit = 'Y' AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        if (results.size() > 0){
            for (Object[] obj : results){
               Position position = new Position();
               position.setKodering(obj[0].toString());
               position.setPositionName(obj[1].toString());
               return position;
            }
        }

        return null;
    }

    public List<KodeRekening> getListKodeRekeningBebanJasaProfesional(){

        String SQL = "SELECT kode_rekening, nama_kode_rekening FROM im_akun_kode_rekening WHERE kode_rekening ILIKE '"+ CommonConstant.COA_BEBAN_JASA_PREFESIONAL+"%'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<KodeRekening> kodeRekenings = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                KodeRekening kodeRekening = new KodeRekening();
                kodeRekening.setKodeRekening(obj[0].toString());
                kodeRekening.setNamaKodeRekening(obj[1].toString());
                kodeRekenings.add(kodeRekening);
            }
        }

        return kodeRekenings;
    }

    public List<Position> getListPosition(){

        String SQL = "SELECT kodering, position_name FROM im_position WHERE flag_cost_unit = 'Y' AND flag = 'Y'";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();

        List<Position> positionList = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                Position position = new Position();
                position.setKodering(obj[0].toString());
                position.setPositionName(obj[1].toString());
                positionList.add(position);
            }
        }

        return positionList;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('pendaftaran_jasa_rekanan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "P"+ CommonUtil.stDateSeq() + sId;
    }
}
