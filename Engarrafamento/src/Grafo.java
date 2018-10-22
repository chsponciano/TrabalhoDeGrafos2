
public class Grafo {

    private int nVertices;
    private int[][] custos;
    private final int max = 99999;

    public Grafo(int nVertices, int[][] custos) {
        this.nVertices = nVertices;
        this.custos = custos;
    }

    public int calcularRota(int inicial, int fim) {
        int result = floydWarshall()[inicial - 1][fim - 1];
        return (result == this.max) ? -1 : result;
    }

    private int[][] floydWarshall() {
        int[][] distancias = this.inicializar();

        for (int k = 0; k < nVertices; k++) {
            for (int i = 0; i < nVertices; i++) {
                for (int j = 0; j < nVertices; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }
        return distancias;
    }

    private int[][] inicializar() {
        int[][] v = new int[nVertices][nVertices];
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                if ((this.custos[i][j] == 0) && (i != j)) {
                    v[i][j] = this.max;
                } else {
                    v[i][j] = this.custos[i][j];
                }
            }
        }

        return v;
    }
}
