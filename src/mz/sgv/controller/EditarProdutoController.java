/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static mz.sgv.controller.AddQuantidadeController.quantidade2;
import static mz.sgv.controller.AddTamanhoController.tamanho2;
import static mz.sgv.controller.NovoProdutoController.telaP;
import mz.sgv.factory.Help;
import mz.sgv.model.Caminhos;
import mz.sgv.model.Categoria;
import mz.sgv.model.Produto;
import static mz.sgv.model.Produto.codigoAcao;
import mz.sgv.model.Tamanho;

/**
 *
 * @author doroteia
 */
public class EditarProdutoController {

    @FXML
    private VBox janela;
    @FXML
    private ImageView foto;
    @FXML
    private TextField codigo;
    @FXML
    private TextField nome;
    @FXML
    private ColorPicker cor;
    @FXML
    private TextField preco;
    @FXML
    private TextField tamanhos;
    @FXML
    private TextField quantidade;
    @FXML
    private ComboBox Combocategoria;

    static String nome1;
    static String cor1;
    public static String tamanhos1;
    static String quantidade1;
    static float preco1;
    static int codigo1;

    public void initialize() {
        // codigoAcao 
       
        
        apenasNumero(codigo);
        apenasNumero(preco);
        InserirCategorias();
        inicializarComponentes();
        quantidade2 = quantidade.getText();
        tamanho2 = tamanhos.getText();
    }

    @FXML
    private void addImage(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpeg", "*.png", "*.jpg"));
        File file;
        file = fc.showOpenDialog(new Stage());

        if (file != null) {
            foto.setImage(new Image("file:///" + file.getAbsolutePath()));

        }
    }

    @FXML
    private void addTamanho(ActionEvent event) throws IOException {
        AddTamanhos(Caminhos.AddTamanhos, "add Tamanhos", Boolean.FALSE);
    }

    @FXML
    private void addTamanh(MouseEvent event) throws IOException {
        AddTamanhos(Caminhos.AddTamanhos, "add Tamanhos", Boolean.FALSE);
    }

    @FXML
    private void addQuantidade(ActionEvent event) throws IOException {
        AddQuantidades(Caminhos.AddQuantidades, "add Quantidade", Boolean.FALSE);
    }

    @FXML
    private void addQuantidaMouseClicked(MouseEvent event) throws IOException {
        AddQuantidades(Caminhos.AddQuantidades, "add Quantidade", Boolean.FALSE);
    }

    @FXML
    private void addCategoria(ActionEvent event) throws IOException {
        mostrarJanela(Caminhos.categoria, "Categoria", Boolean.FALSE);
    }

    @FXML
    private void salvar(ActionEvent event) {

        Alert a = new Alert(Alert.AlertType.NONE);
        telaP = "produtos";
        nome1 = nome.getText();
        cor1 = cor.getValue().toString();
        tamanhos1 = tamanhos.getText();

//        System.out.println(file.getAbsoluteFile());
        //  tamanhos1 = tamanhos.getText();
        boolean erro = false;
        if (codigo.getText().isEmpty()) {
            a.setContentText("o campo codigo esta vazio!");
            a.setAlertType(Alert.AlertType.ERROR);
            erro = true;
            a.show();

        } else if (nome1.isEmpty()) {
            a.setContentText("O nome do produto nao pode estar vazio!");
            a.setAlertType(Alert.AlertType.ERROR);
            erro = true;
            a.show();

        } else if (cor1.isEmpty()) {
            a.setContentText("A cor do produto nao pode estar vazio!");
            a.setAlertType(Alert.AlertType.ERROR);
            erro = true;
            a.show();
        } else if (tamanhos1.isEmpty()) {
            a.setContentText("O produto deve conter pelo menos um tamanho nao pode estar vazio!");
            a.setAlertType(Alert.AlertType.ERROR);
            erro = true;
            a.show();
        } else if (quantidade.getText().isEmpty()) {
            a.setContentText("o campo Quantidade esta vazio!!");
            a.setAlertType(Alert.AlertType.ERROR);
            erro = true;
            a.show();
        }
        if (!codigo.getText().isEmpty()) {
            try {
                codigo1 = Integer.parseInt(codigo.getText());
            } catch (NumberFormatException e) {

                a.setContentText("o campo codigo contem um numero invalido!");
                a.setAlertType(Alert.AlertType.ERROR);
                erro = true;
                a.show();
            }
        }

        if (quantidade.getText().isEmpty()) {

            a.setContentText("Quantidade invalida!");
            a.setAlertType(Alert.AlertType.ERROR);
            erro = true;
            a.show();

        }
        if (!preco.getText().isEmpty()) {
            try {
                preco1 = Float.parseFloat(preco.getText().trim());
            } catch (NumberFormatException e) {
                a.setContentText("preco invalido!");
                a.setAlertType(Alert.AlertType.ERROR);
                erro = true;
                a.show();
            }

        }
        if (!erro) {

            Produto produto = new Produto();
            produto.setCodigo(codigo1);
            produto.setNome(nome1);
            produto.setCor(cor1);
            produto.setPreco(preco1);

            //Criando a nova categoria
            String categoria = Combocategoria.getSelectionModel().getSelectedItem().toString();

            //pegando o id da categoria seleccionada
            int id_categoria = Categoria.findByName(categoria).getId();

            produto.setId_categoria(id_categoria);

            produto.save(tamanhos1, quantidade.getText());

            tamanhos.setText("");
            codigo.setText("");
            nome.setText("");
            quantidade.setText("");
            preco.setText("");
            Combocategoria.getSelectionModel().clearSelection();
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Salvo com sucesso!");
            a.show();
            tamanho2 = "";
            quantidade2 = "";

        }
    }

    private void AddTamanhos(String caminho, String title, Boolean maximized) throws IOException {
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
        stage.setOnHiding((WindowEvent event) -> {
            System.out.println("tam: "+tamanho2);
            tamanhos.setText(tamanho2);
        });
    }

    private void AddQuantidades(String caminho, String title, Boolean maximized) throws IOException {
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
        stage.setOnHiding((WindowEvent event) -> {
            
            quantidade.setText(quantidade2);
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

    private void inicializarComponentes() {

        Produto p = Produto.findByCod(codigoAcao);
        codigo.setText("" + p.getCodigo());
        preco.setText("" + p.getPreco());
        nome.setText(p.getNome());
        List<Float> tamanhos_ = new ArrayList<>();
        List<Integer> quantidades_ = new ArrayList<>();
        for (Tamanho value : Tamanho.findByCodProduto(codigoAcao)) {
            tamanhos_.add(value.getTamanho());
            quantidades_.add(value.getQuantidade());
        }

        tamanhos.setText(Help.ArrayFloatToString(tamanhos_));
        quantidade.setText(Help.ArrayIntToString(quantidades_));

        Combocategoria.getSelectionModel().select(p.getCategoria());
    }

    public void InserirCategorias() {
        List<Categoria> categorias = Categoria.all();

        for (Categoria categoria : categorias) {
            Combocategoria.getItems().add(categoria.getCategoria());
        }
    }

}
