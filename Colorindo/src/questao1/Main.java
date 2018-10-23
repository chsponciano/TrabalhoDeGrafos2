package questao1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Vinícius Luis da Silva && Carlos Henrique Ponciano da Silva
 */
public class Main {

    public static void main(String[] args) {
        boolean sair = false;
        boolean isEntradaValida;
        String input;
        List<Colorindo> problemasResolvidos = new ArrayList<>();
        Colorindo problema = null;
        Scanner scan = new Scanner(System.in);
        //File entrada = Paths.get("data/entrada.in").toFile();
        File entrada = new File("c:\\temp\\entrada.in");
        if (entrada.exists()) {
            try {
                String text = new String();
                String aux;
                BufferedReader br = new BufferedReader(new FileReader(entrada));
                while (br.ready()) {
                    aux = br.readLine();
                    if (aux.isEmpty()) {
                        if (!text.isEmpty()) {
                            problemasResolvidos.add(new Colorindo(text));
                            text = new String();
                        }
                    } else {
                        text += aux + "\n";
                    }
                }
                if (!text.isEmpty()) {
                    problemasResolvidos.add(new Colorindo(text));
                    text = new String();
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erro inesperado ao ler o arquivo entrada.in");
            }
        }

        do {
            if (problema == null) {
                System.out.println("Não existe problema selecionado!");
                if (problemasResolvidos.isEmpty()) {
                    problema = Main.novoProblema(scan);
                    problemasResolvidos.add(problema);
                } else {
                    problema = Main.selecionarOutro(problemasResolvidos, scan);
                }
            } else {
                System.out.println("Problema selecionado: " + problema);
            }
            do {
                isEntradaValida = true;
                System.out.println("1 = criar novo; "
                        + "2 = mostrar saida; "
                        + "3 = mostrar grade; "
                        + "4 = selecionar outro; "
                        + "5 = encerrar programa;");
                input = scan.nextLine();
                if (input.equals("1")) {
                    problema = Main.novoProblema(scan);
                    problemasResolvidos.add(problema);
                } else if (input.equals("2")) {
                    System.out.println(problema.getSaida());
                } else if (input.equals("3")) {
                    do {
                        System.out.println("1 = grade original; 2 = grade pintada; 3 = sair;");
                        input = scan.nextLine();
                        if (input.equals("1")) {
                            System.out.println(problema.getGrade(true));
                            break;
                        } else if (input.equals("2")) {
                            System.out.println(problema.getGrade(false));
                            break;
                        } else if (input.equals("3")) {
                            break;
                        } else {
                            System.out.println("Entrada inválida!");
                        }
                    } while (true);

                } else if (input.equals("4")) {
                    problema = Main.selecionarOutro(problemasResolvidos, scan);
                } else if (input.equals("5")) {
                    sair = true;
                } else {
                    isEntradaValida = false;
                    System.out.println("Entrada Inválida!");
                }
            } while (!isEntradaValida);
        } while (!sair);

    }

    public static Colorindo novoProblema(Scanner scan) {
        System.out.println("1 = digitar valores; 2 = ler arquivo;");
        String input;
        String aux;
        do {
            aux = scan.nextLine();
            if (aux.equals("1")) {
                System.out.println("N = linhas; "
                        + "M = colunas; "
                        + "X = coordenada inicial X; "
                        + "Y = coordenada inicial Y; "
                        + "K = numero de quadrados na figura;");
                System.out.println("A = coordenada X printada; B = coordenada Y printada;");
                while (true) {
                    try {
                        input = scan.nextLine();
                        while (true) {
                            aux = scan.nextLine();
                            if (!aux.isEmpty()) {
                                input += "\n" + aux;
                            } else {
                                break;
                            }
                        }
                        return new Colorindo(input);
                    } catch (Exception e) {
                        System.out.println("Input Inválido!");
                    }
                }
            } else if (aux.equals("2")) {
                input = new String();
                do {
                    try {
                        System.out.print(Paths.get("data").toAbsolutePath().toString() + File.separatorChar);
                        BufferedReader br = new BufferedReader(
                                new FileReader(
                                        new File(Paths.get("data/" + scan.nextLine()).toAbsolutePath().toUri())
                                )
                        );

                        while (br.ready()) {
                            input += br.readLine() + "\n";
                        }
                        br.close();
                        try {
                            return new Colorindo(input);
                        } catch (Exception e) {
                            System.out.println("Erro na leitura do arquivo");
                        }
                        break;
                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não encontrado!");
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao ler o arquivo!");
                    }
                } while (true);
            }
        } while (true);

    }

    public static Colorindo selecionarOutro(List<Colorindo> problemasResolvidos, Scanner scan) {
        while (true) {
            try {
                for (int i = 0; i < problemasResolvidos.size(); i++) {
                    System.out.println((i + 1) + " = " + problemasResolvidos.get(i));
                }
                System.out.print("Selecionar problema: ");
                return problemasResolvidos.get(Integer.parseInt(scan.nextLine()) - 1);
            } catch (Exception e) {
                System.out.println("Entrada Inválida!");
            }
        }
    }

}
