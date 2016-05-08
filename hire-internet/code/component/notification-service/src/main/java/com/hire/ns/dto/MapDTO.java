package com.hire.ns.dto;

import java.util.HashMap;
import java.util.Map;

public class MapDTO {
    private Map<String, Object> map = new HashMap<String, Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
