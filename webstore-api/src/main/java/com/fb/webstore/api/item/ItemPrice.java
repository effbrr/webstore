package com.fb.webstore.api.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_price")
public class ItemPrice {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal defaultPrice;
    private BigDecimal currentPrice;
    private Date validFrom;
    private Date validTo;

    public ItemPrice (final BigDecimal defaultPrice) {
        this(defaultPrice, defaultPrice);
    }

    public ItemPrice (final BigDecimal defaultPrice, final BigDecimal currentPrice) {
        this.defaultPrice = defaultPrice;
        this.currentPrice = currentPrice;
        this.validFrom = new Date(System.currentTimeMillis());
        this.validTo = DateUtils.addDays(this.validFrom, 999);
    }
}
