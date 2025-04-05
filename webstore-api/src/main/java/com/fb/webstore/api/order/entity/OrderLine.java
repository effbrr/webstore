package com.fb.webstore.api.order.entity;

import com.fb.webstore.api.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;

    //TODO Add price/totalPrice?

}
