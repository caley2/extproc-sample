package jcf.sample.extprocdemo.config;

import java.io.File;

import jcf.extproc.ExternalProcessOperator;
import jcf.extproc.fileaccess.FileAccess;
import jcf.extproc.fileaccess.FileAccessImpl;
import jcf.extproc.spring.ExternalProcessOperatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@ImportResource("classpath:jcf/sample/extprocdemo/config/component.xml")
public class Component {

	private static final String baseDirectory = "/home/setq/workspace/tmp/jobs";

	@Bean
	public ExternalProcessOperator getOperator() throws Exception {
		ExternalProcessOperatorFactory factory = new ExternalProcessOperatorFactory();
		factory.setBaseDirectory(baseDirectory);
		factory.setCharset("MS949");
		factory.setTaskExecutor(new SimpleAsyncTaskExecutor());

		return factory.getObject();
	}

	@Bean
	public FileAccess getFileAccess(){
		return new FileAccessImpl(new File(baseDirectory));
	}
}
