package org.first;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;

public class FirstDesign extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Text pmsText;
	private Table table;
	private Composite container;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public FirstDesign(Shell parentShell) {
		super(parentShell);
		//setErrorMessage("Cannot Open");
		setShellStyle(SWT.BORDER | SWT.RESIZE | SWT.TITLE | SWT.SYSTEM_MODAL);
		setTitle("Open PMS");
		
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		//Composite area = (Composite) super.createDialogArea(parent);
		container = new Composite(parent, SWT.NONE);
		container.setLayout(null);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label lblEnterPmsNo = new Label(container, SWT.NONE);
		lblEnterPmsNo.setToolTipText("Enter PMS No.");
		lblEnterPmsNo.setBounds(10, 10, 55, 15);
		lblEnterPmsNo.setText("Enter PMS No.");
		
		pmsText = new Text(container, SWT.BORDER);
		pmsText.setBounds(85, 4, 142, 21);
		
		Button btnSearch = new Button(container, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JOptionPane.showMessageDialog(null, "Search Clicked");
				for (int i = 0 ; i<= 5 ; i++){
		            TableItem item = new TableItem(table, SWT.NONE);
		            item.setText (0, pmsText.getText() +i);
		            item.setText (1, "LastName " +i);
		            item.setText (2, String.valueOf(i));
		            item.setText(3,"Hello");
		            item.setText(4,"jjjjjjj");
		        }
			}
		});
		btnSearch.setBounds(268, 2, 75, 25);
		btnSearch.setText("Search");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLinesVisible(true);
		table.setBounds(10, 43, 743, 236);
		table.setHeaderVisible(true);
		
		
        String[] titles = { "Id", "Message", "Author","Authored Date", "Committer", "Committed Date" };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }
        
      /* for (int i = 0 ; i<= 50 ; i++){
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText (0, "Person " +i);
            item.setText (1, "LastName " +i);
            item.setText (2, String.valueOf(i));
        }*/
        
        for (int i=0; i<titles.length; i++) {
            table.getColumn (i).pack ();
        }    

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		m_bindingContext = initDataBindings();
	}
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				Shell shell=new Shell();
				shell.setMinimumSize(599, 400);
				new FirstDesign(shell).open();
			}
		});
		/*Display display = Display.getDefault();
        Shell shell = new Shell(display);
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();*/
		
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeSizeTableObserveWidget = WidgetProperties.size().observe(table);
		IObservableValue accessiblecontrolaccessibleGetShellObserveValue = PojoProperties.value("accessible.control.accessible").observe(getShell());
		bindingContext.bindValue(observeSizeTableObserveWidget, accessiblecontrolaccessibleGetShellObserveValue, null, null);
		
		return bindingContext;
	}
}
