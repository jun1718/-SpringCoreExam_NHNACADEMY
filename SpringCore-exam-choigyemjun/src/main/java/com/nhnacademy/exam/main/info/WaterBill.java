package com.nhnacademy.exam.main.info;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WaterBill implements Comparable<WaterBill>{
    private final String city;
    private final String sector;
    private final long sectionStart;
    private final long sectionEnd;
    private final long unitPrice;
    private long billTotal = 0L;

    @JsonCreator
    public WaterBill(@JsonProperty("지자체명") String city, @JsonProperty("업종") String sector, @JsonProperty("구간시작(세제곱미터)") long sectionStart, @JsonProperty("구간끝(세제곱미터)") long sectionEnd, @JsonProperty("구간금액(원)") long unitPrice) {
        this.city = city;
        this.sector = sector;
        this.sectionStart = sectionStart;
        this.sectionEnd = sectionEnd;
        this.unitPrice = unitPrice;
    }

    public void setBillTotal(long billTotal) {
        this.billTotal = billTotal;
    }
    public String getCity() {
        return city;
    }
    public String getSector() {
        return sector;
    }
    public long getUnitPrice() {
        return unitPrice;
    }
    public long getBillTotal() {
        return billTotal;
    }
    public long getSectionStart() {
        return sectionStart;
    }
    public long getSectionEnd() {
        return sectionEnd;
    }


    @Override
    public String toString() {
        return "WaterBill{" +
            "city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", unitPrice=" + unitPrice +
            ", billTotal=" + billTotal +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterBill waterBill = (WaterBill) o;
        return sectionStart == waterBill.sectionStart && sectionEnd == waterBill.sectionEnd &&
            unitPrice == waterBill.unitPrice && billTotal == waterBill.billTotal &&
            Objects.equals(city, waterBill.city) &&
            Objects.equals(sector, waterBill.sector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, sector, sectionStart, sectionEnd, unitPrice, billTotal);
    }

    @Override
    public int compareTo(WaterBill o) {
        if (this.getUnitPrice() > o.getUnitPrice()) {
            return 1;
        }

        if (this.getUnitPrice() < o.getUnitPrice()) {
            return -1;
        }

        return 0;
    }
}
