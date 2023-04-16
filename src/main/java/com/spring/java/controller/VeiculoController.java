package com.spring.java.controller;

import com.spring.java.entity.Veiculo;
import com.spring.java.repository.VeiculoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;


    @GetMapping
    public ResponseEntity<?> listar() {
        return new ResponseEntity<>(veiculoRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(veiculoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/marcas")
    public ResponseEntity<?> listarPorMarca() {
        return new ResponseEntity<>(veiculoRepository.separarQuantidadePorMarcas(), HttpStatus.OK);
    }
    @GetMapping("/decada")
    public ResponseEntity<?> listarPorDecada() {
        return new ResponseEntity<>(veiculoRepository.retornaVeiculosPorDecada(), HttpStatus.OK);
    }
    @GetMapping("/semanal")
    public ResponseEntity<?> listarVeiculosCriadosEm7dias() {
        return new ResponseEntity<>(veiculoRepository.retornaVeiculosCriadosEm7dias(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Veiculo veiculo) {
        Veiculo veiculoDTO = veiculoRepository.save(veiculo);

        return new ResponseEntity<>( veiculoDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Veiculo veiculoDTO) {
       Veiculo veiculo = veiculoRepository.findById(id).orElseThrow();

       veiculo.setNome(veiculoDTO.getNome());
       veiculo.setDescricao(veiculoDTO.getDescricao());
       veiculo.setMarca(veiculoDTO.getMarca());
       veiculo.setAno(veiculoDTO.getAno());
       veiculo.setQuantidade(veiculoDTO.getQuantidade());
       veiculo.setCriacaoCadastro(LocalDateTime.now());
       veiculo.setVendido(veiculoDTO.getVendido());
        return new ResponseEntity<>(veiculoRepository.save(veiculo),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
