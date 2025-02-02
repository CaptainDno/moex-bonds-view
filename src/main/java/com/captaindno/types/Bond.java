package com.captaindno.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

import java.util.Currency;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Bond {

    private final String ID;
    private final String name;
    private final double faceValue;
    private final Date maturityDate;
    private final Currency currency;
    @JsonIgnore
    private YieldInfo yieldInfo;

    @JsonCreator
    public Bond(
            @JacksonXmlProperty(isAttribute = true, localName = "SECID") String ID,
            @JacksonXmlProperty(isAttribute = true, localName = "SECNAME") String name,
            @JacksonXmlProperty(isAttribute = true, localName = "FACEVALUE") double faceValue,
            @JacksonXmlProperty(isAttribute = true, localName = "MATDATE") Date maturityDate,
            @JacksonXmlProperty(isAttribute = true, localName = "CURRENCYID") String currency
    ) {
        this.ID = ID;
        this.name = name;
        this.faceValue = faceValue;
        this.maturityDate = maturityDate;
        // MOEX uses SUR (Soviet Union Rouble) code, java library does not support it as USSR does not exist anymore
        this.currency = Currency.getInstance(currency.equals("SUR") ? "RUB" : currency);
    }

    public void updateYieldInfo(YieldInfo info) {
        if (info.isMaturityDate() && (yieldInfo == null || info.getSeqNum() > yieldInfo.getSeqNum())) {
            yieldInfo = info;
        }
    }

    @Override
    public String toString() {

        String tradingInfo;
        String cc = currency.getCurrencyCode();

        if (yieldInfo == null) {
            tradingInfo = "║ Нет информации о доходности                                                                                    ║";
        } else {
            double p = yieldInfo.getCurrentPrice();
            double wap = yieldInfo.getWeightedAveragePrice();


            tradingInfo = String.format(
                    """
                    ║  Текущая цена: %6.2f%% (%10.3f %3s)   ┆   Средняя цена: %6.2f%% (%10.3f %3s)                           ║
                    ║  Доходность: %10.3f%%                  ┆   Доходность: %10.3f%%                                          ║""",
                    p, p * 0.01 * faceValue, cc,
                    wap, wap * 0.01 * faceValue, cc,
                    yieldInfo.getYield(),
                    yieldInfo.getYieldAtWAPrice()
            );
        }

        return String.format(
                // 120 char width
                """
                ╔═ %s ═════════════════════════════════════════════════════════════════════════════════════════════════╗
                ║ %-110s ║
                ║  Номинал: %10.3f %3s           Дата погашения: %td.%tm.%tY                                                  ║
                ║┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅┅║
                %s
                ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
                """,
                ID, // 12 chars long
                name,
                faceValue, cc,
                maturityDate, maturityDate, maturityDate,
                tradingInfo
        );
    }
}
