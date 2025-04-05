package com.fb.webstore.api.item;

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
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private Integer supply;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private ItemPrice price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ItemCategory category;



}
