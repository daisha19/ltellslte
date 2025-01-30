package cadastros;

import app.Turma;
import app.Professor;
import app.Disciplina;
import app.Aluno;
import exceptions.CampoEmBrancoException;
import exceptions.DisciplinaNaoAtribuidaException;
import exceptions.ProfessorNaoAtribuidoException;

import java.util.HashMap;
import java.util.Map;

public class CadastroTurma {

    // Instâncias das Váriaveis

    private Map<String, Turma> turmas;

    // Método Construtor

    public CadastroTurma() {
        this.turmas = new HashMap<>();
    }

    // Método de cadastrar as turmas, já fazendo as verificações
    public void cadastrarTurma(Turma turma) throws CampoEmBrancoException, DisciplinaNaoAtribuidaException, ProfessorNaoAtribuidoException {
        if (turma.getCodigo() == null || turma.getCodigo().isBlank()) {
            throw new CampoEmBrancoException("Código da turma não pode ser vazio.");
        }
        if (turma.getNome() == null || turma.getNome().isBlank()) {
            throw new CampoEmBrancoException("Nome da turma não pode ser vazio.");
        }
        if (turma.getProfessor() == null) {
            throw new ProfessorNaoAtribuidoException("Professor da turma não pode ser vazio.");
        }
        turma.validar(); // Verifica se há disciplinas associadas

        turmas.put(turma.getCodigo(), turma);
    }

    // Método para pesquisar as Turmas
    public Turma pesquisarTurma(String codigo) {
        return turmas.get(codigo);
    }

    // Método para atualizar as Turmas
    public boolean atualizarTurma(String codigo, Turma novaTurma) throws CampoEmBrancoException, DisciplinaNaoAtribuidaException, ProfessorNaoAtribuidoException {
        Turma turma = turmas.get(codigo);
        if (turma != null) {
            turma.setNome(novaTurma.getNome());
            turma.setProfessor(novaTurma.getProfessor());
            turma.getDisciplinas().clear();
            turma.getDisciplinas().addAll(novaTurma.getDisciplinas());
            turma.getAlunos().clear();
            turma.getAlunos().addAll(novaTurma.getAlunos());
            return true;
        }
        return false;
    }

    // Método para remover as Turmas
    public boolean removerTurma(Turma turma) {
        return turmas.remove(turma.getCodigo()) != null;
    }

}
