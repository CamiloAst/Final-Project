package co.edu.uniquindio.proyecto.sgre.mapping.dto;

public record EventoDto(
        String nombre,
        String id,
        String capacidadMax,
        String empleado,
        String descripcion,
        String correo,
        String fecha,
        Double reserva
    )  {
}
