package cadastros;

import app.Professor;
import exceptions.CampoEmBrancoException;

public class CadastroProfessor {

    // Instâncias das Váriaveis

    private int numProfessores;
    private Professor[] professores;

    // Método Construtor

    public CadastroProfessor() {
        numProfessores = 0;
        professores = new Professor[0];
    }

    // Método para cadastrar os professores
    public int cadastrarProfessor(Professor p) throws CampoEmBrancoException {
        if (p == null) {
            throw new CampoEmBrancoException("professor");
        }
        verificarCamposEmBranco(p);
        Professor[] temp = new Professor[numProfessores + 1];
        for (int i = 0; i < professores.length; i++) {
            temp[i] = professores[i];
        }
        temp[temp.length - 1] = p;
        professores = temp;
        numProfessores++;
        return numProfessores;
    }

    // Método para pesquisar os professores
    public Professor pesquisarProfessor(String matriculaFUB) {
        for (Professor p : professores) {
            if (p.getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
                return p;
            }
        }
        return null;
    }

    // Método para remover os professores
    public boolean removerProfessor(Professor p) {
        if (p == null) {
            return false;
        }

        Professor professor = pesquisarProfessor(p.getMatriculaFUB());
        if (professor == null) {
            return false;
        }

        Professor[] temp = new Professor[numProfessores - 1];
        int j = 0;
        for (int i = 0; i < numProfessores; i++) {
            if (professores[i] != professor) {
                temp[j] = professores[i];
                j++;
            }
        }
        professores = temp;
        numProfessores--;
        return true;
    }

    // Método para atualizar os professores
    public boolean atualizarProfessor(String matriculaFUB, Professor p) throws CampoEmBrancoException {
        if (p == null) {
            throw new CampoEmBrancoException("professor");
        }
        verificarCamposEmBranco(p);
        int i;
        for (i = 0; i < professores.length; i++) {
            if (professores[i].getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
                break;
            }
        }
        if (i >= numProfessores) {
            return false;
        } else {
            professores[i] = p;
        }
        return true;
    }

    //Método para verficiar os campos em branco dos Professores
    private void verificarCamposEmBranco(Professor p) throws CampoEmBrancoException {
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new CampoEmBrancoException("nome");
        }
        if (p.getCpf() == null || p.getCpf().trim().isEmpty()) {
            throw new CampoEmBrancoException("CPF");
        }
        if (p.getEmail() == null || p.getEmail().trim().isEmpty()) {
            throw new CampoEmBrancoException("email");
        }
        if (p.getAreaFormacao() == null || p.getAreaFormacao().trim().isEmpty()) {
            throw new CampoEmBrancoException("área de formação");
        }
        if (p.getMatriculaFUB() == null || p.getMatriculaFUB().trim().isEmpty()) {
            throw new CampoEmBrancoException("matrícula FUB");
        }
    }
}
