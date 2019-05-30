/**
 * Título da classe. Insira aqui uma pequena descrição sobre a mesma.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since Apr 29, 2019 at 10:21:44 AM
 * @version 0.1
 */
package controllers;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.table.AbstractTableModel;
import model.Professor;

public class TMProfessor extends AbstractTableModel {

    Scanner input = new Scanner(System.in);

    // NOME, EMAIL, SEXO, CPF
    private final int COLUMNS_NUMBER = 4;
    private final int COLUMN_NAME = 0;
    private final int COLUMN_EMAIL = 1;
    private final int COLUMN_GENDER = 2;
    private final int COLUMN_CPF = 3;
    private final ArrayList<Professor> lstProfessor = new ArrayList<>();

    @Override
    public int getRowCount() {
        return this.lstProfessor.size();
    }

    @Override
    public int getColumnCount() {
        return this.COLUMNS_NUMBER;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Professor aux = new Professor();

        switch (columnIndex) {
            case COLUMN_CPF:
                return aux.getCpf();
            case COLUMN_EMAIL:
                return aux.getEmail();
            case COLUMN_GENDER:
                return aux.getGender();
            case COLUMN_NAME:
                return aux.getName();
            default:
                return "NÃO ENCONTRADO";
        }
    }

}
