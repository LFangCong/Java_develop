import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StoryBook {
	JFrame jf = new JFrame();
    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    JMenuBar mainMenu = new JMenuBar();
    JTextArea jt = new JTextArea(6,40);
    JLabel jl = new JLabel();
    JButton jb = new JButton("��һҳ");
    JButton jb1 = new JButton("��һҳ");
    JMenu fontsize = new JMenu("�ֺ�");
    JMenuItem twelf = new JMenuItem("12");
    JMenuItem fiveteen = new JMenuItem("15");
    JMenuItem eighteen = new JMenuItem("18");
    JMenuItem twenty = new JMenuItem("20");
    JMenu font = new JMenu("����");
    JMenuItem song = new JMenuItem("����");
    JMenuItem hei = new JMenuItem("����");
    JMenuItem kai = new JMenuItem("����");
    JMenu fontstyle = new JMenu("����");
    JMenuItem chang = new JMenuItem("����");
    JMenuItem jia = new JMenuItem("�Ӵ�");
    JMenuItem qing = new JMenuItem("��б");
    JMenu color = new JMenu("��ɫ");
    JMenuItem red = new JMenuItem("��ɫ");
    JMenuItem green = new JMenuItem("��ɫ");
    JMenuItem blue = new JMenuItem("��ɫ");
    JMenu intall = new JMenu("����");
    JMenuItem swap = new JMenuItem("����ֽ");
    Box horizontal = Box.createHorizontalBox();
    public void init() {
    	mainMenu.add(fontsize);
    	mainMenu.add(font);
    	mainMenu.add(fontstyle);
    	mainMenu.add(color);
    	mainMenu.add(intall);
    	
    	fontsize.add(twelf);
    	fontsize.add(fiveteen);
    	fontsize.add(eighteen);
    	fontsize.add(twenty);
    	
    	font.add(song);
    	font.add(hei);
    	font.add(kai);
    	
    	fontstyle.add(chang);
    	fontstyle.add(jia);
    	fontstyle.add(qing);
    	
    	color.add(red);
    	color.add(green);
    	color.add(blue);
    	
    	
    	intall.add(swap);
    	
    	jf.setLayout(new FlowLayout());
    	jp.add(jb);
    	jp.add(jb1);
    	
    	jf.add(jp);
    	jf.add(jp1);
    	jf.setJMenuBar(mainMenu);
    	jf.add(jt);
    	jf.pack();
    	jf.setVisible(true);
    }
    public static void main(String[] args) {
             new StoryBook().init();
    }
}
