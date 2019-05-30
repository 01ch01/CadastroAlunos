package gui;

import controllers.TMStudent;
import model.Student;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 */
public class FrAluno extends javax.swing.JFrame {

//    public  ArrayList<Student> lstStudents = new ArrayList<>();
    // já que eu tenho que instanciar um aluno 3 vezes ao longo da classe, é
    // melhor criar um atributo aqui de uma vez.
    Student aux = new Student();
    private boolean isEdit;
    TMStudent tableStudent = new TMStudent();

    /**
     * Creates new form FrAluno
     */
    public FrAluno() {
        this.isEdit = false;
        initComponents();

        this.tblStudents.setModel(tableStudent);

        this.loadFile("src/csv/lst_students.csv");
        this.enableFields(false);
//        this.tableStudent.lstStudent = this.lstStudents;
//        this.showTable();
//        System.out.println(this.lstStudents.toString());
    }

    /**
     * Carrega a lista de alunos a partir de um arquivo CSV.
     *
     * @param path : o caminho do arquivo CSV.
     */
    private void loadFile(String path) {
        FileReader file;
        try {
            file = new FileReader(path);
            Scanner readFile = new Scanner(file);
            readFile.useDelimiter("\n");

            // pulando linha do cabeçalho
            readFile.next();

            while (readFile.hasNext()) {
                String csvRow = readFile.next();
                this.aux = new Student();
                this.aux.setCSV(csvRow);
//                this.lstStudents.add(this.aux);
                this.tableStudent.addRow(this.aux);
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(FrAluno.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. O arquivo não foi "
                    + "carregado com sucesso.");
        }
    }

    private void saveOnFile(String path) {
        try {
            FileWriter file = new FileWriter(path);
            try (PrintWriter writeOnFile = new PrintWriter(file)) {
                Student aux = new Student();
                String fullText = aux.getCsvHeader();
                for (Student s : this.tableStudent.getLstStudents()) {
                    fullText += s.getCsv();
                }
                writeOnFile.print(fullText);
            }
        } catch (IOException e) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "ERRO. Impossível salvar. \n"
                    + "Provavelmente o arquivo está corrompido. "
                    + "Se isso aconteceu, vá até 'src/csv/lst_students.csv'"
                    + " e apague a última linha.\n");
        }

    }

    /**
     * Altera visibilidade dos campos, de acordo com o parâmetro.
     *
     * @param flag : caso true, ativa, caso contrário, desativa.
     */
    private void enableFields(boolean flag) {
        this.edtEmail.setEnabled(flag);
        this.edtId.setEnabled(flag);
        this.edtName.setEnabled(flag);
        this.edtYear.setEnabled(flag);
        this.rdFemale.setEnabled(flag);
        this.rdMale.setEnabled(flag);
        if (!flag) {
            this.clearFields();
        }
    }

    /**
     * Limpa os campos de texto da interface.
     */
    private void clearFields() {
        this.edtEmail.setText(null);
        this.edtId.setText(null);
        this.edtName.setText(null);
        this.edtYear.setText(null);
        this.rdFemale.setSelected(false);
        this.rdMale.setSelected(false);
    }

    /**
     * Exibe a lista de alunos dentro da uma AbstractTableModel (tableStudent).
     */
    private void showTable() {
        for (Student s : this.tableStudent.getLstStudents()) {
            this.tableStudent.addRow(s);
        }
    }

    /**
     * Verifica se algum campo não foi preenchido pelo usuário.
     */
    private boolean isAnyFieldMissing() {
        return this.edtName.getText().isEmpty()
                || this.edtEmail.getText().isEmpty()
                || this.edtId.getText().isEmpty()
                || this.edtYear.getText().isEmpty()
                || (!this.rdFemale.isSelected() && !this.rdMale.isSelected());
    }

    /**
     * Verifica se determinado aluno já existe na lista.
     *
     * @param student : o aluno cuja existência está sendo verificada.
     * @return : true caso seja encontrada a mesma id na lista.
     */
    private boolean isExisting(Student student) {
        for (Student a : this.tableStudent.getLstStudents()) {
            if (a.getId().equals(student.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procura aluno (usando o número de matrícula) dentro da lista e retorna o
     * índice em que o aluno se encontra.
     *
     * @param term : o número de matrícula do aluno pesquisado.
     * @return : o índice da lista (já que é um ArrayList).
     */
    private int search(String term) {
        boolean flag = false;

        for (int i = 0; i < this.tableStudent.getRowCount(); i++) {
            if (term.equals(this.tableStudent.getLstStudents().get(i).getId())) {
                flag = true;
                return i;
            }
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, "Número de matrícula ["
                    + term + "] não encontrado.\nPor favor verifique os dados e "
                    + "tente novamente");
        }
        return -1;
    }

    /**
     * Copia o que está nos campos de texto da interface e seta pro objeto.
     *
     * @param aux : o objeto cujos atributos serão os dados contidos nos campos
     * da interface.
     */
    private void copyFromGuiFieldsToClass(Student aux) {
        aux.setEmail(this.edtEmail.getText());
        aux.setId(this.edtId.getText());
        aux.setName(this.edtName.getText());
        aux.setYear(this.edtYear.getText());
        if (this.rdFemale.isSelected()) {
            aux.setGender('F');
        } else if (this.rdMale.isSelected()) {
            aux.setGender('M');
        }
    }

    private void copyFromClasstoGuiFields(Student aux) {
        this.enableFields(true);
        this.edtEmail.setText(aux.getEmail());
        this.edtName.setText(aux.getName());
        this.edtId.setText(aux.getId());
        this.edtYear.setText(aux.getYear());

        if (aux.getGender() == 'F') {
            this.rdFemale.setSelected(true);
        } else {
            rdMale.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpGender = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        edtYear = new javax.swing.JTextField();
        edtName = new javax.swing.JTextField();
        edtEmail = new javax.swing.JTextField();
        edtId = new javax.swing.JTextField();
        rdFemale = new javax.swing.JRadioButton();
        rdMale = new javax.swing.JRadioButton();
        lblInfo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editando Aluno");
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        lblTitle.setText("CADASTRAR/EDITAR ALUNO");

        btnNew.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNew.png"))); // NOI18N
        btnNew.setText("Novo");
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNewMouseReleased(evt);
            }
        });
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgEdit.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgCancel.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgDelete.png"))); // NOI18N
        btnDelete.setText("Excluir");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgSave.png"))); // NOI18N
        btnSave.setText("Salvar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lblName.setText("Nome:");

        lblGender.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lblGender.setText("Sexo:");

        lblId.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lblId.setText("Matrícula:");

        lblYear.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lblYear.setText("Ano de ingresso:");

        lblEmail.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lblEmail.setText("E-mail:");

        edtYear.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        edtYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtYearKeyReleased(evt);
            }
        });

        edtName.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        edtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNameActionPerformed(evt);
            }
        });
        edtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtNameKeyReleased(evt);
            }
        });

        edtEmail.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        edtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtEmailKeyReleased(evt);
            }
        });

        edtId.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        edtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtIdKeyReleased(evt);
            }
        });

        btnGrpGender.add(rdFemale);
        rdFemale.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        rdFemale.setText("Feminino");

        btnGrpGender.add(rdMale);
        rdMale.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        rdMale.setText("Masculino");

        lblInfo.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        lblInfo.setText("Exibindo informações sobre os alunos:");

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblStudents);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addGap(103, 103, 103)
                                .addComponent(edtEmail))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblGender)
                                        .addGap(115, 115, 115)
                                        .addComponent(rdFemale)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdMale))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnBack)
                                        .addGap(84, 84, 84)
                                        .addComponent(lblTitle))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblName)
                                        .addGap(104, 104, 104)
                                        .addComponent(edtName, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 76, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblId)
                                        .addGap(76, 76, 76))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblYear)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtId)
                                    .addComponent(edtYear))))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(lblInfo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnCancel)
                    .addComponent(btnDelete)
                    .addComponent(btnSave))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtName)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(rdFemale)
                    .addComponent(rdMale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtEmail)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblEmail)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtId)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblId)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(lblInfo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.enableFields(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.enableFields(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean allOk = false;

        if (this.isAnyFieldMissing()) {
            JOptionPane.showMessageDialog(null, "Por favor, preeencha todos os"
                    + "campos");

        } else {
            int confirmSave = JOptionPane.showConfirmDialog(null, "Deseja "
                    + "realmente salvar?");

            if (confirmSave == JOptionPane.YES_OPTION) {
                Student s = new Student();
                this.copyFromGuiFieldsToClass(s);

                if (this.isEdit) {
                    allOk = true;
                    this.tableStudent.getLstStudents().set(this.search(s.getId()), s);

                } else {
                    if (this.isExisting(s)) {
                        JOptionPane.showMessageDialog(null, "ERRO. O número de"
                                + " matrícula informado já existe. Verifique a "
                                + "ortografia e tente novamente");

                    } else {
                        allOk = true;
                        this.tableStudent.addRow(s);
                    }
                }
                if (allOk) {
                    this.saveOnFile("src/csv/lst_students.csv");
                    this.tableStudent.fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "Aluno cadastrado com"
                            + " sucesso!");
                    this.enableFields(false);
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseReleased
        this.edtName.requestFocus();
    }//GEN-LAST:event_btnNewMouseReleased

    private void edtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNameActionPerformed

    private void edtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNameKeyReleased
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            this.rdFemale.requestFocus();
        }
    }//GEN-LAST:event_edtNameKeyReleased

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void edtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtEmailKeyReleased
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            this.edtId.requestFocus();
        }
    }//GEN-LAST:event_edtEmailKeyReleased

    private void edtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIdKeyReleased
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            this.edtYear.requestFocus();
        }
    }//GEN-LAST:event_edtIdKeyReleased

    private void edtYearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtYearKeyReleased
        if (evt.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            this.btnSave.requestFocus();
        }
    }//GEN-LAST:event_edtYearKeyReleased

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String term = JOptionPane.showInputDialog("Insira aqui o número de "
                + "matrícula de quem deseja editar:");

        this.aux = this.tableStudent.getLstStudents().get(this.search(term));

        int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente"
                + " editar os dados de " + this.aux.getName() + "?");

        if (confirm == JOptionPane.YES_OPTION) {
            this.isEdit = true;
            this.copyFromClasstoGuiFields(this.aux);

        } else if (confirm == JOptionPane.NO_OPTION) {
            this.btnCancelActionPerformed(evt);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String toDelete = JOptionPane.showInputDialog("Insira aqui o "
                + "número de matrícula de quem deseja excluir:");

        this.aux = this.tableStudent.getLstStudents().get(this.search(toDelete));

        int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que "
                + "deseja excluir " + this.aux.getName() + "?");

        if (confirm == JOptionPane.YES_OPTION) {
            this.tableStudent.getLstStudents().remove(this.aux);
            this.saveOnFile("src/csv/lst_students.csv");
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
//            this.showTable();
            this.tableStudent.fireTableDataChanged();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGrpGender;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtId;
    private javax.swing.JTextField edtName;
    private javax.swing.JTextField edtYear;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblYear;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JTable tblStudents;
    // End of variables declaration//GEN-END:variables
}
