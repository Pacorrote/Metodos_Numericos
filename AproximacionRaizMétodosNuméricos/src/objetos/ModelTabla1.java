package objetos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelTabla1 extends AbstractTableModel{
	
	private ArrayList<ColumnasNewtonRaphson> model;
	
	public ModelTabla1(ArrayList<ColumnasNewtonRaphson> model) {
		this.model = model;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return model.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			
			return model.get(rowIndex).getXi();
		case 1:

			return model.get(rowIndex).getFxi();
		case 2:

			return model.get(rowIndex).getDiffxi();
		case 3:

			return model.get(rowIndex).getXj();
		case 4:

			return model.get(rowIndex).getErrorAprox().toString()+"%";

		default:
			return "";
		}
	}

}
