package questao1;

import java.util.ArrayDeque;

/**
 * Esse problema foi resolvido com o algoritmo flood fill de duas maneiras,
 * recusivamente e iterativamente
 * 
 * @author Vinícius Luis da Silva && Carlos Henrique Ponciano da Silva
 */
public class Colorindo {

    private int[][] grade;
    private Coordenada coordInicial;
    private int quadradosCheios;
    private String input;

    public Colorindo(String input) {
        this.input = input;
        this.processInput();
    }

    private void processInput() {
        String[] linhas = this.input.split("\n");
        String[] data = linhas[0].split("[\\s]+");
        int qtdLinhas = Integer.parseInt(data[0]);
        int qtdColunas = Integer.parseInt(data[1]);
        this.coordInicial = new Coordenada(Integer.parseInt(data[2]) - 1, Integer.parseInt(data[3]) - 1);
        this.quadradosCheios = Integer.parseInt(data[4]);
        this.grade = new int[qtdLinhas][qtdColunas];

        for (int i = 1; i < linhas.length; i++) {
            data = linhas[i].split("[\\s]+");
            this.grade[(Integer.parseInt(data[0]) - grade.length) * (-1)][Integer.parseInt(data[1]) - 1] = 1;
        }
    }

    public String getGrade(boolean isOiriginal) {
        if (isOiriginal) {
            processInput();
        } else {
            getSaida();
        }
        String str = new String();
        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[i].length; j++) {
                str += " " + grade[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
    
    public int getSaida() {
        //return floodFillRecursivo(coordInicial, 0, 1, 0);
        return floodFillIterativo(0, 1);
    }
    
    private int floodFillIterativo(int tc, int rc) {
        int saida = 0;

        ArrayDeque<Coordenada> fila = new ArrayDeque<>();
        fila.add(coordInicial);

        Coordenada n;
        boolean isCoordValida;

        while(!fila.isEmpty()) {
            n = fila.poll();
            isCoordValida = n.getX() >= 0 && n.getX() < grade.length && n.getY() >= 0 && n.getY() < grade[0].length;
            if(isCoordValida && grade[n.getX()][n.getY()] == tc) {
                saida++;
                grade[n.getX()][n.getY()] = rc;
                fila.add(new Coordenada(n.getX() + 1, n.getY()));
                fila.add(new Coordenada(n.getX() - 1, n.getY()));
                fila.add(new Coordenada(n.getX(), n.getY() + 1));
                fila.add(new Coordenada(n.getX(), n.getY() - 1));
                
                fila.add(new Coordenada(n.getX() + 1, n.getY() + 1));
                fila.add(new Coordenada(n.getX() + 1, n.getY() - 1));
                fila.add(new Coordenada(n.getX() - 1, n.getY() + 1));
                fila.add(new Coordenada(n.getX() - 1, n.getY() - 1));
            }

        }

        return saida;
    }
    
    private int floodFillRecursivo(Coordenada coord, int tc, int rc, int count) {
        if (tc == rc) {
            return count;
        }
        if (coord.getX() < 0 || coord.getX() >= grade.length) {
            return count;
        }
        if (coord.getY() < 0 || coord.getY() >= grade[0].length) {
            return count;
        }
        if(grade[coord.getX()][coord.getY()] == rc) {
            return count;
        }
        count++;
        grade[coord.getX()][coord.getY()] = rc;
        count = floodFillRecursivo(new Coordenada(coord.getX() + 1, coord.getY()), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX() - 1, coord.getY()), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX(), coord.getY() + 1), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX(), coord.getY() - 1), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX() + 1, coord.getY() + 1), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX() + 1, coord.getY() - 1), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX() - 1, coord.getY() + 1), tc, rc, count);
        count = floodFillRecursivo(new Coordenada(coord.getX() - 1, coord.getY() - 1), tc, rc, count);
        return count;
    }

    @Override
    public String toString() {
        return "N = " + this.grade.length + "; "
                + "M = " + this.grade[0].length + "; "
                + "X = " + (this.coordInicial.getX() + 1) + "; "
                + "Y = " + (this.coordInicial.getY() + 1) + "; "
                + "K = " + this.quadradosCheios + "; ";
    }

}
