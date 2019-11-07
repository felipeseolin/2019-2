package com.example.app.apresentacao;

import com.example.app.negocio.ClienteNegocio;
import com.example.app.negocio.PaisNegocio;
import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteNegocio clienteNegocio;
    private final PaisNegocio paisNegocio;

    @GetMapping("/clientes")
    public Set<ClienteDTO> listar() {
        return clienteNegocio.listar();
    }

    @PostMapping("/clientes")
    public HttpStatus criar(ClienteModel cliente, @RequestParam("paisId") Long paisId) {
        try {
            var pais = paisNegocio.findById(paisId);
            cliente.setPais(pais);
            clienteNegocio.incluir(ClienteDTO.DTOFromModel(cliente));
        } catch (ObjetoJaExisteException ex) {
            System.out.println("erro");
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.CREATED;
    }
}
