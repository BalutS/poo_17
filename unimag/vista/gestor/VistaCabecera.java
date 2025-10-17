package org.unimag.vista.gestor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.unimag.controlador.EditorialVistaControlador;
import org.unimag.controlador.AutorVistaControlador;
import org.unimag.controlador.LibroVistaControlador;
import org.unimag.recurso.constante.Configuracion;

public class VistaCabecera extends HBox {

    private final int menuAncho = 150;
    private final int menuAlto = 35;
    private final int menuEspacio = 10;

    private final Stage miEscenario;
    private final BorderPane miPanelPrincipal;

    public VistaCabecera(Stage esce, BorderPane bpan, double altoCabecera) {
        this.miEscenario = esce;
        this.miPanelPrincipal = bpan;

        setAlignment(Pos.CENTER_LEFT);
        setSpacing(menuEspacio);
        setPadding(new Insets(0, 20, 0, 20));
        setPrefHeight(altoCabecera);
        setStyle(Configuracion.CABECERA_ESTILO_FONDO);

        crearMenus();
    }

    private void agregarBotonMenu(MenuButton miMenu) {
        miMenu.setCursor(Cursor.HAND);
        miMenu.setPrefWidth(menuAncho);
        miMenu.setPrefHeight(menuAlto);
        getChildren().add(miMenu);
    }

    private void crearMenus() {
        menuEditorial();
        menuAutor();
        menuLibro();
    }

    private void menuEditorial() {
        MenuItem opcionCrear = new MenuItem("Crear editorial");
        MenuItem opcionListar = new MenuItem("Listar editoriales");

        opcionCrear.setOnAction(e -> miPanelPrincipal.setCenter(
                EditorialVistaControlador.crearEditorial(
                        miEscenario,
                        Configuracion.ANCHO_APP,
                        Configuracion.ALTO_CABECERA
                )
        ));

        opcionListar.setOnAction(e -> miPanelPrincipal.setCenter(
                EditorialVistaControlador.listarEditorial(
                        miEscenario,
                        Configuracion.ANCHO_APP,
                        Configuracion.ALTO_CABECERA
                )
        ));

        MenuButton miBoton = new MenuButton("Editorial");
        miBoton.getItems().addAll(opcionCrear, opcionListar);
        agregarBotonMenu(miBoton);
    }

    private void menuAutor() {
        MenuItem opcionCrear = new MenuItem("Crear autor");
        MenuItem opcionListar = new MenuItem("Listar autores");

        opcionCrear.setOnAction(e -> miPanelPrincipal.setCenter(
                AutorVistaControlador.crearAutor(
                        miEscenario,
                        Configuracion.ANCHO_APP,
                        Configuracion.ALTO_CABECERA
                )
        ));

        opcionListar.setOnAction(e -> miPanelPrincipal.setCenter(
                AutorVistaControlador.listarAutor(
                        miEscenario,
                        Configuracion.ANCHO_APP,
                        Configuracion.ALTO_CABECERA
                )
        ));

        MenuButton miBoton = new MenuButton("Autor");
        miBoton.getItems().addAll(opcionCrear, opcionListar);
        agregarBotonMenu(miBoton);
    }

    private void menuLibro() {
        MenuItem opcionCrear = new MenuItem("Crear libro");
        MenuItem opcionListar = new MenuItem("Listar libros");

        opcionCrear.setOnAction(e -> miPanelPrincipal.setCenter(
                LibroVistaControlador.crearLibro(
                        miEscenario,
                        Configuracion.ANCHO_APP,
                        Configuracion.ALTO_CABECERA
                )
        ));

        opcionListar.setOnAction(e -> miPanelPrincipal.setCenter(
                LibroVistaControlador.listarLibro(
                        miEscenario,
                        Configuracion.ANCHO_APP,
                        Configuracion.ALTO_CABECERA
                )
        ));

        MenuButton miBoton = new MenuButton("Libro");
        miBoton.getItems().addAll(opcionCrear, opcionListar);
        agregarBotonMenu(miBoton);
    }
}
