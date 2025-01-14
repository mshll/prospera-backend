package com.example.prospera.property.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_property_value")
public class PropertyValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer propertyValue;
    private Double availableShares;
    private Date valueDate;

    @ManyToOne
    @JsonIgnoreProperties(value={"propertyValues"})
    private PropertyEntity property;
}
