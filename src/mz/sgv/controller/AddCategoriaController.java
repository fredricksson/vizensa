/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static mz.sgv.controller.NovoProdutoController.*;
import static mz.sgv.controller.NovoProdutoController.telaP;
import mz.sgv.dao.ProdutoDAO;
import mz.sgv.model.Categoria;
import mz.sgv.model.Produto;

/**
 *
 * @author doroteia
 */
public class AddCategoriaController {

    //botao de adiccionar na vertente do cadastro
    @FXML
    Button add;
    @FXML
    Button add2;
    @FXML
    Button salvarProduto;
    @FXML
    TextField textField;
    @FXML
    ComboBox comboBox;
    public String tela;
    

    @FXML
    public void initialize() {
        listarCategoria();
       
        tela = telaP;
        if (tela.equalsIgnoreCase("produtos")) {
            add.setVisible(false);
            salvarProduto.setVisible(false);
        } else {
            add.setVisible(false);
            salvarProduto.setVisible(false);

        }
    }

    @FXML
    public void addCategoria(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        if (!textField.getText().isEmpty()) {
           
            Categoria categoria = new Categoria(textField.getText());
            categoria.save();
            comboBox.getItems().add(textField.getText());
            a.setContentText("Categoria adiccionada com sucesso!");
            a.show();
        } else {
            a.setContentText("campo categoria esta vazio!!");
            a.setAlertType(Alert.AlertType.ERROR);
            a.show();
        }
        if (tela.equalsIgnoreCase("produtos")) {
            textField.setVisible(false);
            add2.setVisible(false);
           
        }

    }

    @FXML
    public void salvarProduto(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.NONE);
        if (comboBox.getSelectionModel().isEmpty()) {
            a.setContentText("Seleccione categoria, CASO nao haja adiccione!");
            a.setAlertType(Alert.AlertType.ERROR);

            a.show();
        } else {

           
            String categoria = comboBox.getSelectionModel().getSelectedItem().toString();
            Produto produto =new Produto();
            produto.setCodigo(codigo1);
            produto.setNome(nome1);
            produto.setCor(cor1);
            produto.setPreco(preco1);
            
            //Criando a nova categoria
            Categoria categ = new Categoria(categoria);
            categ.save();
            
            //pegando o id da categoria seleccionada
            int id_categoria = Categoria.findByName(categoria).getId();
            
            
            produto.setId_categoria(id_categoria);
            produto.save(tamanhos1,quantidade1);
           
           
          
            
            a.setContentText("Produto salvo com sucesso!");
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.show();
        }
    }

    @FXML
    public void AdiccionarCategoriaProdutos(ActionEvent event) {
        if (tela.equalsIgnoreCase("produtos")) {
            textField.setVisible(true);
            add2.setVisible(true);

        } else {

        }
    }

    public void listarCategoria() {
        comboBox.getItems().clear();
        List<Categoria> categorias = Categoria.all();

        categorias.forEach((categoria) -> {
            comboBox.getItems().add(categoria.getCategoria());
        });
    }
    
    public void atualizarCombo(ProdutosController pc){
        pc.InserirCategorias();
    
    }
}
