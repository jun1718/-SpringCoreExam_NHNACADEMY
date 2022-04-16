package com.nhnacademy.exam.main.info;

import java.util.Objects;

public class WaterBill implements Comparable<WaterBill>{
    private final String city;
    private final String sector;
    private final long sectionStart;
    private final long sectionEnd;
    private final long unitPrice;
    private long billTotal = 0L;

    public WaterBill(String city, String sector, long sectionStart, long sectionEnd, long unitPrice) {
        this.city = city;
        this.sector = sector;
        this.sectionStart = sectionStart;
        this.sectionEnd = sectionEnd;
        this.unitPrice = unitPrice;
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

    public void setBillTotal(long billTotal) {
        this.billTotal = billTotal;
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
