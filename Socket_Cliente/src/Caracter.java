import java.io.Serializable;

public class Caracter implements Serializable{

	private static final long serialVersionUID = 1L;
	//Definindo conteúdo do tipo char
	char conteudo;
    //Método para pegar o conteúdo de char
	public char getConteudo() {
		return conteudo;//Retornando o conteúdo
	}
	//Método que recebe como argumento conteúdo
	public void setConteudo(char conteudo) {
		this.conteudo = conteudo;
	}
	//Método para tratar string
	public String toString() {
		//Retornando conteúdo da string
		return String.valueOf(conteudo);
	}
}
