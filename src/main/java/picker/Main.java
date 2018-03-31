package picker;

import picker.module.ParseFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    
    @Override
    public void start(final Stage stage) throws IOException {
        stage.setTitle("File Chooser Sample");

        Button openCSV = new Button("Order csv path file");
        Button openTXT = new Button("Order txt path");
        Button save = new Button("Save");

        final GridPane rootNode = new GridPane();
        rootNode.setPadding(new Insets(15));
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.BASELINE_LEFT);

        Scene myScene = new Scene(rootNode, 400, 300);

        final TextField csvName = new TextField();
        csvName.setEditable(false);
        rootNode.add(csvName, 1, 1);
        rootNode.add(openCSV, 2, 1);
        GridPane.setHalignment(openCSV, HPos.LEFT);
        final TextField txtName = new TextField();
        txtName.setEditable(false);
        rootNode.add(txtName, 1, 3);
        rootNode.add(openTXT,2,3);
        rootNode.add(save,1,5);
        GridPane.setHalignment(save,HPos.CENTER);

        openCSV.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
                fileChooser.getExtensionFilters().add(extentionFilter);

                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    csvName.setText(file.getAbsolutePath());
                }
            }
        });
        openTXT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showSaveDialog(stage);

                if(file != null){
                    txtName.setText(file.getAbsolutePath());
                }
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               /* ProgressBar pb = new ProgressBar();
                rootNode.add(pb,1,6);*/
                if(!csvName.getText().isEmpty() && !txtName.getText().isEmpty()){
                    try {
                        ParseFile.getInstance().getFiles(csvName.getText(), txtName.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        stage.setScene(myScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}