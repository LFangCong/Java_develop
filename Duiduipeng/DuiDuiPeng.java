import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;


public class DuiDuiPeng extends JFrame {

	private JPanel panel01 = new JPanel();
	private JButton btn_start = new JButton("��ʼ��Ϸ");
	private JLabel label01 = new JLabel("����");
	private JTextField text_score = new JTextField(10); 
	private JLabel label02 = new JLabel("ʱ��");
	private JProgressBar progress = new JProgressBar();
	private JButton btn_exit = new JButton("�˳�");
	
	private JPanel panel02 = new JPanel();
	private JButton button[][] = new JButton[8][8];
	private int animal[][] = new int[8][8];//0è 1ţ 2�� 3���� 4���� 5���� 6��è
    private ImageIcon icon[] = new ImageIcon[]{
    	new ImageIcon("image//cat.png"),
    	new ImageIcon("image//cattle.png"),
    	new ImageIcon("image//chicken.png"),
    	new ImageIcon("image//fox.png"),
    	new ImageIcon("image//frog.png"),
    	new ImageIcon("image//monkey.png"),
    	new ImageIcon("image//panda.png"),
    	
    };//7�ֶ����ͼƬ
    
    private MyListener my = new MyListener();
    private Timer timer;
    private int score=0;//�ܷ�
    private int jindu=0;
    
    private int x1,y1;//��һ�ε����ť������λ��
    private int x2,y2;//�ڶ��ε����ť������λ��
    private final int EMPTY=-1;//Ϊ�յı��
    private boolean isDoubleClicked=false;//�Ƿ�������
    
	/**
	 * @param args
	 */
	DuiDuiPeng(){
		text_score.setText("0");
		text_score.setEditable(false);
		progress.setMinimum(0);
		progress.setMaximum(100);
		progress.setStringPainted(true);
		
		panel01.add(btn_start);
		panel01.add(label01);
		panel01.add(text_score);
		panel01.add(label02);
		panel01.add(progress);
		panel01.add(btn_exit);
		this.setLayout(new BorderLayout());
		this.add(panel01,BorderLayout.NORTH);
		
		panel02.setLayout(new GridLayout(8,8,2,2));
		for(int i=0;i<8;i++){//��
			for(int j=0;j<8;j++){//��
				int temp = (int)(Math.random()*7);//0-6
				button[i][j] = new JButton(icon[temp]);
				if((i+j)%2==0){
					button[i][j].setBackground(new Color(255,222,173));
				}else{
					button[i][j].setBackground(new Color(255,246,143));
				}
			    animal[i][j]=temp;
				panel02.add(button[i][j]);
				button[i][j].setEnabled(false);
				
				button[i][j].addActionListener(my);
			}
		}
		this.add(panel02,BorderLayout.CENTER);
		
		panel01.setOpaque(false);
		panel02.setOpaque(false);
		this.getContentPane().setBackground(Color.orange);
		
		btn_start.addActionListener(my);
		btn_exit.addActionListener(my);
		
		timer=new Timer(1000,new TimerListener());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        DuiDuiPeng  d = new DuiDuiPeng();
        d.setTitle("�Զ�����Ϸ");
        d.setSize(700, 550);
        d.setVisible(true);
        d.setResizable(false);
		d.setLocationRelativeTo(null);
	}
    private  class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btn_start){//��ʼ��Ϸ
				btn_start.setEnabled(false);
				timer.start();
				jindu=0;
				progress.setValue(jindu);
				score=0;
				text_score.setText(""+score);
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						button[i][j].setEnabled(true);
					}
				}
				do{initAllAnimals();
				}while(searchAllAnimals(1));
		    }
			if(e.getSource()==btn_exit){//�˳���ť
				timer.stop();
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						button[i][j].setEnabled(false);
					}
				}
				dispose();
			}
			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(e.getSource()==button[i][j]){//���ﰴť
						System.out.println((i+1)+"��"+(j+1)+"��");
						swapAnimal(i,j);//����ͼƬ
						
					}
				}
			}
		}	
    }
    private class TimerListener implements ActionListener{
    	@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jindu++;
			progress.setValue(jindu);
			if(jindu==100){
				timer.stop();
				for(int i=0;i<8;i++){
					for(int j=0;j<8;j++){
						button[i][j].setEnabled(false);
					}
				}
				btn_start.setEnabled(true);
			}
		}
    }
    
    public void initAllAnimals(){//��ʼ���������
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			int temp=(int)(Math.random()*7);//0-6
    			button[i][j].setIcon(icon[temp]);
    			animal[i][j]=temp;
    		}
    	}
    }
    
    public boolean isThreeLinked(int y,int x){//y�� x��
    	//��ѯ�Ƿ���������������ͬ���ӵ�ͼ�Σ��Ƿ���true,���Ƿ���false
    	int temp;
    	//�ж�ˮƽ�������Ƿ���������������ͬ���ӵ�ͼ��
    	int linked=1;
    	if(x+1<8){
    		temp=x+1;
    		while(temp<8 && animal[y][x]==animal[y][temp]){
    			linked++;
    			temp++;
    		}
    	}
   	   if(x-1>=0){
   		   temp=x-1;
   		   while(temp>=0 && animal[y][x]==animal[y][temp]){
   			   linked++;
   			   temp--;
   		   }
   	   }
   	   if(linked>=3){
   		   return true;
   	   }
      
   	   //�жϴ�ֱ�������Ƿ���������������ͬ���ӵ�ͼ��
    	
   	   linked=1;
   	   if(y+1<8){
   		   temp=y+1;
   		   while(temp<8 && animal[y][x]==animal[temp][x]){
   			   linked++;
   			   temp++;
   		   }
   	   }
   	   if(y-1>=0){
   		   temp=y-1;
   		   while(temp>=0 && animal[y][x]==animal[temp][x]){
   			   linked++;
   			   temp--;
   		   }
   	   }
   	   if(linked>=3){
   		   return true;
   	   }
   	   return false;
    }
    
    public void removeThreeLined(int y,int x){//��������������ͬ����ͼ����ΪEMPTY
    	if(animal[y][x]==EMPTY){
    		return;
    	}
    	int num=0;
    	//�ж�ˮƽ�������Ƿ���������������ͬ���ӵ�ͼ��
    	int temp=0;
    	int linked=1;
    	if(x+1<8){
    		temp=x+1;
    		while(temp<8 && animal[y][x]==animal[y][temp]){
    			linked++;
    			temp++;
    		}
    	}
   	   if(x-1>=0){
   		   temp=x-1;
   		   while(temp>=0 && animal[y][x]==animal[y][temp]){
   			   linked++;
   			   temp--;
   		   }
   	   }
   	//��ˮƽ��������������������ͬ���ӵ�ͼ����ΪEMPTY
   	   if(linked>=3){
   		   num=num+linked;
   		   temp=x+1;
   		   while(temp<8 &&animal[y][x]==animal[y][temp]){
   			   animal[y][temp]=EMPTY;
   			   temp++;
   		   }
   		   temp=x-1;
   		   while(temp>=0 && animal[y][x]==animal[y][temp]){
   			   animal[y][temp]=EMPTY;
   			   temp--;
   		   }
   		   animal[y][x]=EMPTY;
   	   }
   	 //�жϴ�ֱ�������Ƿ���������������ͬ���ӵ�ͼ��
   	   temp=0;
   	   linked=1;
   	   if(y+1<8){
		   temp=y+1;
		   while(temp<8 && animal[y][x]==animal[temp][x]){
			   linked++;
			   temp++;
		   }
	   }
	   if(y-1>=0){
		   temp=y-1;
		   while(temp>=0 && animal[y][x]==animal[temp][x]){
			   linked++;
			   temp--;
		   }
	   }
	 //�Ѵ�ֱ��������������������ͬ���ӵ�ͼ����ΪEMPTY
	   if(linked>=3){
		   num=num+linked;
		   temp=y+1;
		   while(temp<8 &&animal[y][x]==animal[temp][x]){
			   animal[temp][x]=EMPTY;
			   temp++;
		   }
		   temp=y-1;
		   while(temp>=0 && animal[y][x]==animal[temp][x]){
			   animal[temp][x]=EMPTY;
			   temp--;
		   }
		   animal[y][x]=EMPTY;
	   }
	   score=score+num*10;
	   text_score.setText(""+score);
    }
    
    public boolean searchAllAnimals(int flag){
    	//1Ϊ��ѯ����  2Ϊȥ������
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			if(flag==1){
    				if(isThreeLinked(i,j)){//��ѯ�Ƿ�����������������
    					return true;
    				}
    			}else if(flag==2){
    				removeThreeLined(i,j);
    			}
    		}
    	}
    	return false;
    }
    
    public void downAnimal(){//���������ƶ�
    	int temp;
    	for(int y=7;y>=0;y--){
    		for(int x=0;x<8;x++){
    			if(animal[y][x]==EMPTY){
    				//�ҵ�һ���յ�λ��
    				for(int k=y-1;k>=0;k--){
    					if(animal[k][x]!=EMPTY){//�ҵ�����һ���ǿյ�
    						temp=animal[k][x];
    						animal[k][x]=animal[y][x];
    						animal[y][x]=temp;
    						break;
    					}
    				}
    				
    			}
    		}
    	}
    }
    public void showAllAnimals(){//������ʾ����ͼ��
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			button[i][j].setIcon(icon[animal[i][j]]);
    		}
    	}
    }
    public void updateAnimal(){//Ϊ�յ������������ͼ��
    	for(int i=0;i<8;i++){
    		for(int j=0;j<8;j++){
    			if(animal[i][j]==EMPTY){
    				animal[i][j]=(int)(Math.random()*7);
    			}
    		}
    	}
    	
    }
    public void swapAnimal(int y,int x){//yΪ��,xΪ��
    	if((x>=0 && x<8)&&(y>=0 && y<8)){
    		if(!isDoubleClicked){
    			//��һ�ε���
    			x1=x;
    			y1=y;
    			isDoubleClicked=true;
    			System.out.println("��һ�ε���������=("+(y1+1)+","+(x1+1)+")");
    		}else{
    			x2=x;
    			y2=y;
    			isDoubleClicked=false;
    			System.out.println("��һ�ε���������=("+(y2+1)+","+(x2+1)+")");
    			
    			if((Math.abs(x2-x1)==1 &&(y1==y2))
    					||(x1==x2)&&(Math.abs(y2-y1)==1)){//����������ť
    				int temp;
    				temp=animal[y2][x2];//����
    				animal[y2][x2]=animal[y1][x1];
    				animal[y1][x1]=temp;
    				
    				if(isThreeLinked(y1,x1)|| isThreeLinked(y2,x2)){//����������������
    					if(isThreeLinked(y1,x1)){
    						removeThreeLined(y1,x1);
    					}
    					if(isThreeLinked(y2,x2)){
    						removeThreeLined(y2,x2);
    					}
    					downAnimal();//���������ƶ�
    					updateAnimal();//Ϊ�յ������������ͼ��
    					showAllAnimals();//������ʾ����ͼ��
    					while(searchAllAnimals(1)){
    						searchAllAnimals(2);//ȥ������
    						downAnimal();
    						updateAnimal();
    						showAllAnimals();
    					}
    				}else{//û���������ڵ�
    					temp=animal[y2][x2];//����
        				animal[y2][x2]=animal[y1][x1];
        				animal[y1][x1]=temp;
    				}
    			}
    		}
    	}
    }
}
