package com.example.humanresources.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zpy
 * @since 2021/12/09
 */
@NoArgsConstructor
@Data
public class IpAddress {

    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("code")
    private Integer code;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("area")
        private String area;
        @JsonProperty("country")
        private String country;
        @JsonProperty("isp_id")
        private String ispId;
        @JsonProperty("queryIp")
        private String queryIp;
        @JsonProperty("city")
        private String city;
        @JsonProperty("ip")
        private String ip;
        @JsonProperty("isp")
        private String isp;
        @JsonProperty("county")
        private String county;
        @JsonProperty("region_id")
        private String regionId;
        @JsonProperty("area_id")
        private String areaId;
        @JsonProperty("county_id")
        private Object countyId;
        @JsonProperty("region")
        private String region;
        @JsonProperty("country_id")
        private String countryId;
        @JsonProperty("city_id")
        private String cityId;
    }

    public String getAddress() {
        String s = " | ";
        StringBuffer sb = new StringBuffer(this.data.country);
        sb.append(s).append(this.data.region).append(s).append(this.data.city).append(s).append(this.data.isp);
        return sb.toString();
    }
}

