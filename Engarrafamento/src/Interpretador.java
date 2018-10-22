
/**
 *
 * @author Carlos
 */
public abstract class Interpretador {

    public static String ler(String entrada) {
        int[][] grafo;
        int nVertices, nArestas;

        String[] linhas = entrada.split("\n");
        String[] aux;
        String saida = "";

        for (int i = 0; i < linhas.length; i++) {
            aux = linhas[i].split(" ");

            nVertices = Integer.parseInt(aux[0]); //Define numero de vertices
            nArestas = Integer.parseInt(aux[1]);  //Define numero de arestas

            if (nArestas != 0 && nVertices != 0) {
                grafo = new int[nVertices][nVertices]; //Inicia matriz de custo
                for (int j = 0; j < nArestas; j++) {
                    i++;
                    aux = linhas[i].split(" ");
                    
                    //Passa o custo para a posicao
                    grafo[Integer.parseInt(aux[0]) - 1][Integer.parseInt(aux[1]) - 1] = Integer.parseInt(aux[2]); 
                }
                
                i++;
                aux = linhas[i].split(" ");                
                
                //Inicializa o grafo e calula a rota
                saida += new Grafo(nVertices, grafo).calcularRota(Integer.parseInt(aux[0]), Integer.parseInt(aux[1])) + "\n"; 
            }
        }
        
        return saida;
    }
}
