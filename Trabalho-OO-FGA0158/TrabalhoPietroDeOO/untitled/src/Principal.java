import javax.swing.JOptionPane;

import cadastros.CadastroAluno;
import cadastros.CadastroDisciplina;
import cadastros.CadastroProfessor;
import cadastros.CadastroTurma;
import view.MenuAluno;
import view.MenuProfessor;
import view.MenuDisciplina;
import view.MenuTurma;
import view.MenuPrincipal;

public class Principal {

    // Instâncias dos objetos de cadastro
    static CadastroAluno cadAluno;
    static CadastroDisciplina cadDisciplina;
    static CadastroProfessor cadProfessor;
    static CadastroTurma cadTurma;

    public static void main(String[] args) {
        // Inicializa os objetos de cadastro
        cadAluno = new CadastroAluno();
        cadDisciplina = new CadastroDisciplina();
        cadProfessor = new CadastroProfessor();
        cadTurma = new CadastroTurma();

        int opcao = 0; // Variável para armazenar a opção escolhida pelo usuário

        do {
            // Exibe o menu principal e obtém a opção escolhida pelo usuário
            opcao = MenuPrincipal.menuOpcoes();

            // De acordo com a opção escolhida, abre o respectivo menu
            switch (opcao) {
                case 1:
                    // Abre o menu de gerenciamento de Alunos
                    MenuAluno.menuAluno(cadAluno);
                    break;
                case 2:
                    // Abre o menu de gerenciamento de Professores
                    MenuProfessor.menuProfessor(cadProfessor);
                    break;
                case 3:
                    // Abre o menu de gerenciamento de Disciplinas
                    MenuDisciplina.menuDisciplina(cadDisciplina);
                    break;
                case 4:
                    // Abre o menu de gerenciamento de Turmas
                    MenuTurma.menuTurma(cadTurma, cadProfessor, cadDisciplina, cadAluno);
                    break;
                case 0:
                    // Saída do Sistema
                    JOptionPane.showMessageDialog(null, "Saindo do sistema.");
                    break;
                default:
                    // Opção padrão para caso o usuário selecione uma opção inválida
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    opcao = -1; // Força a repetição do menu
                    break;
            }
        } while (opcao != 0); // Continua executando até que o usuário escolha sair (opção 0)
    }
}
