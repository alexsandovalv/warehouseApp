package pe.edu.upc.warehouse.model;

public class Cliente {

    private int id;
    private String imagen;
    private String tipo;
    private String nombre;
    private String razon_social;
    private String tipo_dni;
    private Long n_documento;
    private String email;
    private String distrito;
    private String telefono;
    private String direccion;
    private String referencia;
    private String latitud;
    private String longitud;

    public Cliente(){}

    public Cliente(String tipo, String nombre, String razon_social, String tipo_dni, Long n_documento,
                   String email, String telefono, String latitud, String longitud) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.razon_social = razon_social;
        this.tipo_dni = tipo_dni;
        this.n_documento = n_documento;
        this.email = email;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Cliente(String tipo, String nombre, String razon_social, String tipo_dni,
                   Long n_documento, String email, String distrito, String telefono, String direccion,
                   String referencia, String latitud, String longitud) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.razon_social = razon_social;
        this.tipo_dni = tipo_dni;
        this.n_documento = n_documento;
        this.email = email;
        this.distrito = distrito;
        this.telefono = telefono;
        this.direccion = direccion;
        this.referencia = referencia;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Cliente(int id, String imagen, String tipo, String nombre, String razon_social, String tipo_dni,
                   Long n_documento, String email, String distrito, String telefono, String direccion,
                   String referencia, String latitud, String longitud) {
        this.id = id;
        this.imagen = imagen;
        this.tipo = tipo;
        this.nombre = nombre;
        this.razon_social = razon_social;
        this.tipo_dni = tipo_dni;
        this.n_documento = n_documento;
        this.email = email;
        this.distrito = distrito;
        this.telefono = telefono;
        this.direccion = direccion;
        this.referencia = referencia;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
