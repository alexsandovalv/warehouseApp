package pe.edu.upc.warehouse.storage.network.entity;

import com.google.gson.annotations.SerializedName;

public class ClienteBL {

    private String tipo;
    private String nombre;
    private String razon_social;

    @SerializedName("tipo_documento")
    private String tipo_dni;
    private Long n_documento;
    private String email;
    private String telefono;
    private String latitud;
    private String longitud;

    private String distrito;
    private String direccion;

    public ClienteBL() {
    }

    public ClienteBL(String tipo, String nombre, Long n_documento, String email, String telefono) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.n_documento = n_documento;
        this.email = email;
        this.telefono = telefono;
    }

    public ClienteBL(String tipo, String nombre, String tipo_dni, Long n_documento, String email, String telefono) {
        this(tipo, nombre, n_documento, email, telefono);
        this.tipo_dni = tipo_dni;
    }

    public ClienteBL(String tipo, String nombre, String tipo_dni, Long n_documento, String email, String telefono,
                     String distrito, String direccion) {
        this(tipo, nombre, tipo_dni, n_documento, email, telefono);
        this.distrito = distrito;
        this.direccion = direccion;
    }

    public ClienteBL(String tipo, String nombre,
                     String razon_social, String tipo_dni, Long n_documento, String email,
                     String telefono, String latitud, String longitud) {
        this(tipo,nombre, n_documento, email, telefono);
        this.tipo_dni = tipo_dni;
        this.razon_social = razon_social;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public String getTipo_dni() {
        return tipo_dni;
    }

    public void setTipo_dni(String tipo_dni) {
        this.tipo_dni = tipo_dni;
    }

    public Long getN_documento() {
        return n_documento;
    }

    public void setN_documento(Long n_documento) {
        this.n_documento = n_documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

