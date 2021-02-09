package com.neurix.simrs.transaksi.laporan.model;

public class ConstanQuery {
    public static String plusQuery(){
        return "\nUNION ALL\n" +
                "SELECT \n" +
                "null as nomor,\n" +
                "null as uraian,\n" +
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
                "UNION ALL\n";
    }
}
