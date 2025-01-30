package app;

import exceptions.CampoEmBrancoException;

public class Disciplina {

    // Instâncias das Váriaveis

    private String codigo;
    private String nome;
    private int cargaHoraria;

    // Método Construtor

    public Disciplina(String codigo, String nome, int cargaHoraria) throws CampoEmBrancoException {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters e Setters

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + "\n" +
                "Código: " + codigo + "\n" +
                "Carga Horária: " + cargaHoraria + "\n";
    }

}
