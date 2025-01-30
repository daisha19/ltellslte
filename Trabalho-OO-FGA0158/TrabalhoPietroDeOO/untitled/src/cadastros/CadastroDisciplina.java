package cadastros;

import app.Disciplina;
import exceptions.CampoEmBrancoException;

public class CadastroDisciplina {

    // Instâncias das Váriaveis

    private int numDisciplinas;
    private Disciplina[] disciplinas;

    // Método Construtor

    public CadastroDisciplina() {
        numDisciplinas = 0;
        disciplinas = new Disciplina[0];
    }

    // Método para cadastrar as Disciplinas
    public int cadastrarDisciplina(Disciplina d) throws CampoEmBrancoException {
        if (d == null) {
            throw new CampoEmBrancoException("disciplina");
        }
        verificarCamposEmBranco(d);
        Disciplina[] temp = new Disciplina[numDisciplinas + 1];
        for (int i = 0; i < disciplinas.length; i++) {
            temp[i] = disciplinas[i];
        }
        temp[temp.length - 1] = d;
        disciplinas = temp;
        numDisciplinas++;
        return numDisciplinas;
    }

    // Método para pesquisar as Disciplinas
    public Disciplina pesquisarDisciplina(String codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                return d;
            }
        }
        return null;
    }

    // Método para remover as Disciplinas
    public boolean removerDisciplina(Disciplina d) {
        if (d == null) {
            return false;
        }

        Disciplina disciplina = pesquisarDisciplina(d.getCodigo());
        if (disciplina == null) {
            return false;
        }

        Disciplina[] temp = new Disciplina[numDisciplinas - 1];
        int j = 0;
        for (int i = 0; i < numDisciplinas; i++) {
            if (disciplinas[i] != disciplina) {
                temp[j] = disciplinas[i];
                j++;
            }
        }
        disciplinas = temp;
        numDisciplinas--;
        return true;
    }

    // Método para atualizar as Disciplinas
    public boolean atualizarDisciplina(String codigo, Disciplina d) throws CampoEmBrancoException {
        if (d == null) {
            throw new CampoEmBrancoException("disciplina");
        }
        verificarCamposEmBranco(d);
        int i;
        for (i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i].getCodigo().equalsIgnoreCase(codigo)) {
                break;
            }
        }
        if (i >= numDisciplinas) {
            return false;
        } else {
            disciplinas[i] = d;
        }
        return true;
    }

    // Método para verificar os campos em branco das Disciplinas
    private void verificarCamposEmBranco(Disciplina d) throws CampoEmBrancoException {
        if (d.getNome() == null || d.getNome().trim().isEmpty()) {
            throw new CampoEmBrancoException("nome");
        }
        if (d.getCodigo() == null || d.getCodigo().trim().isEmpty()) {
            throw new CampoEmBrancoException("código");
        }
        if (d.getCargaHoraria() <= 0) {
            throw new CampoEmBrancoException("carga horária");
        }
    }
}
