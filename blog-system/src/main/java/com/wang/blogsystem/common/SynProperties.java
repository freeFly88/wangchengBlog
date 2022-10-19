package com.wang.blogsystem.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "syn")
public class SynProperties {

    /**
     * 是否启动 国控 同步到省级库
     */
    private String isEnable;
    /**
     * 省级同步到全国平台  如 str=jiangxi  就指定江西同步到全国
     */
    private String provincialLevel;

    /**
     * 第三方资源同步到省级 如 如 str=jiangxi 就指定某第三方同步到江西
     */
    private String thirdParty;

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getProvincialLevel() {
        return provincialLevel;
    }

    public void setProvincialLevel(String provincialLevel) {
        this.provincialLevel = provincialLevel;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public Boolean isEnable() {
        return Boolean.valueOf(this.isEnable);
    }

    public Boolean isNotEnable() {
        return !isEnable();
    }

    public Boolean isProvincialLevel() {
        for (SynEnum synEnum : SynEnum.values()) {
            if (StringUtils.equals(synEnum.getValue(), this.provincialLevel)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isNotProvincialLevel() {
        return !this.isProvincialLevel();
    }

    public Boolean isThirdParty() {
        for (SynEnum synEnum : SynEnum.values()) {
            if (StringUtils.equals(synEnum.getValue(), this.thirdParty)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isNotThirdParty() {
        return !this.isThirdParty();
    }


    public enum SynEnum {
        JIANGXI("jiangxi", "江西"), YUNNAN("yunnan", "云南");
        private String value;
        private String name;

        SynEnum(String value) {
            this.value = value;
        }

        SynEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}

