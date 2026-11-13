package com.victor.segundaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class CursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column
    private String nome;

    @Column
    private Integer totAulas;

    @Column
    private Integer carga;

    public CursoModel() {}

    public Long getIdCurso() { return idCurso; }
    public void setIdCurso(Long idCurso) { this.idCurso = idCurso; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getTotAulas() { return totAulas; }
    public void setTotAulas(Integer totAulas) { this.totAulas = totAulas; }

    public Integer getCarga() { return carga; }
    public void setCarga(Integer carga) { this.carga = carga; }
}