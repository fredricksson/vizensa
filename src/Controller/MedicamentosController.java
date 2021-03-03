/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.Help;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Categoria;
import model.Produto;

/**
 * FXML Controller class
 *
 * @author doroteia
 */
public class MedicamentosController {

    @FXML
    private TextField nome;
    @FXML
    private TextField quantidade;
    @FXML
    private TextField preco;
    @FXML
    private DatePicker dataProducao;
    @FXML
    private DatePicker dataExpiracao;
    @FXML
    private ComboBox comboCategoria;
    @FXML
    private TextField pesquisa;
    @FXML
    private ComboBox pesqComboCategoria;
    @FXML
    private TableView<Produto> tableMedicamentos;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        apenasNumero(quantidade);
        apenasNumero(preco);
        buscarCategorias();
        buscarMedicamentos(Produto.all());
       // pesquisar_por_Categoria();
    }

    @FXML
    private void addCategoria(ActionEvent event) {
    }

    @FXML
    private void save(ActionEvent event) throws ParseException {

        Produto produto = new Produto();
        produto.setNome(nome.getText());
        produto.setQuantidade(Integer.parseInt(quantidade.getText()));
        produto.setPreco(Double.parseDouble(preco.getText()));
        produto.setDataCriacao(Help.stringToDate(dataProducao.getEditor().getText()));
        produto.setDataExpiracao(Help.stringToDate(dataExpiracao.getEditor().getText()));
        produto.setId_categoria(Categoria.findIdByName(comboCategoria.getSelectionModel().getSelectedItem().toString()).getId());
        produto.save();

        buscarMedicamentos(Produto.all());
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    public void buscarMedicamentos(List<Produto> lista) {
        ObservableList<Produto> list = FXCollections.observableArrayList(addAction(lista));
        System.out.println(Produto.all());
        tableMedicamentos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableMedicamentos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableMedicamentos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableMedicamentos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("acoes"));

        tableMedicamentos.setItems(list);
    }

    public void buscarCategorias() {

        Categoria.all().forEach((categoria) -> {
            comboCategoria.getItems().add(categoria.getCategoria());
        });

    }

    public List<Produto> addAction(List<Produto> produtos) {

        produtos.forEach((produto) -> {

            ButtonBar acao = new ButtonBar();
            acao.setMaxWidth(235);

            Button editar = new Button("Editar");

            editar.getStylesheets().add("/css/styleModel.css");
            ImageView img = new ImageView(new Image("/icons/edit.png"));
            img.setFitWidth(20);
            img.setFitHeight(20);
            editar.setGraphic(img);

            editar.setId("buttonEdit");

            Button excluir = new Button("Excluir");
            ImageView img2 = new ImageView(new Image("/icons/delete.png"));

            img2.setFitWidth(20);
            img2.setFitHeight(20);
            excluir.setGraphic(img2);

            excluir.getStylesheets().add("/css/styleModel.css");
            excluir.setId("buttonRemove");

            acao.getButtons().addAll(editar, excluir);

            editar.setOnAction((ActionEvent) -> {

            });

            excluir.setOnAction((ActionEvent) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(" Dialogo de Confirmacao");
                alert.setHeaderText("CLIENTE: " + produto.getNome().toUpperCase());
                alert.setContentText("Pretente realmente eliminar o Produto?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    produto.delete();

                } else {
                    alert.close();
                }
            });

            produto.setAcoes(acao);

        });

        return produtos;

    }

    public void apenasNumero(TextField textfield) {

        textfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String s = "";
            for (char c : newValue.toCharArray()) {
                if (((int) c >= 48 && (int) c <= 57 || (int) c == 46)) {
                    s += c;
                }
            }
            textfield.setText(s);
        });
    }

    @FXML
    private void pesquisar(KeyEvent event) {

        TextField text = (TextField) event.getSource();
        if (text.getLength() > 1) {
            String name = pesquisa.getText();

            buscarMedicamentos(Produto.findByName(name));
        } else if (text.getLength() < 1) {
            buscarMedicamentos(Produto.all());
        }
    }
    
     private void pesquisar_por_Categoria() {
        comboCategoria.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            String nome = comboCategoria.getSelectionModel().getSelectedItem().toString();
            for (int i = 0; i < Categoria.all().size(); i++) {

                if (pesqComboCategoria.getSelectionModel().getSelectedItem().equals(Categoria.all().get(i).getCategoria())) {

                
                   buscarMedicamentos(Produto.findWithCategory(nome));

                    
                }

            }
        });

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.menu));
            Parent home_page_parent = loader.load();

          


            Scene home_page_scene = new Scene(home_page_parent);
            Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            main_stage.close();
            main_stage.setScene(home_page_scene);

            main_stage.setResizable(false);
            main_stage.setMaximized(false);
            main_stage.show();
    }
}
