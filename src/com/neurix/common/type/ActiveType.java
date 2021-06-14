package com.neurix.common.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aji Noor on 05/02/2021
 */
public enum ActiveType {
    ACTIVE(1, "Y"),
    NONACTIVE(0, "N");

    private final int code;
    private final String description;
    private static Map<Integer, String> mMap;

    private ActiveType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescriptionByCode(int code) {
        if (mMap == null) {
            initializeMapping();
        }
        if (mMap.containsKey(code)) {
            return mMap.get(code);
        }
        return null;
    }
    private static void initializeMapping() {
        mMap = new HashMap<Integer, String>();
        for (ActiveType s : ActiveType.values()) {
            mMap.put(s.code, s.description);
        }
    }

}
