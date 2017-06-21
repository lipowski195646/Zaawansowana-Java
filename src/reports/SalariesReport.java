package reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import superclasses.Entity;
import datamanagers.DocSalaryDM;
import datamanagers.DocSalaryItemDM;
import entities.DocSalaryItem;

public class SalariesReport {
	private StyleBuilder centerStyle, rightStyle;

	public SalariesReport() {
		build();
	}

	private void build() {
		centerStyle = stl.style(ReportTemplate.columnStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		rightStyle = stl.style(ReportTemplate.columnStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
		
		TextFieldBuilder<String> label = cmp.text("Salaries");
		label.setStyle(ReportTemplate.bold18CenteredStyle);
		
		TextColumnBuilder<Integer> idColumn = col.column("ID", "id", type.integerType());
		idColumn.setFixedWidth(30);
		
		TextColumnBuilder<Date> dateColumn = col.column("Date", "date", type.dateType());
		dateColumn.setFixedWidth(70);
		
		TextColumnBuilder<String> numberColumn = col.column("#", "number", type.stringType());
		numberColumn.setFixedWidth(70);
		
		TextColumnBuilder<String> periodColumn = col.column("Period", "period", type.stringType());
		
		TextColumnBuilder<Integer> daysColumn = col.column("Days", "daysWork", type.integerType());
		daysColumn.setFixedWidth(50);
		
		TextColumnBuilder<Float> summaColumn = col.column("Summa", "summa", type.floatType());
		summaColumn.setFixedWidth(70);
		summaColumn.setStyle(rightStyle);
		
		SubreportBuilder subreport = cmp.subreport(new SubreportExpression()).setDataSource(new SubreportDataSourceExpression());
		subreport.setFixedWidth(500);
		
		try {
		     report()
		     	.title(label)
                .setColumnTitleStyle(ReportTemplate.columnTitleStyle)
		     	.setColumnStyle(centerStyle)
		     	.columns(
		     		idColumn,
		     		dateColumn,
		     		numberColumn,
		     		periodColumn,
		     		daysColumn,
		     		summaColumn
		     	)
		     	.setDetailFooterStyle(stl.style().setLeftPadding(40))
		     	.detailFooter(subreport, cmp.verticalGap(20))
		     	.pageFooter(cmp.pageXofY())
		     	.setDataSource(new DocSalaryDM().getEntityList())
		     	.show(false);
		      } catch (DRException e) {
		         e.printStackTrace();
		      }
		   }
	
	private class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {
		private static final long serialVersionUID = 1L;

		@Override
		public JasperReportBuilder evaluate(ReportParameters reportParameters) {
			StyleBuilder itemTitleStyle = stl.style(ReportTemplate.columnTitleStyle).italic().setBackgroundColor(Color.decode("#eeeeee"));
			
			StyleBuilder itemStyle = stl.style(ReportTemplate.columnStyle).italic().setFontSize(10);
			StyleBuilder itemStyleLeft = stl.style(itemStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
			StyleBuilder itemStyleCenter = stl.style(itemStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
			StyleBuilder itemStyleRight = stl.style(itemStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
			
			JasperReportBuilder report = report();
			
			TextColumnBuilder<String> userColumn = col.column("User", "userName", type.stringType());
			userColumn.setStyle(itemStyleLeft);
			
			TextColumnBuilder<Integer> daysColumn = col.column("Days", "workDays", type.integerType());
			daysColumn.setFixedWidth(50);
			daysColumn.setStyle(itemStyleCenter);
			
			TextColumnBuilder<Float> summaColumn = col.column("Summa", "summa", type.floatType());
			summaColumn.setFixedWidth(70);
			summaColumn.setStyle(itemStyleRight);
			
			TextFieldBuilder<String> label = cmp.text("Details:");
			label.setStyle(ReportTemplate.bold12CenteredStyle.underline().italic());
			
			report
				.setColumnTitleStyle(itemTitleStyle)
	     		.setColumnStyle(centerStyle)
	     		.title(label)
	     		.columns(
	     			userColumn,
	     			daysColumn,
	     			summaColumn
			     );

			return report;
		}
	}
	
	private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
		private static final long serialVersionUID = 1L;

		@Override
		public JRDataSource evaluate(ReportParameters reportParameters) {
			int salaryId = reportParameters.getFieldValue("id");
			DRDataSource dataSource = new DRDataSource("userName", "workDays", "summa");
			
			ArrayList<Entity> items = (new DocSalaryItemDM(salaryId)).getEntityList();
			for (Entity entity : items) {
				DocSalaryItem si = (DocSalaryItem) entity;
				dataSource.add(si.getUserName(), si.getWorkDays(), si.getSumma());
			}
			
			return dataSource;
		}
	}

}