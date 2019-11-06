package com.neurix.common.download.excel;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/03/13
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

public class GroupRowData
{
  private String rowTitle;
  private List<RowData> listOfRowData;

  public String getRowTitle()
  {
    return this.rowTitle;
  }

  public void setRowTitle(String rowTitle) {
    this.rowTitle = rowTitle;
  }

  public List<RowData> getListOfRowData() {
    return this.listOfRowData;
  }

  public void setListOfRowData(List<RowData> listOfRowData) {
    this.listOfRowData = listOfRowData;
  }
}