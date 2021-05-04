package com.neurix.common.download.excel;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/03/13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public class DownloadUtil {

    protected static transient Logger logger = Logger.getLogger(DownloadUtil.class);

    public static HSSFWorkbook generateExcelOutput(String reportTitle, String reportPeriode, List listOfColumn, List listOfData, List listOfSumColumn) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Report Excel");

        HSSFRow row = sheet.createRow(0);
        HSSFFont fontTitle = wb.createFont();
        fontTitle.setFontHeightInPoints((short) 14);
        fontTitle.setFontName("Arial");
        fontTitle.setBoldweight((short) 6);

        HSSFCellStyle styleTitle = wb.createCellStyle();
        styleTitle.setFont(fontTitle);

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellStyle(styleTitle);
        cell.setCellValue(reportTitle);

        if (reportPeriode!=null && !"".equalsIgnoreCase(reportPeriode)) {
            row = sheet.createRow(1);
            HSSFFont fontPeriode = wb.createFont();
            fontPeriode.setFontHeightInPoints((short) 12);
            fontPeriode.setFontName("Arial");
            fontPeriode.setBoldweight((short) 6);

            HSSFCellStyle stylePeriode = wb.createCellStyle();
            styleTitle.setFont(fontPeriode);

            cell = row.createCell((short) 0);
            cell.setCellStyle(stylePeriode);
            cell.setCellValue("Periode : " + reportPeriode);
        }

        row = sheet.createRow(2);
        HSSFFont fontTanggalCetak = wb.createFont();
        fontTanggalCetak.setFontHeightInPoints((short) 11);
        fontTanggalCetak.setFontName("Arial");

        HSSFCellStyle styleTanggalCetak = wb.createCellStyle();
        styleTitle.setFont(fontTanggalCetak);

        cell = row.createCell((short) 0);
        cell.setCellStyle(styleTanggalCetak);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        cell.setCellValue("Printed date : " + format.format(Calendar.getInstance().getTime()));

        if (listOfData != null && listOfData.size() > 0) {
            String className = listOfData.get(0).getClass().getSimpleName();

            if ((className != null) && (className.equalsIgnoreCase("GroupOfGroupOfGroupRowData"))) {
                int row_awal_group;
                Iterator itGroup;
                Iterator i;
                if ((listOfColumn != null) && (!listOfColumn.isEmpty())) {
                    generateHeaderColumn(listOfColumn, sheet, wb);

                    row_awal_group = 6;
                    int row_awal = 7;

                    for (itGroup = listOfData.iterator(); itGroup.hasNext();) {
                        GroupOfGroupOfGroupRowData groupOfGroupOfGroupRowData = (GroupOfGroupOfGroupRowData) itGroup.next();
                        List listOfGroupOfGroupOfGroupData = groupOfGroupOfGroupRowData.getListOfGroupRowDatas();
                        String titleOfGroupOfGroupOfGroup = groupOfGroupOfGroupRowData.getRowTitle();

                        if ((titleOfGroupOfGroupOfGroup != null) && (!titleOfGroupOfGroupOfGroup.equalsIgnoreCase(""))) {
                            row = sheet.createRow((short) row_awal_group);
                            cell = row.createCell((short) 0);
                            HSSFCellStyle styleCell = wb.createCellStyle();
                            styleCell.setAlignment((short) 1);
                            cell.setCellStyle(styleCell);
                            cell.setCellValue(titleOfGroupOfGroupOfGroup);
                            row_awal_group++;
                        }

                        if ((listOfGroupOfGroupOfGroupData != null) && (!listOfGroupOfGroupOfGroupData.isEmpty())) {
                            List listOfSumGroupOfGroup = new ArrayList();

                            for (i = listOfGroupOfGroupOfGroupData.iterator(); i.hasNext();) {
                                GroupOfGroupRowData groupOfGroupRowData = (GroupOfGroupRowData) i.next();
                                List listOfGroupOfGroupData = groupOfGroupRowData.getListOfGroupRowDatas();
                                String titleOfGroupOfGroup = groupOfGroupRowData.getRowTitle();

                                if ((titleOfGroupOfGroup != null) && (!titleOfGroupOfGroup.equalsIgnoreCase(""))) {
                                    row = sheet.createRow((short) row_awal_group);
                                    cell = row.createCell((short) 0);
                                    HSSFCellStyle styleCell = wb.createCellStyle();
                                    styleCell.setAlignment((short) 1);
                                    cell.setCellStyle(styleCell);
                                    cell.setCellValue(titleOfGroupOfGroup);
                                    row_awal_group++;
                                }

                                if ((listOfGroupOfGroupData != null) && (!listOfGroupOfGroupData.isEmpty())) {
                                    List listOfSumGroup = new ArrayList();

                                    for (Iterator it = listOfGroupOfGroupData.iterator(); it.hasNext();) {
                                        GroupRowData groupRowData = (GroupRowData) it.next();
                                        List listOfRowData = groupRowData.getListOfRowData();
                                        String titleOfGroupData = groupRowData.getRowTitle();

                                        List sumGroup = generateRowData(listOfRowData, listOfSumColumn, titleOfGroupData, row_awal_group, sheet, wb);
                                        row_awal_group += listOfRowData.size() + 2;

                                        listOfSumGroup.add(sumGroup);
                                    }
                                    generateSumGroup(listOfSumGroup, listOfSumColumn, row_awal_group, sheet, wb);
                                    row_awal_group++;
                                }
                            }
                        }

                    }

                } else {
                    logger.error("Tidak mengandung columns !!");
                }
            } else if ((className != null) && (className.equalsIgnoreCase("GroupOfGroupRowData"))) {
                int row_awal;
                Iterator it;
                if ((listOfColumn != null) && (!listOfColumn.isEmpty())) {
                    generateHeaderColumn(listOfColumn, sheet, wb);

                    row_awal = 6;

                    for (it = listOfData.iterator(); it.hasNext();) {
                        GroupOfGroupRowData groupOfGroupRowData = (GroupOfGroupRowData) it.next();
                        List listOfGroupOfGroupData = groupOfGroupRowData.getListOfGroupRowDatas();
                        String titleOfGroupOfGroup = groupOfGroupRowData.getRowTitle();

                        if ((titleOfGroupOfGroup != null) && (!titleOfGroupOfGroup.equalsIgnoreCase(""))) {
                            row = sheet.createRow((short) row_awal);
                            cell = row.createCell((short) 0);
                            HSSFCellStyle styleCell = wb.createCellStyle();
                            styleCell.setAlignment((short) 1);
                            cell.setCellStyle(styleCell);
                            cell.setCellValue(titleOfGroupOfGroup);
                            row_awal++;
                        }

                        if ((listOfGroupOfGroupData != null) && (!listOfGroupOfGroupData.isEmpty())) {
                            List listOfSumGroup = new ArrayList();

                            for (Iterator i = listOfGroupOfGroupData.iterator(); i.hasNext();) {
                                GroupRowData groupRowData = (GroupRowData) i.next();
                                List listOfRowData = groupRowData.getListOfRowData();
                                String titleOfGroupData = groupRowData.getRowTitle();
                                List sumGroup = generateRowData(listOfRowData, listOfSumColumn, titleOfGroupData, row_awal, sheet, wb);
                                row_awal += listOfRowData.size() + 2;
                                listOfSumGroup.add(sumGroup);
                            }

                            generateSumGroup(listOfSumGroup, listOfSumColumn, row_awal, sheet, wb);

                            row_awal++;
                        }

                    }

                } else {
                    logger.error("Tidak mengandung columns !!");
                }

            } else if ((className != null) && (className.equalsIgnoreCase("GroupRowData"))) {
                if ((listOfColumn != null) && (!listOfColumn.isEmpty())) {
                    generateHeaderColumn(listOfColumn, sheet, wb);

                    List listOfSumGroup = new ArrayList();

                    int row_awal = 6;
                    for (Iterator i = listOfData.iterator(); i.hasNext();) {
                        GroupRowData groupRowData = (GroupRowData) i.next();
                        List listOfRowData = groupRowData.getListOfRowData();
                        String titleOfGroupData = groupRowData.getRowTitle();
                        List sumGroup = generateRowData(listOfRowData, listOfSumColumn, titleOfGroupData, row_awal, sheet, wb);
                        row_awal += listOfRowData.size() + 2;
                        listOfSumGroup.add(sumGroup);
                    }

                    generateSumGroup(listOfSumGroup, listOfSumColumn, row_awal, sheet, wb);
                } else {
                    logger.error("Tidak mengandung columns !!");
                }

            } else if ((className != null) && (className.equalsIgnoreCase("RowData"))) {
                if ((listOfColumn != null) && (!listOfColumn.isEmpty())) {
                    generateHeaderColumn(listOfColumn, sheet, wb);
                    generateRowData(listOfData, listOfSumColumn, "", 6, sheet, wb);
                } else {
                    logger.error("Tidak mengandung columns !!");
                }

            }

        } else {
            logger.error("Tidak mengandung data !!");
        }


        return  wb;

//        comment cause use struts2 stream action
//        HttpServletResponse response = ServletActionContext.getResponse();
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        response.setContentType("application/force-download");
//        OutputStream outputStream = response.getOutputStream();
//        response.setBufferSize(wb.getBytes().length);
//        wb.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
    }

    private static void generateHeaderColumn(List listOfColumn, HSSFSheet sheet, HSSFWorkbook wb) {
        HSSFRow row = sheet.createRow(5);
        HSSFFont fontColumn = wb.createFont();
        fontColumn.setFontHeightInPoints((short) 10);
        fontColumn.setFontName("Arial");
        fontColumn.setBoldweight((short) 6);

        HSSFCellStyle styleColumns = wb.createCellStyle();
        styleColumns.setFont(fontColumn);
        styleColumns.setFillForegroundColor((short) 55);
        styleColumns.setFillPattern((short) 1);
        styleColumns.setBorderBottom((short) 1);
        styleColumns.setBottomBorderColor((short) 8);
        styleColumns.setBorderLeft((short) 1);
        styleColumns.setBottomBorderColor((short) 8);
        styleColumns.setBorderRight((short) 1);
        styleColumns.setBottomBorderColor((short) 8);
        styleColumns.setBorderTop((short) 1);
        styleColumns.setTopBorderColor((short) 8);

        int j = 0;

        for (Iterator i = listOfColumn.iterator(); i.hasNext(); j++) {
            HSSFCell cell = row.createCell((short) j);
            cell.setCellStyle(styleColumns);
            cell.setCellValue((String) i.next());
        }
    }

    private static List generateRowData(List listOfRowData, List listOfSumColumn, String titleOfGroup, int row_awal, HSSFSheet sheet, HSSFWorkbook wb) {
        double[] doubleColumns = null;
        int[] intColumns = null;
        int n = 0;

        if ((titleOfGroup != null) && (!titleOfGroup.equalsIgnoreCase(""))) {
            HSSFRow row = sheet.createRow((short) row_awal);
            HSSFCell cell = row.createCell((short) 0);
            HSSFCellStyle styleCell = wb.createCellStyle();
            styleCell.setAlignment((short) 1);
            cell.setCellStyle(styleCell);
            cell.setCellValue(titleOfGroup);
            n++;
        }

        for (Iterator i = listOfRowData.iterator(); i.hasNext(); n++) {
            RowData rowData = (RowData) i.next();
            List listOfCell = rowData.getListOfCell();
            HSSFRow row = sheet.createRow((short) row_awal + n);
            int m = 0;

            for (Iterator k = listOfCell.iterator(); k.hasNext(); m++) {
                CellDetail cellDetail = (CellDetail) k.next();
                HSSFCell cell = row.createCell((short) m);
                HSSFCellStyle styleCell = wb.createCellStyle();
                styleCell.setAlignment((short) cellDetail.getAlignmentCell());
                cell.setCellStyle(styleCell);
                if (cellDetail.getTypeCell() == 0) {
                    cell.setCellValue(cellDetail.getValueCellDouble());

                    if ((listOfSumColumn != null) && (listOfSumColumn.contains(Integer.valueOf(cellDetail.getCellID())))) {
                        if (doubleColumns == null) {
                            doubleColumns = new double[listOfCell.size()];
                        }
                        doubleColumns[cellDetail.getCellID()] += cellDetail.getValueCellDouble();
                    }
                } else if (cellDetail.getTypeCell() == 1) {
                    cell.setCellValue(cellDetail.getValueCellInt());

                    if ((listOfSumColumn != null) && (listOfSumColumn.contains(Integer.valueOf(cellDetail.getCellID())))) {
                        if (intColumns == null) {
                            intColumns = new int[listOfCell.size()];
                        }
                        intColumns[cellDetail.getCellID()] += cellDetail.getValueCellInt();
                    }
                } else if (cellDetail.getTypeCell() == 2) {
                    cell.setCellValue(cellDetail.getValueCellString());
                } else if (cellDetail.getTypeCell() == 3) {
                    cell.setCellValue(cellDetail.getValueCellDate());
                }

            }

        }

        List listOfResultSum = generateRowJumlah(intColumns, doubleColumns, listOfSumColumn, row_awal, n, sheet, wb);

        return listOfResultSum;
    }

    private static List generateRowJumlah(int[] intColumns, double[] doubleColumns, List listOfSumColumn, int row_awal, int row_terakhir, HSSFSheet sheet, HSSFWorkbook wb) {
        List listOfResultSum = new ArrayList();

        if ((intColumns != null) && (doubleColumns != null)) {
            for (int i = 0; i < intColumns.length; i++) {
                if (intColumns[i] > doubleColumns[i])
                    listOfResultSum.add(Integer.valueOf(intColumns[i]));
                else if (intColumns[i] < doubleColumns[i])
                    listOfResultSum.add(Double.valueOf(doubleColumns[i]));
                else if ((intColumns[i] == 0) && (doubleColumns[i] == 0.0D))
                    listOfResultSum.add(Integer.valueOf(0));
                else {
                    listOfResultSum.add(Integer.valueOf(0));
                }
            }
        } else if ((intColumns == null) && (doubleColumns != null)) {
            for (int i = 0; i < doubleColumns.length; i++) {
                listOfResultSum.add(Double.valueOf(doubleColumns[i]));
            }
        } else if ((intColumns != null) && (doubleColumns == null)) {
            for (int i = 0; i < intColumns.length; i++)
                listOfResultSum.add(Integer.valueOf(intColumns[i]));
        }
        HSSFRow row;
        Iterator i;
        if ((listOfSumColumn != null) && (!listOfSumColumn.isEmpty())) {
            row = sheet.createRow((short) row_awal + row_terakhir);

            for (i = listOfSumColumn.iterator(); i.hasNext();) {
                Integer objColumn = (Integer) i.next();
                int columnNo = objColumn.intValue();

                HSSFCell cell = row.createCell((short) columnNo);
                HSSFCellStyle styleCell = wb.createCellStyle();
                styleCell.setAlignment((short) 3);
                cell.setCellStyle(styleCell);

                if ((listOfResultSum != null) && (!listOfResultSum.isEmpty())) {
                    if (listOfResultSum.get(columnNo).getClass().getSimpleName().equalsIgnoreCase("double"))
                        cell.setCellValue(((Double) listOfResultSum.get(columnNo)).doubleValue());
                    else if (listOfResultSum.get(columnNo).getClass().getSimpleName().equalsIgnoreCase("integer")) {
                        cell.setCellValue(((Integer) listOfResultSum.get(columnNo)).intValue());
                    }
                }
            }
        }

        return listOfResultSum;
    }

    private static void generateSumGroup(List listOfSumGroup, List listOfSumColumn, int row_awal, HSSFSheet sheet, HSSFWorkbook wb) {
        HSSFRow row;
        Iterator i;
        if ((listOfSumColumn != null) && (!listOfSumColumn.isEmpty())) {
            row = sheet.createRow((short) row_awal);

            for (i = listOfSumColumn.iterator(); i.hasNext();) {
                Integer objColumn = (Integer) i.next();
                int columnNo = objColumn.intValue();

                HSSFCell cell = row.createCell((short) columnNo);
                HSSFCellStyle styleCell = wb.createCellStyle();
                styleCell.setAlignment((short) 3);
                cell.setCellStyle(styleCell);

                double sumGroupOfRowDataDouble = 0.0D;
                int sumGroupOfRowDataInt = 0;
                boolean flagInt = false;
                boolean flagDouble = false;
                for (Iterator j = listOfSumGroup.iterator(); j.hasNext();) {
                    List listOfSumRowData = (List) j.next();

                    if ((listOfSumRowData != null) && (!listOfSumRowData.isEmpty())) {
                        if (listOfSumRowData.get(columnNo).getClass().getSimpleName().equalsIgnoreCase("double")) {
                            sumGroupOfRowDataDouble += ((Double) listOfSumRowData.get(columnNo)).doubleValue();
                            flagDouble = true;
                        } else if (listOfSumRowData.get(columnNo).getClass().getSimpleName().equalsIgnoreCase("integer")) {
                            sumGroupOfRowDataInt += ((Integer) listOfSumRowData.get(columnNo)).intValue();
                            flagInt = true;
                        }
                    }
                }

                if (flagDouble)
                    cell.setCellValue(sumGroupOfRowDataDouble);
                else if (flagInt)
                    cell.setCellValue(sumGroupOfRowDataInt);
            }
        }
    }
}
