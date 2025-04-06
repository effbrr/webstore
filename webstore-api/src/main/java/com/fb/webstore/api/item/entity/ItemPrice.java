package com.fb.webstore.api.item.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_price")
public class ItemPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal defaultPrice;

    @NotNull
    private BigDecimal currentPrice;

    @NotNull
    private LocalDate validFrom;

    @NotNull
    private LocalDate validTo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ItemPrice(final BigDecimal defaultPrice) {
        this(defaultPrice, defaultPrice);
    }

    public ItemPrice(final BigDecimal defaultPrice, final BigDecimal currentPrice) {
        this.defaultPrice = defaultPrice;
        this.currentPrice = currentPrice;
        this.validFrom = LocalDate.now();
        this.validTo = this.validFrom.plusDays(999);
    }
}
