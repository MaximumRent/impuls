package com.megapapa.impulse.broadcaster;

import java.util.LinkedList;
import java.util.List;

public class BroadcastConfiguration {

    private static final int DEFAULT_MAX_DEPTH = 1;

    private boolean includeAll;
    private List<String> includedFields;
    private List<String> excludedFields;
    private int maxDepth = DEFAULT_MAX_DEPTH;

    public BroadcastConfiguration() {
        this.includedFields = new LinkedList<>();
        this.excludedFields = new LinkedList<>();
    }

    public boolean isIncludeAll() {
        return includeAll;
    }

    public void includeAll() {
        this.includeAll = true;
    }

    public void include(String fieldName) {
        includeAll = false;
        includedFields.add(fieldName);
    }

    public void exclude(String fieldName) {
        includeAll = false;
        excludedFields.add(fieldName);
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public List<String> getIncludedFields() {
        return includedFields;
    }

    public List<String> getExcludedFields() {
        return excludedFields;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        BroadcastConfiguration objConfig = ((BroadcastConfiguration) obj);
        boolean isEqual = true;
        for (String field : this.includedFields) {
            isEqual &= objConfig.includedFields.contains(field);
        }
        for (String field : this.excludedFields) {
            isEqual &= objConfig.excludedFields.contains(field);
        }
        isEqual &= this.includeAll == objConfig.includeAll;
        return isEqual;
    }
}
