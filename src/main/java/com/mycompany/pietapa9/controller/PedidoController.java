package com.mycompany.pietapa9.controller;

import com.mycompany.pietapa9.model.Pedido;
import com.mycompany.pietapa9.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private PedidoService service;

    // Formato do JSON que a página envia
    public record PedidoRequest(Long clienteId, Long produtoId, Integer quantidade) {}

    @GetMapping
    public List<Pedido> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody PedidoRequest req) {
        Pedido pedido = service.criar(req.clienteId(), req.produtoId(), req.quantidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
