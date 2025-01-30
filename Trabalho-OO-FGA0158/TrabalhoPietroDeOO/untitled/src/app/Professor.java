package app;

import exceptions.CampoEmBrancoException;

public class Professor extends PessoaFisica {

    // Instâncias das Váriaveis

    private String areaFormacao;
    private String matriculaFUB;

    // Método Construtor

    public Professor(String nome, String cpf, String email, String areaFormacao, String matriculaFUB) throws CampoEmBrancoException {
        super(nome, cpf, email);
        this.areaFormacao = areaFormacao;
        this.matriculaFUB = matriculaFUB;
    }

    // Getters e Setters

    public String getAreaFormacao() {
        return areaFormacao;
    }

    public void setAreaFormacao(String areaFormacao) throws CampoEmBrancoException {
        if (areaFormacao == null || areaFormacao.trim().isEmpty()) {
            throw new CampoEmBrancoException("Área de Formação");
        }
        this.areaFormacao = areaFormacao;
    }

    public String getMatriculaFUB() {
        return matriculaFUB;
    }

    public void setMatriculaFUB(String matriculaFUB) throws CampoEmBrancoException {
        this.matriculaFUB = matriculaFUB;
    }
}
