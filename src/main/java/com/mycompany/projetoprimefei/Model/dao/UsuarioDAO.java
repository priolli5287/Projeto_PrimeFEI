/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoprimefei.Model.dao;

import com.mycompany.projetoprimefei.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luk
 */
public class UsuarioDAO {
    private Connection conn;
    
    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }
    public Connection getConn(){
        return this.conn;
    }

    public void inserir(Usuario usuario) throws SQLException {
    String sql = "INSERT INTO usuarios (nome, usuario, senha) VALUES (?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getUsuario());
        stmt.setString(3, usuario.getSenha());
        
        stmt.execute();
    }
    }
    
    public ResultSet consultar(String usuario, String senha) throws SQLException{
        String sql = "SELECT * from usuarios where usuario = ? and "
                + "senha = ?";
        PreparedStatement statement = this.conn.prepareStatement(sql);
        statement.setString(1, usuario);
        statement.setString(2, senha);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public void salvarFavorito(int idUsuario, int idVideo) throws SQLException {
    String sql = "INSERT INTO favoritos (id_usuario, id_video) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate(); 
        }
    }
    public List<String> listarFavoritos(int idUsuario) throws SQLException {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT v.id_video, v.titulo FROM videos v " +
                 "JOIN favoritos f ON v.id_video = f.id_video " +
                 "WHERE f.id_usuario = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) { 
        stmt.setInt(1, idUsuario);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getInt("id_video") + " - " + rs.getString("titulo"));
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro no SQL: " + e.getMessage());
        throw e; 
    }
    return lista;
    }
    public void removerFavorito(int idUsuario, int idVideo) throws SQLException {
    String sql = "DELETE FROM favoritos WHERE id_usuario = ? AND id_video = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idUsuario);
        stmt.setInt(2, idVideo);
        stmt.executeUpdate();
    }
    }
    
    public void adicionarCurtida(int idUser, int idVideo) throws SQLException {
    String sql = "INSERT INTO curtidas (id_usuario, id_video) "
            + "VALUES (?, ?) ON CONFLICT DO NOTHING";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idUser);
        stmt.setInt(2, idVideo);
        stmt.executeUpdate();
    }
    }

    public void removerCurtida(int idUser, int idVideo) throws SQLException {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_video = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUser);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        }
        }
    
}