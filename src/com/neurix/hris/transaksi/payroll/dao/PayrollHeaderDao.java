/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.dao;


import com.neurix.common.dao.GenericDao;

import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollHeaderEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollHeader;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class PayrollHeaderDao extends GenericDao<ItHrisPayrollHeaderEntity, String> {

    @Override
    protected Class<ItHrisPayrollHeaderEntity> getEntityClass() {
        return ItHrisPayrollHeaderEntity.class;
    }

    @Override
    public List<ItHrisPayrollHeaderEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollHeaderEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("payroll_header_id") != null) {
                criteria.add(Restrictions.eq("payrollHeaderId", (String) mapCriteria.get("payroll_header_id")));
            }

            if (mapCriteria.get("bulan") != null) {
                criteria.add(Restrictions.ge("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun") != null) {
                criteria.add(Restrictions.ge("tahun", (String) mapCriteria.get("tahun")));
            }

            if (mapCriteria.get("bulansd") != null) {
                criteria.add(Restrictions.le("bulan", (String) mapCriteria.get("bulansd")));
            }

            if (mapCriteria.get("tahunsd") != null) {
                criteria.add(Restrictions.le("tahun", (String) mapCriteria.get("tahunsd")));
            }

            if (mapCriteria.get("tipe_payroll") != null) {
                criteria.add(Restrictions.eq("tipePayroll", (String) mapCriteria.get("tipe_payroll")));
            }

            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payrollHeaderId"));

        List<ItHrisPayrollHeaderEntity> results = criteria.list();

        return results;
    }

    //updated by ferdi, 01-12-2020, to generated pph temp seq
    public String getNextPayrollHeaderId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_header')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = "PYRHED" + bulan + per[2] + per[3] + sId;

        return hasil;
    }


    public List<PayrollHeader> getPayrollSearch(String branchId,
                                                String bulan,
                                                String tahun,
                                                String bulansd,
                                                String tahunsd,
                                                String tipePayroll) throws HibernateException {

        Map hsCriteria = new HashMap<>();
        hsCriteria.put("bulan", bulan);
        hsCriteria.put("tahun", tahun);
        hsCriteria.put("bulansd", bulansd);
        hsCriteria.put("tahunsd", tahunsd);

        hsCriteria.put("tipe_payroll", tipePayroll);
        hsCriteria.put("branch_id", branchId);

        List<ItHrisPayrollHeaderEntity> payrollHeaderEntityList = getByCriteria(hsCriteria);
        List<PayrollHeader> listOfResult = new ArrayList<PayrollHeader>();

        if (!payrollHeaderEntityList.isEmpty()) {

            for (ItHrisPayrollHeaderEntity itemItHrisPayrollHeaderEntity : payrollHeaderEntityList) {

                String payrollHeaderId = itemItHrisPayrollHeaderEntity.getPayrollHeaderId();

                List<Object[]> results = new ArrayList<Object[]>();
                String query = "";

                if ("Y".equalsIgnoreCase(itemItHrisPayrollHeaderEntity.getApprovalSdmFlag()) && "Y".equalsIgnoreCase(itemItHrisPayrollHeaderEntity.getApprovalAksFlag())) { //jika sudah ada approval sdm dan keu, maka get data dari table payroll permanen

                    query = "select\n" +
                            "       payroll_header.payroll_header_id,\n" +
                            "       payroll_header.tipe_payroll,\n" +
                            "       payroll_header.bulan,\n" +
                            "       payroll_header.tahun,\n" +
                            "       COUNT(payroll_detail.nip) as total_pegawai,\n" +
                            "       branch.branch_id,\n" +
                            "       branch.branch_name,\n" +
                            "       SUM(payroll_detail.gaji_bersih) as gaji_bersih,\n" +
                            "       SUM(payroll_detail.total_a) as gaji_kotor,\n" +
                            "       payroll_header.approval_sdm_flag,\n" +
                            "       payroll_header.approval_aks_flag,\n" +
                            "       payroll_header.approval_sdm_date,\n" +
                            "       payroll_header.approval_aks_date,\n" +
                            "       payroll_header.flag,\n" +
                            "       payroll_header.no_jurnal,\n" +
                            "       payroll_header.keterangan_aks\n" +
                            "from it_hris_payroll_header payroll_header\n" +
                            "     left join it_hris_payroll payroll_detail on payroll_detail.payroll_header_id = payroll_detail.payroll_header_id\n" +
                            "     left join im_branches branch on payroll_header.branch_id = branch.branch_id\n" +
                            "where\n" +
                            "    payroll_header.payroll_header_id = :payrollHeaderId\n" +
                            "group by payroll_header.payroll_header_id,\n" +
                            "         payroll_header.tipe_payroll,\n" +
                            "         payroll_header.bulan,\n" +
                            "         payroll_header.tahun,\n" +
                            "         branch.branch_id,\n" +
                            "         branch.branch_name\n" +
                            "order by payroll_header.payroll_header_id desc";


                } else { //jika belum ada approval sdm, di get dari table payroll temp

                    query = "select\n" +
                            "       payroll_header.payroll_header_id,\n" +
                            "       payroll_header.tipe_payroll,\n" +
                            "       payroll_header.bulan,\n" +
                            "       payroll_header.tahun,\n" +
                            "       COUNT(payroll_detail.nip) as total_pegawai,\n" +
                            "       branch.branch_id,\n" +
                            "       branch.branch_name,\n" +
                            "       SUM(payroll_detail.gaji_bersih) as gaji_bersih,\n" +
                            "       SUM(payroll_detail.total_a) as gaji_kotor,\n" +
                            "       payroll_header.approval_sdm_flag,\n" +
                            "       payroll_header.approval_aks_flag,\n" +
                            "       payroll_header.approval_sdm_date,\n" +
                            "       payroll_header.approval_aks_date,\n" +
                            "       payroll_header.flag,\n" +
                            "       payroll_header.no_jurnal,\n" +
                            "       payroll_header.keterangan_aks\n" +
                            "from it_hris_payroll_header payroll_header\n" +
                            "     left join it_hris_payroll_temp payroll_detail on payroll_detail.payroll_header_id = payroll_detail.payroll_header_id\n" +
                            "     left join im_branches branch on payroll_header.branch_id = branch.branch_id\n" +
                            "where\n" +
                            "    payroll_header.payroll_header_id = :payrollHeaderId\n" +
                            "group by payroll_header.payroll_header_id,\n" +
                            "         payroll_header.tipe_payroll,\n" +
                            "         payroll_header.bulan,\n" +
                            "         payroll_header.tahun,\n" +
                            "         branch.branch_id,\n" +
                            "         branch.branch_name\n" +
                            "order by payroll_header.payroll_header_id desc";
                }

                results = this.sessionFactory.getCurrentSession()
                        .createSQLQuery(query)
                        .setParameter("payrollHeaderId", payrollHeaderId)
                        .list();

                for (Object[] row : results) {
                    PayrollHeader result  = new PayrollHeader();

                    result.setPayrollHeaderId(row[0]!=null ? (String) row[0] : "");
                    result.setTipePayroll(row[1]!=null ? (String) row[1] : "");
                    result.setBulan(row[2]!=null ? (String) row[2] : "");
                    result.setTahun(row[3]!=null ? (String) row[3] : "");
                    result.setJumlahPegawai(row[4]!=null ? ((BigInteger) row[4]).intValue() : 0);
                    result.setStJumlahPegawai(String.valueOf(result.getJumlahPegawai()));
                    result.setBranchId(row[5]!=null ? (String) row[5] : "");
                    result.setBranchName(row[6]!=null ? (String) row[6] : "");
                    result.setGajiBersihNilai(row[7]!=null ? (BigDecimal) row[7] : new BigDecimal(0));
                    result.setGajiKotorNilai(row[8]!=null ? (BigDecimal) row[8] : new BigDecimal(0));
                    result.setApprovalSdmFlag(row[9]!=null ? (String) row[9] : "");
                    result.setApprovalAksFlag(row[10]!=null ? (String) row[10] : "");
                    result.setApprovalSdmDate(row[11]!=null ? (java.sql.Timestamp) row[11] : null);
                    result.setApprovalAksDate(row[12]!=null ? (java.sql.Timestamp) row[12] : null);
                    result.setFlag(row[13]!=null ? (String) row[13] : "");
                    result.setNoJurnal(row[14]!=null ? (String) row[14] : "");
                    result.setKeteranganAks(row[15]!=null ? (String) row[15] : "");

                    result.setGajiBersih(CommonUtil.numbericFormat(result.getGajiBersihNilai(),"###,###"));
                    result.setGajiKotor(CommonUtil.numbericFormat(result.getGajiKotorNilai(),"###,###"));
                    result.setStApprovalSdmDate(result.getApprovalSdmDate()!=null ? CommonUtil.convertTimestampToString(result.getApprovalSdmDate()) : "");
                    result.setStApprovalAksDate(result.getApprovalAksDate()!=null ? CommonUtil.convertTimestampToString(result.getApprovalAksDate()) : "");

                    listOfResult.add(result);
                }
            }

        }

        return listOfResult;
    }

    public ItHrisPayrollHeaderEntity getDataView(String payrollHeaderId) throws HibernateException {
        List<ItHrisPayrollHeaderEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollHeaderEntity.class)
                .add(Restrictions.eq("payrollHeaderId", payrollHeaderId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        ItHrisPayrollHeaderEntity resultItem = results.get(0);

        return resultItem;
    }

}