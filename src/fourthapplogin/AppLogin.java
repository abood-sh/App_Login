package fourthapplogin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label welcome = new Label("Welcome");
        welcome.setId("wlcom");

        Label username = new Label("User Name ");
        TextField name_field = new TextField();
        HBox username_box = new HBox(10, username, name_field);
        username_box.setAlignment(Pos.CENTER);

        Label Passowrd = new Label("Passowrd ");
        TextField Passowrd_field = new TextField();
        HBox Passowrd_box = new HBox(10, Passowrd, Passowrd_field);
        Passowrd_box.setAlignment(Pos.CENTER);

        Button signin = new Button("Sign In");
        Button exit = new Button("Exit");
        HBox signin_exit_box = new HBox(10, signin, exit);
        signin.setId("button");
        exit.setId("button");
        signin_exit_box.setAlignment(Pos.CENTER);

        GridPane gridP_log = new GridPane();
        gridP_log.add(welcome, 1, 0);
        gridP_log.add(username_box, 1, 1);
        gridP_log.add(Passowrd_box, 1, 2);
        gridP_log.add(signin_exit_box, 1, 3);
        gridP_log.setAlignment(Pos.CENTER);
        gridP_log.setVgap(20);
        Scene Login = new Scene(gridP_log, 800, 400);
        Login.getStylesheets().add("style.css");
        primaryStage.setTitle("Login Page");

        signin.setOnAction(sign -> {
            File validationFile;
            Scanner input;
            try {
                validationFile = new File("validationFile.txt");
                if (!validationFile.exists()) {
                    validationFile.createNewFile();
                }
                input = new Scanner(validationFile);
                boolean Found = false;

                while (input.hasNextLine()) {
                    String[] validate = input.nextLine().split(" ");
                    if (name_field.getText().equals(validate[0]) && Passowrd_field.getText().equals(validate[1])) {
                        Found = true;
                        System.out.println("isValidate");
                        Button add = new Button("Add Student");
                        Button view = new Button("View Student");
                        add.setId("button");
                        view.setId("button");
                        VBox addAndView = new VBox(6, add, view);
                        addAndView.setAlignment(Pos.CENTER);
                        Scene IntirnalLogin = new Scene(addAndView, 800, 400);
                        IntirnalLogin.getStylesheets().add("style.css");
                        primaryStage.setTitle("Options Page");
                        primaryStage.setScene(IntirnalLogin);

                        add.setOnAction(addStd -> {
                            primaryStage.setTitle("Student Entry Page");

                            Label stdData = new Label("Student Data");
                            HBox BoxstdData = new HBox(stdData);

                            stdData.setAlignment(Pos.CENTER);
                            BoxstdData.setAlignment(Pos.CENTER);
                            stdData.setId("wlcom");

                            Label id = new Label("Id:");
                            TextField textField_Id = new TextField();
                            HBox BoxId = new HBox(10, id, textField_Id);
                            BoxId.setAlignment(Pos.CENTER);

                            Label name = new Label("Name:");
                            TextField textField_Name = new TextField();
                            HBox BoxName = new HBox(10, name, textField_Name);
                            BoxName.setAlignment(Pos.CENTER);

                            Label major = new Label("Major");
                            TextField textField_Major = new TextField();
                            HBox box_major = new HBox(10, major, textField_Major);
                            box_major.setAlignment(Pos.CENTER);

                            Label grade = new Label("Grade:");
                            TextField textField_Grade = new TextField();
                            HBox box_grade = new HBox(10, grade, textField_Grade);
                            box_grade.setAlignment(Pos.CENTER);

                            Button btnAdd = new Button("Add");
                            btnAdd.setId("button");
                            Button btnReset = new Button("Reset");
                            btnReset.setId("button");
                            Button btnExit = new Button("Exit");
                            btnExit.setId("button");

                            HBox box_Add_Reset_Exit = new HBox(10, btnAdd, btnReset, btnExit);
                            box_Add_Reset_Exit.setAlignment(Pos.CENTER);

                            ListView listView_Std = new ListView();
                            listView_Std.setPrefHeight(250);
                            listView_Std.setPrefWidth(300);

                            GridPane std_Pane = new GridPane();

                            VBox box_allOfControl = new VBox(10, BoxId, BoxName, box_major
                                    , box_grade, box_Add_Reset_Exit);

                            std_Pane.add(stdData, 1, 0);
                            std_Pane.add(box_allOfControl, 1, 1);
                            std_Pane.add(listView_Std, 2, 1);

                            std_Pane.setHgap(20);
                            std_Pane.setVgap(10);
                            std_Pane.setAlignment(Pos.CENTER);

                            Scene std_Entry = new Scene(std_Pane, 800, 400);
                            std_Entry.getStylesheets().add("style.css");
                            primaryStage.setScene(std_Entry);

                            btnAdd.setOnAction(ad -> {
                                Student std = new Student();
                                try {
                                    std.setId(Integer.parseInt(textField_Id.getText()));
                                    std.setName(textField_Name.getText());
                                    std.setMajor(textField_Major.getText());
                                    std.setGrade(Double.parseDouble(textField_Grade.getText()));

                                } catch (Exception e) {
                                    System.out.println("Error Exception");
                                }
                                listView_Std.getItems().addAll(std);

                                Collections.sort(listView_Std.getItems(), new Comparator<Student>() {
                                    @Override
                                    public int compare(Student t, Student t1) {
                                        if (t.getGrade() == t1.getGrade()) {
                                            return 0;
                                        } else if (t.getGrade() > t1.getGrade()) {
                                            return -1;
                                        } else if (t.getGrade() < t1.getGrade()) {
                                            return 1;
                                        }
                                        return 0;
                                    }
                                });
//Lamda
                                btnReset.setOnAction(reset -> {
                                    textField_Id.setText(null);
                                    textField_Name.setText(null);
                                    textField_Major.setText(null);
                                    textField_Grade.setText(null);
                                });

                                btnExit.setOnAction(value -> {
                                    primaryStage.close();
                                });
                            });
                        }
                        );
                    }
                }
                if (!Found) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Your Acount not Saved "
                            , ButtonType.OK);
                    alert.show();

                }
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Created .. ");
            } catch (IOException ex) {
                System.out.println("Error in Input/Output ");
            }

        });

        exit.setOnAction(ex -> {
            primaryStage.close();
        });

        primaryStage.setScene(Login);
        primaryStage.show();
    }

}
