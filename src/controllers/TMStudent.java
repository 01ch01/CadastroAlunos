/**
 * Título da classe. Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since Apr 29, 2019 at 10:21:24 AM
 * @version 0.1
 */
package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import model.Student;

public class TMStudent extends AbstractTableModel {

    Scanner input = new Scanner(System.in);

    // NOME, EMAIL, SEXO, ID, ANO
    private final ArrayList<Student> lstStudents = new ArrayList<>();
    private final ArrayList<String> columnsName = new ArrayList<>();

    public TMStudent() {
        this.columnsName.add("Nome");
        this.columnsName.add("E-mail");
        this.columnsName.add("Gênero");
        this.columnsName.add("Matrícula");
        this.columnsName.add("Ano");
    }

    public ArrayList<Student> getLstStudents() {
        return lstStudents;
    }

    public ArrayList<String> getColumnsName() {
        return columnsName;
    }

    /**
     * Retorna a linha da tabela de acordo com o parâmetro dado. Ou seja,
     * retorna um objeto Student de índice 'index'.
     *
     * @param index o índice da linha.
     * @return um objeto da Classe Student.
     */
    public Student getRow(int index) {
        return this.lstStudents.get(index);
    }

    /**
     * Retorna o número de linhas (alunos).
     *
     * @return o número de linhas.
     */
    @Override
    public int getRowCount() {
        return this.getLstStudents().size();
    }

    /**
     * Retorna o número de colunas (atributos do objeto).
     *
     * @return o número de colunas.
     */
    @Override
    public int getColumnCount() {
        return this.getColumnsName().size();
    }

    /**
     * Retorna algum dado da tabela, dado sua linha e coluna. Ou seja, retorna
     * algum atributo do objeto Student.
     *
     * @param rowIndex a linha em que se encontra o dado.
     * @param columnIndex a coluna em que se encontra o dado.
     * @return o dado da tabela localizada nessa linha e coluna.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == this.getColumnsName().indexOf("Nome")) {
            return this.lstStudents.get(rowIndex).getName();
        } else if (columnIndex == this.getColumnsName().indexOf("E-mail")) {
            return this.lstStudents.get(rowIndex).getEmail();
        } else if (columnIndex == this.getColumnsName().indexOf("Gênero")) {
            return this.lstStudents.get(rowIndex).getGender();
        } else if (columnIndex == this.getColumnsName().indexOf("Matrícula")) {
            return this.lstStudents.get(rowIndex).getId();
        } else if (columnIndex == this.getColumnsName().indexOf("Ano")) {
            return this.lstStudents.get(rowIndex).getYear();
        }
        System.out.println("Objeto não encontrado");
        return null;
    }

    /**
     * Retorna o nome da coluna da tabela, de acordo com o índice passado como
     * parâmetro.
     *
     * @param index : o índice da tabela em si.
     * @return o nome da coluna especificada.
     */
    @Override
    public String getColumnName(int index) {
        return this.getColumnsName().get(index);
    }

    /**
     * Adiciona uma linha (aluno) à tabela e atualiza a mesma.
     *
     * @param s : o aluno que será adicionado
     */
    public void addRow(Student s) {
        this.getLstStudents().add(s);
        this.fireTableDataChanged();
    }

    /**
     * Remove uma linha (aluno) da tabela e atualiza a mesma.
     *
     * @param row : a linha que será removida.
     */
    public void removeRow(int row) {
        this.lstStudents.remove(row);
        this.fireTableRowsDeleted(row, row);
        this.fireTableDataChanged();
    }
}
