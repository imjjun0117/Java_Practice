package day0128_work;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class UseJDialogEvt extends JDialog implements ActionListener,ListSelectionListener {
	private UseJDialog ujd;
	private UseJFrame uj;
	
	private Font font;
	
	public UseJDialogEvt(UseJDialog ujd, UseJFrame uj) {
	this.ujd = ujd;
	this.uj = uj;
	}//UseJDialogEvt
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ujd.getJtfFont()) {
			ujd.getJltFont().setSelectedValue(ujd.getJtfFont().getText(), true); 
		}//end if
		if(e.getSource() == ujd.getJtfStyle()) {
			ujd.getJltStyle().setSelectedValue(ujd.getJtfStyle().getText(), true); 
		}//end if
		if(e.getSource() == ujd.getJtfSize()) {
			ujd.getJltSize().setSelectedValue(ujd.getJtfSize().getText(), true); 
		}//end if
		if(e.getSource() == ujd.getJbtOk()) {
			uj.getJta().setFont(font);//UseFrame 주소를 받는다
			ujd.dispose();
		}//end if
		if(e.getSource() == ujd.getJbtCancel()) {
			ujd.dispose();
		}//end if
	}//actionPerformed
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
			
		String fontType = ujd.getJltFont().getSelectedValue();
		String fontSize = ujd.getJltSize().getSelectedValue();
		String fontStyle = ujd.getJltStyle().getSelectedValue();
		int fontStyleIndex = ujd.getJltStyle().getSelectedIndex();
		
		ujd.getJtfFont().setText(fontType);
		ujd.getJtfStyle().setText(fontStyle);
		ujd.getJtfSize().setText(fontSize);
		
		
		font = new Font(fontType,fontStyleIndex,Integer.parseInt(fontSize));
		ujd.getJlPreview().setFont(font);
	}//valueChanged

}//class

