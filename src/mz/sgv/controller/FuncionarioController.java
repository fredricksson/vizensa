/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mz.sgv.model.Caminhos;
import mz.sgv.model.Cargo;
import mz.sgv.model.Funcionario;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author doroteia
 */
public class FuncionarioController {

    @FXML
    VBox janela;
    @FXML
    TableView<Funcionario> listaFuncionarios;
    @FXML
    private ComboBox cargo;
    @FXML
    private TextField pesquisa;

    public static Funcionario funcionarioStatic;
   
    public void initialize() {
        BuscarTodos(Funcionario.all());
        buscarCargo();
        pesquisarFuncionario();

    }

    //chama tela de adiccionar Funcionario
    @FXML
    public void adicionarFuncionario(ActionEvent event) throws IOException {
        adicionarFuncionario();

    }

    @FXML
    public void registoEntrada_saida(ActionEvent event) throws IOException {
        mostrarJanela(Caminhos.Registo_login_logout, "Registo de entradas e saidas", Boolean.FALSE);
    }

    @FXML
    public void autenticar(ActionEvent event) throws IOException {
        mostrarJanela(Caminhos.Autenticar, "Autenticar", Boolean.FALSE);
    }

    private void mostrarJanela(String caminho, String title, Boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        stage.setMaximized(maximized);
        stage.setResizable(false);
        stage.show();
    }
    private void addCargo(String caminho, String title, Boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        stage.setMaximized(maximized);
        stage.setOnHidden((e)->{
          buscarCargo();
        });
        
        stage.setResizable(false);
        stage.show();
    }
    private void editar(String caminho, String title, Boolean maximized) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        stage.setMaximized(maximized);
        stage.setResizable(false);
        stage.setOnHidden((event)->{
            if(EditarFuncionarioController.isEditado){
            BuscarTodos(Funcionario.all());
            EditarFuncionarioController.isEditado = false;
            }
        });
        stage.show();
    }

    public void BuscarTodos(List<Funcionario> funcionarioss) {

        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(addAction(funcionarioss));

        listaFuncionarios.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("info"));
        listaFuncionarios.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("id"));
        listaFuncionarios.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nome"));
        listaFuncionarios.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("apelido"));
        listaFuncionarios.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cargo"));
        listaFuncionarios.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("acoes"));
        listaFuncionarios.setItems(funcionarios);

    }

    public void adicionarFuncionario() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminhos.NovoFuncionario));
        Parent parent = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        stage.setResizable(false);
        stage.setOnHiding((WindowEvent event) -> {
            BuscarTodos(Funcionario.all());
        });
        stage.show();
    }

    
    public void buscarCargo(){
        cargo.getItems().clear();
         Cargo.all().forEach((carg) -> {
            cargo.getItems().add(carg.getCargo());
        });
    }
    
    public List<Funcionario> addAction(List<Funcionario> funcionarios) {

        funcionarios.forEach((funcionario) -> {
            ButtonBar acao = new ButtonBar();

            acao.setStyle("-fx-alignment: center");
            Button editar = new Button("Editar");

            editar.getStylesheets().add("/css/styleModel.css");
            ImageView img = new ImageView(new Image("/img/edit.png"));
            img.setFitWidth(20);
            img.setFitHeight(20);
            editar.setGraphic(img);

            editar.setId("buttonEdit");
           
            Button excluir = new Button("Excluir");

            editar.getStylesheets().add("/css/styleModel.css");
            ImageView img4 = new ImageView(new Image("/img/delete.png"));
            img4.setFitWidth(20);
            img4.setFitHeight(20);
            excluir.setGraphic(img4);

            excluir.setId("buttonRemove");

            Button salario = new Button("Pagamentos");
            ImageView img2 = new ImageView(new Image("/img/money.png"));
            Tooltip toolTip = new Tooltip("Pagamentos");
            toolTip.setStyle("-fx-background-color: #1E90FF");
            salario.setTooltip(toolTip);
            img2.setFitWidth(20);
            img2.setFitHeight(20);
            salario.setGraphic(img2);

            salario.getStylesheets().add("/css/styleModel.css");
            salario.setId("buttonSave");

            Button botao = new Button();
            botao.setStyle("-fx-background-color: transparent;;-fx-background-radius:0px;");
            ImageView img1 = new ImageView(new Image("/img/icons8-info-64.png"));
            img1.setFitHeight(35);
            img1.setFitWidth(35);
            botao.setGraphic(img1);
            funcionario.setInfo(botao);
            
            
            
            botao.setOnAction((ActionEvent event) -> {
                try {
                    funcionarioStatic = funcionario;
                    PerfilFuncionarioController.setFuncionario(funcionario);
                    mostrarJanela(Caminhos.perfilFuncionario, funcionario.getNome() + " PerfilFuncionario", Boolean.FALSE);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (JRException ex) {
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            
            salario.setOnAction((ActionEvent event) -> {

                try {
                    funcionarioStatic = funcionario;
                    
                    PerfilFuncionarioController.setFuncionario(funcionario);
                    mostrarJanela(Caminhos.Salario, funcionario.getNome() + " Pagamentos", Boolean.FALSE);
                } catch (IOException e) {
                   
                    System.out.println(e.getMessage());
                } catch (JRException ex) {
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            editar.setOnAction((ActionEvent) -> {

                
                try {
                     funcionarioStatic = funcionario;
                    editar(Caminhos.EditarFuncionario, "Editar Funcionario", Boolean.FALSE);
                } catch (IOException ex) {
                    Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
          
            excluir.setOnAction((ActionEvent) -> {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(" Dialogo de Confirmacao");
                alert.setHeaderText("FUNCIONARIO: " + funcionario.getNome().toUpperCase()+" "+funcionario.getApelido());
                alert.setContentText("Pretente realmente EXCLUIR o Funcionario?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    funcionario.delete();

                    this.BuscarTodos(Funcionario.all());
                } else {
                    alert.close();
                }
            });

            acao.getButtons().addAll(editar, salario, excluir);

            funcionario.setAcoes(acao);

        });

        return funcionarios;

    }

    @FXML
    private void adicionarCargo(ActionEvent event) throws IOException {
        
        addCargo(Caminhos.NovoCargo, "Cargo", Boolean.FALSE);
    }

    private void pesquisarFuncionario() {
        pesquisa.setOnKeyReleased((KeyEvent event) -> {

            TextField text = (TextField) event.getSource();
            if (text.getLength() > 1) {
                listaFuncionarios.getItems().clear();
                String name = pesquisa.getText();

                BuscarTodos(Funcionario.findByName(name));

            } else if (text.getLength() < 1) {
                BuscarTodos(Funcionario.all());
            }
        });
    }

}
