package cadastros;

import app.Aluno;
import exceptions.CampoEmBrancoException;

public class CadastroAluno {

    // Instâncias das Váriaveis

    private int numAlunos;
    private Aluno[] alunos;

    // Método Construtor

    public CadastroAluno() {
        numAlunos = 0;
        alunos = new Aluno[0];
    }

    // Método para cadastrar os alunos
    public int cadastrarAluno(Aluno a) throws CampoEmBrancoException {
        if (a == null) {
            throw new CampoEmBrancoException("aluno");
        }
        verificarCamposEmBranco(a);
        Aluno[] temp = new Aluno[numAlunos + 1];
        for (int i = 0; i < alunos.length; i++) {
            temp[i] = alunos[i];
        }
        temp[temp.length - 1] = a;
        alunos = temp;
        numAlunos++;
        return numAlunos;
    }

    // Método para pesquisar os alunos
    public Aluno pesquisarAluno(String matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula().equalsIgnoreCase(matricula)) {
                return a;
            }
        }
        return null;
    }

    // Método para remover os alunos
    public boolean removerAluno(Aluno a) {
        if (a == null) {
            return false;
        }

        Aluno aluno = pesquisarAluno(a.getMatricula());
        if (aluno == null) {
            return false;
        }

        Aluno[] temp = new Aluno[numAlunos - 1];
        int j = 0;
        for (int i = 0; i < numAlunos; i++) {
            if (alunos[i] != aluno) {
                temp[j] = alunos[i];
                j++;
            }
        }
        alunos = temp;
        numAlunos--;
        return true;
    }

    // Método para atualizar os alunos
    public boolean atualizarAluno(String matricula, Aluno a) throws CampoEmBrancoException {
        if (a == null) {
            throw new CampoEmBrancoException("aluno");
        }
        verificarCamposEmBranco(a);
        int i;
        for (i = 0; i < alunos.length; i++) {
            if (alunos[i].getMatricula().equalsIgnoreCase(matricula)) {
                break;
            }
        }
        if (i >= numAlunos) {
            return false;
        } else {
            alunos[i] = a;
        }
        return true;
    }

    // Método para verificar os campos em branco dos Alunos
    private void verificarCamposEmBranco(Aluno a) throws CampoEmBrancoException {
        if (a.getNome() == null || a.getNome().trim().isEmpty()) {
            throw new CampoEmBrancoException("nome");
        }
        if (a.getCpf() == null || a.getCpf().trim().isEmpty()) {
            throw new CampoEmBrancoException("CPF");
        }
        if (a.getEmail() == null || a.getEmail().trim().isEmpty()) {
            throw new CampoEmBrancoException("email");
        }
        if (a.getMatricula() == null || a.getMatricula().trim().isEmpty()) {
            throw new CampoEmBrancoException("matrícula");
        }
        if (a.getCurso() == null || a.getCurso().trim().isEmpty()) {
            throw new CampoEmBrancoException("curso");
        }
    }
}
