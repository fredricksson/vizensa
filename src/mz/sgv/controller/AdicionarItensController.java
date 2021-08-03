/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static mz.sgv.controller.CaixaController.listaCaixa;
import static mz.sgv.controller.CaixaController.totalPago_;
import mz.sgv.dao.CategoriaDAO;
import mz.sgv.model.Categoria;
import mz.sgv.model.Produto;
import mz.sgv.model.Tamanho;

/**
 *
 * @author doroteia
 */
public class AdicionarItensController {

    @FXML
    VBox janela;
    @FXML
    TextField pesquisa, quantidade;
    @FXML
    ComboBox combo_categoria;
    @FXML
    ComboBox combo_tamanho;
    @FXML
    TableView<Produto> tabelaProdutos;
    @FXML
    TableView<Tamanho> tabelaTamanhos;
    public static List<Produto> itens = new ArrayList<>();
    CategoriaDAO cd = new CategoriaDAO();
    public static int quantidade_;

    @FXML
    public void initialize() {
        apenasNumero(quantidade);
        preencherCategoria();
        listarProdutos();
        pesquisarProduto();
        preenherTamanhos();
        tabelaProdutos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabelaTamanhos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void pesquisarPorCategoria(ActionEvent event) {
        pesquisa.clear();

        if (combo_categoria.getSelectionModel().getSelectedIndex() != -1) {
            pesquisaCategorica();
        }
    }

    @FXML
    public void adicionarItem(ActionEvent event) {
        if (tabelaProdutos.getSelectionModel().getSelectedIndex() != -1) {
            preenherItens();

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Seleccione um item");
            a.show();
        }

    }

    @FXML
    public void concluir(ActionEvent event
    ) {
//        System.out.println(itens.get(0).getProdNome());
        janela.getScene().getWindow().hide();
    }

    public void listarProdutos() {
        ObservableList<Produto> list = FXCollections.observableArrayList(addColumnColors(Produto.all()));

        tabelaProdutos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tabelaProdutos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaProdutos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaProdutos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
        tabelaProdutos.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoria"));
  

        tabelaProdutos.setItems(list);

    }

    public void InserirCategorias() {
        List<Categoria> categorias = Categoria.all();

        for (Categoria categoria : categorias) {
            combo_categoria.getItems().add(categoria.getCategoria());
        }
    }

    private void preenherTamanhos() {
        tabelaProdutos.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) -> {
                    try {
                        try {
                            int cod = tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo();
                            ObservableList<Tamanho> tamanhos = FXCollections.observableArrayList(Tamanho.findByCodProduto(cod));

                            tabelaTamanhos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("tamanho"));
                            tabelaTamanhos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Quantidade"));
                            tabelaTamanhos.setItems(tamanhos);
                            combo_tamanho.getItems().clear();
                            for (Tamanho tamanho : tamanhos) {
                                combo_tamanho.getItems().add(tamanho.getTamanho());
                            }

                        } catch (NullPointerException e) {

                        }
                    } catch (Exception e) {
                        System.out.println("eroo: " + e.getMessage());
                    }
                });

    }

    private void pesquisarProduto() {
        pesquisa.setOnKeyReleased((KeyEvent event) -> {
            combo_categoria.getSelectionModel().clearSelection();
            TextField text = (TextField) event.getSource();
            if (text.getLength() > 1) {
                tabelaTamanhos.getItems().clear();
                String name = pesquisa.getText();
                 ObservableList<Produto> oList = FXCollections.observableArrayList(addColumnColors(Produto.findByName(name)));
                tabelaProdutos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
                tabelaProdutos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
                tabelaProdutos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
                tabelaProdutos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
                tabelaProdutos.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoria"));
                
                tabelaProdutos.setItems(oList);
            } else if (text.getLength() < 1) {
                listarProdutos();
            }
        });
    }

    private void pesquisaCategorica() {
        tabelaTamanhos.getItems().clear();
        ObservableList<Produto> oList = FXCollections.observableArrayList(addColumnColors(Produto.allWithCategory(combo_categoria.getSelectionModel().getSelectedItem().toString())));
        tabelaProdutos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tabelaProdutos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaProdutos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelaProdutos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("prodcor"));
        tabelaProdutos.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tabelaProdutos.setItems(oList);
    }

    public void preencherCategoria() {

        List<Categoria> categorias = Categoria.all();
        for (Categoria categoria : categorias) {
            combo_categoria.getItems().add(categoria.getCategoria());
        }

    }

    private void preenherItens() {
        

        try {
            try {
                try {
                    quantidade_ = Integer.parseInt(quantidade.getText());
                    int cod = tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo();

                    if (combo_tamanho.getSelectionModel().getSelectedIndex() == -1) {
                        thread(combo_tamanho);
                    } else {
                        float tamanho = Float.parseFloat(combo_tamanho.getSelectionModel().getSelectedItem().toString());
                        quantidade.clear();
                        Produto item = Produto.findByCod(cod);
                        item.setQuantidade(quantidade_);
                        item.setTamanho(tamanho);
                         totalPago_ +=item.getPreco()*item.getQuantidade();
                        
                        //adiccionado o item no array estatico pra visualizao na tela caixa
                        listaCaixa.add(item);
                        
                        
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("item adicionado");
                        a.show();
                    }
                } catch (NumberFormatException e) {
                    thread(quantidade);
                }
            } catch (NullPointerException e) {

            }

        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }

    }

    public List<Produto> addColumnColors(List<Produto> produtos) {

        produtos.forEach((produto) -> {
            Rectangle r = new Rectangle();
            String c = produto.getCor().replaceFirst("0x", "");

            r.setStyle("-fx-fill:#" + c + ";");

            r.setWidth(40);
            r.setHeight(30);

            produto.setProdcor(r);

        });

        return produtos;

    }
    
    
        public void thread(TextField textField) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                textField.setStyle("-fx-border-color: red;");
               
                FadeTransition transition = new FadeTransition(Duration.millis(2000), textField);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #333;");
                  
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }
    
        public void thread(ComboBox textField) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                textField.setStyle("-fx-border-color: red;");
               
                FadeTransition transition = new FadeTransition(Duration.millis(2000), textField);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #333;");
                  
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }
    
     public void apenasNumero(TextField textField){
    
           textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
               String s="";
               for(char c : newValue.toCharArray()){
                   if(((int)c >= 48 && (int)c <= 57 || (int)c == 46)){
                       s+=c;
                   }
               }
               textField.setText(s);
           });
    }

}
