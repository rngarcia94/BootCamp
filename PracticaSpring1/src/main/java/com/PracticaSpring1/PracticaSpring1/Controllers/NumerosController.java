package com.PracticaSpring1.PracticaSpring1.Controllers;


import com.PracticaSpring1.PracticaSpring1.Entities.Numero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numero")
public class NumerosController {

    @GetMapping("/convertir")
    public Numero convertirARomano(@RequestParam(value = "number",defaultValue = "1") String number) throws Exception{
        int n = Integer.parseInt(number);
        Numero num = new Numero(n);
        num.convertirANumerosRomanos();
        return num;
    }

}
