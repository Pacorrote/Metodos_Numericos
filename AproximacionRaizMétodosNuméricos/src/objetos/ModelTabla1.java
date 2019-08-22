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
		return 6;
	}
	

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch(column) {
		case 0:
			return "Iteraciones";
		case 1:
			return "Xi";
		case 2:
			return "f(Xi)";
		case 3:
			return "Diff(Xi)";
		case 4:
			return "Xi+1";
		case 5:
			return "Error";
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:

			return rowIndex+1;
		case 1:
			
			return model.get(rowIndex).getXi();
		case 2:

			return model.get(rowIndex).getFxi();
		case 3:

			return model.get(rowIndex).getDiffxi();
		case 4:

			return model.get(rowIndex).getXj();
		case 5:

			return model.get(rowIndex).getErrorAprox().toString()+"%";

		default:
			return "";
		}
	}

}
