package day0128_work;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseJFrameEvt extends WindowAdapter implements ActionListener {

	UseJFrame uj;
	
	public UseJFrameEvt(UseJFrame uj) {
	this.uj = uj;	
	}//UseJFrameEvt

	@Override
	public void windowClosing(WindowEvent e) {
		uj.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == uj.getJmiClose()) {
			uj.dispose();
		}//end if
		if(e.getSource()==uj.getJmiNew()) {
			uj.getJta().setText(null);
		}//end if
		if(e.getSource() == uj.getJmiFont()) {
			new UseJDialog(uj);
		}//end if
	}//actionPerformed
}//class
