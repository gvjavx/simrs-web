
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPphEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPphEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollPph;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class PayrollPphDao extends GenericDao<ItPayrollPphEntity, String> {

    @Override
    protected Class<ItPayrollPphEntity> getEntityClass() {
        return ItPayrollPphEntity.class;
    }

    @Override
    public List<ItPayrollPphEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPphEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_id")!=null) {
                criteria.add(Restrictions.eq("payrollId", (String) mapCriteria.get("payroll_id")));
            }

            if (mapCriteria.get("bulan")!=null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("pph_id"));

        List<ItPayrollPphEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre, updated by ferdi, 01-12-2020
    public String getNextPayrollPphId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_pph')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = "PPH" + bulan + per[2] + per[3] + sId;

        return hasil;
    }

    public List<ItPayrollPphEntity> getDataView(String payrollId) throws HibernateException {
        List<ItPayrollPphEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPphEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
    public ItPayrollPphEntity getIuranJkmJkk(String nip,String tahun) throws HibernateException {
        ItPayrollPphEntity result = new ItPayrollPphEntity();
        result.setIuranJkmJkk(BigDecimal.ZERO);
        result.setPakaianDinas(BigDecimal.ZERO);
        result.setIuranBpjsKesehatan(BigDecimal.ZERO);
        String query = "SELECT\n" +
                "\tmax(pp.pakaian_dinas) as pd,\n" +
                "\tmax(pp.iuran_jkm_jkk) as ijj,\n" +
                "\tsum(p.iuran_bpjs_kesehatan) as ibk\n" +
                "from \n" +
                "\tit_hris_payroll p\n" +
                "\tINNER JOIN it_hris_payroll_pph pp ON p.payroll_id=pp.payroll_id\n" +
                "where \n" +
                "\ttahun='"+tahun+"' \n" +
                "\tand nip='"+nip+"'\n" +
                "\tand flag_payroll='Y'\n" +
                "\t";
        List<Object[]> results ;
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for(Object[] row: results){
            if (row[0]!= null){
                result.setPakaianDinas(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            }
            if (row[1]!= null){
                result.setIuranJkmJkk(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
            }
            if (row[2]!= null){
                result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
            }
        }

        return result;
    }

    //updated by ferdi, 01-12-2020, to get data pph payroll s.d bulan 11
    public List<PayrollPph> getInitialDataPphPayrollUntilNov(String branchId, String tahunPayroll) throws HibernateException {

        List<PayrollPph> listOfResult = new ArrayList<PayrollPph>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "      payroll.nip as nip,\n" +
                "      payroll.nama_pegawai as nama_pegawai,\n" +
                "      payroll.tipe_payroll as tipe_payroll,\n" +
                "      pph.bulan as bulan,\n" +
                "      pph.tahun as tahun,\n" +
                "      pph.bruto as bruto,\n" +
                "      pph.tunj_pph as tunj_pph,\n" +
                "      pph.pph_gaji as pph_gaji,\n" +
                "      pph.iuran_pegawai as iuran_pegawai,\n" +
                "      pph.bonus as income_tidak_tetap\n" +
                "from\n" +
                "it_hris_payroll payroll\n" +
                "left join it_hris_payroll_pph pph on payroll.payroll_id = pph.payroll_id\n" +
                "where payroll.approval_flag = 'Y'\n" +
                "and payroll.flag = 'Y'\n" +
                "and payroll.tahun != '12'\n" +
                "and payroll.tahun = :tahun\n" +
                "and payroll.branch_id = :branchId";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("branchId", branchId)
                .setParameter("tahun", tahunPayroll)
                .list();

        for (Object[] row : results) {
            PayrollPph result  = new PayrollPph();

            result.setNip((String) row[0]);
            result.setNamaPegawai(row[1]!=null ? (String) row[1] : "");
            result.setTipePayroll(row[2]!=null ? (String) row[2] : "");
            result.setBulan(row[3]!=null ? (String) row[3] : "");
            result.setTahun(row[4]!=null ? (String) row[4] : "");
            result.setBrutoNilai(row[5]!=null ? (BigDecimal) row[5] : new BigDecimal(0));
            result.setBruto(CommonUtil.numbericFormat(result.getBrutoNilai(),"###,###"));

            result.setTunjanganPphNilaiBulan(row[6]!=null ? (BigDecimal) row[6] : new BigDecimal(0));
            result.setTunjanganPphBulan(CommonUtil.numbericFormat(result.getTunjanganPphNilaiBulan(),"###,###"));

            result.setPphGajiNilai(row[7]!=null ? (BigDecimal) row[7] : new BigDecimal(0));
            result.setPphGaji(CommonUtil.numbericFormat(result.getPphGajiNilai(),"###,###"));

            result.setIuranPegawaiNilai(row[8]!=null ? (BigDecimal) row[8] : new BigDecimal(0));
            result.setIuranPegawai(CommonUtil.numbericFormat(result.getIuranPegawaiNilai(),"###,###"));

            result.setIncomeTidakTetapNilai(row[9]!=null ? (BigDecimal) row[9] : new BigDecimal(0));
            result.setIncomeTidakTetap(CommonUtil.numbericFormat(result.getIncomeTidakTetapNilai(),"###,###"));

            listOfResult.add(result);
        }

        return listOfResult;
    }
}
