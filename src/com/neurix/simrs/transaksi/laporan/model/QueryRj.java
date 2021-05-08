package com.neurix.simrs.transaksi.laporan.model;

public class QueryRj {
    public static String queryRJ(LaporanOps bean){
        String idPelayanan = "'"+bean.getPelayananId()+"'";
        String label = "SELECT \n" +
                "CAST('poli' AS VARCHAR) as nomor,\n" +
                "a.nama_pelayanan  as uraian,\n" +
                "null as jan,\n" +
                "null as feb,\n" +
                "null as mar,\n" +
                "null as apr,\n" +
                "null as mei,\n" +
                "null as jun,\n" +
                "null as jul,\n" +
                "null as ags,\n" +
                "null as sep,\n" +
                "null as okt,\n" +
                "null as nov,\n" +
                "null as des,\n" +
                "null as total,\n" +
                "null as persen\n" +
                "FROM im_simrs_header_pelayanan a \n" +
                "INNER JOIN im_simrs_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                "WHERE b.id_pelayanan IN ("+idPelayanan+") \n";

        if(bean.getListIdPelayanan().size() > 0){
            String temp = "";
            for (String str: bean.getListIdPelayanan()){
                if(!"".equalsIgnoreCase(temp)){
                    temp = temp +","+"'"+str+"'";
                }else{
                    temp = "'"+str+"'";
                }
            }
            idPelayanan = temp;
            label = "SELECT \n" +
                    "CAST('poli' AS VARCHAR) as nomor,\n" +
                    "CAST('TOTAL' AS VARCHAR)  as uraian,\n" +
                    "null as jan,\n" +
                    "null as feb,\n" +
                    "null as mar,\n" +
                    "null as apr,\n" +
                    "null as mei,\n" +
                    "null as jun,\n" +
                    "null as jul,\n" +
                    "null as ags,\n" +
                    "null as sep,\n" +
                    "null as okt,\n" +
                    "null as nov,\n" +
                    "null as des,\n" +
                    "null as total,\n" +
                    "null as persen\n";
        }

        return  label+
                "UNION ALL\n" +
                "SELECT \n" +
                "CAST('No' AS VARCHAR) as nomor,\n" +
                "CAST('URAIAN' AS VARCHAR) as uraian,\n" +
                "CAST('Jan' AS VARCHAR) as jan,\n" +
                "CAST('Feb' AS VARCHAR) as feb,\n" +
                "CAST('Mar' AS VARCHAR) as mar,\n" +
                "CAST('Apr' AS VARCHAR) as apr,\n" +
                "CAST('Mei' AS VARCHAR) as mei,\n" +
                "CAST('Jun' AS VARCHAR) as jun,\n" +
                "CAST('Jul' AS VARCHAR) as jul,\n" +
                "CAST('Ags' AS VARCHAR) as ags,\n" +
                "CAST('Sep' AS VARCHAR) as sep,\n" +
                "CAST('Okt' AS VARCHAR) as okt,\n" +
                "CAST('Nov' AS VARCHAR) as nov,\n" +
                "CAST('Des' AS VARCHAR) as des,\n" +
                "CAST('Total' AS VARCHAR) as total,\n" +
                "CAST('%' AS VARCHAR) as persen\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('A' AS VARCHAR) as nomor,\n" +
                "CAST('Ownuse' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(a.id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', a.created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup a\n" +
                "\t\tINNER JOIN im_simrs_rekanan_ops b ON a.id_asuransi = b.id_rekanan_ops\n" +
                "\t\tWHERE a.id_jenis_periksa_pasien = 'rekanan'\n" +
                "\t\tAND a.id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', a.created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tAND b.tipe = 'ptpn'\n" +
                "\t\tAND a.status_periksa = '3'\n" +
                "\t\tGROUP BY date_part('month', a.created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT CAST('B' AS VARCHAR) nomor,\n" +
                "CAST('PIHAK III' AS VARCHAR) uraian,\n" +
                "null as jan,\n" +
                "null as feb,\n" +
                "null as mar,\n" +
                "null as apr,\n" +
                "null as mei,\n" +
                "null as jun,\n" +
                "null as jul,\n" +
                "null as ags,\n" +
                "null as sep,\n" +
                "null as okt,\n" +
                "null as nov,\n" +
                "null as des,\n" +
                "null as total,\n" +
                "null as persen\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('1' AS VARCHAR) as nomor,\n" +
                "CAST('Langganan RS / Jaminan Perusahaan' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(a.id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', a.created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup a\n" +
                "\t\tWHERE a.id_jenis_periksa_pasien = 'paket_perusahaan'\n" +
                "\t\tAND a.id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', a.created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tAND a.status_periksa = '3'\n" +
                "\t\tGROUP BY date_part('month', a.created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('2' AS VARCHAR) as nomor,\n" +
                "CAST('Cash' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup\n" +
                "\t\tWHERE id_jenis_periksa_pasien = 'umum'\n" +
                "\t\tAND id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tAND status_periksa = '3'\n" +
                "\t\tGROUP BY date_part('month', created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('3' AS VARCHAR) as nomor,\n" +
                "CAST('Asuransi' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup\n" +
                "\t\tWHERE id_jenis_periksa_pasien = 'asuransi'\n" +
                "\t\tAND id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tAND status_periksa = '3'\n" +
                "\t\tGROUP BY date_part('month', created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('4' AS VARCHAR) as nomor,\n" +
                "CAST('CIVIC MISION' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup\n" +
                "\t\tWHERE id_jenis_periksa_pasien = 'paket_individu'\n" +
                "\t\tAND id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tGROUP BY date_part('month', created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT CAST('C' AS VARCHAR) nomor,\n" +
                "CAST('BPJS' AS VARCHAR) uraian,\n" +
                "null as jan,\n" +
                "null as feb,\n" +
                "null as mar,\n" +
                "null as apr,\n" +
                "null as mei,\n" +
                "null as jun,\n" +
                "null as jul,\n" +
                "null as ags,\n" +
                "null as sep,\n" +
                "null as okt,\n" +
                "null as nov,\n" +
                "null as des,\n" +
                "null as total,\n" +
                "null as persen\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('1' AS VARCHAR) as nomor,\n" +
                "CAST('BPJS Kesehatan' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup\n" +
                "\t\tWHERE id_jenis_periksa_pasien = 'bpjs'\n" +
                "\t\tAND id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tAND status_periksa = '3'\n" +
                "\t\tGROUP BY date_part('month', created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('2' AS VARCHAR) as nomor,\n" +
                "CAST('BPJS Ketenagakerjaan' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup\n" +
                "\t\tWHERE id_jenis_periksa_pasien = 'bpjs_rekanan'\n" +
                "\t\tAND id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tGROUP BY date_part('month', created_date)\n" +
                "\t)a\n" +
                ")aa\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "CAST('jml' AS VARCHAR) as nomor,\n" +
                "CAST('JUMLAH' AS VARCHAR) uraian,\n" +
                "CAST(SUM(jan) AS VARCHAR) as jan,\n" +
                "CAST(SUM(feb) AS VARCHAR) as feb,\n" +
                "CAST(SUM(mar) AS VARCHAR) as mar,\n" +
                "CAST(SUM(apr) AS VARCHAR) as apr,\n" +
                "CAST(SUM(mei) AS VARCHAR) as mei,\n" +
                "CAST(SUM(jun) AS VARCHAR) as jun,\n" +
                "CAST(SUM(jul) AS VARCHAR) as jul,\n" +
                "CAST(SUM(ags) AS VARCHAR) as ags,\n" +
                "CAST(SUM(sep) AS VARCHAR) as sep,\n" +
                "CAST(SUM(okt) AS VARCHAR) as okt,\n" +
                "CAST(SUM(nov) AS VARCHAR) as nov,\n" +
                "CAST(SUM(des) AS VARCHAR) as des,\n" +
                "CAST((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des))AS VARCHAR) as total,\n" +
                "CAST(ROUND(((SUM(jan)+SUM(feb)+SUM(mar)+SUM(apr)+SUM(mei)+SUM(jun)+SUM(jul)+SUM(ags)+SUM(sep)+SUM(okt)+SUM(nov)+SUM(des)) / \n" +
                "(SELECT COUNT(id_detail_checkup)\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE date_part('year', created_date) = '"+bean.getTahun()+"'\n" +
                "AND id_pelayanan IN ("+idPelayanan+") \n" +
                "AND status_periksa = '3')*100),1) AS VARCHAR\n" +
                ") as persen\n" +
                "FROM (\n" +
                "\tSELECT \n" +
                "\tCASE WHEN bulan = 1 THEN jumlah ELSE 0 END as jan,\n" +
                "\tCASE WHEN bulan = 2 THEN jumlah ELSE 0 END as feb,\n" +
                "\tCASE WHEN bulan = 3 THEN jumlah ELSE 0 END as mar,\n" +
                "\tCASE WHEN bulan = 4 THEN jumlah ELSE 0 END as apr,\n" +
                "\tCASE WHEN bulan = 5 THEN jumlah ELSE 0 END as mei,\n" +
                "\tCASE WHEN bulan = 6 THEN jumlah ELSE 0 END as jun,\n" +
                "\tCASE WHEN bulan = 7 THEN jumlah ELSE 0 END as jul,\n" +
                "\tCASE WHEN bulan = 8 THEN jumlah ELSE 0 END as ags,\n" +
                "\tCASE WHEN bulan = 9 THEN jumlah ELSE 0 END as sep,\n" +
                "\tCASE WHEN bulan = 10 THEN jumlah ELSE 0 END as okt,\n" +
                "\tCASE WHEN bulan = 11 THEN jumlah ELSE 0 END as nov,\n" +
                "\tCASE WHEN bulan = 12 THEN jumlah ELSE 0 END as des\n" +
                "\tFROM(\n" +
                "\t\tSELECT\n" +
                "\t\tCOUNT(a.id_detail_checkup) jumlah,\n" +
                "\t\tdate_part('month', a.created_date) bulan\n" +
                "\t\tFROM it_simrs_header_detail_checkup a\n" +
                "\t\tWHERE a.id_pelayanan IN ("+idPelayanan+") \n" +
                "\t\tAND date_part('year', a.created_date) = '"+bean.getTahun()+"'\n" +
                "\t\tAND a.status_periksa = '3'\n" +
                "\t\tGROUP BY date_part('month', a.created_date)\n" +
                "\t)a\n" +
                ")aa";
    }
}
