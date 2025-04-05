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
@Table(name = "image")
public class ItemImage {

    @Id
    @GeneratedValue
    private Integer id;
    private String path;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
