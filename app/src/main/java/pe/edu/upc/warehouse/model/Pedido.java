package pe.edu.upc.warehouse.model;

public class Pedido {

    private Integer id;
    private Integer clientid;
    private String estado;
    private String nota;
    private String creacion;
    private String entrega;

    public Pedido() {
    }

    public Pedido(Integer id, Integer clientid, String estado, String nota, String creacion, String entrega) {
        this.id = id;
        this.clientid = clientid;
        this.estado = estado;
        this.nota = nota;
        this.creacion = creacion;
        this.entrega = entrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
}
