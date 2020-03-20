package com.neurix.authorization.function.model;

import com.neurix.common.displaytag.DisplayObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ferdi on 31/01/2015.
 */
public class Menus extends Functions implements Serializable, Cloneable {
    private List<Functions> listOfFunctions;
    private String statusPath;
    private boolean initForm;
    private DisplayObject displayObjectDiv;

    public DisplayObject getDisplayObjectDiv() {
        return displayObjectDiv;
    }

    public void setDisplayObjectDiv(DisplayObject displayObjectDiv) {
        this.displayObjectDiv = displayObjectDiv;
    }

    public boolean isInitForm() {
        return initForm;
    }

    public void setInitForm(boolean initForm) {
        this.initForm = initForm;
    }

    public String getStatusPath() {
        return statusPath;
    }

    public void setStatusPath(String statusPath) {
        this.statusPath = statusPath;
    }

    public List<Functions> getListOfFunctions() {
        return listOfFunctions;
    }

    public void setListOfFunctions(List<Functions> listOfFunctions) {
        this.listOfFunctions = listOfFunctions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menus)) return false;

        Menus menus = (Menus) o;

        if (initForm != menus.initForm) return false;
        if (displayObjectDiv != null ? !displayObjectDiv.equals(menus.displayObjectDiv) : menus.displayObjectDiv != null)
            return false;
        if (listOfFunctions != null ? !listOfFunctions.equals(menus.listOfFunctions) : menus.listOfFunctions != null)
            return false;
        if (statusPath != null ? !statusPath.equals(menus.statusPath) : menus.statusPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = listOfFunctions != null ? listOfFunctions.hashCode() : 0;
        result = 31 * result + (statusPath != null ? statusPath.hashCode() : 0);
        result = 31 * result + (initForm ? 1 : 0);
        result = 31 * result + (displayObjectDiv != null ? displayObjectDiv.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
