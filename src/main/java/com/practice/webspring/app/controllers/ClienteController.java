package com.practice.webspring.app.controllers;

import com.practice.webspring.app.models.entity.Cliente;
import com.practice.webspring.app.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteService.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){

        Cliente cliente = new Cliente();

        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable Long id, Map<String, Object> model){

        Cliente cliente;

        if (id > 0){
            cliente = clienteService.findOne(id);

        }else {
            return "Redirect:/listar";
        }

        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);

        return "form";
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){ // Seddionstatus para eliminar la sesion

        if (result.hasErrors()){

            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }

        clienteService.save(cliente);
        status.setComplete(); // para eliminar el objeto cliente de la session
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String delete(@PathVariable Long id){

        if (id > 0) {
            clienteService.delete(id);
        }
        return "redirect:/listar";
    }
}
