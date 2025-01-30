package app;

import exceptions.CampoEmBrancoException;

public class Aluno extends PessoaFisica {

    // Instâncias das Váriaveis

    private String matricula;
    private String curso;

    // Método Construtor

    public Aluno(String nome, String cpf, String email, String matricula, String curso) throws CampoEmBrancoException {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.curso = curso;
    }

    // Getters e Setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "CPF: " + getCpf() + "\n" +
                "E-mail: " + getEmail() + "\n" +
                "Matricula: " + getMatricula() + "\n" +
                "Curso: " + getCurso() + " \n";
    }
}
