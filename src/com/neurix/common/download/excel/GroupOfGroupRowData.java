package com.neurix.common.download.excel;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/03/13
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

public class GroupOfGroupRowData
{
  private String rowTitle;
  private List<GroupRowData> listOfGroupRowDatas;

  public String getRowTitle()
  {
    return this.rowTitle;
  }

  public void setRowTitle(String rowTitle) {
    this.rowTitle = rowTitle;
  }

  public List<GroupRowData> getListOfGroupRowDatas() {
    return this.listOfGroupRowDatas;
  }

  public void setListOfGroupRowDatas(List<GroupRowData> listOfGroupRowDatas) {
    this.listOfGroupRowDatas = listOfGroupRowDatas;
  }
}