/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.text.ParseException;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static mz.sgv.controller.FuncionarioController.funcionarioStatic;
import mz.sgv.factory.Help;
import mz.sgv.model.Cargo;
import mz.sgv.model.Funcionario;
import mz.sgv.model.Morada;

/**
 *
 * @author doroteia
 */
public class EditarFuncionarioController {

    @FXML
    private Label titulo;
    @FXML
    private TextField nome;
    @FXML
    private TextField apelido;
  
    @FXML
    private TextField nr_bi;
    @FXML
    private ComboBox combo_genero;
    @FXML
    private ComboBox combo_estado_civil;
    @FXML
    private TextField contacto;
    @FXML
    private TextField contacto_opcional;
    @FXML
    private TextField email;
    @FXML
    private DatePicker data_nacimento;
    @FXML
    private TextField salario;
    @FXML
    private TextField avenida;
    @FXML
    private TextField quarteirao;
    @FXML
    private TextField rua;
    @FXML
    private ComboBox cargo;
    @FXML
    private Button salvar;
    
    
    public static boolean isEditado = false;
    public void initialize(){
      nome.setText(funcionarioStatic.getNome());
      apelido.setText(funcionarioStatic.getApelido());
      nr_bi.setText(funcionarioStatic.getNrBi());
      combo_genero.getSelectionModel().select(funcionarioStatic.getSexo());
      combo_estado_civil.getSelectionModel().select(funcionarioStatic.getEstadoCivil());
      contacto.setText(funcionarioStatic.getContacto());
      contacto_opcional.setText(funcionarioStatic.getContactoOpcional());
      email.setText(funcionarioStatic.getEmail());
      data_nacimento.getEditor().setText(funcionarioStatic.getDataNascimento().toString().replaceAll("-", "/"));
      salario.setText(funcionarioStatic.getSalario()+"");
      avenida.setText(funcionarioStatic.getMorada().getAvenida());
      quarteirao.setText(funcionarioStatic.getMorada().getQuarteirao());
      rua.setText(funcionarioStatic.getMorada().getRua());
      cargo.getSelectionModel().select(Cargo.findById(funcionarioStatic.getId_cargo()).getCargo());
       combo_genero.getItems().add("Masculino");
        combo_genero.getItems().add("Femenino");
   
        combo_estado_civil.getItems().add("Solteiro/a");
        combo_estado_civil.getItems().add("Casado/a");
        combo_estado_civil.getItems().add("Divorciado/a");
        
        
        Cargo.all().forEach((carg) ->{
            cargo.getItems().add(carg.getCargo());
        });
        
        apenasNumero(contacto);
        addTextLimiter(contacto, 9);
        addTextLimiter(contacto_opcional, 9);
        apenasNumero(salario);
        addTextLimiter(nr_bi, 13);
     
    }

   @FXML
    void salvar(ActionEvent event) throws ParseException {
        Alert a = new Alert(Alert.AlertType.ERROR);
        boolean erro = false;
        if (nome.getText().isEmpty()) {
            a.setContentText("o campo de nome esta vazio!");
            a.show();
            erro = true;
        } else if (apelido.getText().isEmpty()) {
            a.setContentText("o campo apelido esta vazio!");
            a.show();
            erro = true;
        } else if (nr_bi.getText().isEmpty()) {
            a.setContentText("o campo Nr de B.I esta vazio!");
            a.show();
            erro = true;

        
        } else if (combo_genero.getSelectionModel().isEmpty()) {
            a.setContentText("o campo Genero nao foi seleccionado");
            a.show();
            erro = true;

        } else if (combo_estado_civil.getSelectionModel().isEmpty()) {
            a.setContentText("o campo estado civil nao foi seleccionado");
            a.show();
            erro = true;
        } else if (data_nacimento.getEditor().getText().isEmpty()) {
            a.setContentText("o campo DATA DE NASCIMENTO esta vazio!");
            a.show();
            erro = true;
        }else if(cargo.getSelectionModel().getSelectedIndex() == -1){
            a.setContentText("o campo data CARGO nao foi seleccionado!");
            a.show();
            erro = true;
        }
        try {
            if (erro != true) {
                Date data = Help.date_from_string(data_nacimento.getEditor().getText().replaceAll("/","-"));

                
                String sexo = combo_genero.getSelectionModel().getSelectedItem().toString();
                String estadoCivil = combo_estado_civil.getSelectionModel().getSelectedItem().toString();
                
                float salario_ = Float.parseFloat(this.salario.getText());

                if (contacto.getText().length() == 9) {
                    if (contacto_opcional.getText().length() == 9) {
                    

                        if (Help.validar_email(email.getText())) {
                            Funcionario funcionario = new Funcionario();
                            funcionario.setId(funcionarioStatic.getId());
                            funcionario.setNome(nome.getText());
                            funcionario.setApelido(apelido.getText());
                            funcionario.setId_cargo(Cargo.FindIdByName(cargo.getSelectionModel().getSelectedItem().toString()));
                            funcionario.setNrBi(nr_bi.getText());
                            funcionario.setSexo(sexo);
                            funcionario.setEstadoCivil(estadoCivil);
                            funcionario.setContacto(this.contacto.getText());
                            funcionario.setContactoOpcional(this.contacto_opcional.getText());
                            funcionario.setEmail(email.getText());
                            funcionario.setDataNascimento(data);
                            funcionario.setSalario(salario_);
                            
                            
                            Morada morada = new Morada();
                            morada.setAvenida(avenida.getText());
                            morada.setQuarteirao(quarteirao.getText());
                            morada.setRua(rua.getText());
                            funcionario.setMorada(morada);
                            isEditado = true;
                            funcionario.update();

                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Funcionario Actualizado com sucesso!");
                            a.show();

                        } else {
                            a.setContentText("Email invalido");
                            a.show();
                        }
                    } else {
                        a.setContentText("contacto opcional  invalido");
                        a.show();
                    }

                } else {
                    a.setContentText("contacto invalido");
                    a.show();
                }

            }
        } catch (NumberFormatException e) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("os campos de contactos,salario,numero de conta devem ser numericos! ");
            a.show();

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
