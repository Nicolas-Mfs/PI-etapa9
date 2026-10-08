package com.mycompany.pietapa9.service;

import com.mycompany.pietapa9.model.Cliente;
import com.mycompany.pietapa9.model.Pedido;
import com.mycompany.pietapa9.model.Produto;
import com.mycompany.pietapa9.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public Pedido criar(Long clienteId, Long produtoId, Integer quantidade) {
        if (clienteId == null) throw new IllegalArgumentException("Selecione um cliente.");
        if (produtoId == null) throw new IllegalArgumentException("Selecione um produto.");
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Cliente cliente = clienteService.buscarPorId(clienteId);
        Produto produto = produtoService.buscarPorId(produtoId);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(quantidade);
        pedido.setTotal(produto.getPreco() * quantidade);
        return repository.save(pedido);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado: " + id);
        }
        repository.deleteById(id);
    }
}
