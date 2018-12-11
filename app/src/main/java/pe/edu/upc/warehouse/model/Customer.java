package pe.edu.upc.warehouse.model;

import java.util.Date;

public class Customer {
    private int id;
    private String tipo;
    private String nombre;
    private String razon_social;
    private String tipo_documento;
    private String n_documento;
    private String email;
    private String distrito;
    private String telefono;
    private String referencia;
    private String estado;
    private Double longitud;
    private Double latitud;
    private Date created_at;
    private Date updated_at;
    private String agregar_pedido_url;
    private String agregar_nota_url;

    public Customer() {
    }

    public Customer(int id, String tipo, String nombre, String razon_social, String tipo_documento, String n_documento, String email, String distrito, String telefono, String referencia, String estado, double longitud, double latitud, Date created_at, Date updated_at, String agregar_pedido_url, String agregar_nota_url) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.razon_social = razon_social;
        this.tipo_documento = tipo_documento;
        this.n_documento = n_documento;
        this.email = email;
        this.distrito = distrito;
        this.telefono = telefono;
        this.referencia = referencia;
        this.estado = estado;
        this.longitud = longitud;
        this.latitud = latitud;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.agregar_pedido_url = agregar_pedido_url;
        this.agregar_nota_url = agregar_nota_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getN_documento() {
        return n_documento;
    }

    public void setN_documento(String n_documento) {
        this.n_documento = n_documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getAgregar_pedido_url() {
        return agregar_pedido_url;
    }

    public void setAgregar_pedido_url(String agregar_pedido_url) {
        this.agregar_pedido_url = agregar_pedido_url;
    }

    public String getAgregar_nota_url() {
        return agregar_nota_url;
    }

    public void setAgregar_nota_url(String agregar_nota_url) {
        this.agregar_nota_url = agregar_nota_url;
    }
}
