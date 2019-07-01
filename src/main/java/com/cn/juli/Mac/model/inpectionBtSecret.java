package com.cn.juli.Mac.model;

public class inpectionBtSecret {
    private Integer indexId;

    private String obuSurfaceNumber;

    private String btSecret;

    private Integer btStatu;

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public String getObuSurfaceNumber() {
        return obuSurfaceNumber;
    }

    public void setObuSurfaceNumber(String obuSurfaceNumber) {
        this.obuSurfaceNumber = obuSurfaceNumber == null ? null : obuSurfaceNumber.trim();
    }

    public String getBtSecret() {
        return btSecret;
    }

    public void setBtSecret(String btSecret) {
        this.btSecret = btSecret == null ? null : btSecret.trim();
    }

    public Integer getBtStatu() {
        return btStatu;
    }

    public void setBtStatu(Integer btStatu) {
        this.btStatu = btStatu;
    }
}