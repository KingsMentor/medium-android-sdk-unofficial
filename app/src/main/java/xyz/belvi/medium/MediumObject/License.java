package xyz.belvi.medium.MediumObject;

/**
 * Created by zone2 on 6/12/16.
 */
public enum License {
    ALL_RIGHT_RESERVED("all-right-reserved"),
    PUBLIC_DOMAIN("public-domain"),
    CC_40_BY("cc-40-by"),
    CC_40_BY_SA("cc-40-by-sa"),
    CC_40_BY_ND("cc-40-by-nd"),
    CC_40_BY_NC("cc-40-by-nc"),
    CC_40_BY_NC_ND("cc-40-by-nc-nd"),
    CC_40_BY_NC_SA("cc-40-by-nc-sa"),
    CC_40_ZERO("cc-40-zero");

    String licenseType;

    License(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseType() {
        return this.licenseType;
    }
}
