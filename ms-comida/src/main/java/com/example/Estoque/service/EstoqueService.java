package com.example.Estoque.service;

import com.example.Estoque.entity.Estoque;
import com.example.Estoque.exception.EntityIdNotFoundException;
import com.example.Estoque.exception.NameUniqueViolationException;
import com.example.Estoque.repository.EstoqueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EstoqueService {

    public final EstoqueRepository repository;
    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public Estoque create(Estoque estoque){
        try {
            log.info("Criando novo produto: " + estoque.toString());
            return repository.save(estoque);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            log.error("Erro ao criar produto: " + e.getMessage());
            throw new NameUniqueViolationException(String.format("Produto (%s) já cadastrado em nosso banco de dados!", estoque.getNome()));
        }
    }

    public List<Estoque> findAll(){
        log.info("Listando todos os produtos");
        return repository.findAll();
    }

    public Estoque findByName(String name){
        log.info("Buscando produto pelo nome: " + name);
        return repository.findByNome(name).orElseThrow(() -> new EntityIdNotFoundException(
                String.format("Produto com o nome(%s) não encontrado!", name)));
    }

    public Estoque findById(Long id){
        log.info("Buscando produto pelo id: " + id.toString());
        return repository.findById(id).orElseThrow(() -> new EntityIdNotFoundException(
                String.format("Produto com o id(%d) não encontrado!", id)));
    }

    public void deleteById(Long id){
        log.info("Deletando produto pelo id: " + id.toString());
        repository.deleteById(id);
    }

    public Estoque buyByName(String nome) {
        Estoque estoque = findByName(nome);
        if (estoque.getQtnd() > 0){
            log.info("Dando baixa no produto pelo nome: " + nome);
            estoque.setQtnd(estoque.getQtnd() - 1);
            } else {
                log.error("Erro ao dar baixa produto");
                throw new RuntimeException("Produto esgotado!");
            }
        return repository.save(estoque);
    }
}
