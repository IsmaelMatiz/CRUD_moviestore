package Model;

import java.time.LocalDate;

public class DTOLoans {
    private Integer id;
    private Integer id_cliente;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_devolucion;
    private Double precio;
    private Integer peliculas_id;
    private Integer peliculas_clientes_id_cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getPeliculas_id() {
        return peliculas_id;
    }

    public void setPeliculas_id(Integer peliculas_id) {
        this.peliculas_id = peliculas_id;
    }

    public Integer getPeliculas_clientes_id_cliente() {
        return peliculas_clientes_id_cliente;
    }

    public void setPeliculas_clientes_id_cliente(Integer peliculas_clientes_id_cliente) {
        this.peliculas_clientes_id_cliente = peliculas_clientes_id_cliente;
    }
}
