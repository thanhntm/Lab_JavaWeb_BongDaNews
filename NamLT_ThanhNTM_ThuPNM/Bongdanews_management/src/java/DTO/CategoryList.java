/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 * @param <T>
 */
public class CategoryList<T> extends ArrayList<T>{

    @Override
    public String toString() {
        String result = "";
        for (T thi : this) {
            result+=thi.toString();
        }
        return result;
    }
    
}
