package com.neurix.authorization.company.model;

import com.neurix.common.model.BaseModel;

import java.io.Serializable;

/**
 * Created by Ferdi on 05/02/2015.
 */
public class Area extends BaseModel implements Serializable, Comparable<Area> {

    private String areaId;
    private String areaName;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int compareTo(Area o) {
        return this.areaName.toLowerCase().compareTo(o.getAreaName().toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Area)) return false;

        Area area = (Area) o;

        if (areaId != null ? !areaId.equals(area.areaId) : area.areaId != null) return false;
        if (areaName != null ? !areaName.equals(area.areaName) : area.areaName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaId != null ? areaId.hashCode() : 0;
        result = 31 * result + (areaName != null ? areaName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
