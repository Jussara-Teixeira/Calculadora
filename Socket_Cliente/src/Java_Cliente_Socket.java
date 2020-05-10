import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import com.google.gson.Gson;

public class Java_Cliente_Socket {
	
public static void main(String[] args) {
		
	    String resultado;
		Pilha pilha = new Pilha();//Criação de uma pilha
		Caracter car = null;//Criação de Caracter car inicialmente null
		String expressao = "(5+9)*2+6*5";//Declaração da expressão
		//Lista recebendo  expressão na forma Pós Fixa 
		List<Character> expressaoPosFixList = PosFix.toPosFix(expressao);
		//Expressão recebendo a conversão da lista expressaoPosFixList para String
		expressao= PosFix.converterToString(expressaoPosFixList);
		//Percorrendo expressão
		for (int i = 0; i < expressao.length(); i++) {
			car = new Caracter();//Novo caractere
			car.setConteudo(expressao.charAt(i));//Conteúdo dos carateres da expressão
			pilha.empilha(car);//Empilhando caracter na pilha
		
		//SEREALIZA
		Gson gson = new Gson();//Cria um Json
        String expressaoJSON = gson.toJson(pilha);//Convertendo pilha para Json
        
		try {
        	//Conexão com o Servidor
            Socket clientSocket = new Socket("127.0.0.1", 9090);
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Enviando os dados
            socketSaidaServer.writeBytes(expressaoJSON+"\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            resultado=messageFromServer.readLine();
            //Resultado
            System.out.println("resultado="+resultado);

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
		}
}
}