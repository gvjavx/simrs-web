package com.neurix.common.exception;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 28/02/13
 * Time: 8:54
 * To change this template use File | Settings | File Templates.
 */
public class GeneralBOException extends RuntimeException {

  public GeneralBOException(Throwable root)
  {
    super(root);
  }

  public GeneralBOException(String string, Throwable root) {
    super(string, root);
  }

  public GeneralBOException(String s) {
    super(s);
  }
}