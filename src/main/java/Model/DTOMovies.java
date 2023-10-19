package Model;

public class DTOMovies {
    private Integer id;
    private String isbn;
    private String nombre;
    private String descripcion;
    private Integer unidadesDisponibles;
    private DTOCategorias Categoria;

    public DTOCategorias getCategoria() {
        return Categoria;
    }

    public void setCategoria(DTOCategorias categoria) {
        Categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    @Override
    public String toString() {
        return "DTOMovies{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", unidadesDisponibles=" + unidadesDisponibles +
                ", Categoria=" + Categoria +
                '}';
    }
}
