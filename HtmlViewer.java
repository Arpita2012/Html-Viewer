import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class HtmlViewer extends JFrame implements ActionListener {
    
    String fileName;
    JMenuBar mb1;
    JMenu mFile, mEdit, mTools;
    JMenuItem mNew, mOpen, mSave, mExit;
    JMenuItem mCut, mCopy, mPaste, mSelectAll;
    JMenuItem  mView;
    JEditorPane jed;
    JFileChooser jfc;

    public HtmlViewer() {
        setVisible(true);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(20, 20, 601, 601);

        mb1 = new JMenuBar();

        mFile = new JMenu("File");
        mEdit = new JMenu("Edit");
        mTools = new JMenu("Tools");

        mNew = new JMenuItem("New");
        mOpen = new JMenuItem("Open");
        mSave = new JMenuItem("Save");
        mExit = new JMenuItem("Exit");

        mCut = new JMenuItem("Cut");
        mCopy = new JMenuItem("Copy");
        mPaste = new JMenuItem("Paste");
        mSelectAll = new JMenuItem("Select All");

       mView = new JMenuItem("View in Browser");

        JSeparator ja1 = new JSeparator();
        JSeparator ja2 = new JSeparator();

        mFile.add(mNew);
        mFile.add(mOpen);
        mFile.add(mSave);
        mFile.add(ja1);
        mFile.add(mExit);

        mEdit.add(mCut);
        mEdit.add(mCopy);
        mEdit.add(mPaste);
        mEdit.add(ja2);
        mEdit.add(mSelectAll);

      
        mTools.add(mView);

        mb1.add(mFile);
        mb1.add(mEdit);
        mb1.add(mTools);

        setJMenuBar(mb1);

        jfc = new JFileChooser();
        jed = new JEditorPane();
        JScrollPane jsp = new JScrollPane(jed);

        getContentPane().add(jsp);

        mNew.addActionListener(this);
        mOpen.addActionListener(this);
        mSave.addActionListener(this);
        mExit.addActionListener(this);

        mCut.addActionListener(this);
        mCopy.addActionListener(this);
        mPaste.addActionListener(this);
        mSelectAll.addActionListener(this);

        
        mView.addActionListener(this);
        setSize(602, 602);
         
      
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mNew) {
            jed.setText("");
        }

        if (e.getSource() == mOpen) {
            jfc.showOpenDialog(this);

            try {
                File f = jfc.getSelectedFile();
                fileName = f.getAbsolutePath();
                setTitle(fileName);
                

                FileInputStream fin = new FileInputStream(f);
                byte data[] = new byte[fin.available()];
                fin.read(data);
                jed.setText(new String(data));
                fin.close();
            } catch (Exception ex) {
            }

        }
        if (e.getSource() == mSave) {
            jfc.showSaveDialog(this);
            try {
                File f = jfc.getSelectedFile();
                fileName = f.getAbsolutePath();
                setTitle(fileName);
               

                FileOutputStream fout = new FileOutputStream(f);
                byte data[] = jed.getText().getBytes();
                fout.write(data);
                fout.flush();
                fout.close();
            } catch (Exception ex) {
            }
        }
        if (e.getSource() == mExit) {
            System.exit(0);
        }
        if (e.getSource() == mCut) {
            jed.cut();
        }
        if (e.getSource() == mCopy) {
            jed.copy();
        }
        if (e.getSource() == mPaste) {
            jed.paste();
        }
        if (e.getSource() == mSelectAll) {
            jed.selectAll();
        }
        
        if (e.getSource() == mView) {
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd /c start " + fileName);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }

    public static void main(String args[]) {
        new HtmlViewer();
    }
}
