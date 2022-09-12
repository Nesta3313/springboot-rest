package com.richarddev.spring.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "product_table", schema = "testProject")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int product_id;
    private String name;
    private long quantity;
    @JsonProperty("available")
    private boolean isAvailable;


}
