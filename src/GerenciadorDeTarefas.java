import java.util.HashSet;
import java.util.Set;

public class GerenciadorDeTarefas {
    private Set<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
        tarefas = new HashSet<>();
    }

    

    public void adicionarTarefa(String titulo, String descricao) {
        tarefas.add(new Tarefa(titulo, descricao));
    }

    public void removerTarefa(String titulo) {
        if (!tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getTitulo().equalsIgnoreCase(titulo)) {
                    tarefas.remove(tarefa);
                    break;
                }
            }
        }
    }

    public void listarTarefas() {
        System.out.println("Tarefas:" + tarefas.toString());
    }

    public void atualizarLista(String titulo, String descricao) {
        if (!tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getTitulo().equalsIgnoreCase(titulo)) {
                    tarefa.setDescricao(descricao);
                }
            }
        }
    }

    public void marcarEDesmarcar(String titulo) {
        if (!tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                if (tarefa.getTitulo().equalsIgnoreCase(titulo)) {
                    tarefa.setConcluida(!tarefa.isConcluida());
                    break;
                }
            }
        }
    }

    public Set<Tarefa> tarefasConcluidas() {
        Set<Tarefa> tarefasConcluidas = new HashSet<>();
        if (!tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                if (tarefa.isConcluida() == true) {
                    tarefasConcluidas.add(tarefa);
                }
            }
        }
        return tarefasConcluidas;
    }

    public Set<Tarefa> getTarefas() {
        return tarefas;
    }
}
