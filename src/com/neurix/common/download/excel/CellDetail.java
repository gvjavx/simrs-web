package com.neurix.common.download.excel;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/03/13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class CellDetail
{
  private int valueCellInt = 0;
  private double valueCellDouble = 0.0D;
  private String valueCellString = "";
  private Date valueCellDate;
  private int typeCell;
  public static final int DOUBLE = 0;
  public static final int INTEGER = 1;
  public static final int STRING = 2;
  public static final int DATE = 3;
  public static final int ALIGN_LEFT = 1;
  public static final int ALIGN_CENTER = 2;
  public static final int ALIGN_RIGHT = 3;
  private int cellID;
  private int alignmentCell;

  public void setValueCell(double value)
  {
    this.typeCell = 0;
    this.valueCellDouble = value;
  }

  public void setValueCell(int value) {
    this.typeCell = 1;
    this.valueCellInt = value;
  }

  public void setValueCell(String value) {
    this.typeCell = 2;
    this.valueCellString = value;
  }

  public void setValueCell(Date value) {
    this.typeCell = 3;
    this.valueCellDate = value;
  }

  public int getTypeCell() {
    return this.typeCell;
  }

  public void setTypeCell(int typeCell) {
    this.typeCell = typeCell;
  }

  public double getValueCellDouble() {
    return this.valueCellDouble;
  }

  public int getValueCellInt() {
    return this.valueCellInt;
  }

  public String getValueCellString() {
    if (this.valueCellString == null) this.valueCellString = "";
    return this.valueCellString;
  }

  public String getValueCellDate() {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    if (this.valueCellDate != null) return format.format(this.valueCellDate);
    return "";
  }

  public int getAlignmentCell() {
    return this.alignmentCell;
  }

  public void setAlignmentCell(int alignmentCell) {
    this.alignmentCell = alignmentCell;
  }

  public int getCellID() {
    return this.cellID;
  }

  public void setCellID(int cellID) {
    this.cellID = cellID;
  }
}