/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static mz.sgv.controller.VendasController.historicoVendas;
import mz.sgv.factory.Help;
import mz.sgv.model.Produto;
import mz.sgv.model.ProdutosVendidos;

/**
 *
 * @author doroteia
 */
public class DetalhesVendaController {

    @FXML
    Label nomeFuncionario, nomeCliente, dataVenda, horaVenda, contactoCliente, emailCliente,
            totalPago;
    @FXML
    private TableView<ProdutosVendidos> tableProdutos;

    public void initialize() {
        inicializar();
        all();
    }

    @FXML
    public void imprimir(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("ImprimirVendas");
        a.show();

    }

    @FXML
    private void fechar(ActionEvent event) {
    }

    public void inicializar() {
        nomeFuncionario.setText(historicoVendas.getFuncionario().getNome());
        nomeCliente.setText(historicoVendas.getCliente().getNome());
        dataVenda.setText("" + historicoVendas.getData());
        horaVenda.setText("" + historicoVendas.getHora());
        contactoCliente.setText("" + historicoVendas.getCliente().getContacto());
        emailCliente.setText("" + historicoVendas.getCliente().getEmail());
        totalPago.setText(historicoVendas.getTotalPago() + "");
        
        
        
    }
    int i = 0 ;
     public void all() {
         
        List<ProdutosVendidos> pv = new ArrayList<>();
        
        List<Integer> codigos = Help.StringToArrayInt(historicoVendas.getVenda().getCodigoProdutos());
        List<Float> tamanhos = Help.StringToArrayFloat(historicoVendas.getVenda().getTamanhos());
        List<Float> precos = Help.StringToArrayFloat(historicoVendas.getVenda().getPrecos());
        
       
        List<Integer> quantidades = Help.StringToArrayInt(historicoVendas.getVenda().getQuantidades());
        i = 0 ;
        codigos.forEach((codigo)->{
            
            ProdutosVendidos pvd = new ProdutosVendidos();
            pvd.setNome( Produto.findByCod(codigo).getNome());
            pvd.setPreco(precos.get(i));
            pvd.setQuantidade(quantidades.get(i));
            pvd.setTamanho(tamanhos.get(i));
            i++;
            
            pv.add(pvd);
        });
        
        ObservableList<ProdutosVendidos> list = FXCollections.observableArrayList(pv);

        tableProdutos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableProdutos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableProdutos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableProdutos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tamanho"));
     

        tableProdutos.setItems(list);

    }
}
