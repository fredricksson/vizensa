/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mz.sgv.model.Caminhos;
import mz.sgv.model.HistoricoVenda;

/**
 *
 * @author doroteia
 */
public class VendasController {

    @FXML
    VBox janela;
    @FXML
    private TableView<HistoricoVenda> table;
    
    public static HistoricoVenda historicoVendas ;
    @FXML
    private TextField pesquisa;
    @FXML
    private ComboBox comboBoxPesquisa;
    public void initialize() {
       all(HistoricoVenda.all());
        
       comboBoxPesquisa.getItems().add("Funcionario");
       comboBoxPesquisa.getItems().add("Cliente");
       pesquisarHistorico();

    }

    //Chama tela de detalhes da venda
    public void detalhesVenda() throws IOException {
        mostrarJanela(Caminhos.DetalhesVenda, "Detalhes venda");
    }

    public void all(List<HistoricoVenda> lista) {
        
        ObservableList<HistoricoVenda> list = FXCollections.observableArrayList(addAction(lista));

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("data"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("totalPago"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("quantidadeTotal"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("acao"));

        table.setItems(list);

    }

    private void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        //stage.setMaximized(true);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.show();
    }

    public List<HistoricoVenda> addAction(List<HistoricoVenda> hv) {

        hv.forEach((h)->
        {
            Button detalhes = new Button("Detalhes");

            detalhes.getStylesheets().add("/css/styleModel.css");
            ImageView img = new ImageView(new Image("/img/icons8-checkout-96.png"));
            detalhes.setPrefSize(200, 30);
            img.setFitWidth(20);
            img.setFitHeight(20);
            detalhes.setGraphic(img);
            
            
            detalhes.setOnAction((e) ->{
                try {
                    historicoVendas = h;
                    mostrarJanela(Caminhos.DetalhesVenda, "Detalhes da venda");
                } catch (IOException ex) {
                    Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            detalhes.setId("buttonSave");

            h.setAcao(detalhes);
        });
        return hv;
                
    }
    
        private void pesquisarHistorico() {
        pesquisa.setOnKeyReleased((KeyEvent event) -> {
            
            TextField text = (TextField) event.getSource();
            if (text.getLength() > 1) {
                table.getItems().clear();
                String name = pesquisa.getText();
               
                if(comboBoxPesquisa.getSelectionModel().getSelectedIndex() > -1){
                  String coluna = comboBoxPesquisa.getSelectionModel().getSelectedItem().toString().substring(0,1).toLowerCase()+".nome";
                  all(HistoricoVenda.findByName(name,coluna));  
                }else{
                    all(HistoricoVenda.findByName(name));
                }
                
                
                
                
                
            } else if (text.getLength() < 1) {
                all(HistoricoVenda.all());
            }
        });
    }

}
