/**
 * Disciplina. Nome, carga horária, id, período, lstAlunos, lstProfessores,
 * set(), toString(), addAlunos(), addProfessores(), showAllAlunos(),
 * showAllProfessores(), showAll().
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since Apr 18, 2019 at 2:34:31 PM
 * @version 0.1
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Subject {

    private final Scanner input = new Scanner(System.in);

    private String name, id, semester;
    private byte workload;
    private ArrayList<Student> lstAlunos;
    private ArrayList<Professor> lstProfessores;

    public Subject() {
        this.name = "";
        this.id = "-1";
        this.semester = "-1";
        this.workload = -1;
        this.lstAlunos = new ArrayList<>();
        this.lstProfessores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public byte getWorkload() {
        return workload;
    }

    public void setWorkload(byte workload) {
        this.workload = workload;
    }

    public ArrayList<Student> getLstAlunos() {
        return lstAlunos;
    }

    public void setLstAlunos(ArrayList<Student> lstAlunos) {
        this.lstAlunos = lstAlunos;
    }

    public ArrayList<Professor> getLstProfessores() {
        return lstProfessores;
    }

    public void setLstProfessores(ArrayList<Professor> lstProfessores) {
        this.lstProfessores = lstProfessores;
    }

    public void set() {
        System.out.print("Nome: ");
        this.name = input.nextLine();

        System.out.print("Código: ");
        this.id = input.nextLine();

        System.out.print("Período (par, ímpar ou ambos): ");
        this.semester = input.nextLine();

        System.out.print("Carga Horária: (33 ou 66): ");
        this.workload = input.nextByte();
        input.nextLine();
    }

    /**
     * Adiciona n alunos na lista de Alunos(lstAlunos).
     *
     * @param n : o número de alunos que será adicionada.
     */
    public void addAlunos(byte n) {
        for (int i = 0; i < n; i++) {
            Student a = new Student();
            this.lstAlunos.add(a);
        }
    }

    /**
     * Adiciona n professores na lista de Professoŕes (lstProfessores).
     *
     * @param n : o número de professores que será adicionada.
     */
    public void addProfessores(byte n) {
        for (int i = 0; i < n; i++) {
            Professor p = new Professor();
            this.lstProfessores.add(p);
        }
    }

    /**
     * Lê a lista de alunos dentro do ArrayList e retorna uma string de cada
     * aluno, contendo suas informações.
     *
     */
    public void showAllAlunos() {
        for (Student a : this.lstAlunos) {
            a.toString();
        }
    }

    /**
     * Lê a lista de professores dentro do ArrayList e retorna uma string de
     * cada professor, contendo suas informações.
     *
     */
    public void showAllProfessores() {
        for (Professor p : this.lstProfessores) {
            p.toString();
        }
    }

    @Override
    public String toString() {
        String result = "";
        String lstProfessores = "";
        String lstAlunos = "";

        for (Professor p : this.lstProfessores) {
            lstProfessores += ("\n\t-> " + p.getName());
        }

        for (Student a : this.lstAlunos) {
            lstAlunos += ("\n\t-> " + a.getName());
        }

        result = "\nNome: " + this.name
            + "\nCódigo: " + this.id
            + "\nPeríodo: " + this.semester
            + "\nCarga horária: " + this.workload
            + "\nProfessores: " + lstProfessores
            + "\nAlunos: " + lstAlunos;

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
        String result = "nome;carga horaria;codigo;periodo;";

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
        // nome;carga horaria;codigo;periodo;
        this.name = result[0];
        String aux = Byte.toString(this.workload);
        aux = result[1];
        this.id = result[2];
        this.semester = result[3];
    }

    /**
     * Retorna uma única linha (string) contendo os atributos do objeto
     * instanciado, separados por ponto-e-vírgula, para ser inserido em um
     * arquivo CSV.
     *
     * @return : a linha em que as informações estão armazenadas.
     */
    public String getCsv() {
        String result = "\n" + this.name + ";" + this.workload + ";" + this.id
            + ";" + this.semester;

        return result;
    }

}
