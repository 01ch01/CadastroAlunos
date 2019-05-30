/**
 * Aluno. Número de matrícula, ano de ingresso.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since Apr 17, 2019 at 6:22:53 PM
 * @version 0.1
 */
package model;

public class Student extends Person {

    private String id, year;

    public Student() {
        super();
        this.id = "-1";
        this.year = "-1";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Preenche informações sobre a classe em seus atributos.
     */
    @Override
    public void set() {
        super.set();

        System.out.print("Número de matrícula: ");
        this.id = input.nextLine();

        System.out.println("Ano de ingresso: ");
        this.year = input.nextLine();
    }

    /**
     * Retorna informações sobre o objeto no formato de string.
     *
     * @return : as informações com quebras de linha.
     */
    @Override
    public String toString() {
        String result = super.toString();
        result += "\nNúmero de matrícula: " + this.id + "\nAno de ingresso: "
            + this.year + "\n";

        return result;
    }

    /**
     * Retorna uma linha com o contendo os atributos da classe. Essa linha será
     * usada mais tarde como cabeçalho de um arquivo csv.
     *
     * @return : uma string com os atributos da classe, separados com
     * ponto-e-vírgula (delimitador do arquivo csv).
     */
    @Override
    public String getCsvHeader() {
        String result = super.getCsvHeader();
        result += "matricula;ano";

        return result;
    }

    /**
     * Preenche a linha 'csvRow' com todas as informações sobre o objeto
     * instanciado, para que possa ser inserido em um arquivo CSV. Os atributos
     * são separados (delimitados) por ponto-e-vírgula (;).
     *
     * @param csvRow : a linha em que as informações serão armazenadas.
     */
    @Override
    public void setCSV(String csvRow) {
        super.setCSV(csvRow);
        // dividir o vetor de string 'result' toda vez que achar um ;
        String[] result = csvRow.split(";");
        // matricula;ano;
        this.id = result[3];
        this.year = result[4];
    }

    /**
     * Retorna uma única linha (string) contendo os atributos do objeto
     * instanciado, separados por ponto-e-vírgula, para ser inserido em um
     * arquivo CSV.
     *
     * @return : a linha em que as informações estão armazenadas.
     */
    @Override
    public String getCsv() {
        String result = super.getCsv();
        result += ";" + this.id + ";" + this.year;

        return result;
    }

}
