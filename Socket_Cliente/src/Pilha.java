import java.io.Serializable;
import java.util.LinkedList;

public class Pilha implements Serializable {

	private static final long serialVersionUID = 1L;
	// Criação de uma linkedList de caracteres
	LinkedList<Caracter> caracteres = new LinkedList<Caracter>();
	// Pega o último elemento da pilha, mas só para checar (olhar)
	// não remove o elemento
	public char peek() {
		return caracteres.getLast().getConteudo();//Pegando conteúdo do próximo caractere
	}
	// Adiciona elemento da pilha do tipo char
	public void push(char c) {
		Caracter car = new Caracter();// Novo caracter
		car.setConteudo(c);// Passando o conteúdo do caracter
		caracteres.push(car);// Inserindo elemento na pilha de caracteres
	}
	//Método para empilhar caracter c
	public void empilha(Caracter c) {
		caracteres.push(c);//Inserindo c na pilha
	}
	//Desempilhar caracter da pilha
	public Caracter desempilha() {
		//Tratando da pilha vazia
		if (eVazia()) {
			System.out.println("Pilha está vazia.");
			return null;
		} else {
			//caracterRemovido recebe a referência do caractere removido da pilha
			Caracter caracterRemovido = this.caracteres.pop();
			return caracterRemovido;//Retorna caractere removido
		}
	}
	//Remove elemento da pilha
	public char pop() {
		//Testando se pilha está vazia
		if (eVazia()) {
			System.out.println("Pilha está vazia.");
			return ' ';
		} else {
			//caracterRemovido recebe a referência do caractere removido da pilha
			Caracter caracterRemovido = this.caracteres.pop();
			return caracterRemovido.getConteudo();//Obtendo conteúdo removido
		}
	}
	//Verifica se a pilha é vazia 
	public boolean eVazia() {
		return caracteres.isEmpty();
	}
}