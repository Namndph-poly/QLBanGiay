/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import views.frm_Login;

public class main {

    public static void main(String[] args) {
        // Suppress SLF4J warnings
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");
        System.setProperty("org.slf4j.simpleLogger.log.org.apache.commons", "error");

        new frm_Login().setVisible(true);
    }
}


