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
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static mz.sgv.controller.NovoProdutoController.telaP;
import mz.sgv.dao.CategoriaDAO;
import mz.sgv.dao.ProdutoDAO;
import mz.sgv.model.Caminhos;
import mz.sgv.model.Categoria;
import mz.sgv.model.Produto;
import static mz.sgv.model.Produto.codigoAcao;
import mz.sgv.model.Tamanho;

/**
 *
 * @author doroteia
 */
public class ProdutosController {

    @FXML
    VBox janela;
    @FXML
    TableView<Produto> tabelaProduto;
    @FXML
    TableView<Tamanho> TabelaTamanhos;
    @FXML
    TextField pesquisar;
    @FXML
    ComboBox comboCategoria;
    CategoriaDAO cd = new CategoriaDAO();
    ProdutoDAO produtodao = new ProdutoDAO();

    @FXML
    public void initialize() {
        Listar();
        pesquisarProduto();
        InserirCategorias();
        preenherTamanhos();
        pesquisar_por_Categoria();
        // apagar();
        //  tabelaProduto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //  TabelaTamanhos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    //chama tela de Adiccionar Produtos
    @FXML
    void adicionarProdutos(ActionEvent event) throws IOException {
        AdicionarProdut();

    }

    //Chama tela de edicao
    @FXML
    public void editarProduto(ActionEvent event) throws IOException {

        mostrarJanela(Caminhos.EditarProdutos, "EditarProduto", Boolean.FALSE);
    }

    //chama tela de excluir
    @FXML
    void excluirProduto(ActionEvent event) {
        if (tabelaProduto.getSelectionModel().getSelectedIndex() != -1) {
            apagar();
            Listar();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Seleccione um item");
            a.show();
        }

    }

    //chama tela se stock
    @FXML
    void Stock(ActionEvent event) throws IOException {
        
        mostrarJanela(Caminhos.stock,"Stock", Boolean.FALSE);
    }

    //botao pra listar todos produtos
    @FXML
    void listarTodosProdutos(ActionEvent event) {
        Listar();
    }

    //chama tela de adicao de categoria
    @FXML
    void adicionarCategoria(ActionEvent event) throws IOException {
        telaP = "";

        AdicionarCategoria();

    }

    public void actulizarCombo() {

    }

    private void pesquisarProduto() {
        pesquisar.setOnKeyReleased((KeyEvent event) -> {
            TextField text = (TextField) event.getSource();
            if (text.getLength() > 1) {
                String name = pesquisar.getText();

                ObservableList<Produto> produtos = FXCollections.observableArrayList(addAction(Produto.findByName(name)));
                tabelaProduto.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
                tabelaProduto.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
                tabelaProduto.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
                tabelaProduto.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
                tabelaProduto.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoria"));
                tabelaProduto.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("acoes"));

                tabelaProduto.setItems(produtos);
            } else if (text.getLength() < 1) {
                Listar();
            }
        });
    }

    private void preenherTamanhos() {
        tabelaProduto.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) -> {
                    try {

                        int cod = tabelaProduto.getSelectionModel().getSelectedItem().getCodigo();
                        // cod is foregin key on table tamanho

                        ObservableList<Tamanho> listT = FXCollections.observableArrayList(Tamanho.findByCodProduto(cod));
                        TabelaTamanhos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("tamanho"));
                        TabelaTamanhos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
                        TabelaTamanhos.setItems(listT);

                    } catch (Exception e) {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.show();
                    }
                });

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

    private void AdicionarProdut() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminhos.NovoProduto));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("NovoProduto");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());

        stage.setResizable(false);

        stage.setOnHiding((WindowEvent event) -> {
            Listar();
            InserirCategorias();
        });

        stage.show();

    }

    private void AdicionarCategoria() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminhos.categoria));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("NovoProduto");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());

        stage.setResizable(false);

        stage.setOnHiding((WindowEvent event) -> {
            Listar();
            InserirCategorias();
        });

        stage.show();

    }

    public void Listar() {

        ObservableList<Produto> list = FXCollections.observableArrayList(addAction(Produto.all()));

        tabelaProduto.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tabelaProduto.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaProduto.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
        //  tabelaProduto.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("quantidadeTotal"));
        tabelaProduto.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
        tabelaProduto.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tabelaProduto.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("acoes"));

        tabelaProduto.setItems(list);
    }

    public void InserirCategorias() {
        comboCategoria.getItems().clear();
        List<Categoria> categorias = Categoria.all();

        categorias.stream().forEach((categoria) -> {
            comboCategoria.getItems().add(categoria.getCategoria());
        });
    }

    public void apagar() {

        tabelaProduto.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) -> {

                    int cod = tabelaProduto.getSelectionModel().getSelectedItem().getCodigo();

                    Produto produto = new Produto();
                    produto.setCodigo(cod);
                    produto.delete();

                });

    }

    private void pesquisar_por_Categoria() {
        comboCategoria.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            String nome = comboCategoria.getSelectionModel().getSelectedItem().toString();
            for (int i = 0; i < Categoria.all().size(); i++) {

                if (comboCategoria.getSelectionModel().getSelectedItem().equals(Categoria.all().get(i).getCategoria())) {

                    ObservableList<Produto> oList = FXCollections.observableArrayList(addAction(Produto.allWithCategory(nome)));
                    tabelaProduto.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
                    tabelaProduto.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
                    tabelaProduto.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
                    tabelaProduto.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
                    tabelaProduto.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoria"));
                    tabelaProduto.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("acoes"));

                    tabelaProduto.setItems(oList);

                }

            }
        });

    }

    @FXML
    public void menu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminhos.PaginaPrincipal));
        Parent home_page_parent = loader.load();

        //InitialController controller = loader.getController();
        // controller.setUser(user);
        Scene home_page_scene = new Scene(home_page_parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.close();
        main_stage.setScene(home_page_scene);
        main_stage.setTitle("SGV");
        main_stage.setResizable(true);
        main_stage.setMaximized(true);
        main_stage.show();

    }

    public List<Produto> addAction(List<Produto> produtos) {

        produtos.forEach((produto) -> {
            Rectangle r = new Rectangle();
            String c = produto.getCor().replaceFirst("0x", "");

            r.setStyle("-fx-fill:#" + c + ";");

            r.setWidth(40);
            r.setHeight(30);
            ButtonBar acao = new ButtonBar();
            acao.setMaxWidth(235);

            Button editar = new Button("Editar");

            editar.getStylesheets().add("/css/styleModel.css");
            ImageView img = new ImageView(new Image("/img/edit.png"));
            img.setFitWidth(20);
            img.setFitHeight(20);
            editar.setGraphic(img);

            editar.setId("buttonEdit");

            Button excluir = new Button("Excluir");
            ImageView img2 = new ImageView(new Image("/img/delete.png"));

            img2.setFitWidth(20);
            img2.setFitHeight(20);
            excluir.setGraphic(img2);

            excluir.getStylesheets().add("/css/styleModel.css");
            excluir.setId("buttonRemove");

            acao.getButtons().addAll(editar, excluir);

            editar.setOnAction((ActionEvent) -> {

                try {

                    codigoAcao = produto.getCodigo();
                    this.editarProduto(ActionEvent);
                } catch (IOException ex) {
                    Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            excluir.setOnAction((ActionEvent) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(" Dialogo de Confirmacao");
                alert.setHeaderText("CLIENTE: " + produto.getNome().toUpperCase());
                alert.setContentText("Pretente realmente eliminar o Produto?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    produto.delete();

                    this.Listar();
                } else {
                    alert.close();
                }
            });

            produto.setAcoes(acao);
            produto.setProdcor(r);

        });

        return produtos;

    }

}
