package view;

import javax.swing.JOptionPane;

public class MenuPrincipal {

    // Exibe o menu principal e retorna a opção escolhida pelo usuário
    public static int menuOpcoes() {
        // Texto com as opções do menu
        String opcoes = "1 - Abrir cadastro de alunos \n"
                + "2 - Abrir cadastro de professores \n"
                + "3 - Abrir cadastro de disciplinas \n"
                + "4 - Abrir cadastro de turmas \n"
                + "0 - Sair";

        // Exibe o menu e obtém a opção escolhida como uma string
        String strOpcao = JOptionPane.showInputDialog(opcoes);
        // Converte a opção escolhida para um número inteiro
        int opcao = Integer.parseInt(strOpcao);

        // Retorna a opção escolhida
        return opcao;
    }
}
