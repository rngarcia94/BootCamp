package com.PracticaSpring2.PracticaSpring2.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodigoMorse {

    private String mensajeOriginal;
    private String mensajeConvertido;
    private HashMap<String, Character> diccionario = new HashMap<>();


    public CodigoMorse(String msg){
        mensajeOriginal = msg.toLowerCase();
        mensajeConvertido = "";
        cargarDiccionario();

    }

    public void convertirCodigo(){
        if(esValido()){
            String palabrasAux[] = mensajeOriginal.split("   ");
            for (String palabra: palabrasAux) {
                String letrasAux[] = palabra.split(" ");
                for(String letra:letrasAux){
                    mensajeConvertido += diccionario.get(letra);
                }
                mensajeConvertido += " ";
            }
            mensajeConvertido = mensajeConvertido.substring(0,mensajeConvertido.length()-1);
        }
        else {
            mensajeConvertido += "El input no es valido";
        }
    }

    public Boolean esValido(){
        return mensajeOriginal.matches("[.-]{1,5}(?> [.-]{1,5})*(?>   [.-]{1,5}(?> [.-]{1,5})*)*");
    }

    public void cargarDiccionario(){
        diccionario.put(".-",'a');
        diccionario.put("-...",'b');
        diccionario.put("-.-.",'c');
        diccionario.put("-..",'d');
        diccionario.put(".",'e');
        diccionario.put("..-.",'f');
        diccionario.put("--.",'g');
        diccionario.put("....",'h');
        diccionario.put("..",'i');
        diccionario.put(".---",'j');
        diccionario.put("-.",'k');
        diccionario.put(".-..",'l');
        diccionario.put("--",'m');
        diccionario.put("-.",'n');
        diccionario.put("---",'o');
        diccionario.put(".--.",'p');
        diccionario.put("--.-",'q');
        diccionario.put(".-.",'r');
        diccionario.put("...",'s');
        diccionario.put("-",'t');
        diccionario.put("..-",'u');
        diccionario.put("...-",'v');
        diccionario.put(".--",'w');
        diccionario.put("-..-",'x');
        diccionario.put("-.--",'y');
        diccionario.put("--..",'z');
        diccionario.put(".----",'1');
        diccionario.put("..---",'2');
        diccionario.put("...--",'3');
        diccionario.put("....-",'4');
        diccionario.put(".....",'5');
        diccionario.put("-....",'6');
        diccionario.put("--...",'7');
        diccionario.put("---..",'8');
        diccionario.put("----.",'9');
        diccionario.put("-----",'0');
    }

}
