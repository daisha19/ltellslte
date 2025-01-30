package app;

import exceptions.CampoEmBrancoException;
import exceptions.DisciplinaNaoAtribuidaException;
import exceptions.ProfessorNaoAtribuidoException;

import java.util.ArrayList;
import java.util.List;

public class Turma {

    // Instâncias das Váriaveis

    private String codigo;
    private String nome;
    private List<Disciplina> disciplinas;
    private List<Aluno> alunos;
    private Professor professor;

    // Método Construtor

    public Turma(String codigo, String nome, Professor professor) throws CampoEmBrancoException, ProfessorNaoAtribuidoException, DisciplinaNaoAtribuidaException {
        this.codigo = codigo;
        this.nome = nome;
        this.professor = professor;
        this.disciplinas = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    // Métodos para gerenciar disciplinas e alunos
    public void adicionarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    public void removerDisciplina(Disciplina d) {
        disciplinas.remove(d);
    }

    public void adicionarAluno(Aluno a) {
        alunos.add(a);
    }

    public void removerAluno(Aluno a) {
        alunos.remove(a);
    }

    // Validação para disciplinas
    public void validar() throws DisciplinaNaoAtribuidaException {
        if (disciplinas.isEmpty()) {
            throw new DisciplinaNaoAtribuidaException("A turma deve ter pelo menos uma disciplina.");
        }
    }

    // Imprimir lista de presença
    public void imprimirListaPresenca() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código da Turma: ").append(codigo).append("\n");
        sb.append("Nome da Turma: ").append(nome).append("\n");
        sb.append("Professor: ").append(professor.getNome()).append("\n");

        sb.append("Disciplinas:\n");
        for (Disciplina d : disciplinas) {
            sb.append("  - ").append(d.getNome()).append("\n");
        }

        sb.append("Lista de Alunos:\n");
        for (Aluno a : alunos) {
            sb.append("  Matrícula: ").append(a.getMatricula())
                    .append(" - Nome: ").append(a.getNome()).append("\n");
        }

        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código da Turma: ").append(codigo).append("\n");
        sb.append("Nome da Turma: ").append(nome).append("\n");
        sb.append("Professor: ").append(professor.getNome()).append("\n");
        sb.append("Disciplinas:\n");
        for (Disciplina d : disciplinas) {
            sb.append("  ").append(d.toString()).append("\n");
        }
        sb.append("Alunos:\n");
        for (Aluno a : alunos) {
            sb.append("  ").append(a.toString()).append("\n");
        }
        return sb.toString();
    }
}
