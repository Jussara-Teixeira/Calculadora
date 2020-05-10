
	import java.util.ArrayList;
	import java.util.List;

	public class PosFix {
		
	    // Função que converte uma expressão numérica para expressão posfix
		public static List<Character> toPosFix(String expressao) {
			Pilha opstack=new Pilha();//Criando uma pilha que guardará os operadores e parenteses
			// Lista que guardará a saída
			List<Character> saida = new ArrayList<Character>(); 
			//Percorendo a expressão
			for (int i = 0; i < expressao.length(); i++) {
				
				//Recebe cada elemento da expressão
				char item = expressao.charAt(i);
				
				//Tratando os elementos da expressão
				switch (item) {
					case '(':
						//Insere item na pilha
						opstack.push(item);
						break;
					case ')':
						//Acho um parentese fechando, então precisamos desempilhar 
						// até achar um parentese abrindo. Isso quer dizer que encontramos um
						// expressão a ser calculada
						char aux = opstack.pop();
						while(aux!='(') {
							saida.add(aux); //Adiciona um elemento novo na pilha
							aux = opstack.pop(); //remove novamente um elemento
						}
						break;
					case '+':
					case '-':
					case '*':
					case '/':
						//Enquanto pilha de operadores não for vazia 
						// e a prioridade do operador encontrado tiver menor prioridade
						// que os operadores na pilha de operadores
						while(!opstack.eVazia()
								&& getPrioridade(opstack.peek()) >= getPrioridade(item)) {
							// Pega o operador da pilha de operandos 
							// e adiciona na lista de saída
							saida.add(opstack.pop()); 
						}
						opstack.push(item);
						break;
					
					default:
						// Se não for operador ou parenteses, então é um operando. 
						// adiciona esse elemento na saída
						saida.add(item);
						break;
				}
			}
			// Adiciona os elementos restantes que ainda estão na pilha de operadores
			while(!opstack.eVazia()) {
				saida.add(opstack.pop());
			}
		        
		    return saida;
		}
		
		// Função que transforma uma pilha em String
		private static String getValues(Pilha pilha) {
			String resultado = "";
			while(!pilha.eVazia()) {
				resultado = "" + pilha.pop() + resultado;
			}
			return resultado;
		}
		
		// Função que pega uma pilha com uma expressão POSFIX e calcula o resultado 
		public static float calculateExpression(Pilha pilha) {
			String expressao = getValues(pilha);
			
			// Lista de operandos
			List<Float> operandosPilha = new ArrayList<Float>();
			
			// Percorrendo a expressão posfix
			// Cada resultado parcial é adicionado na lista de saída
			for (int i = 0; i < expressao.length(); i++) {
				//Pegando o elemento do início da fila
				char head = expressao.charAt(i);
				//Se os operandos forem algum número entre 0 e 9
				if(head == '0' || head == '1' || head == '2' || head == '3' ||
						head == '4' || head == '5' || head == '6' || head == '7' || head == '8' || head == '9') {
					// adiciona o operando na lista
					operandosPilha.add(Float.parseFloat("" + head));//
				}else {
				// Senão, quer dizer que temos um operador, hora de calcular uma expressão
					// Pega o último operando da lista
					float operand2 = operandosPilha.remove(operandosPilha.size()-1);
					// Pega o último operando da lista
					float operand1 = operandosPilha.remove(operandosPilha.size()-1);
					// Calcula o resultado a partir dos últimos operandos
				    float result = doMath(head,operand1,operand2);
				    // Adiciona o resultado da operação na pilha
				    operandosPilha.add(Float.parseFloat("" + result));
				}
			}
			// Retorna o último elemento da lista, o resultado
			return operandosPilha.remove(operandosPilha.size()-1);
			
		}
		
		// Função que calcula as operações
		private static float doMath(char op, float op1, float op2) {
		    if(op == '*'){
		        return op1 * op2;
		    }else if (op == '/'){
		        return op1 / op2;
		    }else if (op == '+'){
		        return op1 + op2;
		    }else {
		    	return op1 - op2;
		    }
		}

		//Tratando a prioridade dos operadores
		private static int getPrioridade(char caractere) {
			switch (caractere) {
				case '/':
				case '*':
					return 3;
					
				case '+':
				case '-':
					return 2;
					
				case '(':
					return 1;
				default:
					return 0;
				}
		}
		
		// Função que converte uma lista para uma String
		public static String converterToString(List<Character> saida) {
			StringBuilder sb = new StringBuilder();

			int i = 0;
			while (i < saida.size() - 1) {
				sb.append(saida.get(i));
				i++;
			}
			sb.append(saida.get(i));

			return sb.toString();
		}
	}
