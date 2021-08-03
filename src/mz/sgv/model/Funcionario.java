/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import mz.sgv.dao.FuncionarioDAO;

/**
 *
 * @author fred
 */
public class Funcionario {

    private int id; //cchave primaria
    private int id_cargo; // chave estrangeira
    private String nome;
    private String apelido;

    private String cargo;

    private String nrBi;
    private String sexo;
    private String estadoCivil;
    private String contacto;
    private String contactoOpcional;
    private String email;
    private Date dataNascimento;
    private float salario;
    private boolean autenticavel;

    private ButtonBar acoes;

    private Morada morada;
    Button info;

    static FuncionarioDAO dao = new FuncionarioDAO();

    public Button getInfo() {
        return info;
    }

    public void setInfo(Button info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNrBi() {
        return nrBi;
    }

    public void setNrBi(String nrBi) {
        this.nrBi = nrBi;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getContactoOpcional() {
        return contactoOpcional;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setContactoOpcional(String contactoOpcional) {
        this.contactoOpcional = contactoOpcional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isAutenticavel() {
        return autenticavel;
    }

    public void setAutenticavel(boolean autenticavel) {
        this.autenticavel = autenticavel;
    }

    public Morada getMorada() {
        return morada;
    }

    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    public ButtonBar getAcoes() {
        return acoes;
    }

    public void setAcoes(ButtonBar acoes) {
        this.acoes = acoes;
    }
    
    public void save() {
        dao.save(this);
    }
    public void update() {
        dao.update(this);
    }

    public static List<Funcionario> all() {
        List<Funcionario> list = new ArrayList<>();

       
        return dao.all();
    }

    public static Funcionario findById(int id) {
        return dao.findById(id);
    }
    public static List<Funcionario> findByName(String nome) {
        return dao.findByName(nome);
    }
    public void delete(){
      dao.delete(this);
    }
   
}
