package com.practice.webspring.app.controllers;

import com.practice.webspring.app.models.entity.Cliente;
import com.practice.webspring.app.models.service.IClienteService;
import com.practice.webspring.app.models.service.IUploadService;
import com.practice.webspring.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private IClienteService clienteService;

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }


    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos.");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente: " + cliente.getFirstName());
        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) { // DefaultValue se refiere a la pagina cero.

        Pageable pageRequest = PageRequest.of(page, 6);

        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes); // "/listar" -> url que va utilizar el pagnador.


        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();

        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente;

        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "El id del cliente no existe en la BBDD");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser cero");
            return "redirect:/listar";
        }

        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);

        return "form";
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
                          @RequestParam("file") MultipartFile foto, // file nombre de la entrada en la vista en el campo Foto
                          RedirectAttributes flash, SessionStatus status) { // Seddionstatus para eliminar la sesion

        if (result.hasErrors()) {

            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }
        if (!foto.isEmpty()) {

            if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {

                uploadService.delete(cliente.getFoto());
            }

            String uniqueFilename = null;

            try {
                uniqueFilename = uploadService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }


            flash.addFlashAttribute("info", "Se ha subido correctamente '" + uniqueFilename + "'");
            cliente.setFoto(foto.getOriginalFilename());
        }

        String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito" : "Cliente creado con éxito";

        clienteService.save(cliente);
        status.setComplete(); // para eliminar el objeto cliente de la session
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {

        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");

            if (uploadService.delete(cliente.getFoto())) {
                flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " eliminada con exito!");
            }

        }
        return "redirect:/listar";
    }
}
