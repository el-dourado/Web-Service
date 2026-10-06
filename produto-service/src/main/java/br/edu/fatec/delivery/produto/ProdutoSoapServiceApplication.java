package br.edu.fatec.delivery.produto;

import br.edu.fatec.delivery.produto.entity.Produto;
import br.edu.fatec.delivery.produto.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Classe principal da aplicação Spring Boot.
 */
@SpringBootApplication
public class ProdutoSoapServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoSoapServiceApplication.class, args);
    }

    /**
     * Popula o banco de dados com produtos de exemplo ao iniciar a aplicação.
     */
    @Bean
    public CommandLineRunner initDatabase(ProdutoRepository repository) {
        return args -> {
            repository.save(new Produto("RTX4090", "NVIDIA GeForce RTX 4090",
                "Placa de vídeo top de linha com 24GB GDDR6X", "NVIDIA", 15));

            repository.save(new Produto("I9-14900K", "Intel Core i9-14900K",
                "Processador Intel 14ª geração, 24 cores, 5.8GHz", "Intel", 25));

            repository.save(new Produto("RYZEN9-7950X", "AMD Ryzen 9 7950X",
                "Processador AMD Ryzen 16 cores, 5.7GHz", "AMD", 30));

            repository.save(new Produto("DDR5-6000-32GB", "Kingston Fury Beast DDR5",
                "Memória RAM DDR5 6000MHz 32GB (2x16GB)", "Kingston", 50));

            repository.save(new Produto("SSD-990PRO-2TB", "Samsung 990 PRO 2TB",
                "SSD NVMe M.2 PCIe 4.0, leitura 7450MB/s", "Samsung", 40));

            repository.save(new Produto("B650-GAMING", "ASUS ROG Strix B650-A",
                "Placa-mãe AMD B650, suporte DDR5 e PCIe 5.0", "ASUS", 20));
        };
    }
}