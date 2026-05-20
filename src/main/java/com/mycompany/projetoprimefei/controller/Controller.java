package com.mycompany.projetoprimefei.controller;

import com.mycompany.projetoprimefei.Model.Usuario;
import com.mycompany.projetoprimefei.Model.dao.Conexao;
import com.mycompany.projetoprimefei.Model.dao.UsuarioDAO;
import com.mycompany.projetoprimefei.Model.dao.VideoDAO;
import com.mycompany.projetoprimefei.View.CadastroJFrame;
import com.mycompany.projetoprimefei.View.BuscasJFrame;
import com.mycompany.projetoprimefei.View.HomeJFrame;
import com.mycompany.projetoprimefei.View.ListaFavJFrame;
import com.mycompany.projetoprimefei.View.LoginJFrame;
import com.mycompany.projetoprimefei.View.TelaInicial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author luk
 */
public class Controller {
    private CadastroJFrame cadastroView;
    private LoginJFrame loginView;
    private BuscasJFrame buscasView;
    private HomeJFrame homeView;
    private ListaFavJFrame listaFavView;
    private TelaInicial telaInicialView;
    private UsuarioDAO usuarioDAO;
    private VideoDAO videoDAO;
    private int idUsuarioLogado;

    public Controller(CadastroJFrame cadastro, LoginJFrame login, 
            BuscasJFrame buscas, 
                      HomeJFrame home, ListaFavJFrame listaFav, 
                      TelaInicial telaInicial) throws SQLException{
        this.cadastroView = cadastro;
        this.loginView = login;
        this.buscasView = buscas;
        this.homeView = home;
        this.listaFavView = listaFav;
        this.telaInicialView = telaInicial;
        Conexao conexao = new Conexao();
        this.usuarioDAO = new UsuarioDAO(conexao.getConnection());
        this.videoDAO = new VideoDAO(conexao.getConnection());
    }

    public void exibirLogin() {
        fecharTodas();
        loginView.setVisible(true);
    }

    public void exibirCadastro() {
        fecharTodas();
        cadastroView.setVisible(true);
    }

    public void exibirHome() {
        fecharTodas();
        homeView.setVisible(true);
    }
    
    public void exibirBuscas() {
        fecharTodas();
        buscasView.setVisible(true);
    }
    
    public void exibirListaFav() {
        fecharTodas();
        listaFavView.setVisible(true);
    }
    private void fecharTodas() {
        cadastroView.setVisible(false);
        loginView.setVisible(false);
        buscasView.setVisible(false);
        homeView.setVisible(false);
        listaFavView.setVisible(false);
        telaInicialView.setVisible(false);
    }

     public void salvarUsuario(){
        String nome = this.cadastroView.getTxtNome().getText();
        String usuario = this.cadastroView.getTxtUsuario().getText();
        String senha = this.cadastroView.getTxtSenha().getText();
        Usuario user = new Usuario(nome, usuario, senha);
        try{
            this.usuarioDAO.inserir(user);
            exibirLogin();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erro ao inserir");
        }
    }
     
    public void fazerLogin() throws SQLException {
        String usuario = this.loginView.getTxtUser().getText();
        String senha = this.loginView.getTxtSenha().getText();
        try {
            ResultSet resultado = this.usuarioDAO.consultar(usuario, senha);
            boolean encontrou = false;
            
            while(resultado.next()){
                String rUsuario = resultado.getString("usuario");
                String rSenha = resultado.getString("senha");
                
                if(rUsuario.equals(usuario) && rSenha.equals(senha)){
                    encontrou = true;
                    this.idUsuarioLogado = resultado.getInt("id_usuario"); 
                    
                    JOptionPane.showMessageDialog(this.loginView,
                            "Bem-vindo!", "SUCESSO", 
                            JOptionPane.INFORMATION_MESSAGE);
                    exibirHome();
                    break; 
                }
            }

            if(!encontrou){
                JOptionPane.showMessageDialog(this.loginView, 
                        "Usuário não existe", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } 
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void favoritarFilme() {
        String selecionado = buscasView.getjList1().getSelectedValue();
        
        if (selecionado == null) {
            JOptionPane.showMessageDialog(buscasView, 
                    "Selecione um filme na lista!");
            return;
        }
        int idVideo = Integer.parseInt(selecionado.split(" - ")[0]);

        try {
            usuarioDAO.salvarFavorito(this.idUsuarioLogado, idVideo);
            JOptionPane.showMessageDialog(buscasView, 
                    "Adicionado aos favoritos!");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(buscasView, 
                    "Erro ao salvar favorito: " + e.getMessage());
        }
    }
    
    
    
    public void pesquisarFilmes() {
    String termo = buscasView.getTxtbusca().getText(); 
    
    try {
        List<String> resultados = videoDAO.buscarVideos(termo);
        javax.swing.DefaultListModel<String> model = 
                new javax.swing.DefaultListModel<>();
        for (String item : resultados) {
            model.addElement(item);
        }
        
        buscasView.getjList1().setModel(model);
        
    } 
    catch (SQLException e) {
        JOptionPane.showMessageDialog(buscasView, 
                "Erro na busca: " + e.getMessage());
    }
}
    public void carregarFavoritos() throws SQLException {
        List<String> favoritos = videoDAO.listarFavoritos(this.idUsuarioLogado);
        javax.swing.DefaultListModel<String> model = 
                new javax.swing.DefaultListModel<>();
        for (String f : favoritos) {
            model.addElement(f);
        }
        listaFavView.getjList1().setModel(model);
        homeView.getjList1().setModel(model);
        exibirListaFav(); 
}
    public void excluirFavorito() {
    String selecionado = listaFavView.getjList1().getSelectedValue();
    
    if (selecionado != null) {
        int idVideo = Integer.parseInt(selecionado.split(" - ")[0]);
        try {
            usuarioDAO.removerFavorito(this.idUsuarioLogado, idVideo);
            JOptionPane.showMessageDialog(listaFavView, "Removido com sucesso!");
            carregarFavoritos(); 
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    public void darLike() {
    String selecionado = buscasView.getjList1().getSelectedValue();
    if (selecionado == null) {
        JOptionPane.showMessageDialog(buscasView, "Selecione um filme para curtir!");
        return;
    }

    int idVideo = Integer.parseInt(selecionado.split(" - ")[0]);

    try {
        usuarioDAO.adicionarCurtida(this.idUsuarioLogado, idVideo);
        JOptionPane.showMessageDialog(buscasView, "Você curtiu este filme!");
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void removerLike() {
        String selecionado = buscasView.getjList1().getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(buscasView, "Selecione "
                    + "o filme que deseja descurtir!");
            return;
        }

        int idVideo = Integer.parseInt(selecionado.split(" - ")[0]);

        try {
            usuarioDAO.removerCurtida(this.idUsuarioLogado, idVideo);
            JOptionPane.showMessageDialog(buscasView, "Curtida removida.");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}