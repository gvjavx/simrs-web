package com.neurix.common.util;

import com.neurix.authorization.role.model.Roles;
import com.neurix.authorization.user.model.UserDetailsLogin;
import com.neurix.common.constant.CommonConstant;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 19/02/13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class CommonUtil {

    protected static transient Logger logger = Logger.getLogger(CommonUtil.class);

    public static String userLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
//                String username=userDetailsLogin.getUsername();

                String username=userDetailsLogin.getUserNameDetail();

                return username;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String userIdLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String username=userDetailsLogin.getUsername();

                return username;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String userPosisiId() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String username=userDetailsLogin.getPositionId();

                return username;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String roleAsLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
//                String username=userDetailsLogin.getUsername();

                String roleName=((Roles)userDetailsLogin.getRoles().get(0)).getRoleName();

                return roleName;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }
    public static String roleIdAsLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
//                String username=userDetailsLogin.getUsername();

                String roleId=String.valueOf(((Roles)userDetailsLogin.getRoles().get(0)).getRoleId());

                return roleId;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }
    public static String userAreaId() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
//                String username=userDetailsLogin.getUsername();

                String areaId=userDetailsLogin.getAreaId();

                return areaId;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String userAreaName() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
//                String username=userDetailsLogin.getUsername();

                String areaName=userDetailsLogin.getAreaName();

                return areaName;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String userBranchLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
//                String username=userDetailsLogin.getUsername();

                String branchId=userDetailsLogin.getBranchId();

                return branchId;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String userBranchNameLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();

                String branchName=userDetailsLogin.getBranchName();

                return branchName;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String companyNameLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String companyName=userDetailsLogin.getCompanyName();

                return companyName;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String companyIdLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String companyId=userDetailsLogin.getCompanyId();

                return companyId;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }


    //for payment gateway customer login
    public static String customerIdLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String customerId=userDetailsLogin.getCustomerId();

                return customerId;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String customerNameLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String customerName=userDetailsLogin.getCustomerName();

                return customerName;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String customerAddressLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String customerAddress=userDetailsLogin.getCustomerAddress();

                return customerAddress;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String customerEmailLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String customerEmail=userDetailsLogin.getCustomerEmail();

                return customerEmail;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String customerNPWPLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();
                String customerNPWP=userDetailsLogin.getCustomerNPWP();

                return customerNPWP;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String userPelayananIdLogin() throws UsernameNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContextImpl.getAuthentication() != null) {
                UserDetailsLogin userDetailsLogin=(UserDetailsLogin)securityContextImpl.getAuthentication().getPrincipal();

                String pelayananId = userDetailsLogin.getIdPleyanan();

                return pelayananId;
            } else {
                throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
            }

        } else {
            throw new UsernameNotFoundException("User Not Found, session may be time out. Please login again.");
        }
    }

    public static String numbericFormat(BigDecimal number,String pattern) {
//        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY); //for indo money format
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US); //for international money format
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern(pattern);
        return df.format(number);
    }

    public static BigDecimal dobelToBigDecimal(Double number){
        Double data = new Double(number);
        BigDecimal hasil = BigDecimal.valueOf(data);

        return hasil;
    }

    public static String longDateFormat(Timestamp timestamp) {
        if (timestamp!=null) {
            FastDateFormat dateFormat = FastDateFormat.getInstance("dd/MM/yyyy HH:mm:ss");
            return dateFormat.format(timestamp.getTime());
        } else {
            return "";
        }
    }
    public static java.util.Date dateSqltoDateUtil(Date date) {
        return new java.util.Date(date.getTime());
    }
    public static Date dateUtiltoDateSql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
    public static int elapsed(Calendar before, Calendar after, int field) {
        Calendar clone = (Calendar) before.clone(); // Otherwise changes are been reflected.
        int elapsed = -1;
        while (!clone.after(after)) {
            clone.add(field, 1);
            elapsed++;
        }
        return elapsed;
    }

    public static String waitingTime(Calendar start, Calendar end) {

        Integer[] elapsed = new Integer[6];
        Calendar clone = (Calendar) start.clone(); // Otherwise changes are been reflected.
        elapsed[0] = elapsed(clone, end, Calendar.YEAR);
        clone.add(Calendar.YEAR, elapsed[0]);
        elapsed[1] = elapsed(clone, end, Calendar.MONTH);
        clone.add(Calendar.MONTH, elapsed[1]);
        elapsed[2] = elapsed(clone, end, Calendar.DATE);
        clone.add(Calendar.DATE, elapsed[2]);
        elapsed[3] = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 3600000;
        clone.add(Calendar.HOUR, elapsed[3]);
        elapsed[4] = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 60000;
        clone.add(Calendar.MINUTE, elapsed[4]);
        elapsed[5] = (int) (end.getTimeInMillis() - clone.getTimeInMillis()) / 1000;

        String selisih = String.format("%d days - %d hours - %d minutes - %d seconds", elapsed[2], elapsed[3], elapsed[4], elapsed[5]);

        return selisih;
    }
    public static String getPropertyParams(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String value = "null";
        try {
            input = CommonUtil.class.getClassLoader().getResourceAsStream("simrs.properties");
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            value = prop.getProperty(key);
            input.close();
            logger.info("success to load simrs.properties");
        } catch (IOException ex) {
            logger.error("Found error," + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Found error," + e.getMessage());
                }
            }
        }
        return value;
    }
    public static String getUploadFolderValue() {

        Properties prop = new Properties();
        InputStream input = null;

        String value = "null";
        try {

            input = CommonUtil.class.getClassLoader().getResourceAsStream("simrs.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty("upload.folder");
            input.close();
            logger.info("success to load simrs.properties");
        } catch (IOException ex) {
            logger.error("Found error," + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("Found error," + e.getMessage());
                }
            }
        }

        return value;

    }

    public static String simpleDateFormat(Date date) {
        FastDateFormat dateFormat = FastDateFormat.getInstance("dd-MM-yyyy");
        String stDate = dateFormat.format(date.getTime());
        return stDate;
    }

    public static Date convertStringToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date sql = null;
        try{
            java.util.Date parsed = format.parse(date);
            sql = new java.sql.Date(parsed.getTime());
        }catch (Exception E){
        }
        return sql;
    }

    public static Date convertStringToDate2(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sql = null;
        try{
            java.util.Date parsed = format.parse(date);
            sql = new java.sql.Date(parsed.getTime());
        }catch (Exception E){
        }
        return sql;
    }

    public static Date convertToDate(String stDate) {
        if (stDate != null && !"".equalsIgnoreCase(stDate)) {
            String sDay;
            String sMonth;
            String sYear;
            int day;
            int month;
            int year;
            sDay = stDate.substring(0, 2);
            day = Integer.parseInt(sDay);
            sMonth = stDate.substring(3, 5);
            month = Integer.parseInt(sMonth);
            sYear = stDate.substring(6);
            year = Integer.parseInt(sYear);
            return new Date(year - 1900, month - 1, day);
        } else {
            return null;
        }

    }

    public static Timestamp convertToTimestamp(String stDate) {
        if (stDate != null && !"".equalsIgnoreCase(stDate)) {
            String sDay;
            String sMonth;
            String sYear;
            int day;
            int month;
            int year;
            sDay = stDate.substring(0, 2);
            day = Integer.parseInt(sDay);
            sMonth = stDate.substring(3, 5);
            month = Integer.parseInt(sMonth);
            sYear = stDate.substring(6);
            year = Integer.parseInt(sYear);
            return new Timestamp(new Date(year - 1900, month - 1, day).getTime());
        } else {
            return null;
        }

    }

    public static String addJamBayar(Timestamp date) {
        String jam = "null";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        java.util.Date createdDate = date;
        java.util.Date newJam = DateUtils.addMinutes(createdDate, CommonConstant.ADD_JAM_BAYAR);
        jam = dateFormat.format(newJam);

        return jam;
    }

    public static String getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int res = cal.getActualMaximum(Calendar.DATE);

        return String.valueOf(res);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static Date convertTimestampToDate(Timestamp stamp){
        Date date = new Date(stamp.getTime());
        return date;
    }

    public static String convertDateToString(Date date){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String text = df.format(date);
        return text;
    }

    public static String convertDateToString(java.util.Date date){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String text = df.format(date);
        return text;
    }
    public static String convertDateToString2(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(date);
        return text;
    }
    public static String convertDateToString2(java.util.Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String text = df.format(date);
        return text;
    }
    public static String removeCommaNumber(String nilai){
        String hasil = nilai.replace(",", "");
        return hasil;
    }

    public static String convertTimestampToString(Timestamp date){
        String tanggal = "";
        String DATE_FORMAT = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date tanggalApp = date;
        tanggal = sdf.format(tanggalApp);

        return tanggal;
    }

    public static String convertDateToDay(Date tanggal){
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggal);
        int hari = cal.get(Calendar.DAY_OF_WEEK);

        String stHari = "";
        switch (hari){
            case 2 :
                stHari = "Senin";
                break;
            case 3 :
                stHari = "Selasa";
                break;
            case 4 :
                stHari = "Rabu";
                break;
            case 5 :
                stHari = "Kamis";
                break;
            case 6 :
                stHari = "Jum'at";
                break;
            case 7 :
                stHari = "Sabtu";
                break;
            case 1 :
                stHari = "Minggu";
                break;
        }
        return stHari;
    }
    public static String convertDateToMonth(Date tanggal){
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggal);
        int month = cal.get(Calendar.MONTH);
        String stBulan = "";
        switch (month){
            case 0 :
                stBulan = "Januari";
                break;
            case 1 :
                stBulan = "Februari";
                break;
            case 2 :
                stBulan = "Maret";
                break;
            case 3 :
                stBulan = "April";
                break;
            case 4 :
                stBulan = "Mei";
                break;
            case 5 :
                stBulan = "Juni";
                break;
            case 6 :
                stBulan = "Juli";
                break;
            case 7 :
                stBulan = "Agustus";
                break;
            case 8 :
                stBulan = "September";
                break;
            case 9 :
                stBulan = "Oktober";
                break;
            case 10 :
                stBulan = "November";
                break;
            case 11 :
                stBulan = "Desember";
                break;
        }
        return stBulan;
    }
    public static String convertDateToYearTerbilang(Date tanggal){
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggal);
        int year = cal.get(Calendar.YEAR);

        return angkaToTerbilang((long) year);
    }
    public static String convertDateToTanggalTerbilang(Date tanggal){
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggal);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return angkaToTerbilang((long) day);
    }
    public static String angkaToTerbilang(Long angka){
        String[] huruf={"","Satu","Dua","Tiga","Empat","Lima","Enam","Tujuh","Delapan","Sembilan","Sepuluh","Sebelas"};
        if(angka < 12)
            return huruf[angka.intValue()];
        if(angka >=12 && angka <= 19)
            return huruf[angka.intValue() % 10] + " Belas";
        if(angka >= 20 && angka <= 99)
            return angkaToTerbilang(angka / 10) + " Puluh " + huruf[angka.intValue() % 10];
        if(angka >= 100 && angka <= 199)
            return "Seratus " + angkaToTerbilang(angka % 100);
        if(angka >= 200 && angka <= 999)
            return angkaToTerbilang(angka / 100) + " Ratus " + angkaToTerbilang(angka % 100);
        if(angka >= 1000 && angka <= 1999)
            return "Seribu " + angkaToTerbilang(angka % 1000);
        if(angka >= 2000 && angka <= 999999)
            return angkaToTerbilang(angka / 1000) + " Ribu " + angkaToTerbilang(angka % 1000);
        if(angka >= 1000000 && angka <= 999999999)
            return angkaToTerbilang(angka / 1000000) + " Juta " + angkaToTerbilang(angka % 1000000);
        if(angka >= 1000000000 && angka <= 999999999999L)
            return angkaToTerbilang(angka / 1000000000) + " Milyar " + angkaToTerbilang(angka % 1000000000);
        if(angka >= 1000000000000L && angka <= 999999999999999L)
            return angkaToTerbilang(angka / 1000000000000L) + " Triliun " + angkaToTerbilang(angka % 1000000000000L);
        if(angka >= 1000000000000000L && angka <= 999999999999999999L)
            return angkaToTerbilang(angka / 1000000000000000L) + " Quadrilyun " + angkaToTerbilang(angka % 1000000000000000L);
        return "";
    }

    public static String convertNumberToStringBulan(String bulan){
        String stBulan = "";
        switch (bulan){
            case "01" :
                stBulan = "Januari";
                break;
            case "02" :
                stBulan = "Februari";
                break;
            case "03" :
                stBulan = "Maret";
                break;
            case "04" :
                stBulan = "April";
                break;
            case "05" :
                stBulan = "Mei";
                break;
            case "06" :
                stBulan = "Juni";
                break;
            case "07" :
                stBulan = "Juli";
                break;
            case "08" :
                stBulan = "Agustus";
                break;
            case "09" :
                stBulan = "September";
                break;
            case "10" :
                stBulan = "Oktober";
                break;
            case "11" :
                stBulan = "November";
                break;
            case "12" :
                stBulan = "Desember";
                break;
        }
        return stBulan;
    }


    public static String convertIdBagian(String id){
        String bagian = "-";
        switch (id){
            case "0002" :
                bagian = "Bagian Risk Management dan GCG";
                break;
            case "0003" :
                bagian = "Bagian Teknik";
                break;
            case "0004" :
                bagian = "Bagian Pengadaan";
                break;
            case "0005" :
                bagian = "Bagian Teknologi";
                break;
            case "0006" :
                bagian = "Bagian Keuangan";
                break;
            case "0007" :
                bagian = "Bagian Teknologi Informasi";
                break;
            case "0008" :
                bagian = "Bagian Pemeriksa Non Ops";
                break;
            case "0009" :
                bagian = "Bagian Umum dan Aset";
                break;
            case "0010" :
                bagian = "Bagian SDM";
                break;
            case "0011" :
                bagian = "Bagian Agronomi";
                break;
            case "0012" :
                bagian = "Bagian Sosial Ekonomi";
                break;
            case "0013" :
                bagian = "Bagian Akuntansi";
                break;
            case "0014" :
                bagian = "Bagian Pemeriksa Operasional";
                break;
            case "0015" :
                bagian = "Bagian Riset dan Pengembangan";
                break;
            case "0016" :
                bagian = "Bagian Sekretaris Perusahaan";
                break;
        }
        return bagian;
    }

    public static String statusName(String id){
        String status = "-";
        switch (id) {
            case "00":
                status="Mangkir";
                break;
            case "01":
                status="Sesuai";
                break;
            case "02":
                status="Telat masuk";
                break;
            case "03":
                status="Pulang/Masuk tidak Izin";
                break;
            case "04":
                status="Sesuai Lebih";
                break;
            case "05":
                status="Sesuai Dengan ijin";
                break;
            case "06":
                status="Tidak sesuai ijin";
                break;
            case "07":
                status="Ijin lebih dari sehari";
                break;
            case "08":
                status="Cuti";
                break;
            case "09":
                status="Lembur";
                break;
            case "10":
                status="Blokir";
                break;
            case "11":
                status="Dispensasi";
                break;
            case "12":
                status="SPPD";
                break;
            case "13":
                status="Training";
                break;
            case "14":
                status="Pulang Tidak Sesuai";
                break;
            case "15":
                status="Masuk saat Libur";
                break;
        }
        return status;
    }

    public static String divisiName(String id){
        String status = "-";
        switch (id) {
            case "D01":
                status="Tanaman";
                break;
            case "D02":
                status="Teknik";
                break;
            case "D03":
                status="Sekper";
                break;
            case "D05":
                status="Risbang";
                break;
            case "D04":
                status="SPI";
                break;
            case "D06":
                status="Akuntansi Keuangan";
                break;
            case "D07":
                status="SDM & Umum";
                break;
        }
        return status;
    }

    public static String tipeNotifName(String id){
        String status = "-";
        switch (id) {
            case "TN23":
                status="Training";
                break;
            case "TN22":
                status="Medical Record";
                break;
            case "TI":
                status="SPPD";
                break;
            case "TN33":
                status="Absensi";
                break;
            case "TN44":
                status="Indisipliner";
                break;
            case "TN55":
                status="Dispensasi";
                break;
            case "TN66":
                status="Cuti Pegawai";
                break;
            case "TN77":
                status="Lembur";
                break;
            case "TN88":
                status="Ijin Keluar Kantor";
                break;
            case "TN99":
                status="Rekruitmen Pabrik";
                break;
            case "umum":
                status="Pemberitahuan";
                break;
        }
        return status;
    }
    public static int countDays(String startDate, String endDate) throws Exception {
        int workingDays = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar startdate = Calendar.getInstance();
        startdate.setTime(sdf.parse(startDate));
        Calendar enddate = Calendar.getInstance();
        enddate.setTime(sdf.parse(endDate));

        while (!startdate.after(enddate)) {
            int day = startdate.get(Calendar.DAY_OF_WEEK);
            System.out.println(day);
            if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)) {
                workingDays++;
            }
            // increment start date, otherwise while will give infinite loop
            startdate.add(Calendar.DATE, 1);
        }
        return workingDays;
    }
    public static int countAllDays(String startDate, String endDate) throws Exception {
        int days = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Calendar startdate = Calendar.getInstance();
        startdate.setTime(sdf.parse(startDate));
        Calendar enddate = Calendar.getInstance();
        enddate.setTime(sdf.parse(endDate));

        while (!startdate.after(enddate)) {
            days++;
            // increment start date, otherwise while will give infinite loop
            startdate.add(Calendar.DATE, 1);
        }
        return days;
    }
    public static int getAge(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("You don't exist yet");
        }
        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
            age--;
        }
        return age;
    }

    public static Date convertStringToTime(String hh_mm_ss){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        java.sql.Date sql = null;
        try{
            java.util.Date parsed = format.parse(hh_mm_ss);
            sql = new java.sql.Date(parsed.getTime());
        }catch (Exception E){
        }

        return sql;
    }

    public static java.util.Date addDay(java.util.Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
    public static java.util.Date addMonth(java.util.Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }

    public static java.util.Date addYear(java.util.Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }
    public static BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(new BigDecimal(100));
    }
    public static String cekDateBeforeNow(String tglAwal){
        String status = "";
        //mengambil Tanggal Sekarang
        java.util.Date now = new java.util.Date();
        String stNow = CommonUtil.convertDateToString(now);
        String[] arrNow = stNow.split("-");
        Integer intNow = Integer.parseInt(arrNow[0]+arrNow[1]+arrNow[2]);

        String tglawalnew = tglAwal.replaceAll("/","-");
        String[] arrTglAwal = tglawalnew.split("-");
        Integer intTglAwal = Integer.parseInt(arrTglAwal[0]+arrTglAwal[1]+arrTglAwal[2]);

        Integer jarak = intTglAwal - intNow;

        if (jarak>0){
            status="Boleh";
        }else {
            status="tidak";
        }
        return status;
    }

    public static String convertTipePtt(String tipePttId){
        switch (tipePttId){
            case "t":
                return "Tantiem";
            case "R":
                return "Rekreasi";
            case "tk":
                return "Tunjangan Khusus";
            case "bPer":
                return "Biaya Pernikahan";
            case "bPin":
                return "Biaya Pindah";
            case "bPis":
                return "Biaya Pisah";
            default:
                return "";
        }
    }

    public static String convertTipePayroll(String tipePayroll){
        switch (tipePayroll){
            case "PR":
                return "Payroll";
            case "T":
                return "THR";
            case "JP":
                return "Jasa Operasional";
            case "JB":
                return "Jubileum";
            case "PN":
                return "Pensiun";
            case "IN":
                return "Insentif";
            case "CP":
                return "Cuti Panjang";
            case "CT":
                return "Cuti Tahunan";
            default:
                return "";
        }
    }

    public static String getDateParted(Date date, String tipe){
        //create calander instance and get required params
        switch (tipe){
            case "YEAR":
                SimpleDateFormat y = new SimpleDateFormat("yyyy");
                return y.format(date);
            case "MONTH":
                SimpleDateFormat m = new SimpleDateFormat("M");
                return m.format(date);
            case "DAY":
                SimpleDateFormat d = new SimpleDateFormat("d");
                return d.format(date);
            default: return "";
        }
    }

    public static String stDateSeq(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public static Timestamp getCurrentDateTimes(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static String bulanSebelumnya (String bulan){
        switch (bulan){
            case "01":
                return "";
            case "02":
                return "01";
            case "03":
                return "02";
            case "04":
                return "03";
            case "05":
                return "04";
            case "06":
                return "05";
            case "07":
                return "06";
            case "08":
                return "07";
            case "09":
                return "08";
            case "10":
                return "09";
            case "11":
                return "10";
            case "12":
                return "11";
            default:
                return "";
        }
    }

    public static String periodeBulanSebelumnya (String bulan,String tahun){
        String tahunSebelumnya = String.valueOf(Integer.parseInt(tahun)-1);

        switch (bulan){
            case "01":
                return tahunSebelumnya+"-12";
            case "02":
                return tahun+"-01";
            case "03":
                return tahun+"-02";
            case "04":
                return tahun+"-03";
            case "05":
                return tahun+"-04";
            case "06":
                return tahun+"-05";
            case "07":
                return tahun+"-06";
            case "08":
                return tahun+"-07";
            case "09":
                return tahun+"-08";
            case "10":
                return tahun+"-09";
            case "11":
                return tahun+"-10";
            case "12":
                return tahun+"-11";
            default:
                return "";
        }
    }

    public static Double SubtractJamAwalDanJamAkhir(String jamAwal, String jamAkhir, String status) throws ParseException {
        java.text.DateFormat df = new java.text.SimpleDateFormat("dd:MM:yyyy HH:mm");
        java.util.Date date1 = df.parse("01:01:2000 "+jamAwal);
        java.util.Date date2 = df.parse("01:01:2000 "+jamAkhir);
        long diff = date2.getTime() - date1.getTime();
        if (diff<0&&status.equalsIgnoreCase("positif")){
            date2 = df.parse("02:01:2000 "+jamAkhir);
            diff = date2.getTime() - date1.getTime();
        }
        int timeInSeconds = (int) (diff / 1000);
        int hours, minutes;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        double hasil=hours;
        if (minutes<15){hasil=hasil+0;}
        else if (minutes<30){hasil=hasil+0;}
        else if (minutes<45){hasil=hasil+0.5;}
        else if (minutes<60){hasil=hasil+0.5;}
        return hasil;
    }

    public static Double SubtractJam(String jamAwal, String jamAkhir) throws ParseException {
        java.text.DateFormat df = new java.text.SimpleDateFormat("dd:MM:yyyy HH:mm");
        java.util.Date date1 = df.parse("01:01:2000 "+jamAwal);
        java.util.Date date2 = df.parse("01:01:2000 "+jamAkhir);
        long diff = date2.getTime() - date1.getTime();
        int timeInSeconds = (int) (diff / 1000);
        int hours, minutes;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;
        double hasil=hours;
        if (minutes<15){hasil=hasil+0;}
        else if (minutes<30){hasil=hasil+0;}
        else if (minutes<45){hasil=hasil+0.5;}
        else if (minutes<60){hasil=hasil+0.5;}
        return hasil;
    }

    public static int getRandomNumberInts(int min, int max){
        Random random = new Random();
        return random.ints(min,(max+1)).findFirst().getAsInt();
    }
}