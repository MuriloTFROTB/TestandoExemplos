import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class TarefaUI extends JFrame {
    private GerenciadorDeTarefas gerenciador;
    private DefaultListModel<Tarefa> listaModel;
    private JList<Tarefa> listaTarefas;
    private JTextArea areaDescricao;

    public TarefaUI() {
        gerenciador = new GerenciadorDeTarefas();
        listaModel = new DefaultListModel<>();
        listaTarefas = new JList<>(listaModel);
        areaDescricao = new JTextArea(5, 20);

        setTitle("Gerenciador de Tarefas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // Painel com a JList
        painel.add(new JScrollPane(listaTarefas), BorderLayout.CENTER);

        // Botão para adicionar tarefa
        JButton btnAdicionar = new JButton("Adicionar Tarefa");
        btnAdicionar.addActionListener(e -> adicionarTarefa());

        // Painel inferior
        JPanel painelInferior = new JPanel();
        painelInferior.add(btnAdicionar);
        painelInferior.add(new JLabel("Descrição:"));
        painelInferior.add(new JScrollPane(areaDescricao));

        painel.add(painelInferior, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        add(painel);

        // Ação para alternar o status de concluída/pendente
        listaTarefas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = listaTarefas.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Tarefa tarefaSelecionada = listaModel.getElementAt(index);
                        tarefaSelecionada.setConcluida(!tarefaSelecionada.isConcluida());
                        atualizarLista();
                    }
                }
            }
        });
    }

    private void adicionarTarefa() {
        String titulo = JOptionPane.showInputDialog(this, "Título da tarefa:");
        String descricao = areaDescricao.getText();

        if (titulo != null && !titulo.isEmpty() && !descricao.isEmpty()) {
            gerenciador.adicionarTarefa(titulo, descricao);
            atualizarLista();
        }
    }

    private void atualizarLista() {
        listaModel.clear();
        Set<Tarefa> tarefas = gerenciador.getTarefas();
        for (Tarefa tarefa : tarefas) {
            listaModel.addElement(tarefa);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TarefaUI ui = new TarefaUI();
            ui.setVisible(true);
        });
    }
}