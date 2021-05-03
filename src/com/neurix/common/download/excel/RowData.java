package com.neurix.common.download.excel;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/03/13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

public class RowData
{
  private List<CellDetail> listOfCell;

  public List<CellDetail> getListOfCell()
  {
    return this.listOfCell;
  }

  public void setListOfCell(List<CellDetail> listOfCell) {
    this.listOfCell = listOfCell;
  }
}