package org.example.lab8.controller;

import org.example.lab8.entity.Equipo;
import org.example.lab8.repository.EquipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    final EquipoRepository equipoRepository;

    public EquipoController(EquipoRepository equipoRepository){
        this.equipoRepository = equipoRepository;
    }

//crear
    @PostMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> creaEquipo(
            @RequestBody Equipo equipo,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> rpta = new HashMap<>();

        List<Equipo> equipos = equipoRepository.findAll();
        for(Equipo e : equipos){
            if(equipo.getNombre().equals(e.getNombre())){
                rpta.put("result", "error");
                rpta.put("msg", "El nombre debe ser único");
                return ResponseEntity.badRequest().body(rpta);
            }
        }
        if(equipo.getTag().length()<2 || equipo.getTag().length()>5){
            rpta.put("result", "error");
            rpta.put("msg", "El tag debe tener entre 2 y 5 caractéres");
            return ResponseEntity.badRequest().body(rpta);
        }
        for(Equipo e : equipos){
            if(equipo.getTag().equals(e.getTag())){
                rpta.put("result", "error");
                rpta.put("msg", "El tag debe ser único");
                return ResponseEntity.badRequest().body(rpta);
            }
        }
        if(equipo.getTelefono().length()!=9){
            rpta.put("result", "error");
            rpta.put("msg", "El teléfono debe tener 9 dígitos");
            return ResponseEntity.badRequest().body(rpta);
        }
        equipo.setEstado("Activo");
        equipoRepository.save(equipo);
        if (fetchId) {
            rpta.put("id", equipo.getId());
        }
        rpta.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(rpta);
    }
    //listar
    @GetMapping(value = {"/list", ""})
    public List<Equipo> listaProductos() {
        return equipoRepository.findAll();
    }

    //por tag
    @GetMapping("/list/{tag}")
    public HashMap<String, Object> buscarTag(@PathVariable("tag") String tag) {

        HashMap<String, Object> respuesta = new HashMap<>();

        Optional<Equipo> equipo = equipoRepository.findByTag(tag);
        if (equipo.isPresent()) {
            respuesta.put("result", "ok");
            respuesta.put("equipo", equipo.get());
            return respuesta;
        } else {
            respuesta.put("result", "no existe");
            return respuesta;
        }
    }
    //actualizar
    @PutMapping(value = {"", "/"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Equipo equipo) {

        HashMap<String, Object> rpta = new HashMap<>();

        List<Equipo> equipos = equipoRepository.findAll();
        for(Equipo e : equipos){
            if(equipo.getNombre().equals(e.getNombre())){
                rpta.put("result", "error");
                rpta.put("msg", "El nombre debe ser único");
                return ResponseEntity.badRequest().body(rpta);
            }
        }
        if(equipo.getTag().length()<2 || equipo.getTag().length()>5){
            rpta.put("result", "error");
            rpta.put("msg", "El tag debe tener entre 2 y 5 caractéres");
            return ResponseEntity.badRequest().body(rpta);
        }
        for(Equipo e : equipos){
            if(equipo.getTag().equals(e.getTag())){
                rpta.put("result", "error");
                rpta.put("msg", "El tag debe ser único");
                return ResponseEntity.badRequest().body(rpta);
            }
        }
        if(equipo.getTelefono().length()!=9){
            rpta.put("result", "error");
            rpta.put("msg", "El teléfono debe tener 9 dígitos");
            return ResponseEntity.badRequest().body(rpta);
        }

        if (equipo.getId() != null && equipo.getId() > 0) {

            Optional<Equipo> byId = equipoRepository.findById(equipo.getId());
            if (byId.isPresent()) {
                Equipo eq = byId.get();

                if (equipo.getNombre() != null)
                    eq.setNombre(equipo.getNombre());

                if (equipo.getTag() != null)
                    eq.setTag(equipo.getTag());

                if (equipo.getCapitan() != null)
                    eq.setCapitan(equipo.getCapitan());

                if (equipo.getJugadores() != null)
                    eq.setJugadores(equipo.getJugadores());

                if (equipo.getJuego() != null)
                    eq.setJuego(equipo.getJuego());

                if (equipo.getEstado() != null)
                    eq.setEstado(equipo.getEstado());

                if (equipo.getPais() != null)
                    eq.setPais(equipo.getPais());

                if (equipo.getCorreo() != null)
                    eq.setCorreo(equipo.getCorreo());

                if (equipo.getTelefono() != null)
                    eq.setTelefono(equipo.getTelefono());

                equipoRepository.save(eq);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID del producto enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un producto con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }
}
