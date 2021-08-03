/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.factory;

/**
 *
 * @author doroteia
 */
public class ValidationEmail {
    private String email;

    public ValidationEmail(String email) {
        this.email = email;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean validarEmail(){
        String recebe = "";//recebe aparit @gmail.com
        boolean pode = false;
        for(int i = 0 ; i < email.length(); i++){
            if(email.charAt(i) == '@'){
                pode =true;
            }
            if(pode){
              recebe= recebe+""+email.charAt(i)+"";;
            }
        }
        
       return recebe.equalsIgnoreCase("@gmail.com");
    }
    
    
}
