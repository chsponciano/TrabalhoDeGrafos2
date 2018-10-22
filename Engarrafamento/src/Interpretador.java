
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */
public abstract class Interpretador {
    private static final String LOCAL = "c:\\temp\\entrada.in";
    
    static String ler() {
        int[][] grafo;
        int nVertices, nArestas;
        String[] aux;
        String saida = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(LOCAL));

            while (br.ready()) {
                aux = br.readLine().split(" ");
                nVertices = Integer.parseInt(aux[0]); //Define numero de vertices
                nArestas = Integer.parseInt(aux[1]);  //Define numero de arestas

                if (nArestas != 0 && nVertices != 0) {
                    grafo = new int[nVertices][nVertices]; //Inicia matriz de custo
                    for (int j = 0; j < nArestas; j++) {
                        aux = br.readLine().split(" ");

                        //Passa o custo para a posicao
                        grafo[Integer.parseInt(aux[0]) - 1][Integer.parseInt(aux[1]) - 1] = Integer.parseInt(aux[2]);
                    }

                    aux = br.readLine().split(" ");

                    //Inicializa o grafo e calula a rota
                    saida += new Grafo(nVertices, grafo).calcularRota(Integer.parseInt(aux[0]), Integer.parseInt(aux[1])) + "\n";
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interpretador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interpretador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saida;
    }
}
