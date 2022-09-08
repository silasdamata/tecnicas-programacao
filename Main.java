import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("campeonato-brasileiro-full.csv");

        Stream<String> stream = null;

        quantidadePartidas(path);
        quantidadeGols(path);
        maisVitorioso(path);
        partidasEntre2010E2015(path);

    }

    //Exercicio 1 - Quantas partidas
    private static void quantidadePartidas(Path path) throws IOException {
        Stream<String> stream;
        stream = Files.lines(path);
                int soma = (int) stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .count();
        System.out.println("De 2003 à 2021 foram realizadas " + soma + " partidas");
    }

    private static ResultadosCampeonato mapToTabela (String resultadosCampeonato){

        String [] coluna = resultadosCampeonato.split(",");

        ResultadosCampeonato tabela = new ResultadosCampeonato();
        tabela.setRodada(Integer.parseInt(coluna [1]));
        tabela.setData(LocalDate.parse(coluna[2]));
        tabela.setMandante(coluna[5]);
        tabela.setVisitante(coluna[6]);
        tabela.setVencedor(coluna[11]);
        tabela.setArena(coluna[12]);
        tabela.setMandantePlacar(Integer.parseInt(coluna[13]));
        tabela.setVisitantePlacar(Integer.parseInt(coluna[14]));
        tabela.setMandanteEstado(coluna[15]);
        tabela.setVisitanteEstado(coluna[16]);
        tabela.setVencedorEstado(coluna[17]);

        return tabela;
    }
    //Exercício2 - Quantos gols tiveram no total?
    private static void quantidadeGols(Path path) throws IOException {
        Stream<String> stream;
        stream = Files.lines(path);
        int sum =  stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .map(table -> table.getMandantePlacar()+ table.getVisitantePlacar())
                .reduce((p1, p2) -> p1 + p2)
                .get();
        System.out.println("De 2003 à 2021 foram marcados " + sum + " gols");
    }

    //Exercício 3 - Quantas partidas ocorreram entre 2010 e 2015?
    private static void partidasEntre2010E2015(Path path) throws IOException {
        Stream<String> stream;
        stream = Files.lines(path);

        System.out.println("De 2010 à 2015 foram realizadas " + stream + " partidas");
    }

    //Exercício 4 - Qual o jogo que teve mais gols no campeonato?

    //Exercício 5 - Qual o time que fez mais gols?

    //Exercício 6 - Qual o time que mais levou gols?

    //Exercício 7 - Qual o time teve mais vitórias?
    private static void maisVitorioso(Path path) throws IOException {
        Stream<String> stream;
        stream = Files.lines(path);
        Optional<ResultadosCampeonato> max = stream
                .skip(1)
                .map(table -> mapToTabela(table))
                .max(Comparator.comparing(ResultadosCampeonato::getVencedor));

        System.out.println("O Time que teve mais vitórias foi " + max.get().getVencedor());
    }

    //Exercício 8 - Quantos jogos foram vencidos por mandantes?


    //Exercício 9 - Quantos jogos foram vencidos por visitantes?

    //Exercício 10 - Qual o time que mais perdeu?

    //Exercício 11 - Mostre quantos jogos aconteceram por ano desde 2003.

    //Exercício 12 - Mostre quantos jogos cada um dos times jogaram.

    //Exercício 13 - Faça outras duas análises definadas pelo grupo.

    //Exercício 14 - Por último, salve as análises em um arquivo texto, uma informação por linha. O formato do arquivo é livre.

    }