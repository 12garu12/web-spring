package com.practice.webspring.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;



/*  Getters and Setters ***********************************************************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long calcularImporte(){
        return this.cantidad.longValue();
    }

    private static final long serialVersionUID = 1L;
}
