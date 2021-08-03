/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import static mz.sgv.controller.AddQuantidadeController.quantidade2;
import static mz.sgv.controller.AddTamanhoController.tamanho2;
import mz.sgv.model.Caminhos;
import mz.sgv.model.Categoria;
import mz.sgv.model.Produto;

import mz.sgv.factory.Help;

/**
 *
 * @author doroteia
 */
public class NovoProdutoController {

    @FXML
    VBox janela;
    @FXML
    TextField nome;
    @FXML
    ColorPicker cor;

    @FXML
    TextField tamanhos;
    @FXML
    TextField quantidade;
    @FXML
    TextField preco;
    @FXML
    TextField codigo;
    @FXML
    ComboBox Combocategoria;
    @FXML
    ImageView foto;
    static String nome1;
    static String cor1;
    public static String tamanhos1;
    static String quantidade1;
    static float preco1;
    static int codigo1;
    static String telaP = "produtos";
    File file;
    @FXML
    private Label labelCodigo;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelCor;
    @FXML
    private Label labelPreco;
    @FXML
    private Label labelTamanho;
    @FXML
    private Label labelQuantidade;

    public void initialize() {
        tamanho2 = "";
        InserirCategorias();
        apenasNumero(codigo);
        apenasNumero(preco);
        //apenasNumero(quantidade);
    }

    @FXML
    public void salvar(ActionEvent event) throws IOException {

        telaP = "produtos";
        nome1 = nome.getText();
        cor1 = cor.getValue().toString();
        tamanhos1 = tamanhos.getText();

//        System.out.println(file.getAbsoluteFile());
        //  tamanhos1 = tamanhos.getText();
        boolean erro = false;
        if (codigo.getText().isEmpty()) {
            thread(codigo, labelCodigo);
            erro = true;

        } else if (nome1.isEmpty()) {
            thread(nome, labelNome);
            erro = true;

        } else if (cor1.isEmpty()) {
            threadColorPicker(cor, labelCor);
            erro = true;

        } else if (tamanhos1.isEmpty()) {
            thread(tamanhos, labelTamanho);
            erro = true;

        } else if (quantidade.getText().isEmpty()) {
            thread(quantidade, labelQuantidade);
            erro = true;

        }

        if (!erro) {

            codigo1 = Integer.parseInt(codigo.getText());
            preco1 = Float.parseFloat(preco.getText().trim());
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
//            a.setAlertType(Alert.AlertType.INFORMATION);
//            a.setContentText("Salvo com sucesso!");
//            a.show();
            tamanho2 = "";
            quantidade2 = "";

        }
    }

//    @FXML
//    public void addTamanhos(ActionEvent event) throws IOException {
//  
//        AddTamanhos(Caminhos.AddTamanhos, "tamanhos", Boolean.FALSE);
//    }
    public void addTamanho(ActionEvent event) throws IOException {

        AddTamanhos(Caminhos.AddTamanhos, "tamanhos", Boolean.FALSE);
    }

    @FXML
    public void addTamanh(MouseEvent event) throws IOException {

        AddTamanhos(Caminhos.AddTamanhos, "tamanhos", Boolean.FALSE);
    }

    @FXML
    public void addImage(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpeg", "*.png", "*.jpg"));
        file = fc.showOpenDialog(new Stage());

        if (file != null) {
            foto.setImage(new Image("file:///" + file.getAbsolutePath()));

        }

    }

    @FXML
    public void addCategoria(ActionEvent event) throws IOException {

        mostrarJanela(Caminhos.categoria, "Categoria", Boolean.FALSE);
    }

    //remover ultimo tamanho
    void remTamanhos(ActionEvent event) {

        tamanhos.setText(Help.removerTamanho(tamanhos.getText()));
        tamanho2 = tamanhos.getText();
        if (tamanho2.equalsIgnoreCase(",")) {
            tamanho2 = "";
            tamanhos.setText("");
        }
    }

    private void addQuantidade(ActionEvent event) throws IOException {
        AddQuantidades(Caminhos.AddQuantidades, "quantidades", Boolean.FALSE);
    }

    @FXML
    private void addQuantidaMouseClicked(MouseEvent event) throws IOException {
        AddQuantidades(Caminhos.AddQuantidades, "quantidades", Boolean.FALSE);
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

    public void InserirCategorias() {
        List<Categoria> categorias = Categoria.all();

        for (Categoria categoria : categorias) {
            Combocategoria.getItems().add(categoria.getCategoria());
        }
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        });

    }

    public void apenasNumero(TextField textField) {

        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String s = "";
            for (char c : newValue.toCharArray()) {
                if (((int) c >= 48 && (int) c <= 57 || (int) c == 46)) {
                    s += c;
                }
            }
            textField.setText(s);
        });
    }

    public void thread(TextField textField, Label label) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                textField.setStyle("-fx-border-color: red;");
                label.setStyle("-fx-text-fill: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000), label);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #94734C;");
                    label.setStyle("-fx-text-fill: #94734C;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

    public void threadColorPicker(ColorPicker textField, Label label) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                textField.setStyle("-fx-border-color: red;");
                label.setStyle("-fx-text-fill: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000), label);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #94734C;");
                    label.setStyle("-fx-text-fill: #94734C;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

}
