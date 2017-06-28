package br.com.alberson.desafiojava.controllers;

import br.com.alberson.desafiojava.model.Transferencia;
import br.com.alberson.desafiojava.servico.TransferenciaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    TransferenciaServico transferenciaServico;

    @GetMapping("/")
    public String getIndex(Transferencia transferencia, Map<String, Object> model) {
        model.put("transferencias", transferenciaServico.listar());
        return "home";
    }

    @PostMapping("/")
    public String postIndex(@Valid Transferencia transferencia, BindingResult result, Map<String, Object> model) {
        try {
            if (!result.hasErrors()) {
                transferenciaServico.adicionar(transferencia);
                return "redirect:/";
            }
            model.put("transferencias", transferenciaServico.listar());
            return "home";
        } catch (Exception ex) {
            model.put("mensagemErro", ex.getLocalizedMessage());
            return "home";
        }
    }

}
