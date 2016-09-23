package com.github.brunothg.swing.mvp.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.github.brunothg.swing.mvp.annotation.SwingView;
import com.github.brunothg.swing.mvp.annotation.UiScope;
import com.github.brunothg.swing.mvp.components.View;
import com.github.brunothg.swing.mvp.event.EventBus;
import com.github.brunothg.swing.mvp.event.PayloadEvent;

@UiScope
@SwingView
public class TestView extends JDialog implements View
{
	private static final long serialVersionUID = 1L;

	private EventBus eventBus;

	private JTextArea taTxt = new JTextArea();
	private JButton btnClick = new JButton("Click me");

	@PostConstruct
	protected void ini()
	{
		setTitle("Test MVP View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);

		setLayout(new BorderLayout());
		JScrollPane spTxt = new JScrollPane();
		spTxt.setViewportView(taTxt);
		add(spTxt, BorderLayout.CENTER);

		btnClick.addActionListener(getClickListener());
		add(btnClick, BorderLayout.SOUTH);

		pack();
	}

	private ActionListener getClickListener()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				eventBus.fire(new PayloadEvent<String>(TestView.this, "Aktuell ist es " + new Date().toString()));
				pack();
			}
		};
	}

	public void setInfoText(String txt)
	{
		taTxt.setText(txt);
	}

	@Override
	public void setEventBus(EventBus eventBus)
	{
		this.eventBus = eventBus;
	}

}
