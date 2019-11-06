package com.example.app.apresentacao;

import com.example.app.negocio.ClienteNegocio;
import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteNegocio clienteNegocio;

    @GetMapping("/clientes")
    public Set<ClienteDTO> listar() {
        return clienteNegocio.listar();
    }

    @PostMapping("/clientes")
    public HttpStatus criar(ClienteModel cliente) {
        try {
            clienteNegocio.incluir(ClienteDTO.DTOFromModel(cliente));
        } catch (ObjetoJaExisteException ex) {
            System.out.println("erro");
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.CREATED;
    }
}
