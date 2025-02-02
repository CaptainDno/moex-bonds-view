package com.captaindno.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class YieldInfo {

    private final double currentPrice;

    private final double yield;

    private final double weightedAveragePrice;

    private final double yieldAtWAPrice;

    @ToString.Exclude
    private final boolean isMaturityDate;

    @ToString.Exclude
    private final long seqNum;

    @JsonCreator
    public YieldInfo(
            @JacksonXmlProperty(isAttribute = true, localName = "EFFECTIVEYIELDWAPRICE") double yieldAtWAPrice,
            @JacksonXmlProperty(isAttribute = true, localName = "WAPRICE") double weightedAveragePrice,
            @JacksonXmlProperty(isAttribute = true, localName = "EFFECTIVEYIELD") double yield,
            @JacksonXmlProperty(isAttribute = true, localName = "PRICE") double currentPrice,
            @JacksonXmlProperty(isAttribute = true, localName = "YIELDDATETYPE") String type,
            @JacksonXmlProperty(isAttribute = true, localName = "SEQNUM") long seqNum
    ) {
        this.yieldAtWAPrice = yieldAtWAPrice;
        this.weightedAveragePrice = weightedAveragePrice;
        this.yield = yield;
        this.currentPrice = currentPrice;
        isMaturityDate = "MATDATE".equals(type);
        this.seqNum = seqNum;
    }
}
