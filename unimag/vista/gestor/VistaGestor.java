package org.unimag.vista.gestor;

import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.unimag.recurso.constante.Configuracion;

public class VistaGestor {

    private final Stage miEscenario;
    private final Scene miEscena;
    private final Pane miPanelCuerpo;
    private final BorderPane miPanelPrincipal;

    public VistaGestor(Stage esce) {
        miEscenario = esce;
        miPanelCuerpo = new Pane();
        miPanelPrincipal = new BorderPane();

        VistaCabecera cabecera = new VistaCabecera(esce, miPanelPrincipal,
                Configuracion.ALTO_CABECERA);

        miPanelPrincipal.setTop(cabecera);
        miPanelPrincipal.setCenter(miPanelCuerpo);

        miEscena = new Scene(miPanelPrincipal, Configuracion.ANCHO_APP,
                Configuracion.ALTO_APP);
        miEscenario.setScene(miEscena);
    }
     public void habilitarXCerrar(Runnable accion) {
        miEscenario.setOnCloseRequest((e) -> {
            e.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de salida");
            alert.setHeaderText("¿Estás seguro de que quieres salir?");
            alert.setContentText("Si sales, se perderán todos los cambios no guardados.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                accion.run();
            }
        });
    }
}