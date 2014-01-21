package com.blusoft.blucargo.dao.ddl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public final class SchemaExport implements SchemaConstants {

	private static final Logger LOGGER = Logger.getLogger(SchemaExport.class);

	private SchemaExport() {

	}

	public static void main(String[] args) throws IOException {

		boolean drop = false;
		boolean create = false;
		String hsqlCreateOutputFilePath = null;
		String hsqlDropOutputFilePath = null;
		String mysqlCreateOutputFilePath = null;
		String mysqlDropOutputFilePath = null;
		String oracleCreateOutputFilePath = null;
		String oracleDropOutputFilePath = null;
		String delimiter = "";

		for (final String arg : args) {

			if (arg.startsWith(PREFIX)) {

				if (arg.equals(DROP)) {
					drop = true;
				} else if (arg.equals(CREATE)) {
					create = true;
				} else if (arg.startsWith(HSQL_CREATE_OUTPUT)) {
					hsqlCreateOutputFilePath = arg.substring(HSQL_CREATE_OUTPUT.length());
				} else if (arg.startsWith(HSQL_DROP_OUTPUT)) {
					hsqlDropOutputFilePath = arg.substring(HSQL_DROP_OUTPUT.length());
				} else if (arg.startsWith(ORACLE_CREATE_OUTPUT)) {
					oracleCreateOutputFilePath = arg.substring(ORACLE_CREATE_OUTPUT.length());
				} else if (arg.startsWith(ORACLE_DROP_OUTPUT)) {
					oracleDropOutputFilePath = arg.substring(ORACLE_DROP_OUTPUT.length());
				} else if (arg.startsWith(MYSQL_CREATE_OUTPUT)) {
					mysqlCreateOutputFilePath = arg.substring(MYSQL_CREATE_OUTPUT.length());
				} else if (arg.startsWith(MYSQL_DROP_OUTPUT)) {
					mysqlDropOutputFilePath = arg.substring(MYSQL_DROP_OUTPUT.length());
				} else if (arg.startsWith(DELIMITER)) {
					delimiter = arg.substring(DELIMITER.length());
				}
			}
		}

		final Formatter formatter = FormatStyle.DDL.getFormatter();

		final ApplicationContext hsqlExportContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/blucargo-hsql-ddl-export-context.xml");
		prepareExportedSchema(drop, create, hsqlCreateOutputFilePath, hsqlDropOutputFilePath, delimiter, formatter, hsqlExportContext);

		final ApplicationContext oracleExportContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/blucargo-oracle-ddl-export-context.xml");
		prepareExportedSchema(drop, create, oracleCreateOutputFilePath, oracleDropOutputFilePath, delimiter, formatter, oracleExportContext);

		final ApplicationContext mysqlExportContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/blucargo-mysql-ddl-export-context.xml");
		prepareExportedSchema(drop, create, mysqlCreateOutputFilePath, mysqlDropOutputFilePath, delimiter, formatter, mysqlExportContext);

	}

	private static void prepareExportedSchema(boolean drop, boolean create, String createOutputFilePath, String dropOutputFilePath, String delimiter,
			final Formatter formatter, final ApplicationContext exportContext) throws IOException {

		final LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) exportContext.getBean("&sessionFactory");
		final org.hibernate.cfg.Configuration configuration = sessionFactory.getConfiguration();
		final String[] createScript = configuration.generateSchemaCreationScript(Dialect.getDialect(configuration.getProperties()));
		final String[] dropScript = configuration.generateDropSchemaScript(Dialect.getDialect(configuration.getProperties()));

		if (create && null != createOutputFilePath) {
			exportToFile(createOutputFilePath, delimiter, formatter, createScript);
		}
		if (drop && null != dropOutputFilePath) {
			exportToFile(dropOutputFilePath, delimiter, formatter, dropScript);
		}
	}

	private static void exportToFile(String outputFilePath, String delimiter, Formatter formatter, String[] script) throws IOException {

		PrintWriter writer = null;

		File file = new File(outputFilePath);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}

		try {

			writer = new PrintWriter(outputFilePath);
			for (final String string : script) {
				writer.print(formatter.format(string) + "\n" + delimiter + "\n");
			}

		} catch (final FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
