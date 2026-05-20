/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoprimefei.Model.dao;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luk
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        Dotenv dotenv = Dotenv.load();
        Connection conexao = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/projeto Prime fei", "postgres",
                dotenv.get("SENHA_DB"));
        System.out.println("Conexão bem sucedida"); 
        return conexao;
    }
}
