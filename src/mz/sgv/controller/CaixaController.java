/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static mz.sgv.controller.LoginController.adimSession;
import static mz.sgv.controller.LoginController.usuarioSession;
import static mz.sgv.controller.NovoClienteController.nomeCliente1;

import mz.sgv.factory.Help;
import mz.sgv.model.Adim;
import mz.sgv.model.Caminhos;
import mz.sgv.model.Funcionario;
import mz.sgv.model.Produto;
import mz.sgv.model.Venda;

/**
 *
 * @author doroteia
 */
public class CaixaController {

    @FXML
    VBox janela;
    @FXML
    Label total;
    @FXML
    Label troco;
    @FXML
    TextField valorRecebido, nomeCliente;
    @FXML
    TableView<Produto> tabelaItens;

    public static float totalPago_ = 0;
    public static List<Produto> listaCaixa = new ArrayList<>();
    private String codigoProdutos = "";
    private String quantidades = "";
    private String tamanhos = "";
    private String precos = "";
    private int quantidadeTotal = 0;
    private int idFuncionario;
    private float totalPago = 0;
    @FXML
    private Label nome;

    public void initialize() {

        if (adimSession == null) {
            idFuncionario = usuarioSession.getId_funcionario();
            String nomecompleto = Funcionario.findById(idFuncionario ).getNome() + ""
                    + " " + Funcionario.findById(usuarioSession.getId_funcionario()).getApelido();
            nome.setText("Funcionario:: " + nomecompleto);
        } else {
            String nomecompleto = Adim.getAdim().getNome() + " " + Adim.getAdim().getApelido();
            nome.setText("Admnistrador:: " + nomecompleto);
        }
        total.setText("" + totalPago_);
        ListarItens();
        CalcularTroco();

    }

    @FXML
    public void onClickCliente(ActionEvent event) throws IOException {
        if (nomeCliente.getText().equalsIgnoreCase("Sem Nome")) {
            novoCliente();
        }
    }

    @FXML
    public void adicionarItens(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminhos.AdicionarItens));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("NovoProduto");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());

        // stage.setResizable(false);
        stage.setOnHiding((WindowEvent event1) -> {
            ListarItens();
            total.setText(totalPago_ + "");

        });

        stage.show();

    }

    //campo de calcular trocos
    @FXML
    public void confirmarVenda(ActionEvent event) throws IOException, ParseException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Dialogo de Confirmacao");
        alert.setHeaderText("VENDAS ");
        alert.setContentText("tem certeza de que deseja efectua a venda?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Venda venda = new Venda();

            listaCaixa.forEach((produto) -> {
                codigoProdutos += produto.getCodigo() + ",";
                quantidades += produto.getQuantidade() + ",";
                tamanhos += produto.getTamanho() + ",";
                totalPago += produto.getPreco() * produto.getQuantidade();
                quantidadeTotal += produto.getQuantidade();

                precos += produto.getPreco() + ",";
            }
            );
            LocalDate date = LocalDate.now();

            venda.setDataVenda(Help.date_from_string(date.toString()));
            venda.setQuantidades(quantidades);
            venda.setCodigoProdutos(codigoProdutos);
            venda.setTamanhos(tamanhos);
            venda.setTotalPago(totalPago);
            venda.setPrecos(precos);
            venda.setId_funcionario(idFuncionario);
            venda.setQuantidadeTotal(quantidadeTotal);
            quantidadeTotal = 0;
            precos = "";
            codigoProdutos = "";
            quantidades = "";
            totalPago_ = 0;
             venda.save();

        } else {
            alert.close();
        }
    }

    //campo de calcular trocos
    @FXML
    public void ExcuirProduto(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("eXCUIR");
        a.show();
    }

    //campo de calcular trocos
    @FXML
    public void cancelarVenda(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("CANCELAR VENDA");
        a.show();
    }

    public void ListarItens() {

        ObservableList<Produto> list = FXCollections.observableArrayList(addAction(listaCaixa));

        tabelaItens.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tabelaItens.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaItens.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
        tabelaItens.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaItens.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        tabelaItens.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabelaItens.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("acoes"));

        tabelaItens.setItems(list);
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
        stage.setResizable(true);
        stage.show();
    }

    private void novoCliente() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Caminhos.NovoCliente));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());

        stage.setResizable(true);
        stage.show();
        stage.setOnHiding((WindowEvent event) -> {

            if (nomeCliente1.equalsIgnoreCase("")) {
                nomeCliente.setText("Sem Nome");
            } else {
                nomeCliente.setText(nomeCliente1);
            }

        });

        stage.show();

    }

    public void CalcularTroco() {

        valorRecebido.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {

                float valor_recebido = Float.parseFloat(newValue);

                if (valor_recebido > totalPago_) {
                    String g = valor_recebido - totalPago_ + "Mt";

                    String g1 = g.replace(".", ",");
                    troco.setText(g1);
                } else {
                    troco.setText("00.00 Mt");
                }

            } catch (NumberFormatException e) {

                troco.setText("00.00 Mt");
            }
        });

    }

    public List<Produto> addAction(List<Produto> produtos) {

        produtos.forEach((produto) -> {
            Rectangle r = new Rectangle();
            String c = produto.getCor().replaceFirst("0x", "");

            r.setStyle("-fx-fill:#" + c + ";");

            r.setWidth(40);
            r.setHeight(30);
            Button excluir = new Button("Excluir");
            ImageView img2 = new ImageView(new Image("/img/delete.png"));

            img2.setFitWidth(20);
            img2.setFitHeight(20);
            excluir.setGraphic(img2);

            excluir.getStylesheets().add("/css/styleModel.css");
            excluir.setId("buttonRemove");

            ButtonBar br = new ButtonBar();
            br.setMaxWidth(130);
            br.getButtons().add(excluir);

            excluir.setOnAction((ActionEvent) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(" Dialogo de Confirmacao");
                alert.setHeaderText("PRODUTO: " + produto.getNome().toUpperCase());
                alert.setContentText("Pretente remover  da lista?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    listaCaixa.remove(produto);

                    this.ListarItens();
                } else {
                    alert.close();
                }
            });

            produto.setAcoes(br);

            produto.setProdcor(r);

        });

        return produtos;

    }

}
