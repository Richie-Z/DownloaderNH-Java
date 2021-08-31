/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.richie.nhdownloder.url;

/**
 *
 * @author Richie-PC
 */
public class CodeValidator {

    private String code;

    public CodeValidator(String code) {
        this.code = this.validation(code);
    }
    
    private final String validation(String c) {
        if (c.toCharArray()[0] == '#') {
            return this.validInteger(c.substring(1));
        }
        return this.validInteger(c);
    }

    private final String validInteger(String c) {
        try {
            int newInt = Integer.parseInt(c);
        } catch (NumberFormatException ex) {
            System.out.println("Not valid int : " + ex);
            return "";
        }
        return c;
    }

    @Override
    public String toString() {
        return code;
    }

    public static void main(String[] args) {
        CodeValidator cv = new CodeValidator("111");
        System.out.println(cv);
    }
}
