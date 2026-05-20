/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoprimefei.Model.dao;

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
public class VideoDAO {
    private Connection conn;

    public VideoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> buscarVideos(String termo) throws SQLException {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT DISTINCT id_video, titulo, genero FROM videos " +
             "WHERE titulo ILIKE ? OR genero ILIKE ? " +
             "ORDER BY titulo";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        String busca = "%" + termo + "%";
        stmt.setString(1, busca); 
        stmt.setString(2, busca); 
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                
                lista.add(rs.getInt("id_video") + " - " + rs.getString("titulo")
                        + " (" + rs.getString("genero") + ")");
            }
        }
    }
    return lista;
}

    public List<String> listarFavoritos(int idUsuario) throws SQLException {
    List<String> lista = new ArrayList<>();
    String sql = "SELECT v.id_video, v.titulo FROM videos v " +
                 "JOIN favoritos f ON v.id_video = f.id_video " +
                 "WHERE f.id_usuario = ?";

    try (PreparedStatement stmt = this.conn.prepareStatement(sql)) { 
        stmt.setInt(1, idUsuario);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getInt("id_video") + " - " + rs.getString("titulo"));
            }
        }
    }
    return lista;
}
}
    

