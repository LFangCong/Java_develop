import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;


public class testsystem extends JFrame{//����
	private JPanel panel01 =new JPanel();
	private JLabel problem =new JLabel();
	private ButtonGroup group=new ButtonGroup();
	private JRadioButton buttona=new JRadioButton();
	private JRadioButton buttonb=new JRadioButton();
	private JRadioButton buttonc=new JRadioButton();
	private JRadioButton buttond=new JRadioButton();
	private String str_problem[]=new String[]{
			"1����ʵϰ���ڼ�ʻ�������ģ�Ӧ���ڳ����ճ�������������ֱ�־��",
			"2����������Ļ�������ʻ֤����Ч��Ϊ�����ꣿ",
			"3��ҹ���·�����԰�ȫ�г�����ҪӰ����ʲô��",
			"4��·����˫��ʵ���Ǻκ��壿",
			"5����ʻ����������·��ת�䴦��Ӧ��������"
	};
	private String answer_a[]=new String[]{
			"A��ע�����ֱ�־",
			"A��3��",
			"A���ܼ��ȵ͡������ڹ۲��·��ͨ���",
			"A���ɿ�Խ���򳵵��ֽ���",
			"A������򳵵���ʻ"			
	};
	private String answer_b[]=new String[]{
		"B��ע����ñ�־",
		"B��5��",
		"B��·�渴�Ӷ��",
		"B����ֹ��Խ�����е��ֽ���",
		"B�������ƶ�����ͨ��"	
	};
	private String answer_c[]=new String[]{
			"C��ͳһʽ����ʵϰ��־",
			"C��6��",
			"C����ʻ�������½�",
			"C��˫��ɿ�Խͬ�򳵵��ֽ���",
			"C������������ʻ"
			
	};
	private String answer_d[]=new String[]{
			"D��ע�⳵���ע",
			"D��12��",
			"D����ʻ���ײ����嶯���þ�",
			"D��������ʻ�����ֽ���",
			"D����ּ��ٲ����Ҳ���ʻ"
	};
	private int num=0;//��ǰ���
	
	private JPanel panel02=new JPanel();
	private JButton btn_index[]=new JButton[5];
	
	private JPanel panel03=new JPanel();
	private JButton btn_last=new JButton("��һ��");
	private JButton btn_next=new JButton("��һ��");
	private JButton btn_finish=new JButton("����");
	private JLabel label01=new JLabel("ʣ��ʱ��");
	private JLabel label_time=new JLabel("5:00");
	
	private JPanel panel04 = new JPanel();
	private JLabel label_score = new JLabel();
	private JLabel image = new JLabel(new ImageIcon());
	
	private JPanel imagePanel;
	private ImageIcon bg = new ImageIcon("image//bg.jpg");
	private JLabel label = new JLabel(bg);
	
	private MyListener ml = new MyListener();
	
	private int right[] = new int[]{3,3,1,2,4};//��ȷ�� 
	private int my_answer[]=new int[]{0,0,0,0,0};//�û���
	private int score = 0;
	
	private Timer timer;
	private int minute=4,second=60;

	  
	  testsystem(){ // ����
		  problem.setFont(new Font("����",Font.BOLD,18));
		  buttona.setFont(new Font("����",Font.BOLD,18));
		  buttonb.setFont(new Font("����",Font.BOLD,18));
		  buttonc.setFont(new Font("����",Font.BOLD,18));
		  buttond.setFont(new Font("����",Font.BOLD,18));
		  
		  problem.setText(str_problem[num]);
		  buttona.setText(answer_a[num]);
		  buttonb.setText(answer_b[num]);
		  buttonc.setText(answer_c[num]);
		  buttond.setText(answer_d[num]);
		  
		  group.add(buttona);//ʵ�ֵ�ѡ����                                             
		  group.add(buttonb);
		  group.add(buttonc);
		  group.add(buttond);
		  panel01.setLayout(new GridLayout(5, 1, 0, 30));
		                          //���񲼾֣��У��У�ˮƽ��࣬��ֱ���
		  panel01.add(problem);
		  panel01.add(buttona);
		  panel01.add(buttonb);
		  panel01.add(buttonc);
		  panel01.add(buttond);
          this.setLayout(new BorderLayout());
          this.add(panel01,BorderLayout.NORTH);
          
          for(int i=0;i<5;i++){
        	  btn_index[i]=new JButton(""+(i+1));
        	  btn_index[i].setBackground(Color.red);
        	  panel02.add(btn_index[i]);
        	  
        	  btn_index[i].addActionListener(ml);
        	  
          }
          this.add(panel02,BorderLayout.CENTER);
          
          btn_last.setEnabled(false);
          label_time.setFont(new Font("����",Font.BOLD,30));
          label_time.setForeground(Color.RED);
          panel03.add(btn_last);
          panel03.add(btn_next);
          panel03.add(btn_finish);
          panel03.add(label01);
          panel03.add(label_time);
          this.add(panel03,BorderLayout.SOUTH);
          
          label_score.setFont(new Font("����",Font.PLAIN,30));
          label_score.setForeground(Color.BLUE);
          panel04.add(label_score);
          panel04.add(image);
          this.add(panel04,BorderLayout.EAST);
          
          buttona.setOpaque(false);
          buttonb.setOpaque(false);
          buttonc.setOpaque(false);
          buttond.setOpaque(false);
          
          panel01.setOpaque(false);
          panel02.setOpaque(false);
          panel03.setOpaque(false);
          panel04.setOpaque(false);
          
          label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
          imagePanel=(JPanel)this.getContentPane();
          imagePanel.setOpaque(false);
          this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
          
          btn_last.addActionListener(ml);
          btn_next.addActionListener(ml);
          btn_finish.addActionListener(ml);
          buttona.addActionListener(ml);
          buttonb.addActionListener(ml);
          buttonc.addActionListener(ml);
          buttond.addActionListener(ml);
          
          
          timer = new Timer(1000,new TimerListener());
          timer.start();

	  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   testsystem t = new testsystem();
		   t.setTitle("���տ���");
		   t.setSize(660,430);
		   t.setVisible(true);
		   t.setResizable(false);//���ô����Ƿ���Ե���
		   t.setLocationRelativeTo(null);//null��ʾû�в�������е���

	}
	
	public class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for(int i=0;i<5;i++){
				if(e.getSource()==btn_index[i]){//��ť1��5
					num = i;//���µ�ǰ���
					showItem(num);//�л���Ŀ��ѡ��
					showMychoice(num);//��ʾ��ѡѡ��
					showButton(num);//�ж���ʾ��Щ��ť
	     		}
			}
			
			
			if(e.getSource()==btn_last){
				if(num>0){
					num--;
				}
				showItem(num);
				showMychoice(num);
				showButton(num);
			}
			
			if(e.getSource()==btn_next){
				if(num<str_problem.length-1){
					num++;
				}
				showItem(num);
				showMychoice(num);
				showButton(num);
     		}
			
			if(e.getSource()==buttona){
				my_answer[num]=1;
				btn_index[num].setBackground(Color.GREEN);
				
			}
			
			if(e.getSource()==buttonb){
				my_answer[num]=2;
				btn_index[num].setBackground(Color.GREEN);
			}
			
			if(e.getSource()==buttonc){
				my_answer[num]=3;
				btn_index[num].setBackground(Color.GREEN);
			}
			
			if(e.getSource()==buttond){
				my_answer[num]=4;
				btn_index[num].setBackground(Color.GREEN);
			}
			
			if(e.getSource()==btn_finish){//����
				timer.stop();//ֹͣ
				TextFinish();
				
			}
			
		}
		
	}
	
	public void showItem(int i){
		problem.setText(str_problem[i]);
		buttona.setText(answer_a[i]);
		buttonb.setText(answer_b[i]);
		buttonc.setText(answer_c[i]);
		buttond.setText(answer_d[i]);
		group.clearSelection();//���

	}
	
	public void showMychoice(int i){//��ʾ��ѡѡ��,iΪ��ǰ���
		switch(my_answer[i]){
		case 1:
			buttona.setSelected(true);
			break;
		case 2:
			buttonb.setSelected(true);
			break;
		case 3:
			buttonc.setSelected(true);
			break;	
		case 4:
			buttond.setSelected(true);
			break;	
		}
	}
	
	public void showButton (int i){//�ж���ʾ��Щ��ť��iΪ��ǰ���
		if(i==0){//��һ��
			btn_last.setEnabled(false);
			btn_next.setEnabled(true);
			
		}else if(i==str_problem.length-1){//���һ��
			btn_last.setEnabled(true);
			btn_next.setEnabled(false);
		}else{//������
			btn_last.setEnabled(true);
			btn_next.setEnabled(true);
		}		
	}
	
	public void TextFinish(){
		btn_last.setEnabled(false);//���ܵ��
		btn_next.setEnabled(false);
		btn_finish.setEnabled(false);
		buttona.setEnabled(false);
		buttonb.setEnabled(false);
		buttonc.setEnabled(false);
		buttond.setEnabled(false);
		for(int i=0;i<4;i++){
			btn_index[i].setEnabled(false);
			if(my_answer[i]==right[i]){
				score=score+20;
			}
		}
		label_score.setText("�ܳɼ���"+score);
		if(score==100){
			image.setIcon(new ImageIcon("image//lauge.jpg"));			
		}else{
			image.setIcon(new ImageIcon("image//cry.jpg"));
		}
		
	}
	
	 public class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			second--;
			if(second<0){
				minute--;
				second=59;
			}
			label_time.setText(minute+":"+second);
			if(minute==0 && second==0){
				timer.stop();
				label_time.setText("���Խ�����");
				TextFinish();
			}
		}
		
	}

}
