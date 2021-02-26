package com.practice.webspring.app.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private String observacion;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id") // Cuando la relacion es en un solo sentido indicamos cual sera la llave foranea, siendo esta una relacion unidireccional
    private List<ItemFactura> items;

    /* Constructor **************************************************************************************************/

    public Factura(){
        this.items = new ArrayList<>();
    }

/* Persistencia en la base de datos  *********************************************************************************/
    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }

    /* Getters and Setters  ******************************************************************************************/

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

   public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    public void addItemFactura(ItemFactura item){
        this.items.add(item);
    }

    public Double getTotal(){
        Double total = 0.0;

        for (ItemFactura item : items) {
            total += item.calcularImporte();
        }

        return total;
    }

    private static final long serialVersionUID = 1L;
}
