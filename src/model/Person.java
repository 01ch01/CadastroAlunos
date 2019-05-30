/**
 * Super Classe Pessoa. Nome, sexo, e-mail, set(), toString(), csvHeader(),
 * setCsv(), getCsv().
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since Apr 17, 2019 at 6:18:14 PM
 * @version 0.1
 */
package model;

import java.util.Scanner;

public class Person {

    Scanner input = new Scanner(System.in);

    private String name;
    private String email;
    private char gender;

    public Person() {
        this.name = "";
        this.email = "";
        this.gender = 'F';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Preenche informações sobre a classe em seus atributos.
     */
    public void set() {
        System.out.println("Nome: ");
        this.name = input.nextLine();
        System.out.println("E-mail: ");
        this.email = input.nextLine();
        System.out.println("Sexo (M ou F): ");
        this.gender = input.next().charAt(0);
        input.nextLine();
    }

    /**
     * Retorna informações sobre o objeto no formato de string.
     *
     * @return : as informações com quebras de linha.
     */
    @Override
    public String toString() {
        String result = "\nNome: " + this.name + "\nE-mail: " + this.email + "\nSexo: " + this.gender;
        return result;
    }

    /**
     * Retorna uma linha com o contendo os atributos da classe. Essa linha será
     * usada mais tarde como cabeçalho de um arquivo csv.
     *
     * @return : uma string com os atributos da classe, separados com
     * ponto-e-vírgula (delimitador do arquivo csv).
     */
    public String getCsvHeader() {
        String result = "nome;email;sexo;";
        return result;
    }

    /**
     * Preenche a linha 'csvRow' com todas as informações sobre o objeto
     * instanciado, para que possa ser inserido em um arquivo CSV. Os atributos
     * são separados (delimitados) por ponto-e-vírgula (;).
     *
     * @param csvRow : a linha em que as informações serão armazenadas.
     */
    public void setCSV(String csvRow) {
        // dividir o vetor de string 'result' toda vez que achar um
        // ponto-e-vírgula
        String[] result = csvRow.split(";");
        // nome;email;sexo;
        this.name = result[0];
        this.email = result[1];
        this.gender = result[2].charAt(0);
    }

    /**
     * Retorna uma única linha (string) contendo os atributos do objeto
     * instanciado, separados por ponto-e-vírgula, para ser inserido em um
     * arquivo CSV.
     *
     * @return : a linha em que as informações estão armazenadas.
     */
    public String getCsv() {
        String result = "\n" + this.name + ";" + this.email + ";" + this.gender;
        return result;
    }
}
