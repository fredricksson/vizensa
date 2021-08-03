/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.time.LocalDate;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static mz.sgv.controller.FuncionarioController.funcionarioStatic;
import mz.sgv.factory.Help;
import mz.sgv.model.Salario;

/**
 *
 * @author doroteia
 */
public class SalarioController {

    @FXML
    ComboBox combo_mes;
    @FXML
    private Label nome;
    @FXML
    private TableView<Salario> tableSalario;
    @FXML
    private TextField salarioField;
    @FXML
    private Label total;

    public void initialize() {
        all();
        combo_mes.getItems().add("Janeiro");
        combo_mes.getItems().add("fevereiro");
        combo_mes.getItems().add("Marco");
        combo_mes.getItems().add("Maio");
        combo_mes.getItems().add("Junho");
        combo_mes.getItems().add("Julho");
        combo_mes.getItems().add("Agosto");
        combo_mes.getItems().add("Setembro");
        combo_mes.getItems().add("Outubro");
        combo_mes.getItems().add("Novembro");
        combo_mes.getItems().add("Dezembro");
        nome.setText(funcionarioStatic.getNome() + " " + funcionarioStatic.getApelido());
        salarioField.setText(funcionarioStatic.getSalario() + "");
    }

    @FXML
    public void confirmarPagamento(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        if (!salarioField.getText().isEmpty() && combo_mes.getSelectionModel().getSelectedIndex() >= 0) {
              a.setContentText("Pretende realmente fazer pagamento?");
            Optional<ButtonType> result = a.showAndWait();
          
            if (result.get() == ButtonType.OK) {

                ProgressIndicator progress = new ProgressIndicator(0.0);
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setGraphic(progress);
                a1.setContentText("Processando..");

               a1.show();
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final int max = 10;
                        for (int i = 1; i <= max; i++) {
                            updateProgress(i, max);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        Salario salario = new Salario();
                        salario.setId_funcionario(funcionarioStatic.getId());
                        salario.setMes(combo_mes.getSelectionModel().getSelectedItem().toString());
                        salario.setValor(Float.parseFloat(salarioField.getText()));
                        LocalDate date = LocalDate.now();
                        salario.setData(Help.date_from_string(date.toString()));
                        salario.save();
                        all();
                        return null;
                    }
                };
                progress.progressProperty().bind(task.progressProperty()); new Thread(task).start();
                
            } else {
                a.close();
            }

        } else {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    salarioField.setStyle("-fx-border-color: red;-fx-text-fill:red");
                    combo_mes.setStyle("-fx-border-color: red;-fx-text-fill:red");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }

                    Platform.runLater(() -> {
                       
                        salarioField.setStyle("-fx-border-color: #94734C;-fx-text-fill:black;");
                        combo_mes.setStyle("-fx-border-color: #94734C;-fx-text-fill:red;");
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.show();
                        a.setContentText("Campo mes nao foi seleccionado ou salario invalido");
                    });
                    return null;
                }
            };
            new Thread(task).start();

        }
    }

    public void all() {
        System.out.println(funcionarioStatic.getId());
        ObservableList<Salario> list = FXCollections.observableArrayList(Salario.FindById(funcionarioStatic.getId()));

        tableSalario.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mes"));
        tableSalario.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableSalario.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("data"));

        tableSalario.setItems(list);

    }

}
