package micevisualization;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class MouseViz extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =  new FXMLLoader();
        loader.setLocation(getClass().getResource("GUIScaffold.fxml"));
        Parent root = loader.load();
        AppStageController asc = loader.getController();
        asc.prox.setStage(stage);
        Scene scene = new Scene(root, 1000, 760);
        
        stage.setTitle("Mice Visualization Program");
        Image image = new Image("resources/icon-300.png");
        stage.getIcons().add(image);
        
        stage.setScene(scene);
        stage.show();

        String chart = getClass().getResource("/resources/ProximityChart.xlsx").getPath();
        System.out.println(chart);
        File f = new File(chart);
        if (f.exists()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Proximity Chart Exists");
            alert.setHeaderText(null);
            alert.setContentText("A Proximity Chart Spreadsheet already Exists.\nUse this file?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                asc.prox.fillProxArray(f);
            } else {
                //Prompt for Proximity Coefficient Spreadsheet and create array of lookup values
                asc.prox.fillProxArray(asc.prox.showFileDialog(asc.prox.OPEN,
                        "Select Proximity Chart File", asc.prox.EXCELFILE, asc.prox.XLSFILES));            }
        }
        else {
            //Prompt for Proximity Coefficient Spreadsheet and create array of lookup values
            asc.prox.fillProxArray(asc.prox.showFileDialog(asc.prox.OPEN,
                    "Select Proximity Chart File", asc.prox.EXCELFILE, asc.prox.XLSFILES));
        }
        //Closes All windows if main stage is closed
        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }
    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file and perform clean up
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
