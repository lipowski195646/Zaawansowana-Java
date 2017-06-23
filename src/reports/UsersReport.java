package reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.util.ArrayList;
import java.util.Date;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import superclasses.Entity;
import datamanagers.RefUserDM;
import entities.RefUser;

public class UsersReport {

	public UsersReport() {
		build();
	}

	private void build() {
		StyleBuilder leftStyle = stl.style(ReportTemplate.columnStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		StyleBuilder centerStyle = stl.style(ReportTemplate.columnStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		StyleBuilder rightStyle = stl.style(ReportTemplate.columnStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
		
		TextFieldBuilder<String> label = cmp.text("Users");
		label.setStyle(ReportTemplate.bold18CenteredStyle);
		
		TextColumnBuilder<String> userColumn = col.column("User", "user", type.stringType());
		userColumn.setStyle(leftStyle);
		
		TextColumnBuilder<String> loginColumn = col.column("Login", "login", type.stringType());
		loginColumn.setStyle(centerStyle);
		loginColumn.setFixedWidth(70);
		
		TextColumnBuilder<String> positionColumn = col.column("Position", "position", type.stringType());
		positionColumn.setStyle(leftStyle);
		
		TextColumnBuilder<Float> salaryColumn = col.column("Salary", "salary", type.floatType());
		salaryColumn.setFixedWidth(50);
		salaryColumn.setStyle(rightStyle);
		
		TextColumnBuilder<Float> premiaColumn = col.column("Premia, %", "premia", type.floatType());
		premiaColumn.setFixedWidth(50);
		premiaColumn.setStyle(centerStyle);
		
		TextColumnBuilder<Date> dateInColumn = col.column("Date in", "dateIn", type.dateType());
		dateInColumn.setFixedWidth(70);
		dateInColumn.setStyle(centerStyle);
		
		TextColumnBuilder<Date> dateOutColumn = col.column("Date out", "dateOut", type.dateType());
		dateOutColumn.setFixedWidth(70);
		dateOutColumn.setStyle(centerStyle);
		
		try {
		     report()
		     	.title(label)
                .setColumnTitleStyle(ReportTemplate.columnTitleStyle)
		     	.setColumnStyle(centerStyle)
		     	.highlightDetailEvenRows()
		     	.columns(
		     		userColumn,
		     		loginColumn,
		     		positionColumn,
		     		salaryColumn,
		     		premiaColumn,
		     		dateInColumn,
		     		dateOutColumn
		     	)
		     	.pageFooter(cmp.pageXofY())
		     	.setDataSource(createDataSource())
		     	.show(false);
		      } catch (DRException e) {
		         e.printStackTrace();
		      }
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("user", "login", "position", "salary", "premia", "dateIn", "dateOut");

		ArrayList<Entity> items = (new RefUserDM()).getEntityList();
		for (Entity entity : items) {
			RefUser user = (RefUser) entity;
			
			dataSource.add(
				user.getName() + " " + user.getSurname(),
				user.getLogin(),
				user.getState(),
				user.getSalary(),
				user.getPremia(),
				user.getDateIn(),
				user.getDateOut()
			);
		}
		
		return dataSource;
	}
		
}