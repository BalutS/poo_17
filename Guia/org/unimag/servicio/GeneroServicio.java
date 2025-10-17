package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.GeneroDto;
import org.unimag.modelo.Genero;
import org.unimag.recurso.constante.Persistencia;

public class GeneroServicio implements ApiOperacionBD<GeneroDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public GeneroServicio() {
        nombrePersistencia = Persistencia.NOMBRE_GENERO;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(GeneroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(GeneroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public GeneroDto insertInto(GeneroDto dto, String ruta) {
        Genero objGenero = new Genero();
        objGenero.setIdGenero(getSerial());
        objGenero.setNombreGenero(dto.getNombreGenero());
        objGenero.setEstadoGenero(dto.getEstadoGenero());

        String filaGrabar
                = objGenero.getIdGenero() + Persistencia.SEPARADOR_COLUMNAS
                + objGenero.getNombreGenero() + Persistencia.SEPARADOR_COLUMNAS
                + objGenero.getEstadoGenero();
        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdGenero(objGenero.getIdGenero());
            return dto;
        }

        return null;
    }

    @Override
    public List<GeneroDto> selectFrom() {
        List<GeneroDto> arregloGeneroDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int codigo = Integer.parseInt(columnas[0].trim());
                String nombre = columnas[1].trim();
                Boolean estado = Boolean.valueOf(columnas[2].trim());

                arregloGeneroDtos.add(new GeneroDto(codigo, nombre, estado));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return arregloGeneroDtos;
    }

    public List<GeneroDto> selectFromWhereActivos() {
        List<GeneroDto> arregloGeneroDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int codigo = Integer.parseInt(columnas[0].trim());
                String nombre = columnas[1].trim();
                Boolean estado = Boolean.valueOf(columnas[2].trim());

                if (Boolean.TRUE.equals(estado)) {
                    arregloGeneroDtos.add(new GeneroDto(codigo, nombre, estado));
                }

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return arregloGeneroDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(GeneroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    @Override
    public Boolean delectFrom(Integer coidgo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GeneroDto updateSet(Integer codigo, GeneroDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GeneroDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

