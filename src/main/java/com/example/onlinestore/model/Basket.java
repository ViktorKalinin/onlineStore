package com.example.onlinestore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "baskets")
public class Basket {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @ManyToMany
    @JoinTable(name = "baskets_products",
    joinColumns = @JoinColumn(name = "basket_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
