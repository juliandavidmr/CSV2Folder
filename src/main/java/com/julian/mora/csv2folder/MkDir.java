/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.julian.mora.csv2folder;

import java.io.File;

/**
 *
 * @author macbook
 */
public class MkDir {

    public static boolean CreateDirectory(String path) {
        File theDir = new File(path);
        if (!theDir.exists()) {
            return theDir.mkdirs();
        }
        
        return false;
    }
}
