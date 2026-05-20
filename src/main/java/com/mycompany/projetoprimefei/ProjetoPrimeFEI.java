/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetoprimefei;
import com.mycompany.projetoprimefei.View.CadastroJFrame;
import com.mycompany.projetoprimefei.View.BuscasJFrame;
import com.mycompany.projetoprimefei.View.HomeJFrame;
import com.mycompany.projetoprimefei.View.ListaFavJFrame;
import com.mycompany.projetoprimefei.View.LoginJFrame;
import com.mycompany.projetoprimefei.View.TelaInicial;
import com.mycompany.projetoprimefei.controller.Controller;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProjetoPrimeFEI {

    public static void main(String[] args) throws SQLException {
   
        // 1. Instancia as 6 telas
        CadastroJFrame cadastro = new CadastroJFrame();
        LoginJFrame login = new LoginJFrame();
        BuscasJFrame buscas = new BuscasJFrame();
        HomeJFrame home = new HomeJFrame();
        ListaFavJFrame listaFav = new ListaFavJFrame();
        TelaInicial inicial = new TelaInicial();

        // 2. Cria o controller passando as 6 (na ordem do construtor acima)
        Controller controller = new Controller(cadastro, login, buscas, home, listaFav, inicial);

        // 3. Vincula o controller às telas
        cadastro.setController(controller);
        login.setController(controller);
        buscas.setController(controller);
        home.setController(controller);
        listaFav.setController(controller);
        inicial.setController(controller);

        inicial.setVisible(true);
}
}