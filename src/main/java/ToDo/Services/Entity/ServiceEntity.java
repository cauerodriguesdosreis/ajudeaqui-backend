package ToDo.Services.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do serviço é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição do serviço é obrigatória.")
    private String descricao;

    @NotNull(message = "O preço base é obrigatório.")
    @Positive(message = "O preço base deve ser um valor positivo.")
    private Double precoBase;

    @ElementCollection
    @CollectionTable(name = "servico_atividades", joinColumns = @JoinColumn(name = "servico_id"))
    @Column(name = "atividade")
    private List<@NotBlank(message = "A atividade não pode estar vazia.") String> atividades;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    public ServiceEntity() { }

    public ServiceEntity(String nome, String descricao, Double precoBase, List<String> atividades, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.atividades = atividades;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }

    public List<String> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<String> atividades) {
        this.atividades = atividades;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
