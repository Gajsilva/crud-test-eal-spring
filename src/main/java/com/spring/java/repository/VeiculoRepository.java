package com.spring.java.repository;
import com.spring.java.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query("SELECT CONCAT(marca) AS marca, SUM(quantidade) AS quantidade FROM Veiculo  GROUP BY marca")
    List<Map<String, Veiculo>> separarQuantidadePorMarcas();

    @Query("SELECT CONCAT(FLOOR(ano/10)*10) AS decada, SUM(quantidade) AS quantidade FROM Veiculo GROUP BY decada")
    List<Map<String, Veiculo>> retornaVeiculosPorDecada();

    @Query("SELECT v FROM Veiculo v WHERE v.criacaoCadastro >= CURRENT_DATE - 7")
    List<Veiculo> retornaVeiculosCriadosEm7dias();
}
